<template>
  <header class="header">
    <router-link :to="{ path: '/' }">
      <div>
        <!-- Image and text -->
        <b-navbar variant="faded" type="light">
          <b-navbar-brand>
            <img
              src="../assets/Logo.png"
              class="d-inline-block align-top"
              alt="Img"
              width="150px"
            />
          </b-navbar-brand>
        </b-navbar>
      </div>
    </router-link>
    <nav class="nav">
      <ul class="centered-list">
        <li>
          <router-link :to="{ name: 'Plan' }">여행일정 만들기</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'JournalWrite' }">여행기 쓰기</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'JournalView' }">여행기</router-link>
        </li>
        <li>
          <!-- <router-link :to="{ name: 'Search' }"
            ><b-icon icon="search"></b-icon
          ></router-link> -->
          <router-link :to="{ name: 'Search' }"
            >여행지 검색 <b-icon class="test" icon="search"></b-icon
          ></router-link>
        </li>

        <ul v-if="token">
          <li>
            <router-link :to="{ name: 'UserMypageView' }"
              >마이페이지</router-link
            >
          </li>
          <li>
            <div
              class="logout"
              @mouseover="onLogoutMouseOver"
              @mouseleave="onLogoutMouseLeave"
              @click="logout"
            >
              로그아웃
            </div>
          </li>
          <li>
            <b-avatar :src="ImageUrl"></b-avatar>
          </li>
        </ul>
        <li v-else>
          <router-link :to="{ name: 'UserLogin' }">로그인</router-link>
        </li>
      </ul>
    </nav>
  </header>
</template>

<script>
  import jwt from "@/api/jwt";
  // import { mapActions } from "vuex";

  export default {
    data() {
      return {
        token: "",
        ImageUrl: "",
      };
    },
    methods: {
      // ...mapActions(["formChange"]),
      main() {
        if (this.$route.path !== "/") {
          this.$router.push({ path: "/" });
        } else {
          this.$router.go(0);
        }
      },
      logout() {
        sessionStorage.removeItem("token");
        this.$router.push({ name: "Main" });

        location.reload();
      },
      goHome() {
        this.$router.push({ path: "/" });
      },
      UserLogin() {
        this.$router.push("/user");
      },
      onLogoutMouseOver() {},
      onLogoutMouseLeave() {},
    },
    mounted() {
      this.token = sessionStorage.getItem("token");
      if (this.token)
        jwt
          .get("/member/userimage")
          .then((res) => {
            console.log(res.data);
            this.ImageUrl = res.data;
          })
          .catch((err) => {
            console.error(err);
          });
    },
  };
</script>
<style scoped>
  .header {
    height: 100px;
    background-color: white;
    padding: 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 1px solid #ccc; /* 구분선 스타일 지정 */
  }

  .logo {
    font-size: 24px;
    font-weight: bold;
    width: 8rem;
  }

  .nav ul {
    list-style-type: none;
    display: flex;
    margin: 0px;
  }

  .nav li {
    margin-left: 10px;
    display: flex;
    align-items: center;
  }

  .nav a {
    text-decoration: none;
    color: #333;
    font-weight: bold;
  }

  .centered-list {
    display: flex;
    justify-content: center;
  }
  .logout {
    display: inline-block;
    cursor: pointer;
    text-decoration: underline;
  }

  .logout:hover {
    color: blue;
  }
  .test {
    font-size: 20px;
  }
</style>
