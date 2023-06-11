<template>
  <div>
    <div></div>
    <div class="plan-info" v-if="selectPlan">
      <h1 class="plan-title">여행 계획 제목 : {{ selectPlan.title }}</h1>
      <h3 class="plan-duration">여행 기간: {{ selectPlan.startAt }} ~ {{ selectPlan.endAt }}</h3>
      <b-button v-b-modal.modal-scrollable
        ><b-icon icon="list-check" aria-hidden="true"></b-icon>여행 리스트 보기</b-button
      >
      <b-button variant="success" rounded class="btn" @click="publish">
        <b-icon icon="geo-alt"></b-icon> 발행[공개]
      </b-button>
    </div>
    <b-input-group size="lg" prepend="제목">
      <b-form-input v-model="title"></b-form-input>
    </b-input-group>
    <div class="example">
      <quill-editor
        class="editor"
        ref="myTextEditor"
        :disabled="false"
        v-model="content"
        :options="editorOption"
        @ready="onEditorReady($event)"
      />
    </div>

    <b-modal
      id="modal-scrollable"
      scrollable
      title="여행 내역 조회"
      no-close-on-backdrop
      no-close-on-esc
    >
      <div v-if="plans.length == 0">작성할 수 있는 여행 후기가 존재하지 않습니다.</div>
      <div
        v-for="(plan, index) in plans"
        :key="index"
        :class="{ 'wide-card': true, selected: selectPlan === plan }"
        @click="clickPlan(plan)"
      >
        <div class="card-header">
          <h5 class="card-title">{{ plan.title }}</h5>
          <p class="card-location">{{ plan.sidoName }}</p>
        </div>
        <div class="card-body">
          <p class="card-date">{{ date(plan) }}</p>
        </div>
      </div>
      <template #modal-footer>
        <p class="footer-font">후기 작성을 원하는 여행을 선택해주세요.</p>
      </template>
    </b-modal>
  </div>
</template>

<script>
import jwt from "@/api/jwt";
import hljs from "highlight.js";
import debounce from "lodash/debounce";
import { quillEditor } from "vue-quill-editor";
import "highlight.js/styles/tomorrow.css";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import axios from "axios";

