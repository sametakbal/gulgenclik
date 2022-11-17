import { Module } from 'vuex';

export const translationStore: Module<any, any> = {
  state: {
    currentLanguage: localStorage.getItem('currentLanguage') || 'tr',
    languages: {
      en: { name: 'English' },
      tr: { name: 'Türkçe' },
      // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
    },
  },
  getters: {
    currentLanguage: state => state.currentLanguage,
    languages: state => state.languages,
  },
  mutations: {
    currentLanguage(state, newLanguage) {
      state.currentLanguage = newLanguage;
      localStorage.setItem('currentLanguage', newLanguage);
    },
  },
};
