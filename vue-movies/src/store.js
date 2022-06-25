import Vue from 'vue';
import Vuex from 'vuex';
import AppServices from '@/services/AppServices';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    baseUrl: '',
    itemInfo: {},
    user: null,
  },
  getters: {
    imgPath: state => `${state.baseUrl}w370_and_h556_bestv2`,
    user: state => state.user
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
    },
    LOAD_USER: (state, { user }) => {
      state.user = user;
    }
  },

  actions: {
    getInitialData: async ({ commit }) => {
      const response = await Promise.all([
        AppServices.getMe()
      ]);

      commit('LOAD_USER', {user: response.data.data})
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
    },

    setAuth: async ({ commit }, {id, username, phone_number, email, full_name}) => {
      const user = {
        username: username,
        id: id,
        phone_number: phone_number,
        email: email,
        full_name: full_name
      };

      commit('LOAD_USER', {
        user
      })
    }
  }
});
