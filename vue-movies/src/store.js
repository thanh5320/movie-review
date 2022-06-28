import Vue from 'vue';
import Vuex from 'vuex';
import AppServices from '@/services/AppServices';
import {destroyToken, getToken, saveToken} from "@/services/jwt";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    baseUrl: '',
    itemInfo: {},
    user: null,
  },
  getters: {
    imgPath: state => `${state.baseUrl}w370_and_h556_bestv2`,
    user: state => state.user,
    isAuth: (state) => !!state.user,
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
          .join(', '),
        comments: info.comments
      };
      Vue.set(state, 'itemInfo', itemInfo);
    },
    LOAD_USER: (state, { user }) => {
      Vue.set(state, 'user', user);
    },
    SET_AUTH: (state, { user, token }) => {
        Vue.set(state, 'user', user);
        saveToken(token);
    },
  },

  actions: {
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

    checkAuth: async ({ commit, state }) => {
      const token = getToken();

      if(token && !state.user){
        await AppServices.getMe(token)
            .then(response => commit("SET_AUTH", {user: response.data.data, token: token}))
            .catch(() => destroyToken());
      }
    },

    setAuth: async ({commit}, {token}) => {
      const response = await AppServices.getMe(token);
      const user = response.data.data;

      commit("SET_AUTH", {user: user, token: token});
    }
  }
});
