<template>
    <button v-on:click="deleteReview">Delete Review</button>
</template>

<script>

import ReviewService from '../services/ReviewService';

export default {

    props: ['review'],
    computed: {

    },
    methods: {
        deleteReview() {
            ReviewService.deleteReview(this.review.id)
                .then(
                    response => {
                    })
                .catch(
                    error => {
                        this.handleErrorResponse(error);
                    }
                )
            window.location.reload();
        },
        handleErrorResponse(error) {
            if (error.response.status == 404 || error.response.status == 500) {
                this.$router.push({ name: 'HomeView' });
                this.$store.commit('SET_NOTIFICATION', `Could not get game data from server.`);
            }
        }
    }
};
</script>

<style></style>