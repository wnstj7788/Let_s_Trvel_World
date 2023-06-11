<template>
  <div id="search">
    <div class="input-group">
      <input
        class="form-control"
        type="text"
        v-model="searchKeyword"
        placeholder="입력하세요"
      />
      <div class="input-group-append">
        <button class="btn btn-primary" @click="search">검색</button>
      </div>
    </div>
    <keyword-button @searchkeyword="keywordClick"></keyword-button>
    <search-content
      v-if="keyword.length > 0"
      :keyword="keyword"
    ></search-content>
  </div>
</template>

<script>
  import KeywordButton from "@/components/button/keywordButton.vue";
  import SearchContent from "./SearchContent.vue";
  export default {
    name: "SearchView",
    components: { KeywordButton, SearchContent },
    props: {
      word: String,
    },
    data() {
      return {
        keyword: "",
        searchKeyword: "",
      };
    },
    methods: {
      search() {
        if (this.searchKeyword === "") return;
        if (this.searchKeyword !== this.keyword) {
          this.keyword = this.searchKeyword;
          this.$router.push({
            name: "SearchContent",
            params: { keyword: this.searchKeyword },
          });
        }
      },
      keywordClick(word) {
        this.keyword = word;
        this.searchKeyword = word;
      },
    },
    mounted() {
      if (this.word) {
        this.keyword = this.word;
        this.searchKeyword = this.word;
      }
    },
  };
</script>
