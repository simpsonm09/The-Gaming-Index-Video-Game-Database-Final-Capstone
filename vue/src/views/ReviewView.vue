<template>
  <div>
    <h1 class="gameName">Go Back to <router-link v-bind:to="{ name: 'game', params: { gameId: game.id } }">{{ game.name }}</router-link></h1>
    <div v-if="reviews.length">
    <div class="combined" v-for="review in reviews" v-bind:key="review.id">
      <ReviewCard v-bind:review="review"></ReviewCard>
    </div>
  </div>
  <div v-else>
    <h3>No reviews found for game</h3>
  </div>
  </div>
</template>

<script>

import ReviewService from '../services/ReviewService';
import GameService from '../services/GameService';
import ReviewCard from '../components/ReviewCard.vue';

export default {
  data() {
    return {
      reviews: [],
      game: {}
    };
  },

  components: {
    ReviewCard,
  },
  methods: {
    getReviewsByGameId(id) {
      ReviewService.getReviewsByGameId(id)
        .then(
          response => {
            this.reviews = response.data;
          })
        .catch(
          error => {
            this.handleErrorResponse(error);
          }
        )
    },
    getGame(gameId) {
      GameService.getGameById(gameId)
        .then(
          response => {
            this.game = response.data;
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
        this.$store.commit('SET_NOTIFICATION', `Could not get game data from server.`);
      }
    }
  },
  created() {
    this.getReviewsByGameId(this.$route.params.gameId);
    this.getGame(this.$route.params.gameId);
  }
}
</script>

<style scoped>

.gameName {
  text-align: center;
}
body {
  font-family: 'Arial', sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f4f4f4;
}

Header {
  background-color: #333;
  color: #fff;
  padding: 10px;
  text-align: center;
}



.search-side {
  flex: 1;
  margin-right: 20px;
}

GameSearch {
  margin-bottom: 10px;
}

SideBar {
  background-color: #fff;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

ReviewCard {
  flex: 1;
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>