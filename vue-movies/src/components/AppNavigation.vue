<template>
  <div class="navigation">
    <ul>
      <li v-for="(link, index) in links" :key="index">
        <router-link :to="link.url">
          <font-awesome-icon :icon="link.icon" :transform="link.transform" size="2x" class="icon" />
          <br />
          <span>{{ link.text }}</span>
        </router-link>
      </li>
      <li v-if="!user">
        <router-link to="/login">
          <font-awesome-icon icon="recycle" transform="shrink-5" size="2x" class="icon" />
          <br />
          <span>Login</span>
        </router-link>
      </li>
      <li v-if="user">
        <router-link to="/user">
          <font-awesome-icon icon="recycle" transform="shrink-5" size="2x" class="icon" />
          <br />
          <span>User</span>
        </router-link>
      </li>
      <li v-if="!user">
        <router-link to="/register">
          <font-awesome-icon icon="won-sign" transform="shrink-5" size="2x" class="icon" />
          <span>Register</span>
        </router-link>
      </li>
      <li>
        <AppSearch />
      </li>
    </ul>
  </div>
</template>

<script>
import AppSearch from '@/components/AppSearch';
import {mapGetters} from "vuex";

export default {
  name: 'AppNavigation',
  components: { AppSearch },
  data() {
    return {
      links: [
        {
          url: '/tops',
          text: 'Tops',
          icon: 'star',
          transform: 'shrink-7'
        },
        {
          url: '/movies',
          text: 'Movies',
          icon: 'film',
          transform: 'shrink-6'
        },
        {
          url: '/tv-shows',
          text: 'TV',
          icon: 'tv',
          transform: 'shrink-8'
        }
      ]
    };
  },
  computed: {
    ...mapGetters(['user'])
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
ul {
  margin: 0;
  padding: 0;
  display: flex;
}
li {
  height: 60px;
  width: 50px;
  border-left: 1px solid $background-border;
  list-style: none;
  display: inline-block;
  box-sizing: border-box;

  &:last-child{
    flex-grow: 1;
    border-right: 1px solid $background-border;
  }
}
a {
  display: block;
  height: 100%;
  text-align: center;
  color: $color-text-primary;
  text-decoration: none;

  &:hover {
    color: $color-text-secondary;
  }

  &.router-link-active {
    background: $background-active;
    background: radial-gradient(
      circle,
      $background-active-gradient1,
      $background-active-gradient2
    );
    color: $color-text-secondary;
  }
}
span {
  display: none;
}
.navigation{
  margin: 0 auto;
    max-width: 1000px;
}
.icon {
  margin-top: 15px;
}

@include sm {
  li {
    width: 85px;
  }
  span {
    display: inline;
    padding: 0 10px;
  }
  .icon {
    margin-top: 3px;
  }
}
</style>
