<template>
  <div class="d-flex justify-content-center align-items-center vh-100">
    <div class="w-40">
      <router-link :to="{ path: '/' }">
        <div>
          <!-- Image and text -->

          <img
            src="../../assets/Logo.png"
            class="d-inline-block align-top"
            alt="Img"
            width="400px"
          />
        </div>
      </router-link>

      <b-form @submit="onSubmit" v-if="show">
        <b-form-group
          id="input-group-1"
          label="Id address:"
          label-for="input-1"
          description="We'll never share your id with anyone else."
        >
          <b-form-input
            id="input-1"
            v-model="form.id"
            type="text"
            placeholder="Enter id"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="input-group-2"
          label="Your Password:"
          label-for="input-2"
        >
          <b-form-input
            id="input-2"
            type="password"
            v-model="form.password"
            placeholder="Enter password"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group id="input-group-4" v-slot="{ ariaDescribedby }">
          <b-form-checkbox-group
            v-model="form.checked"
            id="checkboxes-4"
            :aria-describedby="ariaDescribedby"
          >
          </b-form-checkbox-group>
        </b-form-group>

        <b-button type="submit" variant="primary">로그인</b-button>
      </b-form>

      <router-link :to="{ name: 'UserJoin' }">회원가입</router-link>
      <br />
    </div>
  </div>
</template>

<script>
  import http from "@/api/http";
  export default {
    data() {
      return {
        form: {
          id: "",
          name: "",
          password: "",
          repassword: "",
          food: null,
          checked: [],
        },
        foods: [
          { text: "Select One", value: null },
          "Carrots",
          "Beans",
          "Tomatoes",
          "Corn",
        ],
        show: true,
      };
    },
    methods: {
      onSubmit(event) {
        event.preventDefault();
        console.log("로그인 이벤트 발생 ");
        const loginInfo = {
          id: this.form.id,
          pw: this.form.password,
        };
        http
          .post("/member/login", loginInfo)
          .then((response) => {
            // 반환값이 정상인 경우
            if (response.status === 200) {
              const headers = response.headers;
              if (headers && headers["authorization"]) {
                const token = headers["authorization"].split(" ")[1];
                console.log("토큰 값:", token);
                alert("로그인 성공");

                sessionStorage.setItem("token", token);

                this.$router.push("Main");
                location.reload();
                // 리다이렉트 로직 작성
                // 예: window.location.href = "/success";
              } else {
                alert("로그인 실패");
              }
            } else {
              alert("로그인 실패");
            }
          })
          .catch((error) => {
            // 요청 실패 시 에러 처리
            console.error("로그인 요청 실패", error);
            alert("로그인 요청 실패");
          });
      },
    },
  };
</script>

<style scoped>
  .centered-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh; /* 전체 화면 높이 */
  }

  .centered-container > div {
    width: 50%; /* 50%의 너비로 설정 */
  }

  .centered-container > router-link {
    margin-top: 1rem;
  }
</style>
