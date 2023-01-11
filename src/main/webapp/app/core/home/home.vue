<template>
  <div>
    <div v-if="authenticated" class="home row">
      <div class="col-md-3">
        <div class="card text-end text-bg-light mt-5" v-for="billiard in americans">
          <img src="../../../content/images/american.png" class="card-img" alt="">
          <div class="card-img-overlay">
            <h5 class="card-title">
              <button class="btn btn-link" @click="getSessionByBilliard(billiard.id)"><strong>{{
                  billiard.name
                }}</strong></button>
            </h5>
            <p class="card-text">
              {{billiard.using ? '1 saat 36 dk' : ''}}
              <br>
              Toplam tutar : 0 ₺
            </p>
            <button v-if="!billiard.using" @click="openSession(billiard.id)" type="button" class="btn btn-primary">
              Oturumu
              Başlat
            </button>
            <button v-else type="button" @click="openSession(billiard.id)" class="btn btn-danger">Oturumu Kapat
            </button>
          </div>
        </div>
      </div>
      <div class="col-md-3">
        <div class="card text-end text-bg-light mt-5" v-for="billiard in threeBalls">
          <img src="../../../content/images/3ball.png" class="card-img" alt="">
          <div class="card-img-overlay">
            <h5 class="card-title">
              <button class="btn btn-link" @click="getSessionByBilliard(billiard.id)"><strong>{{
                  billiard.name
                }}</strong></button>
            </h5>
            <h5 class="card-text mb-5"><small>Toplam tutar : 0 ₺</small></h5>
            <button v-if="!billiard.using" @click="openSession(billiard.id)" type="button"
                    class="btn btn-primary">
              Oturumu Başlat
            </button>
            <button v-else type="button" @click="openSession(billiard.id)" class="btn btn-danger">Oturumu Kapat
            </button>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="justify-content-start" v-if="selectedSession.id">
          <h4 class="text-end">{{ selectedSession.billiardTable ? selectedSession.billiardTable.name : '' }}  </h4>
          <label v-if="buffetContents" for="ordered-buffet-contents">Büfe İçerikleri</label>
          <ul class="list-group" id="ordered-buffet-contents" v-if="buffetContents">
            <li v-for="(content,index) in buffetContents"
                class="list-group-item d-flex justify-content-between align-items-center">
              {{ index + 1 }}. {{ content.name }}
              <button class="badge bg-secondary text-white"> Ekle </button>
            </li>
          </ul>
          <label v-if="selectedSession.buffetContents" for="ordered-buffet-contents">Sipariş Edilen Büfe
            İçerikleri</label>
          <ul class="list-group" id="ordered-buffet-contents" v-if="selectedSession.buffetContents">
            <li v-for="(content,index) in selectedSession.buffetContents"
                class="list-group-item d-flex justify-content-between align-items-center">
              {{ index + 1 }}. {{ content.name }}
              <span class="badge bg-light">{{ content.price }} ₺</span>
            </li>
          </ul>
        </div>
      </div>
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
  right: auto;
}

.list-group {
  max-height: 250px;
  min-height: 250px;
  margin-bottom: 10px;
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
}

.logo {
  height: 220px;
  width: 220px;
}
</style>
