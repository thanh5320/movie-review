import Vue from 'vue';
import Vuex from 'vuex';
import AppServices from '@/services/AppServices';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    baseUrl: '',
    itemInfo: {}
  },
  getters: {
    imgPath: state => `${state.baseUrl}w370_and_h556_bestv2`
    //backgroundPath: state => `${state.baseUrl}w1400_and_h450_face`
  },

  mutations: {
    LOAD_CONF: (state, baseUrl) => {
      state.baseUrl = baseUrl;
    },
    RESET_ITEM: state => {
      Vue.set(state, 'itemInfo', {});
    },
    LOAD_ITEM: (state, { type, info }) => {
      const itemInfo = {
        type,
        title: info.title,
        year: info.year,
        release_year: info.release_year,
        director: info.director,
        trailer: info.trailer,
        homepage: "#",
        thumbnail: info.thumbnail,
        rating: info.rating,
        actor: info.actors
          .map(item => item.full_name)
          .join(', ')
      };
      Vue.set(state, 'itemInfo', itemInfo);
    }
  },

  actions: {
    getInitialData: async ({ commit }) => {
      // const response = await AppServices.getConfiguration();
      // commit('LOAD_CONF', response.data.images.secure_base_url);
    },

    getItem: async ({ commit }, { id, type }) => {
      commit('RESET_ITEM');
      const [response] = await Promise.all([
        AppServices.getItemInfo(id, type),
      ]);

      commit('LOAD_ITEM', {
        type,
        info: response.data.data
      });
    }
  }
});
