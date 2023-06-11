<template>
  <div id="planday">
    <b-card
      bg-variant="light"
      :header="` ${day}일차`"
      @click="foucsDay"
      class="text-center rounded"
    >
      <!-- <button @click="deleteDayList">X</button> -->
      <b-card-text>
        <draggable v-model="items" @end="onDragEnd" class="draggable-container">
          <div v-for="(item, idx) in items" :key="idx" class="list-item">
            <strong>
              <span class="index-circle">{{ idx + 1 }}</span>
              <span class="item-title">{{ item.title }}</span>
            </strong>
            <b-icon
              icon="x-circle"
              @click="deleteListItem(item)"
              class="delete-button"
            ></b-icon>
          </div>
        </draggable>
        <b-button @click="toggleSidebar">여행지 추가</b-button>
      </b-card-text>
    </b-card>

    <!-- 첫 번째 슬라이드바 -->
    <div
      :class="['sidebar', { 'sidebar-open': isSidebarOpen }]"
      style="z-index: 9999"
    >
      <b-button class="close-button" @click="closeSidebar">닫기</b-button>
      <div class="sidebar-content">
        <h2>계획한여행지~</h2>

        <div class="search-container">
          <input
            type="text"
            v-model="searchKeyword"
            @keyup.enter="handleEnter"
            placeholder="가고싶은 여행지를 입력해봐용"
            class="search-input"
          />
        </div>

        <div>
          <span
            v-for="(item, ii) in selectItem"
            :key="ii"
            class="hashtag"
            @click="deleteSelectItem(item)"
          >
            {{ item.title }} x
          </span>
        </div>

        <hr />
        <div>
          <b-button class="close-button" @click="addList">
            선택항목 적용
          </b-button>
        </div>

        <hr />

        <div v-if="locations">
          <card-location
            v-for="(location, index) in locations"
            :key="index + 'l'"
            :location="location"
            @selectLocationDetail="toggleDetailsSidebar"
            @selectLocation="selectLocation"
            v-show="
              !selectItem.some((item) => item.contentId === location.contentId)
            "
          ></card-location>
        </div>
      </div>
    </div>

    <!-- 두 번째 슬라이드바 -->
    <div
      :class="['sidebar2', { 'sidebar-open': isDetailsSidebarOpen }]"
      style="z-index: 9998"
    >
      <button class="close-button" @click="closeDetailsSidebar">닫기</button>
      <div class="sidebar-content">
        <location-info
          v-if="isDetailsSidebarOpen"
          :locationId="detailContentId"
        ></location-info>
      </div>
    </div>
  </div>
</template>
<script>
  import draggable from "vuedraggable";
  import CardLocation from "@/components/cards/CardLocation2.vue";
  import LocationInfo from "@/components/info/locationInfo.vue";
  import http from "@/api/http";
  export default {
    name: "PlanDay",
    props: {
      day: {
        type: Number,
        required: true,
      },
      locationName: String,
    },
    components: {
      draggable,
      CardLocation,
      LocationInfo,
    },
    data() {
      return {
        items: [],
        selectItem: [],
        detailContentId: 0,
        locations: [],
        isSidebarOpen: false, // 첫 번째 슬라이드바 열림 여부를 관리하는 데이터
        isDetailsSidebarOpen: false, // 두 번째 슬라이드바 열림 여부를 관리하는 데이터
        searchKeyword: "",
      };
    },
    mounted() {},
    methods: {
      onDragEnd() {
        console.log("드래그앤 드랍 종료");
        this.$emit("changeList", this.items, this.day - 1);
      },
      toggleSidebar() {
        this.items.forEach((item) => {
          this.selectItem.push(item);
        });
        this.isSidebarOpen = !this.isSidebarOpen; // 첫 번째 슬라이드바 열림 여부를 토글
      },
      toggleDetailsSidebar(contentId) {
        console.log("2번째 슬라이더 true 처리");
        this.isDetailsSidebarOpen = !this.isDetailsSidebarOpen; // 첫 번째 슬라이드바 열림 여부를 토글
        this.detailContentId = contentId;
      },
      closeSidebar() {
        this.selectItem = [];
        this.detailContentId = 0;
        this.locations = [];
        this.isSidebarOpen = false;
        this.isDetailsSidebarOpen = false;
        this.searchKeyword = "";
      },
      closeDetailsSidebar() {
        this.isDetailsSidebarOpen = false; // 두 번째 슬라이드바를 닫음
      },
      handleEnter() {
        let tmp = -1;
        http
          .get(
            `/plan/location/list/${this.locationName}/${this.searchKeyword}/${tmp}`
          )
          .then((req) => {
            this.locations = req.data;
          })
          .catch((err) => {
            console.error(err);
          });
      },
      selectLocation(content) {
        if (this.selectItem.length < 10) this.selectItem.push(content);
        else alert("목록은 최대 10개 까지 입력 가능합니다.");
      },
      deleteSelectItem(item) {
        const findIndex = this.selectItem.findIndex(
          (selectitem) => selectitem === item
        );

        // 10보다 큰 요소의 위치를 찾은 경우 해당 요소를 제거합니다.
        if (findIndex > -1) {
          this.selectItem.splice(findIndex, 1);
        }
      },
      addList() {
        this.items = this.selectItem;
        this.$emit("changeList", this.items, this.day - 1);
        this.closeSidebar();
      },

      foucsDay() {
        this.$emit("foucsDay", this.day - 1);
      },

      deleteListItem(item) {
        this.items = this.items.filter((selectItem) => selectItem !== item);
        this.$emit("deleteListItem", this.items, this.day - 1);
      },
      deleteDayList() {
        this.$emit("deleteDayList", this.day - 1);
      },
    },
    watch: {
      locationName() {
        this.items = [];
      },
    },
  };
</script>
<style scoped>
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
    right: -450px; /* 슬라이드바가 숨겨진 초기 위치 */
    width: 450px;
    height: 100%;
    background-color: white;
    transition: right 0.3s ease-in-out; /* 슬라이드 애니메이션 속성 */
    box-shadow: -10px 0 10px rgba(0, 0, 0, 0.1); /* 왼쪽에 은은한 선으로 경계를 표시 */
  }
  .sidebar2 {
    position: fixed;
    top: 0;
    right: -1800px; /* 슬라이드바가 숨겨진 초기 위치 */
    width: 900px;
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
