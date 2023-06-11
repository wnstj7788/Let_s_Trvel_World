<template>
  <div>
    <div class="main">
      <br />
      <br />
      <h1 class="title">뀨돈이가 뽑아봤어요!😁</h1>
      <location-list></location-list>
    </div>
  </div>
</template>

<script>
  import LocationList from "@/components/list/locationList.vue";
  export default {
    name: "MainContent",
    components: { LocationList },
    props: { searchWord: String },
    data() {
      return {};
    },
  };
</script>

<style scoped>
  .title {
    text-align: left;
  }

  .main {
    background-color: whitesmoke;
  }
</style>
