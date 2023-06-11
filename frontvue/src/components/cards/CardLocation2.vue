<template>
  <div class="card-container">
    <div class="card-image">
      <img :src="location.firstImage" alt="Card Image" />
    </div>
    <div class="card-info">
      <h3 class="card-title">{{ location.title }}</h3>
      <p class="card-address">{{ location.addr }}</p>
      <p class="card-number">{{ location.tel }}</p>
    </div>
    <b-row>
      <b-col cols="6">
        <b-button @click="addLocation" class="horizontal-button small-text">추가</b-button>
      </b-col>
      <b-col cols="6">
        <b-button @click="DetailClick()" class="horizontal-button small-text">상세보기</b-button>
      </b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  name: "CardLocation2",
  props: ["location"],
  components: {},
  data() {
    return {
      message: "",
    };
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

.card-container {
  display: flex;
  align-items: flex-start;
  width: 100%;
}

.card-image {
  flex: 30%;
}

.card-image img {
  width: 100%;
  max-width: 100%;
  height: auto;
}

.card-info {
  flex: 70%;
  padding: 10px;
}

.card-title {
  font-size: 18px;
  font-weight: bold;
}

.card-address,
.card-number {
  font-size: 14px;
  margin-bottom: 5px;
}
</style>
