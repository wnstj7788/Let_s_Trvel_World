<template>
  <div>
    <h5 class="text-left">{{ journal.sidoName }}</h5>

    <h3 class="text-left">{{ journal.title }}</h3>
    <!-- <pre>{{ cardInfo.title }}</pre> -->

    <div class="text-center">
      <span class="text-gray">{{ journal.userName }} </span>
      <span class="spacer"></span>
      <span>{{ journal.startAt }} ~ {{ journal.endAt }}</span>
    </div>

    <hr />
    <div class="content" v-html="journalContent"></div>
  </div>
</template>

<script>
  import http from "@/api/http";
  export default {
    name: "journalInfo",
    components: {},
    props: {
      journalId: String,
    },
    data() {
      return {
        journal: {},
        journalContent: "", // 추가된 부분
      };
    },
    mounted() {
      http
        .get(`/journal/info/${this.journalId}`)
        .then((req) => {
          this.journal = req.data;
          this.journalContent = this.addInlineStyles(this.journal.content); // 수정된 부분
        })
        .catch((error) => {
          console.log("error", error);
        });
    },
    methods: {
      addInlineStyles(html) {
        // content 클래스에 스타일을 인라인으로 추가합니다.
        const styledHTML = html.replace(/<img/g, '<img style="width: 50%;"'); // 이미지의 너비를 100%로 설정
        return styledHTML;
      },
    },
    created() {},
  };
</script>

<style scoped>
  .content {
    text-align: left;
  }
  .text-left {
    text-align: left;
  }

  .text-center {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .text-gray {
    color: gray;
  }

  .spacer {
    flex-grow: 1;
  }
</style>
