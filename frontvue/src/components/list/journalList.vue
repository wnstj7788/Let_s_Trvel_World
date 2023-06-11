<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4" v-for="(card, index) in cards" :key="index">
        <card-journal-list :cardInfo="card"></card-journal-list>
      </div>
    </div>
    <b-button v-if="shouldShowLoadMoreButton" @click="loadMore">더보기</b-button>
    <div v-if="cards.length == 0">검색 가능한 여행 정보가 없습니다.</div>
  </div>
</template>

<script>
import http from "@/api/http";
import CardJournalList from "../cards/CardJournalListItem.vue";
export default {
  name: "JournalList",
  props: {
    keyword: String,
  },
  components: { CardJournalList },
  data() {
    return {
      shouldShowLoadMoreButton: true,
      cards: [],
      currentPage: 0,
    };
  },
  mounted() {
    this.getList(false);
  },
  methods: {
    loadMore() {
      this.getList(false);
    },
    getList(c) {
      let url = "";
      if (this.keyword) url = `/journal/list/${this.keyword}/${this.currentPage}`;
      else url = `/journal/list/${this.currentPage}`;
      http
        .get(url)
        .then((req) => {
          if (req.data) {
            if (c) this.cards = req.data;
            else this.cards = this.cards.concat(req.data);
          }
          if (req.data.length >8) {
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
  margin-bottom: 20px;
}
</style>
