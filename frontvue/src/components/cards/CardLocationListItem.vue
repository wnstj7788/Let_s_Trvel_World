<template>
  <div>
    <b-card
      :img-src="
        cardInfo.firstImage
          ? cardInfo.firstImage
          : require('@/assets/no_image.png')
      "
      img-alt="Image"
      img-top
      tag="article"
      style="width: 20rem; height: 30rem"
      class="mb-2 rounded-card"
      @click="locationInfo"
    >
      <div class="card-content">
        <div class="sido-name">
          <b-card-text class="left-text">
            <b-icon icon="geo-alt"></b-icon>
            {{ cardInfo.sidoName }}</b-card-text>
        </div>
          <br>
        <div class="title">
          <strong>{{ cardInfo.title }}</strong>
        </div>
         <div class="title">
          {{ cardInfo.addr }}
        </div>
      </div>
      <b-card-text class = "right-text"> 조회수 :  <strong>{{ cardInfo.readCount }} </strong></b-card-text>

      <!-- <b-button @click="locationInfo" variant="primary">상세 보기11</b-button> -->
    </b-card>
  </div>
</template>

<script>
  import {  BCard, BCardText } from "bootstrap-vue";
  export default {
    name: "CardLocationList",
    components: {  BCard, BCardText },
    data() {
      return {
        image: "",
      };
    },
    props: {
      cardInfo: Object,
    },
  
    methods: {
      // 매개변수로 id 넘겨주기
      locationInfo() {
        this.$router.push({
          path: `/info/location/${this.cardInfo.contentId}`,
        });
      },

      
    },
  };
</script>

<style scoped>
  .card-img-top {
    display: inline-block;
    width: 100%;
    height: 250px;
    background-size: cover;
    background-position: center;
    background-color: white;
  }

  .card-body {
    background-color: white;
  }
  div {
    display: inline-block;
    padding: 5px;
    text-align: left;
  }

  .rounded-card {
  border-radius: 1.5rem; /* Adjust the border-radius value as needed */
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1); /* Add a subtle box shadow */
  overflow: hidden; /* Hide any content that exceeds the rounded corners */
  cursor: pointer;
}

.card-content {
  
  padding: 1rem; /* Adjust the padding as needed */
}

.sido-name {
  margin-bottom: 0.5rem;
  background-color: #F7D358;
  text-align: left;
  border-radius: 20%; /* Add border-radius with a value equal to half of the element's height */
  display: inline-block;
  padding: 5px; /* Adjust padding as needed */
}

.left-text {
  text-align: left !important; /* Align the text to the left */
}

.right-text{
   text-align: right;
}
</style>
