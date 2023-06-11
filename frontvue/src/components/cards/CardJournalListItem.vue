<template>
  <div>
    <b-card
      no-body
      class="rounded-0 mt-0 stretched d-flex align-items-center justify-content-center"
      style="max-width: 540px"
      @click="journalInfo"
    >
      <b-row no-gutters>
        <b-col md="6">
          <b-card-img
            :src="require(`@/assets/cardImg/${travel}.jpg`)"
            alt="Image"
            img-width="450px"
            img-height="300px" 
            class="rounded-0 mt-0 top"
            
          ></b-card-img>
        </b-col>
        <b-col md="6">
          <div>
            <strong> {{ cardInfo.title }} </strong>
          </div>
          <br />
          <div class="sido-name">
            <b-card-text class="left-text">
              <b-icon icon="geo-alt"></b-icon>
              {{ cardInfo.sidoName }}</b-card-text
            >
          </div>
          <br />
          <div>{{ cardInfo.userName }} 님의 여행</div>
        </b-col>
      </b-row>
    </b-card>
  </div>
</template>

<script>
  export default {
    name: "CardJournalList",
    props: {
      cardInfo: Object,
    },
    data() {
      return {
        travel: 1,
      };
    },
    created() {
      this.ramdp();
    },
    methods: {
      ramdp() {
        var num = String(Math.floor(Math.random() * 10) + 1);
        this.travel = num;
      },
      journalInfo() {
        this.$router.push({
          path: `/info/journal/${this.cardInfo.journalId}`,
        });
      },
    },
  };
</script>

<style scoped>
  .rounded-card {
    border-radius: 1.5rem;
    /* Adjust the border-radius value as needed */
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
    /* Add a subtle box shadow */
    overflow: hidden;
    /* Hide any content that exceeds the rounded corners */
    cursor: pointer;
  }

  .align-items-start {
    align-items: flex-start;
  }

  .card-body {
    background-color: white;
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
    padding: 0.5rem;
    /* Adjust the padding as needed */
  }

  .sido-name {
    margin-bottom: 0.5rem;
    text-align: left;
    border-radius: 20%;
    /* Add border-radius with a value equal to half of the element's height */
    display: inline-block;
    padding: 5px;
    /* Adjust padding as needed */
  }

  .left-text {
    text-align: left !important;

    /* Align the text to the left */
  }

  .right-text {
    text-align: right;
    padding-right: 10px;
    position: relative;
    bottom: 0;
  }

  .mt-0 {
    margin-top: 0 !important;
  }

  .rounded-0 {
    border-radius: 0 !important;
  }

  .top {
    width: 100%;
    height: 100%;
    top: 0;
    object-fit: cover;
    object-position: center;
  }
</style>
