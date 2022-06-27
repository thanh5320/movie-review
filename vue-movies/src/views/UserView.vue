<template>
  <div class="user-profile">
    <nav><h1>Hello {{user.name}}</h1></nav>
    <div class="row">
      <div class="col-3">sideBar</div>
      <div class="col-5">mainContent</div>
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "User",
  computed: {
    ...mapGetters(['user'])
  }
}
</script>

<style scoped>
nav,
.row>div {
  border-radius: 0.3em;
  border: 1px solid rgb(0, 51, 255);
}

.col-3 {
  overflow: scroll;
}
</style>
