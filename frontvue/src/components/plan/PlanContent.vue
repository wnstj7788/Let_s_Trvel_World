<template>
  <div>
    <div class="container">
      <div class="col-6">
        <div class="form-group">
          <div class="datepicker">
            <b-datepicker
              v-model="startDate"
              :min="new Date()"
              :date-format-options="{
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
              }"
              @input="changeDay"
              class="form-control custom-input"
            />
          </div>
        </div>
      </div>
      <div class="col-6">
        <div class="form-group">
          <div class="datepicker">
            <b-datepicker
              v-model="endDate"
              :min="startDate"
              :date-format-options="{
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
              }"
              @input="changeDay"
              class="form-control custom-input"
            />
          </div>
        </div>
      </div>
    </div>

    <div class="form-group">
      <select v-model="location" class="form-control custom-input">
        <option
          v-for="location in locationList"
          :key="location.name"
          :value="location.name"
        >
          {{ location.name }}
        </option>
      </select>
    </div>

    <map-view
      v-if="closeModal"
      :locations="locations"
      :mapId="`planContent`"
    ></map-view>
  </div>
</template>

<script>
  import MapView from "../MapView.vue";
  import "bootstrap/dist/css/bootstrap.css";
  import "pc-bootstrap4-datetimepicker/build/css/bootstrap-datetimepicker.css";

  export default {
    name: "PlanContent",
    components: { MapView },
    props: {
      closeModal: Boolean,
      planInfo: Object,
      locationList: Array,
      list: Array,
      selectedDay: Number,
      renderKey: Number,
    },
    data() {
      return {
        title: "",
        location: "",
        startDate: "",
        endDate: "",
        locations: [],
      };
    },
    mounted() {
      this.location = this.planInfo.location;
      this.startDate = this.planInfo.startDate;
      this.endDate = this.planInfo.endDate;
    },
    methods: {
      formatDate(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0");
        const day = String(date.getDate()).padStart(2, "0");

        return `${year}-${month}-${day}`;
      },
      changeDay() {
        if (typeof this.startDate == "object")
          this.startDate = this.formatDate(this.startDate);
        if (typeof this.endDate == "object")
          this.endDate = this.formatDate(this.endDate);
        if (this.endDate < this.startDate) this.endDate = this.startDate;
        this.$emit("changeDay", {
          startDate: this.startDate,
          endDate: this.endDate,
          location: this.location,
        });
      },
    },
    watch: {
      planInfo() {
        this.location = this.planInfo.location;
        this.startDate = this.planInfo.startDate;
        this.endDate = this.planInfo.endDate;
      },
      location() {
        this.$emit("changeLocation", this.location);
      },
      list() {
        this.locations = this.list[this.selectedDay];
      },
      selectedDay() {
        this.locations = this.list[this.selectedDay];
      },
      changeList() {
        this.locations = this.list[this.selectedDay];
      },
      renderKey() {
        this.locations = this.list[this.selectedDay];
      },
    },
  };
</script>

<style scoped>
  .container {
    display: flex;
    padding: 0px;
  }

  .col-6 {
    padding: 0px;
  }
  .custom-datepicker {
    width: 200px; /* 원하는 크기로 조절 */
    height: 32px; /* 원하는 크기로 조절 */
    font-size: 14px; /* 원하는 크기로 조절 */
  }
  .form-group {
    padding: 0px;
  }
</style>
