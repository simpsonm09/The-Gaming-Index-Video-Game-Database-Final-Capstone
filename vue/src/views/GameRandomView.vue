<template>
    <div class="combined" v-if="!isLoading">
        <GameCard v-bind:game="game" v-bind:userLists="userLists"></GameCard>
    </div>
    <div v-else class="game-loading-text">
        <h3>Retrieving random game. Page is currently Loading...</h3>
    </div>
</template>
  
<script>
import GameCard from '../components/GameCard.vue';
import GameService from '../services/GameService';
import UserListService from '../services/UserListService';

export default {
    data() {
        return {
            game: {},
            userLists: [],
            isLoading: true
        };
    },
    components: {
        GameCard
    },
    methods: {
        getRandomGame(gameId) {
            GameService.getRandomGame()
                .then(
                    response => {
                        this.game = response.data;
                        this.isLoading = false;
                    })
                .catch(
                    error => {
                        this.handleErrorResponse(error);
                    }
                );
        },
        getUserLists(userId) {
            UserListService.getUserListsByUserId(userId)
                .then(
                    response => {
                        this.userLists = response.data;
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
        this.getRandomGame();
        this.getUserLists(this.$store.state.user.id);
    }
}
</script>
<style>
.game-loading-text {
    text-align: center;
}
</style>