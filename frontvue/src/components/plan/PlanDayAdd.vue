<template>
  <div>
    <b-button v-b-toggle.sidebar-right>여행지 추가 </b-button>
    <b-sidebar id="sidebar-right" title="여행지 추가" right shadow>
      <div class="input-group">
        <input class="form-control" type="text" v-model="searchKeyword" placeholder="입력하세요" />
        <div class="input-group-append">
          <button class="btn btn-primary" @click="search">검색</button>
        </div>
      </div>
      <div class="px-6 py-4">
        <p>
          Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis
          in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
        </p>
        <b-img src="https://picsum.photos/500/500/?image=54" fluid thumbnail></b-img>
      </div>
    </b-sidebar>
  </div>
</template>

<script>
export default {
  name: "PlanDayAdd",
  components: {},
  data() {
    return {
      searchKeyword: "",
    };
  },
  created() {},
  methods: {
    search() {},
  },
};
</script>
