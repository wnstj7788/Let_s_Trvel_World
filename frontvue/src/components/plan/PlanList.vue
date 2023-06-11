<template>
  <div class="plan-list-container">
    <div class="plan-day-container scrollbar">
      <plan-day
        v-for="i in days"
        :key="i"
        :day="i"
        :locationName="locationName"
        class="plan-day"
        @changeList="changeList"
        @foucsDay="foucsDay"
        @deleteListItem="deleteListItem"
        @deleteDayList="deleteDayList"
      ></plan-day>
    </div>
  </div>
</template>

<script>
  import PlanDay from "@/components/plan/PlanDay.vue";

  export default {
    name: "PlanList",
    components: { PlanDay },
    props: {
      locationName: String,
      days: Number,
    },
    data() {
      return { locaiton: "" };
    },
    mounted() {
      this.locaiton = this.locationName;
    },
    methods: {
      foucsDay(day) {
        this.$emit("foucsDay", day);
      },
      changeList(items, day) {
        this.$emit("changeList", items, day);
      },
      deleteListItem(item, day) {
        this.$emit("deleteListItem", item, day);
      },
      deleteDayList(day) {
        this.$emit("deleteDayList", day);
      },
    },
    watch: {
      locationName() {
        this.locaiton = this.locationName;
      },
    },
  };
</script>

<style scoped>
  .plan-day {
    margin-right: 10px;
    width: 400px;
    height: 400px;
  }
  .plan-list-container {
    /* 부모 컨테이너의 스타일 */
    overflow: auto;
    white-space: nowrap;
  }

  .scrollbar {
    /* 스크롤바 스타일 설정 */
    scrollbar-width: thin;
    scrollbar-color: #888 transparent;
  }

  .scrollbar::-webkit-scrollbar {
    width: 8px;
  }

  .scrollbar::-webkit-scrollbar-track {
    background-color: transparent;
  }

  .scrollbar::-webkit-scrollbar-thumb {
    background-color: #888;
    border-radius: 4px;
  }

  .scrollbar::-webkit-scrollbar-thumb:hover {
    background-color: #555;
  }
</style>
