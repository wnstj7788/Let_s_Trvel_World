<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4" v-for="(card, index) in cards" :key="index">
        <card-location-list :cardInfo="card"></card-location-list>
      </div>
    </div>
    <b-button v-if="shouldShowLoadMoreButton" @click="loadMore">더보기</b-button>
    <div v-if="cards.length == 0">검색 가능한 여행 정보가 없습니다.</div>
  </div>
</template>

<script>
import http from "@/api/http";
import CardLocationList from "../cards/CardLocationListItem.vue";
export default {
  name: "LocationList",
  props: {
    keyword: String,
  },
  data() {
    return {
      shouldShowLoadMoreButton: true,
      cards: [],
      currentPage: 0,
    };
  },

  components: { CardLocationList },
  mounted() {
    this.getList(false);
  },
  methods: {
    loadMore() {
      this.getList(false);
    },
    getList(c) {
      let url = "";
      if (this.keyword) url = `/plan/location/list/${this.keyword}/${this.currentPage}`;
      else url = `/plan/location/list/${this.currentPage}`;
      console.log(url);
      http
        .get(url)
        .then((req) => {
          if (req.data) {
            if (c) this.cards = req.data;
            else this.cards = this.cards.concat(req.data);
          }
          if (req.data.length == 9) {
            this.currentPage++;
          } else {
            this.shouldShowLoadMoreButton = false;
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
  watch: {
    keyword() {
      this.currentPage = 0;
      this.getList(true);
    },
  },
};
</script>
<style scoped>
.container {
  padding-bottom: 20px;
}
</style>