export default {
  name: "quill-example-snow",
  title: "Theme: snow",
  components: {
    quillEditor,
  },
  data() {
    return {
      plans: [],
      selectPlan: {},
      input: "",
      title: "",
      content: "",
      showPlanModal: false, // Flag to control the visibility of planwritemodal

      editorOption: {
        placeholder: "place holder test",
        modules: {
          toolbar: [
            ["bold", "italic", "underline", "strike"], // <strong>, <em>, <u>, <s>
            ["blockquote", "code-block"], // <blockquote>, <pre class="ql-syntax" spellcheck="false">
            [{ header: 1 }, { header: 2 }], // <h1>, <h2>
            [{ list: "ordered" }, { list: "bullet" }],
            [{ script: "sub" }, { script: "super" }], // <sub>, <sup>
            [{ indent: "-1" }, { indent: "+1" }], // class제어
            [{ direction: "rtl" }], //class 제어
            [{ size: ["small", false, "large", "huge"] }], //class 제어 - html로 되도록 확인
            [{ header: [1, 2, 3, 4, 5, 6, false] }], // <h1>, <h2>, <h3>, <h4>, <h5>, <h6>, normal
            [{ font: [] }], // 글꼴 class로 제어
            [{ color: [] }, { background: [] }], //style="color: rgb(230, 0, 0);", style="background-color: rgb(230, 0, 0);"
            [{ align: [] }], // class
            // ["clean"],
            ["image"],
          ],
          syntax: {
            highlight: (text) => hljs.highlightAuto(text).value,
          },
        },
      },
    };
  },
  mounted() {
    this.login();

    jwt
      .get("/member/plan/list")
      .then((req) => {
        this.plans = req.data;
        console.log(this.plans);
        if (this.plans.length == 0) {
          alert("작성가능한 여행이 존재하지 않습니다.");
          this.$router.push({ path: "/main" });
        }
        this.$nextTick(() => {
          this.$bvModal.show("modal-scrollable");
        });
      })
      .catch((err) => {
        console.error(err);
      });
  },

  methods: {
    login() {
      if (sessionStorage.getItem("token") === null) {
        // 등호 변경
        alert("로그인 후 사용이 가능한 페이지입니다.");
        this.$router.push({ path: "/main" }); // 로그인 페이지로 리디렉션
      }
    },

    date(plan) {
      return plan.startAt + " ~ " + plan.endAt;
    },
    handleSelectPlan(plan) {
      console.log("부모 컴포넌트에서 받은 데이터:", plan);
      this.selectPlan = plan;
    },
    onEditorChange: debounce(function (value) {
      this.content = value.html;
    }, 466),
    onEditorBlur(editor) {
      console.log("editor blur!", editor);
    },
    onEditorFocus(editor) {
      console.log("editor focus!", editor);
    },
    onEditorReady(editor) {
      // 이미지 메소드 추가
      editor.getModule("toolbar").addHandler("image", this.imageHandler);
      console.log("editor ready!", editor);
    },
    imageHandler() {
      console.log("imageHandler start=============");

      // 1. 이미지를 저장할 input type=file DOM을 만든다.
      const input = document.createElement("input");
      // 속성 써주기
      input.setAttribute("type", "file");
      input.setAttribute("accept", "image/*");
      input.click(); // 에디터 이미지버튼을 클릭하면 이 input이 클릭된다.
      // input이 클릭되면 파일 선택창이 나타난다.

      // input에 변화가 생긴다면 = 이미지를 선택
      input.addEventListener("change", async () => {
        const file = input.files[0];
        console.log("file :", file);
        const formData = new FormData();
        formData.append("images", file);
        try {
          let imgUrl = "returnData";
          // 파일 업로드 api 호출
          axios
            .post("http://localhost:80/journal/fileUpload", formData, {
              headers: {
                "Content-Type": "multipart/form-data",
              },
            })
            .then((req) => {
              console.log("file", req.data);
              imgUrl = req.data;
              // 현재 에디터 커서 위치 조회
              const range = this.editor.getSelection();

              // 커서 위치에 이미지 삽입
              this.editor.insertEmbed(range.index, "image", imgUrl);
            })
            .catch((error) => {
              console.log("error", error);
            });
        } catch (error) {
          console.log("error");
        }
      });
    },
    publish() {
      const PlanTourInfoDto = {
        title: this.title,
        content: this.$refs.myTextEditor.quill.root.innerHTML,
        planId: this.selectPlan.id,
      };

      jwt
        .post("/journal/write", PlanTourInfoDto)
        .then((response) => {
          if (response.data === 1) {
            alert("글 작성이 정상적으로 저장되었습니다.");
            this.$router.push({ path: "/" });
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    clickPlan(plan) {
      this.selectPlan = plan;
      this.$bvModal.hide("modal-scrollable");
    },
  },
  computed: {
    editor() {
      return this.$refs.myTextEditor.quill;
    },
    contentCode() {
      return hljs.highlightAuto(this.content).value;
    },
  },
};
</script>
<style scoped>
.ql-container {
  height: 600px;
}
.quill-editor {
  height: 600px;
}
.textzone {
  text-align: left;
}
.btn {
  text-align: right;
  margin-right: 5px;
}
.ql-snow .ql-editor img {
  width: 50%;
}
.example {
  height: 700px;
}
.ql-container {
  height: 700px; /* 원하는 높이로 조정 */
}

.modal-body {
  max-height: 600px;
  overflow-y: auto;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.wide-card {
  width: 100%;
  background-color: #fff;
  margin-bottom: 10px;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.wide-card:hover {
  background-color: #f8f9fa;
}

.selected {
  background-color: #3d7fc5;
}

.card-header {
  background-color: #f8f9fa;
  padding: 10px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

.card-title {
  margin: 0;
  font-size: 18px;
  font-weight: bold;
}

.card-date {
  margin: 0;
  font-size: 14px;
  color: #6c757d;
}

.card-body {
  padding: 10px;
}

.card-location {
  margin: 0;
  font-size: 16px;
  color: #6c757d;
}

/* Hide the ok and close buttons */
.modal-footer {
  display: none;
}
.plan-info {
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 5px;
  margin-top: 20px;
}

.plan-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 10px;
}
img {
  width: 300px;
  height: auto;
}
p img {
  width: 300px;
}
.plan-duration {
  font-size: 18px;
  margin-bottom: 0;
  color: #6c757d;
}
.footer-font {
  color: rgba(0, 0, 0, 0.5);
}
</style>
