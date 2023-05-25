<template>
  <div>
    <div id="map"></div>
    <!-- <div class="button-group">
      <button @click="changeSize(0)">Hide</button>
        <button @click="changeSize(800)">show</button>
        <button @click="displayMarker(markerPositions1)">marker set 1</button>
        <button @click="displayMarker(markerPositions2)">marker set 2</button>
        <button @click="displayMarker([])">marker set 3 (empty)</button>
        <button @click="displayInfoWindow">infowindow</button>
    </div> -->
  </div>
</template>
  
<script>
import noImg from "@/assets/img/noImg.png"

export default {
  name: "kakaoMap",
  data() {
    return {
      map: null,
      markerPositions1: [
        [33.452278, 126.567803],
        [33.452671, 126.574792],
        [33.451744, 126.572441],
      ],
      markerPositions2: [
        [37.499590490909185, 127.0263723554437],
        [37.499427948430814, 127.02794423197847],
        [37.498553760499505, 127.02882598822454],
        [37.497625593121384, 127.02935713582038],
        [37.49629291770947, 127.02587362608637],
        [37.49754540521486, 127.02546694890695],
        [37.49646391248451, 127.02675574250912],
      ],
      markers: [],
      infowindow: null,
      customOverlay: null,
      drawingFlag: false, // 선이 그려지고 있는 상태를 가지고 있을 변수입니다
      moveLine: null, // 선이 그려지고 있을때 마우스 움직임에 따라 그려질 선 객체 입니다
      clickLine: null, // 마우스로 클릭한 좌표로 그려질 선 객체입니다
      distanceOverlay: null, // 선의 거리정보를 표시할 커스텀오버레이 입니다
      dots: {}, // 선이 그려지고 있을때 클릭할 때마다 클릭 지점과 거리를 표시하는 커스텀 오버레이 배열입니다.
      clickLines: [],
      lines:[],

    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */


      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8ce68fe8da91c9f36d81376b8131b49e&libraries=services,clusterer,drawing";
      script.onload = () => kakao.maps.load(this.initMap);

      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.500613, 127.036431),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      // kakao.maps.event.addListener(this.map, 'click', (mouseEvent) => {
      //   // 마우스로 클릭한 위치입니다 
      //   var clickPosition = mouseEvent.latLng;

      //   // 지도 클릭이벤트가 발생했는데 선을 그리고있는 상태가 아니면
      //   if (!this.drawingFlag) {

      //     // 상태를 true로, 선이 그리고있는 상태로 변경합니다
      //     this.drawingFlag = true;

      //     // 지도 위에 선이 표시되고 있다면 지도에서 제거합니다
      //     this.deleteClickLine();

      //     // 지도 위에 커스텀오버레이가 표시되고 있다면 지도에서 제거합니다
      //     this.deleteDistnce();

      //     // 지도 위에 선을 그리기 위해 클릭한 지점과 해당 지점의 거리정보가 표시되고 있다면 지도에서 제거합니다
      //     this.deleteCircleDot();

      //     // 클릭한 위치를 기준으로 선을 생성하고 지도위에 표시합니다
      //     this.clickLine = new kakao.maps.Polyline({
      //       map: this.map, // 선을 표시할 지도입니다 
      //       path: [clickPosition], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
      //       strokeWeight: 3, // 선의 두께입니다 
      //       strokeColor: '#db4040', // 선의 색깔입니다
      //       strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
      //       strokeStyle: 'solid' // 선의 스타일입니다
      //     });

      //     // 선이 그려지고 있을 때 마우스 움직임에 따라 선이 그려질 위치를 표시할 선을 생성합니다
      //     this.moveLine = new kakao.maps.Polyline({
      //       strokeWeight: 3, // 선의 두께입니다 
      //       strokeColor: '#db4040', // 선의 색깔입니다
      //       strokeOpacity: 0.5, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
      //       strokeStyle: 'solid' // 선의 스타일입니다    
      //     });

      //     // 클릭한 지점에 대한 정보를 지도에 표시합니다
      //     this.displayCircleDot(clickPosition, 0);


      //   } else { // 선이 그려지고 있는 상태이면

      //     // 그려지고 있는 선의 좌표 배열을 얻어옵니다
      //     var path = this.clickLine.getPath();

      //     // 좌표 배열에 클릭한 위치를 추가합니다
      //     path.push(clickPosition);

      //     // 다시 선에 좌표 배열을 설정하여 클릭 위치까지 선을 그리도록 설정합니다
      //     this.clickLine.setPath(path);

      //     var distance = Math.round(this.clickLine.getLength());
      //     this.displayCircleDot(clickPosition, distance);
      //   }

      //   // 지도에 마우스무브 이벤트를 등록합니다
      //   // 선을 그리고있는 상태에서 마우스무브 이벤트가 발생하면 그려질 선의 위치를 동적으로 보여주도록 합니다
      //   kakao.maps.event.addListener(this.map, 'mousemove', (mouseEvent) => {

      //     // 지도 마우스무브 이벤트가 발생했는데 선을 그리고있는 상태이면
      //     if (this.drawingFlag) {

      //       // 마우스 커서의 현재 위치를 얻어옵니다 
      //       var mousePosition = mouseEvent.latLng;

      //       // 마우스 클릭으로 그려진 선의 좌표 배열을 얻어옵니다
      //       var path = this.clickLine.getPath();

      //       // 마우스 클릭으로 그려진 마지막 좌표와 마우스 커서 위치의 좌표로 선을 표시합니다
      //       var movepath = [path[path.length - 1], mousePosition];
      //       this.moveLine.setPath(movepath);
      //       this.moveLine.setMap(this.map);

      //       var distance = Math.round(this.clickLine.getLength() + this.moveLine.getLength()), // 선의 총 거리를 계산합니다
      //         content = '<div class="dotOverlay distanceInfo">총거리 <span class="number">' + distance + '</span>m</div>'; // 커스텀오버레이에 추가될 내용입니다

      //       // 거리정보를 지도에 표시합니다
      //       this.showDistance(content, mousePosition);
      //     }

      //     // 지도에 마우스 오른쪽 클릭 이벤트를 등록합니다
      //     // 선을 그리고있는 상태에서 마우스 오른쪽 클릭 이벤트가 발생하면 선 그리기를 종료합니다
      //     kakao.maps.event.addListener(this.map, 'rightclick', () => {

      //       // 지도 오른쪽 클릭 이벤트가 발생했는데 선을 그리고있는 상태이면
      //       if (this.drawingFlag) {

      //         // 마우스무브로 그려진 선은 지도에서 제거합니다
      //         this.moveLine.setMap(null);
      //         this.moveLine = null;

      //         // 마우스 클릭으로 그린 선의 좌표 배열을 얻어옵니다
      //         var path = this.clickLine.getPath();

      //         // 선을 구성하는 좌표의 개수가 2개 이상이면
      //         if (path.length > 1) {

      //           // 마지막 클릭 지점에 대한 거리 정보 커스텀 오버레이를 지웁니다
      //           if (this.dots[this.dots.length - 1].distance) {
      //             this.dots[this.dots.length - 1].distance.setMap(null);
      //             this.dots[this.dots.length - 1].distance = null;
      //           }

      //           var distance = Math.round(this.clickLine.getLength()), // 선의 총 거리를 계산합니다
      //             content = this.getTimeHTML(distance); // 커스텀오버레이에 추가될 내용입니다

      //           // 그려진 선의 거리정보를 지도에 표시합니다
      //           this.showDistance(content, path[path.length - 1]);

      //         } else {

      //           // 선을 구성하는 좌표의 개수가 1개 이하이면 
      //           // 지도에 표시되고 있는 선과 정보들을 지도에서 제거합니다.
      //           this.deleteClickLine();
      //           this.deleteCircleDot();
      //           this.deleteDistnce();

      //         }

      //         // 상태를 false로, 그리지 않고 있는 상태로 변경합니다
      //         this.drawingFlag = false;
      //       }
      //     });
      //   });
      // });
    },

    // 클릭으로 그려진 선을 지도에서 제거하는 함수입니다
    deleteClickLine() {
      console.log("deleteClickLine called.............", this.clickLine)
      if (this.clickLine) {
        this.clickLine.setMap(null);
        this.clickLine = null;
      }
    },

    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },

    displayMarker(attrList) {
      this.markers.forEach(marker => {
        marker.setMap(null);
      });
      this.markers = [];

      // 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
      var bounds = new kakao.maps.LatLngBounds();

      for (var i = 0; i < attrList.length; i++) {

        let content = attrList[i];
        let curContentId = attrList[i].contentId;
        console.log(curContentId)
        console.log(attrList[i].latitude)
        console.log(attrList[i].longitude)

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: new kakao.maps.LatLng(attrList[i].latitude, attrList[i].longitude),
          title: attrList[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          // image: normalMarkerImage, // 마커 이미지
          clickable: true
        });
        bounds.extend(marker.getPosition());

        const tmp = this;
        this.markers.push(marker);
        marker.addListener('click', () => {
          console.log(content)
          tmp.drawCustomOverlay(content)
        })
      }
      this.markers.forEach(element => {
        element.setMap(this.map);
      });
      this.map.setBounds(bounds);

    },

    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },

    moveCenter(trip) {
      this.map.setLevel(2);
      this.map.setCenter(new kakao.maps.LatLng(trip.latitude, trip.longitude));
      this.drawCustomOverlay(trip);
    },

    drawCustomOverlay(content) {
      console.log(content);
      if (this.customOverlay != null)
        this.closeOverlay();

      console.log(content.imgPath)
      if (content.imgPath == "")
        content.imgPath = noImg;
      var tmp = `<div class="wrap">
    <div class="info">
      <div class="title">
        ${content.title}
        <div class="close" @click="closeOverlay" title="닫기"></div>
        </div>
        <div class="body">
          <div class="img">
            <img src=${content.imgPath} width="73" height="73"/>
          </div>
        <div class="desc">
          <div class="address1">${content.address1}</div>
          <div class="address2">${content.address2}</div>
        </div>
      </div>
    </div>
  </div>`;

      const container = document.createElement('div');
      container.innerHTML = tmp;

      const closeButton = container.querySelector('.close');
      closeButton.addEventListener('click', this.closeOverlay);

      this.customOverlay = new kakao.maps.CustomOverlay({
        content: container,
        position: new kakao.maps.LatLng(content.latitude, content.longitude),
        yAnchor: 1,
        zIndex: 3
      });

      this.customOverlay.setMap(this.map);
    },


    closeOverlay() {
      console.log("================================================")
      this.customOverlay.setMap(null);
    },

    // 마우스 드래그로 그려지고 있는 선의 총거리 정보를 표시하거
    // 마우스 오른쪽 클릭으로 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 생성하고 지도에 표시하는 함수입니다
    showDistance(content, position) {

      if (this.distanceOverlay) { // 커스텀오버레이가 생성된 상태이면

        // 커스텀 오버레이의 위치와 표시할 내용을 설정합니다
        this.distanceOverlay.setPosition(position);
        this.distanceOverlay.setContent(content);

      } else { // 커스텀 오버레이가 생성되지 않은 상태이면

        // 커스텀 오버레이를 생성하고 지도에 표시합니다
        this.distanceOverlay = new kakao.maps.CustomOverlay({
          map: this.map, // 커스텀오버레이를 표시할 지도입니다
          content: content,  // 커스텀오버레이에 표시할 내용입니다
          position: position, // 커스텀오버레이를 표시할 위치입니다.
          xAnchor: 0,
          yAnchor: 0,
          zIndex: 3
        });
      }
    },

    // 그려지고 있는 선의 총거리 정보와 
    // 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 삭제하는 함수입니다
    deleteDistnce() {
      if (this.distanceOverlay) {
        this.distanceOverlay.setMap(null);
        this.distanceOverlay = null;
      }
    },

    // 선이 그려지고 있는 상태일 때 지도를 클릭하면 호출하여 
    // 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 표출하는 함수입니다
    displayCircleDot(position, distance) {

      // 클릭 지점을 표시할 빨간 동그라미 커스텀오버레이를 생성합니다
      var circleOverlay = new kakao.maps.CustomOverlay({
        content: '<span class="dot"></span>',
        position: position,
        zIndex: 1
      });

      // 지도에 표시합니다
      circleOverlay.setMap(this.map);

      if (distance > 0) {
        // 클릭한 지점까지의 그려진 선의 총 거리를 표시할 커스텀 오버레이를 생성합니다
        var distanceOverlay = new kakao.maps.CustomOverlay({
          content: '<div class="dotOverlay">거리 <span class="number">' + distance + '</span>m</div>',
          position: position,
          yAnchor: 1,
          zIndex: 2
        });

        // 지도에 표시합니다
        distanceOverlay.setMap(this.map);
      }

      // 배열에 추가합니다
      this.dots.push({ circle: circleOverlay, distance: distanceOverlay });
    },

    // 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 지도에서 모두 제거하는 함수입니다
    deleteCircleDot() {
      var i;

      for (i = 0; i < this.dots.length; i++) {
        if (this.dots[i].circle) {
          this.dots[i].circle.setMap(null);
        }

        if (this.dots[i].distance) {
          this.dots[i].distance.setMap(null);
        }
      }

      this.dots = [];
    },

    // 마우스 우클릭 하여 선 그리기가 종료됐을 때 호출하여 
    // 그려진 선의 총거리 정보와 거리에 대한 도보, 자전거 시간을 계산하여
    // HTML Content를 만들어 리턴하는 함수입니다
    getTimeHTML(distance) {

      // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
      var walkkTime = distance / 67 | 0;
      var walkHour = '', walkMin = '';

      // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
      if (walkkTime > 60) {
        walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + '</span>시간 '
      }
      walkMin = '<span class="number">' + walkkTime % 60 + '</span>분'

      // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
      // var bycicleTime = distance / 227 | 0;
      var bycicleTime = distance / 600 | 0;
      var bycicleHour = '', bycicleMin = '';

      // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
      if (bycicleTime > 60) {
        bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + '</span>시간 '
      }
      bycicleMin = '<span class="number">' + bycicleTime % 60 + '</span>분'

      // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
      var content = '<ul class="dotOverlay distanceInfo">';
      content += '    <li>';
      content += '        <span class="label">총거리</span><span class="number">' + distance + '</span>m';
      content += '    </li>';
      content += '    <li>';
      content += '        <span class="label">도보</span>' + walkHour + walkMin;
      content += '    </li>';
      content += '    <li>';
      content += '        <span class="label">자동차</span>' + bycicleHour + bycicleMin;
      content += '    </li>';
      content += '</ul>'

      return content;
    },

    addPoint(selectedMyPlan) {

      // 지도 위에 선이 표시되고 있다면 지도에서 제거합니다
      this.deleteClickLine();

      // 지도 위에 커스텀오버레이가 표시되고 있다면 지도에서 제거합니다
      this.deleteDistnce();

      // 지도 위에 선을 그리기 위해 클릭한 지점과 해당 지점의 거리정보가 표시되고 있다면 지도에서 제거합니다
      this.deleteCircleDot();

      this.clickLine = null;

      console.log("selectedMyPlan:", selectedMyPlan)

      selectedMyPlan.forEach(element => {
        console.log("lng:", element.latitude)
        var latlng = new kakao.maps.LatLng(element.latitude, element.longitude);
        var clickPosition = latlng;

        // 지도 클릭이벤트가 발생했는데 선을 그리고있는 상태가 아니면
        if (!this.clickLine) {


// // 지도 위에 선이 표시되고 있다면 지도에서 제거합니다
//       this.deleteClickLine();

//       // 지도 위에 커스텀오버레이가 표시되고 있다면 지도에서 제거합니다
//       this.deleteDistnce();

//       // 지도 위에 선을 그리기 위해 클릭한 지점과 해당 지점의 거리정보가 표시되고 있다면 지도에서 제거합니다
//       this.deleteCircleDot();

//       this.clickLine = [];

          // 클릭한 위치를 기준으로 선을 생성하고 지도위에 표시합니다
          this.clickLine = new kakao.maps.Polyline({
            map: this.map, // 선을 표시할 지도입니다 
            path: [clickPosition], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
            strokeWeight: 3, // 선의 두께입니다 
            strokeColor: '#db4040', // 선의 색깔입니다
            strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid' // 선의 스타일입니다
          });

          // 선이 그려지고 있을 때 마우스 움직임에 따라 선이 그려질 위치를 표시할 선을 생성합니다
          this.moveLine = new kakao.maps.Polyline({
            strokeWeight: 3, // 선의 두께입니다 
            strokeColor: '#db4040', // 선의 색깔입니다
            strokeOpacity: 0.5, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid' // 선의 스타일입니다    
          });

          // 클릭한 지점에 대한 정보를 지도에 표시합니다
          this.displayCircleDot(clickPosition, 0);


        } else { // 선이 그려지고 있는 상태이면

          // 그려지고 있는 선의 좌표 배열을 얻어옵니다
          var path = this.clickLine.getPath();

          // 좌표 배열에 클릭한 위치를 추가합니다
          path.push(clickPosition);

          // 다시 선에 좌표 배열을 설정하여 클릭 위치까지 선을 그리도록 설정합니다
          this.clickLine.setPath(path);
          console.log("clickLine:", this.clickLine)
          var distance = Math.round(this.clickLine.getLength());
          this.displayCircleDot(clickPosition, distance);
          var content = this.getTimeHTML(distance);
          console.log("showDistance Called.............")
          this.showDistance(content, path[path.length-1]);
        }

      });

    },

  },
}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 700px;
  height: 700px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}

