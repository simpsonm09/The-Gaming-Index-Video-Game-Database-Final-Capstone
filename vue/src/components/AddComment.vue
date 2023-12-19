<template>
    <div class="comments-link">
        <router-link v-bind:to="{ name: 'comments', params: { reviewId: review.id } }">See All Comments for
            this Review
        </router-link>
    </div>
    <div class="comment">
        <br>
        <form v-show="displayForm" v-on:submit.prevent="submitComment" class="form-input-comment">
            <textarea class="form-control-comment" id="comment" rows="8" cols="25" v-model="comment.commentText"></textarea>
            <br>
            <button type="submit">Submit Comment</button>
            <br><br>
        </form>
        <div class="toggle2">
            <a v-on:click="toggleForm">Toggle to add a comment</a>
        </div>
        <div class="form-response" v-show="formResponse">
            <br>
            {{ formResponse }}
        </div>
        <br>
    </div>
</template>
  
<script>
import CommentService from '../services/CommentService';

export default {
    data() {
        return {
            comment: {
                text: ""
            },
            formResponse: "",
            displayForm: false
        };
    },
    props: ['review'],
    methods: {
        submitComment() {
            this.comment.reviewId = this.review.id;
            this.comment.userId = this.$store.state.user.id;
            CommentService.addComment(this.comment).then(
                response => {
                    this.comment = response.data;
                    this.clearComment();
                    this.toggleForm();
                    this.formResponse = "Comment submitted!";
                    window.location.reload();
                }).catch(
                    error => {
                        this.handleErrorResponse(error);
                    }
                );
        },
        clearComment() {
            this.comment = {};
        },
        toggleForm() {
            this.displayForm = !this.displayForm;
            this.formResponse = "";
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
.toggle2 {
    text-align: center;
}

.comment {
    justify-items: center;
    display: flex;
    flex-direction: column;
}

.comments-link {
    text-align: center;
}

.form-control-comment {
    display: flex;
    align-items: center;

}

.toggle {
    border-style: solid;
    width: fit-content;
    margin-left: 15px;
}

.form-input-comment {
    display: inline-flex;
    flex-direction: column;
    align-content: space-between;
    justify-content: space-evenly;
    align-items: center;
    flex-wrap: nowrap;
}

.toggle2>a {
    cursor: pointer;
    color: blue;
    text-decoration: underline;
}

.form-response {
    text-align: center;
}
</style>