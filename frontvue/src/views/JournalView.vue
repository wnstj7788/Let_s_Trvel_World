<template>
  <div>
     <div class="search-container">
      <input type="text" v-model="inputKeyword" class="rounded-input" @keyup.enter="search" />
      <button @click="search" class="search-button">검색</button>
    </div>
    <journal-list :keyword="keyword"></journal-list>
  </div>




</template>

<script>
import JournalList from "@/components/list/journalList.vue";
export default {
  name: "JournalView",
  components: { JournalList },
  data() {
    return {
      inputKeyword: "",
      keyword: "",
    };
  },
  methods: {
    search() {
      this.keyword = this.inputKeyword;
    },
  },
};
</script>

<style scoped>
.search-container {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
  border: 1;
  
}

.rounded-input {
  padding: 10px;
  border-radius: 20px;
  border: none;
  outline: none;
  font-size: 16px;
  width: 800px;
   border: 1px solid black; /* 검은 선 추가 */
}

.search-button {
  padding: 10px 20px;
  border-radius: 20px;
  border: none;
  outline: none;
  font-size: 16px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

.search-button:hover {
  background-color: #0056b3;
}

</style>
