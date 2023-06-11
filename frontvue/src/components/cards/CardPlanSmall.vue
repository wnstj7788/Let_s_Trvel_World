<template>
  <div @click="handleCardClick">
    <div>{{ plan.title }}</div>
    <div>{{ date }}</div>
    <div>{{ plan.sidoName }}</div>
  </div>
</template>

<script>
  export default {
    name: "cardPlanSmall",
    props: { plan: Object },
    components: {},
    data() {
      return {};
    },
    computed: {
      date() {
        return this.plan.startAt + " ~ " + this.plan.endAt;
      },
    },
    methods: {
      handleCardClick() {
        this.$emit("selectPlan", this.plan);
      },
    },
  };
</script>

<style scoped>
  .container {
    height: 300px; /* 컨테이너의 높이를 지정 (스크롤 가능한 영역 크기) */
    overflow: auto; /* 스크롤 가능하도록 설정 */
  }

  .box {
    width: 100px;
    height: 50px;
    background-color: lightgray;
    margin-bottom: 10px;
    border: 1px solid black;
    border-radius: 5px;
  }
</style>
