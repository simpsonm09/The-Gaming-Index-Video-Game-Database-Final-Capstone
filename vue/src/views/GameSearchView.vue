<template>
  <div class="combined">
    <div v-if="games.length" class="grid-container"> <!-- class = "grid-container" -->
      <GameCardSearch v-for="game in games" v-bind:key="game.id" v-bind:game="game"></GameCardSearch>
    </div>
    <div v-else>
      <h1>Unable to find any games for search term</h1>
    </div>
  </div>
</template>

<script>
import GameService from '../services/GameService'
import GameCardSearch from '../components/GameCardSearch.vue';

export default {
  components: {
    GameCardSearch,
  },
  data() {
    return {
      isLoading: true
    };
  },
  computed: {
    games() {
      return this.$store.state.games;
    }
  },
  methods: {
    getGamesByString(searchString) {
      GameService.getGamesByString(searchString)
        .then(
          response => {
            this.$store.commit("SET_SEARCH_GAMES", response.data);
          })
        .catch(
          error => {
            this.handleErrorResponse(error);
          }
        );
    },
    handleErrorResponse(error) {
      if (error.response.status == 404 || error.response.status == 500) {
        this.$router.push({ name: 'HomeView' });
        this.$store.commit('SET_NOTIFICATION', `Could not get games data from server.`);
      }
    }
  },
  created() {
    this.getGamesByString(this.$route.query.stringSearch);
  },
  beforeUpdate() {
    this.getGamesByString(this.$route.query.stringSearch);
  }
};
</script>

<style>

</style>