<template>
  <div>
    
    <router-view></router-view>
    <b-button @click="goBack" variant="light">이전</b-button>
  </div>
</template>

<script>


export default {
  name: "InfoView",
  data() {
    return {
      cardInfo: null,
    };
  },
 
  methods: {
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
button {
  float: right;
  margin-bottom: 20px;
}


</style>