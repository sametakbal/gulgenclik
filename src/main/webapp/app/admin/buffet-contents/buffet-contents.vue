<template>
  <div>
    <div class="row">
      <div class="col-md-8">
        <table class="table table-striped table-hover">
          <caption>Buffet Contents</caption>
          <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Başlık</th>
            <th scope="col">Fiyat</th>
            <th scope="col">İşlem</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="con in contents">
            <th scope="row">{{ con.id }}</th>
            <td>{{ con.name }}</td>
            <td>{{ con.price }} ₺</td>
            <td>
              <button type="button" @click="selectContent(con)" class="btn btn-secondary">
                <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span
                v-text="$t('entity.action.edit')">Edit</span>
              </button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="col-md-4">
        <div class="justify-content-start">
          <h4>Yeni Bir İçerik Ekle</h4>
          <hr>
          <div class="form-group">
            <label class="form-control-label" v-text="'Başlık'">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              v-model="buffetContent.name"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="'Fiyat'">Name</label>
            <input
              type="number"
              class="form-control"
              name="name"
              v-model="buffetContent.price"
            />
          </div>
          <div class="form-group">
            <button type="submit" @click="createBuffetContent()" class="btn btn-primary">
              <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span
              v-text="$t('entity.action.save')">Save</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./buffet-contents.component.ts"></script>

<style scoped>

</style>
