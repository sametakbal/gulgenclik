<template>
  <div>
    <div v-if="authenticated" class="home row">
      <div class="col-md-1"></div>
      <div class="col-md-5">
        <div class="card text-bg-light">
          <img src="../../../content/images/american.png" class="card-img" alt="">
          <div class="card-img-overlay">
            <h5 class="card-title">Amerikan Bilardo 1</h5>
            <p class="card-text"><small>Last updated 3 mins ago</small></p>
          </div>
          <div class="card-footer text-muted">
            <button v-if="!isOpen" @click="openSession()" type="button" class="btn btn-primary">Oturumu Başlat</button>
            <button v-else type="button" @click="isOpen=false;" class="btn btn-danger">Oturumu Kapat</button>
            <span class="ml-5">
            <strong>Süre:</strong> 1 s 30 dk
          </span>
            <span class="ml-5">
            <strong>Ücret:</strong> 42.5 ₺
          </span>
          </div>
        </div>
        <div class="card text-bg-light mt-3">
          <img src="../../../content/images/american.png" class="card-img" alt="">
          <div class="card-img-overlay">
            <h5 class="card-title">Amerikan Bilardo 2</h5>
            <p class="card-text"><small>Last updated 3 mins ago</small></p>
          </div>
          <div class="card-footer text-muted">
            <button type="button" class="btn btn-primary">Oturumu Başlat</button>
          </div>
        </div>
      </div>
      <div class="col-md-5">
        <div class="card text-bg-light">
          <img src="../../../content/images/3ball.png" class="card-img" alt="">
          <div class="card-img-overlay">
            <h5 class="card-title">Üç Top Bilardo 1</h5>
            <p class="card-text"><small>Last updated 3 mins ago</small></p>
          </div>
          <div class="card-footer text-muted">
            <button type="button" class="btn btn-primary">Oturumu Başlat</button>
          </div>
        </div>
        <div class="card text-bg-light mt-3">
          <img src="../../../content/images/3ball.png" class="card-img" alt="">
          <div class="card-img-overlay">
            <h5 class="card-title">Üç Top Bilardo 2</h5>
            <p class="card-text"><small>Last updated 3 mins ago</small></p>
          </div>
          <div class="card-footer text-muted">
            <button type="button" class="btn btn-primary">Oturumu Başlat</button>
          </div>
        </div>
      </div>
      <div class="col-md-1"></div>
    </div>
    <div v-else>
      <div class="container text-center" :style="{'min-height':height +'px'}">
        <img src="../../../content/images/logo.jpeg" class="logo rounded" alt="">
        <h1>Gülgençlik Derneği</h1>
        <h5 class="mt-3">Derneğin bilardo oturum paneline hoşgeldiniz</h5>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./home.component.ts"></script>
<style scoped>
.card-img {
  opacity: 0.125;
  height: 200px;
  width: 200px;
}

.logo {
  height: 220px;
  width: 220px;
}
</style>
