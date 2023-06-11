<template>
  <div class="map_wrap">
    <div :id="mapId" class="map"></div>
    <div class="custom_zoomcontrol radius_border">
      <span @click="zoomIn">
        <img
          src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png"
          alt="확대"
        />
      </span>
      <span @click="zoomOut">
        <img
          src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png"
          alt="축소"
        />
      </span>
    </div>
  </div>
</template>

<script>
export default {
  name: "MapView",
  props: {
    locations: Array,
    location: Object,
    mapId: String,
  },
  data() {
    return {
      map: undefined,
      markers: [], // 마커 객체를 저장할 배열
      lines: [],
      selectedDay: 0,
    };
  },
  mounted() {
    if (!this.map) this.initMap();
  },
  methods: {
    initMap() {
      const container = document.getElementById(this.mapId);
      let center = new kakao.maps.LatLng(37.4979, 127.0276);
      const options = {
        // 초기 값 설정(props로 받은 좌표)
        center: center,
        level: 8,
      };
      this.map = new kakao.maps.Map(container, options);
      if (this.map) this.createMarkers(this.locations);
    },
    createMarkerOne(location, idx) {
      if (this.locations) {
        var imageSrc =
            "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
          imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
          imgOptions = {
            spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
          },
          markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions);
      }
      const markerPosition = new kakao.maps.LatLng(location.latitude, location.longitude);
      const markerOptions = {
        position: markerPosition,
        image: markerImage,
      };
      const marker = new kakao.maps.Marker(markerOptions);
      this.map.setCenter(new kakao.maps.LatLng(location.latitude, location.longitude));
      marker.setMap(this.map);
      return marker;
    },
    createMarkers(locations) {
      if (!locations || locations.length == 0) return;
      let idx = 0;
      locations.forEach((location) => {
        const marker = this.createMarkerOne(location, idx++);
        this.createCustomMarkers(location, marker);
        this.markers.push(marker);
      });
      this.map.setCenter(new kakao.maps.LatLng(locations[0].latitude, locations[0].longitude));
      // 선 그리기
      if (locations.length < 2) return;
      const linePath = locations.map(
        (location) => new kakao.maps.LatLng(location.latitude, location.longitude)
      );

      const polyline = new kakao.maps.Polyline({
        path: linePath,
        strokeWeight: 4,
        strokeColor: "#FF0000",
        strokeOpacity: 0.7,
        strokeStyle: "solid",
      });

      polyline.setMap(this.map);
      this.lines.push(polyline);
    },
    clearMarkers() {
      this.markers.forEach((marker) => marker.setMap(null));
      this.lines.forEach((line) => line.setMap(null));
      this.markers = [];
      this.lines = [];
    },
    createCustomMarkers(location, marker) {
      var content =
        '<div class="customoverlay">' +
        `    <span class="title">${location.title}</span>` +
        "</div>";
      // 커스텀 오버레이가 표시될 위치입니다
      var position = new kakao.maps.LatLng(location.latitude, location.longitude);

      // 커스텀 오버레이를 생성합니다
      var mapCustomOverlay = new kakao.maps.CustomOverlay({
        position: position,
        content: content,
        xAnchor: 0.5, // 커스텀 오버레이의 x축 위치입니다. 1에 가까울수록 왼쪽에 위치합니다. 기본값은 0.5 입니다
        yAnchor: 1.1, // 커스텀 오버레이의 y축 위치입니다. 1에 가까울수록 위쪽에 위치합니다. 기본값은 0.5 입니다
        zIndex: 3,
      });

      // 커스텀 오버레이를 지도에 표시합니다
      kakao.maps.event.addListener(marker, "mouseover", () => {
        mapCustomOverlay.setMap(this.map);
      });
      kakao.maps.event.addListener(marker, "mouseout", () => {
        mapCustomOverlay.setMap(null);
      });
    },
    zoomIn() {
      this.map.setLevel(this.map.getLevel() - 1);
    },
    zoomOut() {
      this.map.setLevel(this.map.getLevel() + 1);
    },
  },
  watch: {
    locations() {
      if (!this.map) {
        this.initMap();
      }
      this.clearMarkers();
      this.createMarkers(this.locations);
    },
    location() {
      if (!this.map) this.initMap();
      this.createMarkerOne(this.location);
    },
  },
};
</script>

<style scoped>
.map_wrap {
  position: relative;
  overflow: hidden;
  width: 100%;
  height: 350px;
}
.radius_border {
  border: 1px solid #919191;
  border-radius: 5px;
}

.custom_zoomcontrol {
  position: absolute;
  top: 50px;
  right: 10px;
  width: 36px;
  height: 80px;
  overflow: hidden;
  z-index: 1;
  background-color: #f5f5f5;
}
.custom_zoomcontrol span {
  display: block;
  width: 36px;
  height: 40px;
  text-align: center;
  cursor: pointer;
}
.custom_zoomcontrol span img {
  width: 15px;
  height: 15px;
  margin-top: 12px;
  border: none;
}
.custom_zoomcontrol span:first-child {
  border-bottom: 1px solid #bfbfbf;
}

.customoverlay {
  position: relative;
  bottom: 85px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.map {
  width: 100%;
  height: 400px;
}
</style>
