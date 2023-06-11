<template>
  <div v-if="keyword">
    <b-tabs content-class="mt-3" v-model="activeTab" lazy fill>
      <b-tab
        v-for="(route, index) in routes"
        :key="index"
        :title="route.name"
        :active="activeTab === index"
      >
        <component
          :is="getComponentName(route.path)"
          :keyword="keyword"
          v-if="activeTab === index"
        ></component>
      </b-tab>
    </b-tabs>
  </div>
</template>

<script>
import LocationList from "@/components/list/locationList.vue";
import JournalList from "@/components/list/journalList.vue";
import PlanList from "@/components/list/planList.vue";

export default {
  name: "SearchContent",
  props: {
    keyword: String,
  },
  components: {
    LocationList,
    JournalList,
    PlanList,
  },
  data() {
    return {
      activeTab: 0,
      routes: [
        {
          path: "/search/list/location/",
          name: "여행 장소",
          component: LocationList,
        },
        {
          path: "/search/list/plan/",
          name: "여행 일정",
          component: PlanList,
        },
        {
          path: "/search/list/journal/",
          name: "여행 후기",
          component: JournalList,
        },
      ],
    };
  },
  methods: {
    getComponentName(path) {
      const route = this.routes.find((route) => route.path === path);
      return route ? route.component : null;
    },
  },
  watch: {
    keyword() {
      this.activeTab = 0;
    },
  },
};
</script>
