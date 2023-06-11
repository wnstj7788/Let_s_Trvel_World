<template>
  <footer class="footer">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <h3>About Us</h3>
          <p>여행 계획이 편리해지는 그날까지 Let's Travel the World </p>
        </div>
        <div class="col-md-3">
          <h3>Quick Links</h3>
          <ul>
            <li><router-link :to="{ name: 'MainSearch' }">여행지 검색</router-link></li>
            <li><router-link :to="{ name: 'Plan' }">여행일정 만들기</router-link></li>
            <li><router-link :to="{ name: 'JournalWrite' }">여행기 쓰기</router-link></li>
            <li><router-link :to="{ name: 'JournalView' }">여행일지</router-link></li>
          </ul>
        </div>
        <div class="col-md-3">
          <h3>Contact Us</h3>
          <p>대전 광역시 유성구 삼성 화재 연수원</p>
          <p>Email: ssafy@ssafy.com</p>
          <p>Phone: 010-9184-9881</p>
        </div>
      </div>
    </div>
    <div class="bottom-footer">
      <div class="container">
        <p>&copy; {{ currentYear }} 싸피(주). All rights reserved.</p>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  data() {
    return {
      currentYear: new Date().getFullYear(),
    };
  },
};
</script>

<style scoped>
.footer {
  width: 100%;
  background-color: #f8f9fa;
  padding: 20px;
  text-align: center;
  color: #555;
}

.footer h3 {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 20px;
}

.footer p {
  font-size: 14px;
  margin-bottom: 10px;
}

.footer ul {
  list-style: none;
  padding: 0;
}

.footer ul li {
  margin-bottom: 5px;
}

.footer ul li a {
  color: #555;
  text-decoration: none;
}

.bottom-footer {
  background-color: #333;
  color: #fff;
  padding: 10px 0;
  text-align: center;
}

.bottom-footer p {
  margin: 0;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 15px;
}

.content {
  margin-bottom: 100px; /* footer의 높이보다 큰 여백 추가 */
}
</style>
