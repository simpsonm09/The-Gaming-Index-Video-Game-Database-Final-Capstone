<template>
    <button v-on:click="deleteComment">Delete Comment</button>
</template>

<script>

import CommentService from '../services/CommentService';

export default {

    props: ['comment'],
    computed: {

    },
    methods: {
        deleteComment() {
            CommentService.deleteCommentById(this.comment.id)
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