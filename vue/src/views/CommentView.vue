<template>
   
    <br>
    <br>
    <div> 
        <p class="review-link-from-comments"><router-link v-bind:to="{ name: 'reviews', params: { gameId: game.id } }" class="allReviews">
        Go Back to Reviews for {{ game.name }}
    </router-link></p>
        <ReviewCard v-bind:review="review"></ReviewCard>
        <div v-if="comments.length">
            <div class="combined" v-for="i in comments.length" v-bind:key="i">
                <CommentCard v-bind:comment="comments[i-1]" v-bind:commentCount="i"></CommentCard>
            </div>
        </div>
        <div v-else>
            <h3 class="no-comment-display">No comments found for review</h3>
        </div>
    </div>
</template>
  
<script>

import CommentCard from '../components/CommentCard.vue';
import CommentService from '../services/CommentService';
import ReviewService from '../services/ReviewService';
import ReviewCard from '../components/ReviewCard.vue'
import GameService from '../services/GameService';


export default {
    data() {
        return {
            comments: [],
            review: {},
            game: {}
        };
    },
    components: {
        CommentCard,
        ReviewCard
    },
    methods: {
        getCommentsByReviewId(id) {
            CommentService.getCommentsByReviewId(id)
                .then(
                    response => {
                        this.comments = response.data;
                    })
                .catch(
                    error => {
                        this.handleErrorResponse(error);
                    }
                )
        },
        getReview(id) {
            ReviewService.getReviewById(id)
                .then(
                    response => {
                        this.review = response.data;
                        this.getGame(this.review.gameId);
                    })
                .catch(
                    error => {
                        this.handleErrorResponse(error);
                    }
                );
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
        this.getCommentsByReviewId(this.$route.params.reviewId);
        this.getReview(this.$route.params.reviewId);
    }
}
</script>
  
<style scoped>
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

.combined {
    display: inline-flex;
    justify-content: space-evenly;
    padding: 20px;
    flex-wrap: wrap;
    align-items: center;
    align-content: stretch;
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

.no-comment-display {
    text-align: center;
}

.review-link-from-comments {
    text-align: center;
}
</style>