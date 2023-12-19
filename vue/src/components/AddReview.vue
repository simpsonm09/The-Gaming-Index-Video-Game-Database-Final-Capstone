<template>
    <div class="review">
        <div class="reviewLink">
            <router-link v-bind:to="{ name: 'reviews', params: { gameId: game.id } }" class="allReviews">
                See All Reviews for {{ game.name }}
            </router-link>
        </div>

        <form v-on:submit.prevent="submitReview" class="reviewForm">
            <p>Write a Review for {{ game.name }}!</p>
            <div class="form-input-group">
                <div class="stars">
                    <label for="numStars">Overall Rating</label>
                    <select class="form-control" id="numStars" v-model="review.numberOfStars">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>
                <br><br>
                <div class="replayability">
                    <label for="replayScore">Replayability Score</label>
                    <select class="form-control" id="replayScore" v-model="review.replayabilityScore">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>
                <br><br>
                <div class="diversity">
                    <label for="diversityScore">Diversity Score</label>
                    <select class="form-control" id="diversityScore" v-model="review.diversityScore">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>
                <br><br>
                <div class="aggro">
                    <label for="aggroScore">Aggro Score</label>
                    <select class="form-control" id="aggroScore" v-model="review.aggroScore">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>
                <br>
                <br>

            </div>
            <textarea class="form-control" id="review" rows="7" v-model="review.reviewText"></textarea>
            <br>
            <button type="submit">Add Review</button>
        </form>
        <br>

        <div v-show="reviewSubmitted">
            <!--<ReviewCard v-bind:review="review"></ReviewCard>-->
            Review Submitted!
        </div>

    </div>
</template>
  
<script>
import ReviewService from '../services/ReviewService';
import ReviewCard from './ReviewCard.vue';

export default {
    data() {
        return {
            review: {
                numberOfStars: 1,
                reviewText: "",
                replayabilityScore: 1,
                diversityScore: 1,
                aggroScore: 1,
            },
            reviewSubmitted: false
        };
    },
    components: {
        ReviewCard
    },
    props: ['game'],
    methods: {
        submitReview() {
            this.review.gameId = this.game.id;
            this.review.userId = this.$store.state.user.id;
            ReviewService.addReview(this.review).then(
                response => {
                    this.review = response.data;
                    this.reviewSubmitted = true;
                    this.clearReview();
                }).catch(
                    error => {
                        this.handleErrorResponse(error);
                    }   
                );
            
        },
        clearReview() {
            this.review = {
                numberOfStars: 1,
                reviewText: "",
                replayabilityScore: 1,
                diversityScore: 1,
                aggroScore: 1,
        };

        },
        handleErrorResponse(error) {
            if (error.response.status == 404 || error.response.status == 500) {
                this.$router.push({ name: 'HomeView' });
                this.$store.commit('SET_NOTIFICATION', `Could not get username from server.`);
            }
        }
    }
};
</script>

<style>

.stars {
    display: inline-flex;
    justify-content: space-between;
    width: 100%;
}

.replayability {
    display: inline-flex;
    justify-content: space-between;
    width: 100%;
}

.diversity {
    display: inline-flex;
    justify-content: space-between;
    width: 100%;
}

.aggro {
    display: inline-flex;
    justify-content: space-between;
    width: 100%;
}

.reviewForm {
    display:flex;
    flex-wrap:wrap;
    justify-content: space-evenly;

}
.reviewLink {
    background-color: rgb(167, 161, 252);
    padding:40px;
    border-radius: 50%;
}

.reviewLink>a {
    color: white;
}

.reviewLink>a:hover {
    color: black;
}

.reviewLink:hover {
    background-color: #F084E4;
    padding:40px;
    border-radius: 50%;
    transform: scale(0.9);
}

.allReviews {
    font-size: xx-large;
    padding-bottom: 10px;
    color: white;

}
</style>