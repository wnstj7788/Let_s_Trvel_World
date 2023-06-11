<template>
  <b-container class="mt-4">
    <b-row>
      <b-col>
        <h3>나의 여행 관리</h3>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <template #header>My Page</template>
        <div>
          <b-carousel
            id="carousel-fade"
            style="text-shadow: 0px 0px 2px #000"
            fade
            indicators
            img-width="1024"
            img-height="480"
          >
            <b-carousel-slide
              caption="여행기 관리 "
              img-src="https://picsum.photos/1024/480/?image=10"
            ></b-carousel-slide>
            <b-carousel-slide
              caption="여행기 관리 "
              img-src="https://picsum.photos/1024/480/?image=12"
            ></b-carousel-slide>
            <b-carousel-slide
              caption="여행기 관리 "
              img-src="https://picsum.photos/1024/480/?image=22"
            ></b-carousel-slide>
          </b-carousel>
        </div>

        <template #lead> 내 정보 확인페이지입니다. </template>

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
                      {{ userName }}
                    </td>
                  </tr>
                  <tr>
                    <td>이메일</td>
                    <td>
                      {{ email }}
                    </td>
                  </tr>
                  <tr>
                    <td>전화번호</td>
                    <td>
                      {{ phone }}
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>

        <hr class="my-4" />

        <div>
          <b-tabs content-class="mt-3" fill>
            <b-tab title="나의 여행 계획" active>
              <div v-for="item in planList" :key="item.title">
                <card-plan-list-item :cardInfo="item"></card-plan-list-item>
              </div>
            </b-tab>
            <b-tab title="나의 여행 후기"
              ><div v-for="item in journalList" :key="item.title">
                <card-journal-list-item
                  :cardInfo="item"
                ></card-journal-list-item></div
            ></b-tab>
          </b-tabs>
        </div>

        <hr class="my-4" />
        <!-- 
        <b-button variant="primary" href="#" class="mr-1">정보수정</b-button> -->
<b-button @click="modify">
 정보수정
</b-button>
        <!-- <b-button variant="danger" href="#">회원탈퇴</b-button> -->
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
  import jwt from "@/api/jwt";

  import CardPlanListItem from "@/components/cards/CardPlanListItem.vue";
  import CardJournalListItem from "@/components/cards/CardJournalListItem.vue";
  export default {
    name: "UserMyPage",
    data() {
      return {
        planList: [],
        journalList: [],
        userId: "",
        userName: "",
        email: "",
        phone: "",
        imageUrl: "", // 이미지 URL 저장을 위한 데이터
      };
    },
    methods: {
      modify(){
           this.$router.push({ name: 'UserModify' })
      },

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
            // 이미지 업로드 후 백엔드에서 반환한 이미지 URL을 userInfo에 설정합니다.
            this.imageUrl = response.data;
          })
          .catch((error) => {
            console.error(error);
          });
      },
    },
    components: { CardPlanListItem, CardJournalListItem },
    mounted() {
      jwt
        .get("/member/mypage")
        .then((response1) => {
          this.userId = response1.data.id;
          this.userName = response1.data.userName;
          this.email = response1.data.email;
          this.phone = response1.data.phone;
          this.imageUrl = response1.data.userImg;
        })
        .catch((err) => {
          console.error(err);
        });
      jwt
        .get("/member/myplan")
        .then((response2) => {
          this.planList = response2.data;
        })
        .catch((err) => {
          console.error(err);
        });

      jwt
        .get("/member/myjournal")
        .then((response3) => {
          this.journalList = response3.data;
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
