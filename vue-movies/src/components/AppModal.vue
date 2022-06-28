<template>
  <div class="modal">
    <div class="overlay" @click="closeModal"></div>
    <div class="modal_content">
      <div class="item_content clearfix">
        <template v-if="showItemInfo">
          <aside class="poster">
            <figure>
              <img :src="urlImg" />
            </figure>
            <ScoreIndicator
              :score="itemInfo.rating"
              size="82"
              stroke-width="5"
              stroke-color="#ff6633"
            />
            <i>Vote Average</i>
          </aside>

          <section class="info">
            <h1>{{ itemInfo.title }}</h1>

            <p>{{ itemInfo.trailer}}</p>

            <h2 class="label">
              <font-awesome-icon
                :icon="icon"
                size="1x"
                class="icon" />&nbsp;
                {{ dateLabel }}</h2>
            <p>{{ this.date }}</p>

            <h2 class="label">Actors</h2>
            <p>{{ itemInfo.actor }}</p>

            <h2 class="label">CAST</h2>
            <p>{{ itemInfo.actor }}</p>

            <h2 class="label">Website</h2>
            <p class="web" v-if="itemInfo.homepage != null">
              <a :href="itemInfo.homepage">{{ itemInfo.homepage}}</a>
            </p>
          </section>
        </template>
        <template v-else>Loading ...</template>
      </div>
      <button title="Close" class="close_modal" @click="closeModal">
        <font-awesome-icon icon="times" transform="shrink-6" size="1x" />
      </button>
      <!-- comment box -->
      <div class="comment-box">
        <h5>Total comment ({{itemInfo.comments.length}})</h5>
        <div v-for="(comment,id) in itemInfo.comments" :key="id" class="comment-line" style="border: 2px solid; padding: 10px">
          <div class="comment-line-container">
            <div class="d-flex flex-column ml-3">
              <div class="d-flex flex-row post-title">
                <h5>{{comment.comment}}</h5><span class="ml-2">(Jesshead)</span>
              </div>
              <div>
                <span class="mr-2 dot"></span><span> {{toHumanTime(comment.created_at)}}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ScoreIndicator from '@/components/ScoreIndicator';
import { mapState, mapGetters } from 'vuex';
import dayjs from 'dayjs'
import {toHumanTime} from "@/helpers";

export default {
  name: 'Modal',
  components: { ScoreIndicator },
  computed: {
    ...mapState(['type', 'itemInfo']),
    ...mapGetters(['imgPath']),
    showItemInfo() {
      return Object.keys(this.itemInfo).length;
    },
    urlImg() {
      return this.itemInfo.thumbnail != null
        ? this.itemInfo.thumbnail
        : require('@/assets/images/poster-not-available.png');
    },
    date(){
      return (this.itemInfo.year) ? dayjs(this.itemInfo.year).format('MMM D, YYYY') : ''
    },
    dateLabel() {
      return this.itemInfo.type === 'movie' ? 'Release date' : 'First air date';
    },
    icon(){
      return this.itemInfo.type === 'movie' ? 'film' : 'tv';
    }
  },
  methods: {
    closeModal() {
      this.$emit('close-modal');
    },
    toHumanTime(timestamp){
      return toHumanTime(timestamp);
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
figure {
  margin: 0 0 1em;
}
img {
  width: 100%;
  height: auto;
}
h1 {
  margin-top: 0;
  font-size: 2em;
  color: $color-primary;
}
ul {
  margin: 0;
  padding: 0;
  list-style: none;
  display: flex;
}
li {
  display: inline;
  padding: 4px;
  margin-right: 5px;

  background-color: $color-tags;
  color: $color-text-secondary;
  font-size: 0.9em;
}
p {
  margin: 0;
  line-height: 1.4;
}
button {
  color: $color-text-secondary;
}
a {
  display: inline-block;
  color: $color-text-secondary;
  text-decoration: none;
  padding-bottom: 2px;
  border-bottom: 1px dotted $color-text-secondary;

  &:hover {
    color: $color-primary;
  }
}
.item_content {
  padding: 2em;
}
.poster{
  display: block;
  margin-bottom: 2em;
  text-align: center;
}
.info {
  display: block;
}
.label {
  margin: 2em 0 0.2em;
  font-family: $font-secondary, sans-serif;
  color: $color-secondary;
  font-size: 1em;
  text-transform: uppercase;
}
.web a {
  max-width: 90%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.modal {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  z-index: 990;

  .overlay {
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    z-index: 995;
    background: $modal-background-overlay;
  }

  .modal_content {
    z-index: 999;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: $modal-background;
    box-shadow: 0 1px 5px $modal-color-shadow;
    text-align: left;
    border-radius: 4px;
    width: 780px; /* This just a default width */
    max-width: 90%;
    max-height: 90%;
    overflow-y: auto;

    .buttons_wrapper {
      padding: 20px;
    }

    &::-webkit-scrollbar {
      border-radius: 10px;
      width: 8px;
    }

    &::-webkit-scrollbar-thumb {
      background: $scrollbar-color-thumb;
      border-radius: 10px;
    }

    &::-webkit-scrollbar-track {
      border-radius: 10px;
      background: $scrollbar-color-track;
    }
  }

  .close_modal {
    position: absolute;
    right: 10px;
    top: 10px;
    cursor: pointer;
    font-size: 18px;
    background: none;
    border: none;
    transition: opacity 0.2s ease;

    &:hover {
      color: $color-primary;
    }
  }
}

@include sm {
  .poster {
    float: left;
    margin: 0;
    width: 33%;
    text-align: center
  }
  .info{
    display: block;
    padding-left: 2em;
    margin-left: 33%;
  }
}

.comments {
  text-decoration: underline;
  text-underline-position: under;
  cursor: pointer;
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
