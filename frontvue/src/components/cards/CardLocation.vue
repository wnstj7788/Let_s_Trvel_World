<template>
  <div>
    <b-card no-body class="overflow-hidden" style="max-width: 400px">
      <b-row no-gutters>
        <b-col md="6">
          <b-card-img
            :src="location.firstImage"
            alt="Image"
            class="rounded-0"
            style="width: 200px; height: 250px"
          ></b-card-img>
        </b-col>

        <b-col md="6" style="max-width: 200px">
          <br />
          <br />

          <b-card-body :title="location.title" class="card-body">
            <b-card-text>주소: {{ location.addr }}</b-card-text>
            <b-row>
              <b-col cols="6">
                <b-button @click="addLocation" class="horizontal-button small-text">추가</b-button>
              </b-col>
              <b-col cols="6">
                <b-button @click="DetailClick()" class="horizontal-button small-text"
                  >상세보기</b-button
                >
              </b-col>
            </b-row>
          </b-card-body>
        </b-col>
      </b-row>
    </b-card>
  </div>
</template>

<script>
export default {
  name: "CardLocation",
  props: ["location"],
  components: {},
  data() {
    return {};
  },
  created() {},
  methods: {
    DetailClick() {
      // 클릭한 카드에 대한 처리 로직을 구현합니다.
      this.$emit("selectLocationDetail", this.location.contentId);
    },
    addLocation() {
      this.$emit("selectLocation", this.location);
    },
  },
};
</script>

<style scoped>
div {
  display: inline-block;
  padding: 5px;
  cursor: pointer;
}

.card-location {
  display: inline-block;
  padding: 5px;
  cursor: pointer;
  width: 100%;
  max-width: 700px; /* 원하는 최대 너비 설정 */
}

.small-text {
  font-size: 12px; /* 원하는 작은 글자 크기 설정 */
}
</style>
