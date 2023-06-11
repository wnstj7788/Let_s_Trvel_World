<template>
  <div id="MainNew">
    <main-search></main-search>
    <main-content></main-content>
    <main-review></main-review>
  </div>
</template>

<script>
  import MainSearch from "@/components/main/MainSearch.vue";
  import MainContent from "@/components/main/MainContent.vue";
  import MainReview from "@/components/main/MainReview.vue";
  export default {
    data() {
      return {};
    },
    components: { MainSearch, MainContent, MainReview },
  };
</script>
<style scoped></style>
