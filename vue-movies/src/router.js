import Vue from "vue";
import Router from "vue-router";
import TopsView from "@/views/TopsView";
import SearchView from "@/views/SearchView";
import MoviesView from "@/views/MoviesView";
import TvShowsView from "@/views/TvShowsView";
import LoginView from "@/views/LoginView";
import RegisterView from "@/views/RegisterView";
import UserView from "@/views/UserView";
import store from '@/store';

Vue.use(Router);

const router = new Router({
  //mode: 'history',
  base: process.env.NODE_ENV === "development" ? "/" : "/vue-movies/",
  routes: [
    {
      path: "/",
      redirect: { name: "tops" },
    },
    {
      path: "/tops",
      name: "tops",
      component: TopsView,
    },
    {
      path: "/search",
      name: "search",
      component: SearchView,
    },
    {
      path: "/movies",
      name: "movies",
      component: MoviesView,
    },
    {
      path: "/tv-shows",
      name: "tv-shows",
      component: TvShowsView,
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
    },
    {
      path: "/register",
      name: "register",
      component: RegisterView,
    },
    {
      path: "/user",
      name: "user",
      component: UserView,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: "/*",
      redirect: { name: "tops" }
    }
  ]
});

// Ensure checked auth before each page load.
router.beforeEach((to, from, next) => {
  if (store.getters.isAuth) {
    next();
    return;
  }

  // check auth before first load or page reload to get token to get the user data
  store.dispatch("checkAuth").then(() => {
    if (to.matched.some((record) => record.meta.requiresAuth)) {
      if (store.getters.isAuth) {
        next();
        return;
      }
      next('/login');
    } else {
      next();
    }
  });
});

export default router;
