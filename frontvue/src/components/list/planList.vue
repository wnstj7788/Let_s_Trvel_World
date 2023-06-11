<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4" v-for="(card, index) in cards" :key="index">
        <card-plan-list :cardInfo="card"></card-plan-list>
      </div>
    </div>
    <button v-if="shouldShowLoadMoreButton" @click="loadMore">더보기</button>
    <div v-if="cards.length == 0">검색 가능한 여행 정보가 없습니다.</div>
  </div>
</template>

<script>
import http from "@/api/http";
import CardPlanList from "../cards/CardPlanListItem.vue";
export default {
  name: "PlanList",
  props: {
    keyword: String,
  },
  data() {
    return {
      cards: [],
      currentPage: 0,
      shouldShowLoadMoreButton: true,
    };
  },

  components: { CardPlanList },
  mounted() {
    this.getList(false);
    if (this.cards.length == 0) this.shouldShowLoadMoreButton = false;
  },
  methods: {
    loadMore() {
      this.getList(false);
    },
    getList(c) {
      let url = "";
      if (this.keyword) url = `/plan/list/${this.keyword}/${this.currentPage}`;
      else url = `/plan/list/${this.currentPage}`;
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
