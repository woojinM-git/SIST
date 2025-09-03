<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>주소로 장소 표시하기</title>

</head>
<body>
  <h1>서울특별시 보라매로 84-4</h1>
<article>
  <form>
    <input type="text" name="loc" id="loc"/>
    <button type="button" onclick="search(this.form)">검색</button>
  </form>
</article>

<div id="map" style="width:500px;height:400px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=79d78ca55b52bc2291d3a6fedb9f75a8&libraries=services"></script>
<script>
  var mapContainer = document.getElementById('map'), // 지도를 표시할 div
          mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
          };

  // 지도를 생성합니다
  var map = new kakao.maps.Map(mapContainer, mapOption);

  var marker;

  function search(frm) {
    var value = frm.loc.value.trim();
    console.log(value);

    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(value, function (result, status) {

      // 정상적으로 검색이 완료됐으면
      if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        if (marker) {
          marker.setMap(null); // 지도에서 기존 마커 제거
        }

        // 결과값으로 받은 위치를 마커로 표시합니다
        marker = new kakao.maps.Marker({
          map: map,
          position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
          content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
        });
        infowindow.open(map, marker);



        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
      }
    });
  }
</script>
</body>