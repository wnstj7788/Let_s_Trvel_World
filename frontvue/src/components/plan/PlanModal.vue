<template>
  <div id="modal">
    <b-modal
      id="planModal"
      ref="modal"
      title="여행 정보 입력"
      @ok="handleOk"
      no-close-on-backdrop
      no-close-on-esc
      hide-close
      hide-header-close
      ><div class="container">
        <div class="col-6">
          <div class="form-group">
            <b-datepicker
              v-model="startDate"
              :min="new Date()"
              :date-format-options="{
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
              }"
              block
              wrapper-class="datepicker-wrapper"
              input-class="datepicker-input"
              popover-class="datepicker-popover"
            />
          </div>
        </div>
        <div class="col-6">
          <div class="form-group">
            <div class="datepicker">
              <b-datepicker
                v-model="endDate"
                :min="startDate"
                calendar-width="400px"
                :date-format-options="{
                  year: 'numeric',
                  month: '2-digit',
                  day: '2-digit',
                }"
                :height="`50px`"
                wrapper-class="datepicker-wrapper"
                input-class="datepicker-input"
                popover-class="datepicker-popover"
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

      <!-- 하단의 버튼을 footer 슬롯으로 이동 -->
      <template #modal-footer>
        <div id="footer">
          <b-button variant="primary" @click="handleOk">확인</b-button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        location: "서울",
        startDate: this.formatDate(new Date()),
        endDate: this.formatDate(new Date()),
      };
    },
    props: {
      locationList: Array,
    },
    mounted() {
      this.$bvModal.show("planModal");

      // 임시 리스트
    },
    methods: {
      formatDate(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0");
        const day = String(date.getDate()).padStart(2, "0");

        return `${year}-${month}-${day}`;
      },
      handleOk() {
        const data = {
          location: this.location,
          startDate: this.startDate,
          endDate: this.endDate,
        };
        this.$emit("getdata", data);
        this.$bvModal.hide("planModal");
      },
    },
    watch: {
      startDate() {
        if (this.endDate < this.startDate) this.endDate = this.startDate;
      },
    },
  };
</script>

<style scoped>
  span {
    text-align: center;
  }
  #modal {
    width: 70%;
  }
  .datepicker-wrapper {
    width: 100%;
    max-width: unset;
  }

  .datepicker-input {
    height: auto;
    padding: 0.375rem 0.75rem;
  }

  .datepicker-popover {
    max-width: 100%;
  }

  #footer {
    display: flex;
    justify-content: flex-end;
    padding-top: 1rem;
  }

  .b-calendar-nav {
    justify-content: space-around;
  }

  .b-calendar * {
    text-align: center !important;
  }
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

  .btn {
    text-align: center;
  }
</style>
