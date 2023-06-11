<template>
  <div id="Main">
    <main-header></main-header>
    <b-container class="bv-example-row bv-example-row-flex-cols">
      <b-row>
        <b-col align-self="center"><router-view id="main"></router-view></b-col>
      </b-row>
    </b-container>
    <main-footer></main-footer>
  </div>
</template>

<script>
import MainHeader from "@/common/MainHeader.vue";
import MainFooter from "@/common/MainFooter.vue";
export default {
  data() {
    return {};
  },
  components: { MainHeader, MainFooter },
};
</script>
<style scoped></style>
