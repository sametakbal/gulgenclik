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
            <td>{{ con.price.toFixed(2) }} ₺</td>
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
          <h4>{{ buffetContent.id != null ? 'İçeriği Güncelle' : 'Yeni Bir İçerik Ekle' }}</h4>
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
            <button type="submit" :disabled="!contentIsValid()" @click="createBuffetContent()" class="btn btn-primary">
              <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span
              v-text="$t('entity.action.save')">Save</span>
            </button>
            <button v-if="buffetContent.id"  type="submit" @click="clearForm()" class="btn btn-light">
              <font-awesome-icon icon="fa-broom" />&nbsp;<span
              v-text="$t('entity.action.clear')">Clear</span>
            </button>
            <button v-if="buffetContent.id"  type="submit" @click="showDeletePartial = true;" class="btn btn-danger">
              <font-awesome-icon icon="trash"></font-awesome-icon>&nbsp;<span
              v-text="$t('entity.action.delete')">Delete</span>
            </button>
          </div>
          <div class="form-group" v-if="showDeletePartial">
            <div class="alert alert-danger" role="alert">
              Silmek istediğinize emin misiniz?
            </div>
            <button v-if="buffetContent.id"  type="submit" @click="deleteBuffetContent()" class="btn btn-danger">
             &nbsp;<span
              v-text="$t('entity.action.delete')">Delete</span>
            </button>
            <button v-if="buffetContent.id"  type="submit" @click="showDeletePartial = false;" class="btn btn-success">
             &nbsp;<span
              v-text="$t('entity.action.cancel')">Cancel</span>
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
