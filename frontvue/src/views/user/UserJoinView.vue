<template>
  <div>
    <div class="container my-5">
      <div class="d-flex flex-column align-items-center">
        <h2 class="mb-4">가입을 환영합니다!</h2>
        <ul
          class="nav nav-tabs tab-body-header rounded d-inline-flex w-25 mb-4"
        >
          <li class="nav-item flex-grow-1 text-center">
            <input value="common" class="d-none" type="radio" id="common" />
            <label class="nav-link cursor-pointer" for="common"
              >일반 회원</label
            >
          </li>
        </ul>
        <form class="row g-1 p-0 p-md-4 w-100">
          <table>
            <tr>
              <td style="width: 50%" class="px-4 border-top border-dark">
                <label class="mb-4 form-label" for="userName"
                  >이름 <span class="text-danger">*</span></label
                >
              </td>
              <td class="px-4">
                <input
                  v-model="userName"
                  id="userName"
                  type="text"
                  class="mb-4 form-control form-control-lg"
                />
              </td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark">
                <label class="mb-4 form-label" for="userId"
                  >아이디 <span class="text-danger">*</span></label
                >
              </td>
              <td v-if="idcheck" class="px-4">
                <input
                  v-model="userId"
                  @change="idcheck"
                  id="userId"
                  type="text"
                  class="mb-4 form-control form-control-lg"
                />
              </td>
              <td v-else class="px-4 form-floating">
                <input
                  v-model="userId"
                  @change="idcheck"
                  id="userId"
                  type="text"
                  class="mb-4 form-control is-invalid form-control-lg"
                />
                <label for="userId" class="ps-5 text-danger"
                  >중복된 아이디가 존재합니다. 다른 아이디를 입력하세요.</label
                >
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
                />
              </td>
              <td v-else class="px-4 form-floating">
                <input
                  v-model="userPw"
                  id="userPw"
                  type="password"
                  class="mb-4 form-control is-invalid form-control-lg"
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
            <tr>
              <td class="px-4 border-top border-dark">
                <label class="mb-4 form-label" for="userEmail"
                  >이메일 <span class="text-danger">*</span></label
                >
              </td>
              <td class="px-4">
                <input
                  v-model="userEmail"
                  id="userEmail"
                  type="email"
                  class="mb-4 form-control form-control-lg"
                />
              </td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark">
                <label class="mb-4 form-label" for="userPhone"
                  >휴대전화 <span class="text-danger">*</span></label
                >
              </td>
              <td class="px-4">
                <input
                  v-model="userPhone"
                  id="userPhone"
                  type="text"
                  class="mb-4 form-control form-control-lg"
                />
              </td>
            </tr>

            <tr>
              <td class="px-4 border-top border-dark">
                <label class="mb-4 form-label" for="userId"
                  >닉네임 <span class="text-danger">*</span></label
                >
              </td>
              <td v-if="nick_nameCheck" class="px-4">
                <input
                  v-model="nick_name"
                  @change="nick_nameCheck"
                  id="nick_name"
                  type="text"
                  class="mb-4 form-control form-control-lg"
                />
              </td>
              <td v-else class="px-4 form-floating">
                <input
                  v-model="nick_nameCheck"
                  @change="nick_nameCheck"
                  id="nick_name"
                  type="text"
                  class="mb-4 form-control is-invalid form-control-lg"
                />
                <label for="nick_name" class="ps-5 text-danger"
                  >중복된 아이디가 존재합니다. 다른 아이디를 입력하세요.</label
                >
              </td>
            </tr>
            <tr></tr>
          </table>

          <div class="col-12 text-center mt-4">
            <router-link :to="{ name: 'UserLogin' }">로그인 창 이동</router-link
            >&nbsp;
            <a class="btn px-4 btn-block btn-primary lift text-uppercase" @click="join"
              >회원가입</a
            >
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
  import http from "@/api/http";
  export default {
    name: "UserJoinView",
    data() {
      return {
        userId: "",
        userPw: "",
        userName: "",
        userPhone: "",
        nick_name: "",
        userEmail: "",
        userRePassword: "",
        idcheck: true,
        passwordcheck: true,
        nick_nameCheck: true,
      };
    },
    watch: {
      userId() {
        console.log(this.userId);
        this.checkUserId(this.userId);
      },
      userRePassword() {
      this.checkPw(this.userRePassword);
    },
    },
    methods: {
      join(){
        if(this.idcheck && this.passwordcheck && this.nick_nameCheck ){
          const memberInfo = {
            id : this.userId,
            pw : this.userPw,
            userName : this.userName,
            phone : this.userPhone,
            nickName : this.nick_name,
            email : this.userEmail,
          }
          http.post("/member/register",memberInfo)
          .then((response) => {        
          if(response.status === 200){
            console.log("회원가입 성공 로그인 후 사용해주세요 !!!")
            alert("회원가입 성공!.")
            this.$router.push({ name: "UserLogin" });
          }else{
            alert("오류가 발생했습니다. 잠시 후 다시 시도해주시길 바랍니다.")
          } 
        })
        .catch((error) => {
          // 요청이 실패한 경우
          // 실패 메시지 출력 등의 동작 수행
          console.error("회원 가입 실패:", error);
          this.showErrorToast("회원 가입에 실패했습니다.");

          })
        }

      },
      checkPw() {
      if (this.userPw === this.userRePassword) {
        this.passwordcheck = true;
      } else {
        this.passwordcheck = false;
      }
    },
      checkUserId(userId) {
        // userId 유효성 검사 및 중복 확인 등의 로직 수행
        // 예시로 axios 요청을 보내는 코드를 작성하였습니다.
        http
          .get(`/member/checkId/${userId}`)
          .then((response) => {
            // 응답 처리 로직
            const used = response.data; // 서버에서 반환한 used 값을 받음
            // 중복 여부에 따라 처리 로직을 수행
            if (used) {
              // 중복된 아이디가 존재하는 경우
              this.idcheck = false;
              // ...
            } else {
              // 중복된 아이디가 존재하지 않는 경우
              this.idcheck = true;
              // ...
            }
          })
          .catch((error) => {
            // 오류 처리 로직
            console.error("아이디 확인 요청 실패", error);
          });
      },
    },
  };
</script>

<style scoped >
  .input_box button {
    float: left;
    margin-left: 1.04712%;
    width: 10.471204%;
    height: 55px;
    font-size: 16px;
    color: #fff;
    text-align: center;
    background: #6e7c8c;
  }
</style>
