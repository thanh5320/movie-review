<template>
  <div>
    <h2 class="title">
      <font-awesome-icon
        icon="film"
        size="1x" />&nbsp;
        Upcoming Movies
      </h2>
    <ItemList :results="results" type="movie" @item-clicked="viewDetailInfo" />
    <ItemListMore :loading="loading" :loadMore="loadMore" @view-more="fetchData('MORE')"/>
  </div>
</template>

<script>
import ItemList from "@/components/ItemList";
import ItemListMore from "@/components/ItemListMore";
import AppServices from "@/services/AppServices";
import { viewDetailMixin } from "@/mixins/viewDetailMixin";

export default {
  name: 'MoviesView',
  mixins: [viewDetailMixin],
  components: {
    ItemList,
    ItemListMore
  },
  data() {
    return {
      page: 1,
      loading: false,
      error: '',
      results: [],
      totalPages: null
    };
  },
  computed: {
    loadMore() {
      return this.totalPages > this.page;
    },
    showMessage() {
      return !!(this.searching || this.error !== '');
    }
  },
  created(){
    this.fetchData('INIT');
  },
  methods: {
    async fetchData(action) {
      if (action === 'INIT') {
        this.page = 20;
      } else {
        this.page += 20;
        this.loading = true;
      }

      try {
        const response = await AppServices.getMoviesUpcoming(this.page);
        this.results = response.data.data;
        this.totalPages = response.data.total;
      } catch (e) {
        if (action === 'MORE') this.page--;
        this.error = e;
      } finally {
        this.loading = false;
      }
    }
  }
};
</script>

<style lang="scss" scoped>
</style>
