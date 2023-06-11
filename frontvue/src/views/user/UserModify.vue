<template>
  <b-container class="mt-4">
    <b-row>
      <b-col>
        <h3>개인 정보 수정</h3>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <hr class="my-4" />

        <div class="container mt-5">
          <div class="row">
            <div class="col-md-4">
              <div class="text-center">
                <img
                  :src="imageUrl"
                  alt="프로필 이미지"
                  class="img-fluid rounded-circle profile-image"
                />
                <input
                  type="file"
                  @change="uploadImage"
                  accept="image/*"
                  class="mt-2"
                />
              </div>
            </div>
            <div class="col-md-8">
              <table class="table">
                <tbody>
                  <tr>
                    <td>아이디</td>
                    <td>{{ userId }}</td>
                  </tr>
                  <tr>
                    <td>이름</td>
                    <td>
                      <input
                        v-model="userName"
                        class="form-control"
                        type="text"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td>이메일</td>
                    <td>
                      <input
                        v-model="email"
                        class="form-control"
                        type="email"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td>전화번호</td>
                    <td>
                      <input v-model="phone" class="form-control" type="tel" />
                    </td>
                  </tr>
                  <tr>
                    <td>닉네임</td>
                    <td>
                      <input
                        v-model="nick_name"
                        class="form-control"
                        type="text"
                      />
                    </td>
                  </tr>

                  <tr>
                    <td class="px-4 border-top border-dark">
                      <label class="mb-4 form-label" for="userPw"
                        >비밀번호 <span class="text-danger">*</span></label
                      >
                    </td>
                    <td v-if="passwordcheck" class="px-4">
                      <input
                        v-model="userPw"
                        id="userPw"
                        type="password"
                        class="mb-4 form-control form-control-lg"
                        required
                      />
                    </td>
                    <td v-else class="px-4 form-floating">
                      <input
                        v-model="userPw"
                        id="userPw"
                        type="password"
                        class="mb-4 form-control is-invalid form-control-lg"
                        required
                      />
                      <label for="userPw" class="ps-5 text-danger"
                        >비밀번호가 일치하지 않습니다.
                      </label>
                    </td>
                  </tr>
                  <tr>
                    <td class="px-4 border-top border-dark">
                      <label class="mb-4 form-label" for="userRePassword"
                        >비밀번호 확인 <span class="text-danger">*</span></label
                      >
                    </td>
                    <td v-if="passwordcheck" class="px-4">
                      <input
                        v-model="userRePassword"
                        @change="passwordcheck"
                        id="userRePassword"
                        type="password"
                        class="mb-4 form-control form-control-lg"
                      />
                    </td>
                    <td v-else class="px-4 form-floating">
                      <input
                        v-model="userRePassword"
                        @change="passwordcheck"
                        id="userRePassword"
                        type="password"
                        class="mb-4 form-control is-invalid form-control-lg"
                      />
                      <label for="userRePassword" class="ps-5 text-danger"
                        >비밀번호가 일치하지 않습니다.
                      </label>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>

        <b-button
          variant="primary"
          href="#"
          class="mr-1"
          @click="updateUserInfo"
          >수정</b-button
        >
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
  import jwt from "@/api/jwt";

  export default {
    name: "UserModify",
    data() {
      return {
        planList: [],
        journalList: [],
        userId: "",
        userName: "",
        email: "",
        phone: "",
        imageUrl: "", // 이미지 URL 저장을 위한 데이터
        userPw: "",
        userRePassword: "",
        passwordcheck: true,
        nick_name: "",
      };
    },
    methods: {
      uploadImage(event) {
        const file = event.target.files[0];
        const formData = new FormData();
        formData.append("image", file);

        jwt
          .post("/member/upload", formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          })
          .then((response) => {
            console.log("data", response.data);
            // 이미지 업로드 후 백엔드에서 반환한 이미지 URL을 userInfo에 설정합니다.
            this.imageUrl = response.data;
          })
          .catch((error) => {
            console.error(error);
          });
      },

      updateUserInfo() {
        if (this.userPw == "" || this.userRePassword == "") {
          alert("비밀번호는 필수 입력 사항입니다");
          return;
        }

        if (this.userPw != this.userRePassword) {
          alert("비밀번호를 확인하세요");
          return;
        }
        const userInfo = {
          userName: this.userName,
          email: this.email,
          phone: this.phone,
          pw: this.userPw,
          // userImg : this.imageUrl,
          nickName: this.nick_name,
          userImg: this.imageUrl,
        };

        jwt
          .put("/member/mypageModify", userInfo)
          .then((response) => {
            console.log(response + "Update successful");
            alert("정상 수정 되었습니다.");
            this.$router.push({ name: "UserMypageView" });
            location.reload();
          })
          .catch((error) => {
            console.error(error);
            // 업데이트 중 오류 발생 시 처리
          });
      },

      checkPw() {
        if (this.userPw === this.userRePassword) {
          this.passwordcheck = true;
        } else {
          this.passwordcheck = false;
        }
      },
    },
    components: {},
    watch: {
      userRePassword() {
        this.checkPw(this.userRePassword);
      },
    },
    mounted() {
      jwt
        .get("/member/mypage")
        .then((response1) => {
          console.log(response1.data);
          this.userId = response1.data.id;
          this.userName = response1.data.userName;
          this.email = response1.data.email;
          this.phone = response1.data.phone;
          this.imageUrl = response1.data.userImg;
          this.nick_name = response1.data.nickName;
        })
        .catch((err) => {
          console.error(err);
        });
    },
  };
</script>

<style scoped>
  .profile-image {
    width: 200px;
    height: 200px;
    object-fit: cover;
  }

  table {
    width: 100%;
  }

  td {
    vertical-align: middle;
  }

  label {
    font-weight: bold;
  }

  .form-control {
    border-radius: 0;
  }
</style>
