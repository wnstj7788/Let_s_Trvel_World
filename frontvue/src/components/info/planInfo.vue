<template>
  <div>
    <h5 class="text-left">여행 지역: {{ planInfo.regionName }}</h5>

    <h3 class="text-left">{{ planInfo.title }}</h3>

    <div class="text-center">
      <span class="text-gray"> 작성자 : {{ planInfo.userId }} </span>
      <span class="spacer"></span>
      <span>여행 일자 : {{ planInfo.startAt }} ~ {{ planInfo.endAt }}</span>
    </div>
    <hr />
    <map-view :locations="locations" :mapId="`planInfo${currentDay}`"></map-view>
    <div>
      <b-card
        v-for="(item, idx) in list"
        bg-variant="light"
        :header="` ${idx + 1}일차`"
        :key="idx"
        @click="foucsDay(idx)"
        class="text-center rounded"
      >
        <b-card-text>
          <div v-for="(ii, i) in item" :key="i" class="list-item">
            <strong>
              <span class="index-circle">{{ i + 1 }}</span>
              <span class="item-title">{{ ii.title }}</span>
            </strong>
          </div>
        </b-card-text>
      </b-card>
    </div>
  </div>
</template>
<script>
import http from "@/api/http";
import MapView from "../MapView.vue";
export default {
  name: "planInfo",
  props: {
    planId: String,
  },
  data() {
    return {
      planInfo: {},
      locations: [],
      list: [],
      currentDay: 0,
      tmp: {},
    };
  },
  components: { MapView },
  mounted() {
    http
      .get(`/plan/${this.planId}`)
      .then((req) => {
        this.planInfo = req.data.planInfo;
        // this.planInfo = this.tmp.planInfo;
        this.list = this.groupByDate(req.data.list);
        this.locations = this.list[this.currentDay];
      })
      .catch((err) => {
        console.error(err);
      });
  },
  methods: {
    foucsDay(idx) {
      this.currentDay = idx;
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");
      return `${year}-${month}-${day}`;
    },
    groupByDate(locations) {
      const groupedLocations = [];
      const dates = [];

      for (const location of locations) {
        const date = location.date;

        if (!dates.includes(date)) {
          dates.push(date);
          groupedLocations.push([]);
        }

        const index = dates.indexOf(date);
        groupedLocations[index].push(location);
      }

      return groupedLocations;
    },
  },
  watch: {
    currentDay() {
      this.locations = this.list[this.currentDay];
    },
  },
};
</script>
<style scoped>
.board {
  border: 1px solid #ddd;
  padding: 20px;
  background-color: #f9f9f9;
}

.board-header {
  margin-bottom: 20px;
}

.board-title {
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 10px;
}

.board-meta {
  display: flex;
  align-items: center;
}

.board-meta-item {
  display: flex;
  align-items: center;
  margin-right: 20px;
  font-size: 14px;
}

.meta-label {
  margin-right: 5px;
  font-weight: bold;
}

.content {
  text-align: initial;
}

div > img {
  width: 300px;
}

.text-left {
  text-align: left;
}

.text-center {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text-gray {
  color: gray;
}

.spacer {
  flex-grow: 1;
}
#planday {
  display: inline-block;
  padding: 5px;
}

div {
  overflow-x: hidden; /* 가로 스크롤 숨김 */
  white-space: initial;
}

.day {
  margin-top: 10px;
  text-align: center;
  font-weight: bold;
}

.sidebar {
  position: fixed;
  top: 0;
  right: -500px; /* 슬라이드바가 숨겨진 초기 위치 */
  width: 500px;
  height: 100%;
  background-color: white;
  transition: right 0.3s ease-in-out; /* 슬라이드 애니메이션 속성 */
  box-shadow: -10px 0 10px rgba(0, 0, 0, 0.1); /* 왼쪽에 은은한 선으로 경계를 표시 */
}
.sidebar2 {
  position: fixed;
  top: 0;
  right: -2000px; /* 슬라이드바가 숨겨진 초기 위치 */
  width: 1000px;
  height: 100%;
  background-color: white;
  transition: right 0.5s ease-in-out; /* 슬라이드 애니메이션 속성 */
  box-shadow: -10px 0 10px rgba(0, 0, 0, 0.1); /* 왼쪽에 은은한 선으로 경계를 표시 */
  transform: translateX(-50%); /* 중앙에 위치하도록 이동 */
}

.sidebar-open {
  right: 0; /* 슬라이드바가 열린 위치 */
}

.details-sidebar {
  right: -100%; /* 상세 슬라이드바가 숨겨진 초기 위치 */
}

.sidebar-content {
  padding: 20px;
  max-height: 80vh; /* 스크롤 가능한 컨테이너의 최대 높이 설정 */
  overflow-y: auto; /* 세로 스크롤 활성화 */
}

button {
  margin: 20px;
}

.card-location {
  width: 100%; /* card-location 컴포넌트의 가로 크기 조정 */
  max-width: 600px; /* 원하는 최대 가로 크기 설정 */
}

.container {
  width: 300px; /* 컨테이너의 너비를 원하는 크기로 설정 */
}

.search-container {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 10px;
}

.item-container {
  display: flex;
  flex-wrap: wrap;
}

.hashtag {
  display: inline-block;
  padding: 5px;
  margin: 5px;
  background-color: #e2e2e2;
  border-radius: 5px;
  cursor: pointer;
}

.search-input {
  padding: 10px;
  border: none;
  border-bottom: 1px solid #ccc;
  font-size: 16px;
  width: 300px;
  outline: none;
}

/* 마우스 오버시 아래 보더 색상 변경 */
.search-input:hover {
  border-bottom-color: #999;
}

.list-item {
  background-color: white;
  color: #000000;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 10px;
  display: flex;
  justify-content: space-between;
  /* align-items: center;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis; */
}
.draggable {
  background-color: #f0f0f0; /* 아이보리 색상 */
}

.index-circle {
  display: inline-block;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  background-color: #ff0000;
  color: #ffffff;
  text-align: center;
  line-height: 24px;
  margin-right: 5px;
}

.item-title {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  margin-left: 10px; /* Add some margin-left for spacing */
}

.delete-button {
  text-align: right;
  margin-left: 10px; /* Adjust the spacing between the item title and delete button */
}

.draggable-container {
  max-height: 200px; /* 필요에 따라 높이 조정 */
  overflow-y: auto; /* 세로 스크롤 활성화 */
  overflow-x: hidden; /* 가로 스크롤 숨김 */
}

.rounded {
  border-radius: 0.8rem;
  cursor: pointer;
}
</style>
