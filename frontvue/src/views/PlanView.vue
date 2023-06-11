<template>
  <div id="container">
    <plan-modal :locationList="locationList" @getdata="getdata"></plan-modal>
    <div class="input-container">
      <label for="title" class="title">계획명:</label>
      <b-form-input
        id="title"
        v-model="title"
        placeholder="ex)규돈이와 떠나는 즐거운 부산 바캉스"
        required
        class="custom-input"
      ></b-form-input>
    </div>
    <br />

    <plan-content
      :key="renderKey"
      :planInfo="planInfo"
      :locationList="locationList"
      :list="list"
      :selectedDay="selectedDay"
      @changeDay="changeDay"
      :closeModal="closeModal"
      @changeLocation="changeLocation"
    ></plan-content>
    <plan-list
      @foucsDay="foucsDay"
      @changeList="changeList"
      @deleteListItem="deleteListItem"
      @deleteDayList="deleteDayList"
      :days="list.length"
      :locationName="locationName"
    ></plan-list>

    <b-button @click="addPlan">계획 추가 하기</b-button>
  </div>
</template>

<script>
  import PlanModal from "@/components/plan/PlanModal.vue";
  import PlanContent from "@/components/plan/PlanContent.vue";
  import PlanList from "@/components/plan/PlanList.vue";
  import http from "@/api/http";
  import jwt from "@/api/jwt";

  export default {
    name: "PlanView",
    components: { PlanContent, PlanList, PlanModal },
    data() {
      return {
        renderKey: 0,
        planInfo: {},
        locationName: "",
        list: [],
        locationList: [],
        selectedDay: 0,
        closeModal: false,
        title: "",
      };
    },
    mounted() {
      this.login();
      http
        .get("plan/location")
        .then((req) => {
          this.locationList = req.data;
        })
        .catch((err) => {
          console.error(err);
        });
    },
    methods: {
      login() {
        if (sessionStorage.getItem("token") === null) {
          // 등호 변경
          alert("로그인 후 사용이 가능한 페이지입니다.");
          this.$router.push({ path: "/main" }); // 로그인 페이지로 리디렉션
        }
      },

      foucsDay(day) {
        this.selectedDay = day;
      },
      changeLocation(locationName) {
        this.planInfo.location = locationName;
        this.locationName = locationName;
        let size = this.list.length;
        console.log(size);
        this.list = [];
        for (let k = 0; k < size; k++) {
          this.list.push([]);
        }
      },
      changeDay(content) {
        this.getdata(content);
      },
      changeList(items, day) {
        this.list.splice(day, 1, items);
        this.list = [...this.list];
      },
      getdata(datas) {
        this.closeModal = true;
        const timeDiff = Math.abs(
          new Date(datas.endDate).getTime() -
            new Date(datas.startDate).getTime()
        );
        const dayDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
        for (let i = this.list.length; i < dayDiff + 1; i++) {
          this.list.push([]);
        }
        if (this.list.length > dayDiff + 1) this.list.splice(dayDiff + 1);
        this.planInfo = {
          startDate: new Date(datas.startDate),
          endDate: new Date(datas.endDate),
        };
        this.planInfo.location = datas.location;
        this.selectDay = 0;
      },
      addPlan() {
        if (!this.title) {
          alert("계획명은 필수 입력 항목입니다.");
          return; // 처리 중단
        }
        const day = [];
        for (let k = 0; k < this.list.length; k++) {
          day.push({ places: this.list[k] });
        }

        const plan = {
          startAt: new Date(this.planInfo.startDate),
          endAt: new Date(this.planInfo.endDate),
          regionName: this.planInfo.location,
          plans: day,
          title: this.title,
        };
        jwt
          .post("/plan/insert", plan)
          .then((req) => {
            // 결과 (문자열로 success fail로 받음)
            console.log(req.data);
            if (req.data == "success") {
              alert("글 작성 완료");
              this.$router.push({ path: "/" });
            }
          })
          .catch((err) => {
            console.error(err);
          });
      },
      deleteListItem(item, day) {
        this.list[day] = [...item];
        this.list = [...this.list];
      },
      deleteDayList(day) {
        if (this.list.length > 1) {
          this.list.splice(day, 1);
          this.list = [...this.list];
          this.planInfo = { ...this.planInfo };
          this.planInfo.endDate.setDate(this.planInfo.endDate.getDate() - 1);
        } else alert("1일 이상의 계획만 작성할 수 있습니다.");
      },
    },
  };
</script>

<style scoped>
  .input-container {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
  }

  .title {
    font-size: 16px;
    font-weight: bold;
    margin-right: 10px;
    width: 15%;
    margin-top: 30px;
  }
  #title {
    font-size: 16px;
    margin-top: 25px;
  }

  .custom-input {
    border-radius: 10px;
    background-color: #f7e6db; /* 따뜻하고 부드러운 색상 설정 */
    padding: 10px;
    font-size: 14px;
    color: #333;
  }

  .custom-input:focus {
    outline: none;
    box-shadow: 0 0 3px rgba(255, 165, 0, 0.5); /* 포커스 시에 주황색 그림자 효과 추가 */
  }
</style>
