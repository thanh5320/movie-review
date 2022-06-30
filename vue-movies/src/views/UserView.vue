<template>
  <div class="user-profile">
    <div class="row-1">
      <h2 class="title">
        Information
      </h2>
      <table>
        <tr>
          <td><h3>Name</h3></td>
          <td><h3>{{user.full_name}}</h3></td>
        </tr>
        <tr>
          <td><h3>Username</h3></td>
          <td><h3>{{user.username}}</h3></td>
        </tr>
        <tr>
          <td><h3>Email</h3></td>
          <td><h3>{{user.email}}</h3></td>
        </tr>
        <tr>
          <td><h3>Phone number</h3></td>
          <td><h3>{{user.phone_numer}}</h3></td>
        </tr>
      </table>
      <button @click="logout()">Logout</button>
    </div>
    <div class="row-2">
      <h2 class="title">
        Comments
      </h2>
      <div class="comment-box">
        <h5>Total comment ({{user.comments.length}})</h5>
        <div v-for="(comment,id) in user.comments" :key="id" class="comment-line" style="border: 2px solid; padding: 10px">
          <a href="javascript:void(0)" @click="showModel(comment.movie.id, comment.movie.type)">{{comment.movie.title}}</a>
          <div class="comment-line-container">
            <div class="d-flex flex-column ml-3">
              <div class="d-flex flex-row post-title">
                <h5>{{comment.comment}}</h5>
              </div>
              <div>
                <span class="mr-2 dot"></span><span> {{toHumanTime(comment.created_at)}}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row-3">
      <h2 class="title">
        Reviews
      </h2>
      <div class="review-box">
        <h5>Total comment ({{user.comments.length}})</h5>
        <div v-for="(review,id) in user.reviews" :key="id" class="comment-line" style="border: 2px solid; padding: 10px">
          <a href="javascript:void(0)" @click="showModel(review.movie.id, review.movie.type)">{{review.movie.title}}</a>
          <div class="comment-line-container">
            <div class="d-flex flex-column ml-3">
              <div class="d-flex flex-row post-title">
                <h5>{{review.rating}} star</h5>
              </div>
              <div>
                <span class="mr-2 dot"></span><span> {{toHumanTime(review.created_at)}}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import {toHumanTime} from "@/helpers";

export default {
  name: "User",
  computed: {
    ...mapGetters(['user'])
  },
  methods:{
    logout() {
      this.$store.dispatch('deleteAuth');
      this.$router.go(0);
    },
    toHumanTime(timestamp){
      return toHumanTime(timestamp);
    },
    showModel(id, type){
      this.$store.dispatch('getItem', { id, type });
      this.$emit('open-modal');
    }
  }
}
</script>

<style scoped>
.user-profile{
  display: flex;
  justify-content: space-between;
}

table{
  padding-top: 40px;
  padding-bottom: 10px;
}

table tr td {
  border: 2px solid;
  padding-left: 10px;
  padding-right: 20px;
}

.comment-box {
  text-underline-position: under;
  cursor: pointer;
  width: 150%;
}

.review-box {
  text-underline-position: under;
  cursor: pointer;
  width: 150%;
}

.dot {
  height: 7px;
  width: 7px;
  margin-top: 3px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
}
</style>
