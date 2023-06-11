<template>
  <div class="menu-nav">
    <br />
    <br />
    <div class="search-form d-flex align-items-center justify-content-center">
      <div class="input-group rounded" align="center">
        <b-form-input
          size="m"
          class="p-3 mb-1 rounded ttt"
          type="text"
          placeholder="검색어를 입력해주세요"
          v-model="keyword"
          @keyup.enter="searchresultshow(keyword)"
        ></b-form-input>
        <div class="input-group-append">
          <b-button size="sm" class="my-2 my-sm-0" @click="searchresultshow">
            검색
            <b-icon icon="search"></b-icon>
          </b-button>
        </div>
      </div>
    </div>

    <h2 class="title">🔥현재 가장 핫한 여행지🔥</h2>

    <div class="center-icon">
      <location-button></location-button>
    </div>
    <br />
  </div>
</template>

<script>
import { BButton, BIcon } from "bootstrap-vue";
import LocationButton from "../button/locationButton.vue";
export default {
  components: {
    BButton,
    BIcon,
    LocationButton,
  },
  data() {
    return {
      keyword: "",
    };
  },
  methods: {
    searchresultshow() {
      if (this.keyword !== "") {
        //검색어를 입력한 경우
        if (!this.$route.path.endsWith(this.keyword))
          this.$router.push({
            name: "SearchRedirect",
            params: {
              word: this.keyword,
            },
          });
      } else {
        alert("검색어를 입력해주세요!"); //검색어를 입력하지 않은 경우
      }
    },
  },
};
</script>

<style scoped>
.button-group {
  display: flex;
  gap: 10px;
}

.button-group b-button {
  border-radius: 20px;
}

.center-icon {
  display: flex;
  justify-content: center;
}
.ttt {
  margin: 0;
}
.title {
  margin-top: 7px;
}
</style>
