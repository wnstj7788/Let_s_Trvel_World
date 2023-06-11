<template>
  <div @click="handleCardClick(plan)">
    <b-card no-body class="overflow-hidden" style="max-width: 540px">
      <b-row no-gutters>
        <b-col md="6">
          <b-card-img
            :src="plan.img"
            alt="Image"
            class="rounded-0"
          ></b-card-img>
        </b-col>
        <b-col md="6">
          <b-card-body :title="plan.title">
            <b-card-text> 일정 : "{{ plan.date }}" </b-card-text>
          </b-card-body>
        </b-col>
      </b-row>
    </b-card>
  </div>
</template>

<script>
  export default {
    name: "cardPlan",
    props: ["plan"],
    components: {},
    data() {
      return {};
    },
    created() {},
    methods: {
      handleCardClick(plan) {
        // 클릭한 카드에 대한 처리 로직을 구현합니다.
        console.log("클릭한 카드:", plan);
        this.$emit("selectPlan", this.plan);
      },
    },
  };
</script>

<style scoped>
  div {
    display: inline-block;
    padding: 5px;
    cursor: pointer;
  }
</style>
