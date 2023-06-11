<template>
  <div>
    <div class="main">
      <br />
      <br />
      <h1 class="title">여행 다녀왔어욤!✈</h1>
      <plan-list></plan-list>
    </div>
  </div>
</template>

<script>
import PlanList from "@/components/list/planList";
export default {
  components: { PlanList },
  data() {
    return {};
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
.title {
  text-align: left;
}

.main {
  background-color: white;
}
</style>