.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: 'Noto Sans KR', sans-serif;
  line-height: 1.5;
}

.wrap * {
  padding: 0;
  margin: 0;
}

.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #6E91E3;
  border-right: 1px solid #6E91E3;
  overflow: hidden;
  background: #fff;
}

.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}

.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #6E91E3;
  border-bottom: 1px solid #6E91E3;
  font-size: 18px;
  font-weight: bold;
}

.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');
}

.info .close:hover {
  cursor: pointer;
}

.info .body {
  position: relative;
  overflow: hidden;
}

.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}

.desc .address1 {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.desc .address2 {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}

.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #6E91E3;
  color: #888;
  overflow: hidden;
}

.info:after {
  content: '';
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')
}

.info .link {
  color: #5085BB;
}

.dot {
  overflow: hidden;
  float: left;
  width: 12px;
  height: 12px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/mini_circle.png');
}

.dotOverlay {
  position: relative;
  bottom: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
  font-size: 12px;
  padding: 5px;
  background: #fff;
}

.dotOverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}

.number {
  font-weight: bold;
  color: #ee6152;
}

.dotOverlay:after {
  content: '';
  position: absolute;
  margin-left: -6px;
  left: 50%;
  bottom: -8px;
  width: 11px;
  height: 8px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png')
}

.distanceInfo {
  position: relative;
  top: 5px;
  left: 5px;
  list-style: none;
  margin: 0;
}

.distanceInfo .label {
  display: inline-block;
  width: 50px;
}

.distanceInfo:after {
  content: none;
}
</style>
  


