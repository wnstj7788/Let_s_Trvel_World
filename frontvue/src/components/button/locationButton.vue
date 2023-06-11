<template>
  <div class="button-group">
    <span
      v-for="(item, idx) in list"
      :key="idx"
      class="custom-button"
      @click="search(item.name)"
    >
      <b-icon icon="geo-alt" class="icon"></b-icon>
      <span class="button-text">{{ item.name }}</span>
    </span>
    <br />
    <br />
  </div>
</template>

<script>
  import http from "@/api/http";
  export default {
    name: "locationButton",
    components: {},
    data() {
      return {
        list: [],
      };
    },
    mounted() {
      http
        .get(`/plan/location/hotLocation`)
        .then((req) => {
          this.list = req.data;
        })
        .catch((err) => {
          console.error(err);
        });
    },
    methods: {
      search(name) {
        if (!this.$route.path.endsWith(location))
          this.$router.push({
            name: "Search",
            params: {
              word: name,
            },
          });
      },
    },
  };
</script>

<style scoped>
  .custom-button {
    display: flex;
    align-items: center;
    border: 1px solid #e6e6e6;
    border-radius: 20px;
    background-color: #fff;
    padding: 5px 10px;
    cursor: pointer;
    transition: background-color 0.3s ease-in-out;
  }

  .custom-button:hover {
    background-color: #e6e6e6;
    color: #fff;
  }

  .icon {
    margin-right: 10px;
  }

  .button-text {
    color: #000;
  }
</style>
