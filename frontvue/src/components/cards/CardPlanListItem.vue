<template>
  <div class="mt-4">
    <b-card
      v-if="travel"
      :img-src="require(`@/assets/img/${travel}.jpeg`)"
      img-alt="Card image"
      img-left
      class="mb-3 rounded-card card-image"
      img-width="100%"
      img-height="300px"
      @click="planInfo"
    >
      <!-- <b-img :img-src="require('@/assets/ ' + ramdp() +'.jpg')"> -->
      <b-card-body>
        <div class="card-content">
          <div class="sido-name">
            <b-card-text class="left-text">
              <b-icon icon="geo-alt"></b-icon>
              {{ cardInfo.regionName }}</b-card-text
            >
          </div>
          <br />
          <div class="title">
            <h4>{{ cardInfo.title }}</h4>

            <strong>기간</strong> : {{ cardInfo.startAt }} ~
            {{ cardInfo.endAt }}
          </div>
        </div>
        <b-card-text> </b-card-text>
        <b-card-text class="right-text">
          작성자 : <strong>{{ cardInfo.userId }} </strong></b-card-text
        >
      </b-card-body>
    </b-card>
  </div>
</template>

<script>
  export default {
    name: "CardPlanList",
    props: {
      cardInfo: Object,
    },
    components: {},
    data() {
      return { travel: "" };
    },
    mounted() {
      this.ramdp();
    },
    methods: {
      planInfo() {
        this.$router.push({
          path: `/info/plan/${this.cardInfo.id}`,
          params: { planId: this.cardInfo.id },
        });
      },
      ramdp() {
        var num = String(Math.floor(Math.random() * 3) + 1);
        if (this.cardInfo.regionName === "서울") {
          this.travel = "seoul/" + num;
        } else if (this.cardInfo.regionName === "인천") {
          this.travel = "incheon/" + num;
        } else if (this.cardInfo.regionName === "대전") {
          this.travel = "daejeon/" + num;
        } else if (this.cardInfo.regionName === "대구") {
          this.travel = "daegu/" + num;
        } else if (this.cardInfo.regionName === "광주") {
          this.travel = "gwangju/" + num;
        } else if (this.cardInfo.regionName === "부산") {
          this.travel = "busan/" + num;
        } else if (this.cardInfo.regionName === "울산") {
          this.travel = "ulsan/" + num;
        } else if (this.cardInfo.regionName === "세종특별자치시") {
          this.travel = "Sejong_/" + num;
        } else if (this.cardInfo.regionName === "경기도") {
          this.travel = "gyeonggi/" + num;
        } else if (this.cardInfo.regionName === "강원도") {
          this.travel = "Gangwon/" + num;
        } else if (this.cardInfo.regionName === "충청북도") {
          this.travel = "Chung-cheong bukdo/" + num;
        } else if (this.cardInfo.regionName === "충청남도") {
          this.travel = "Chungcheongnam-do/" + num;
        } else if (this.cardInfo.regionName === "경상북도") {
          this.travel = "Gyeongsangbuk-do/" + num;
        } else if (this.cardInfo.regionName === "경상남도") {
          this.travel = "Gyeongsangnam-do/" + num;
        } else if (this.cardInfo.regionName === "전라북도") {
          this.travel = "Jeollabuk do/" + num;
        } else if (this.cardInfo.regionName === "전라남도") {
          this.travel = "Jeollanam-do/" + num;
        } else if (this.cardInfo.regionName === "제주도") {
          this.travel = "jeju/" + num;
        }
      },
    },
  };
</script>

<style scoped>
  div {
    display: inline-block;
    text-align: left;
  }
  .card-image {
    width: 100%; /* 이미지의 가로 크기를 100%로 설정 */
    height: auto; /* 이미지의 세로 크기를 자동으로 조정 */
  }

  .rounded-card {
    border-radius: 1.5rem;
    /* Adjust the border-radius value as needed */
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
    /* Add a subtle box shadow */
    overflow: hidden;
    /* Hide any content that exceeds the rounded corners */
    cursor: pointer;
  }

  .card-content {
    padding: 1rem;
    /* Adjust the padding as needed */
  }

  .sido-name {
    margin-bottom: 0.5rem;
    background-color: #f7d358;
    text-align: left;
    border-radius: 20%;
    /* Add border-radius with a value equal to half of the element's height */
    display: inline-block;
    /* Adjust padding as needed */
  }

  .left-text {
    text-align: left !important;
    /* Align the text to the left */
  }

  .right-text {
    text-align: right;
  }

  .rounded-card {
    border-radius: 1.5rem; /* Adjust the border-radius value as needed */
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1); /* Add a subtle box shadow */
    overflow: hidden; /* Hide any content that exceeds the rounded corners */
    cursor: pointer;
  }
</style>
