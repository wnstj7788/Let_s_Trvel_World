  <template>
    <div>
      <div id="titleArea">
        <h2>{{ locationInfo.title }}</h2>
        <p>{{ locationInfo.sidoName }} | {{ locationInfo.gugunName }}</p>
        <p>조회수 : {{ locationInfo.readcount }}</p>
      </div>
      <hr />
      <b-container fluid class="p-4">
        <b-row>
          <b-col class="text-center">
            <b-img
              thumbnail
              fluid
              :src="
                locationInfo.firstImage
                  ? locationInfo.firstImage
                  : require('@/assets/no_image.png')
              "
              alt="Image 2"
            ></b-img>
          </b-col>
        </b-row>
      </b-container>
      <hr />
      <div>
        <p><strong>간략소개</strong></p>
        <p>
          {{ locationInfo.content }}
        </p>
        <p><strong>주소</strong></p>
        <p>{{ locationInfo.addr }}</p>
      </div>
      <hr />
      <div id="mapDiv">
        <h4>지도</h4>
        <map-view
          :location="{ latitude: locationInfo.lat, longitude: locationInfo.lng }"
          :mapId="`locationInfo${locationInfo.locationId}`"
        ></map-view>
      </div>
    </div>
  </template>

  <script>
    import http from "@/api/http";
    import MapView from "@/components/MapView.vue";
    export default {
      name: "locationInfo",
      components: { MapView },
      props: ["locationId"],
      data() {
        return {
          locationInfo: {},
        };
      },
      mounted() {
        if (this.locationId) this.getLocation();
      },
      methods: {
        getLocation() {
          http
            .get(`/plan/location/${this.locationId}`)
            .then((req) => {
              this.locationInfo = req.data;
            })
            .catch((err) => {
              console.error(err);
            });
        },
      },
      watch: {
        locationId() {
          this.getLocation();
        },
      },
    };
  </script>

  <style scoped>
    div {
      text-align: left;
    }
    p {
      margin-bottom: 8px;
    }
    #mapDiv {
      margin-bottom: 20px;
    }
    #titleArea {
      margin-top: 15px;
    }
  </style>
