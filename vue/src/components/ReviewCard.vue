<template>
  <div class="cool" v-bind:key="review.id">
    <div class="showReview" v-bind:key="review.id">
      <h3 class="review">
        Review by: {{ user.username }}
        <br>
        Overall Rating: {{ review.numberOfStars }}
        <br>
        Replayability Score: {{ review.replayabilityScore }}
        <br>
        Diversity Score: {{ review.diversityScore }}
        <br>
        Aggro Score: {{ review.aggroScore }}
        <br>
        <br>
        {{ review.reviewText }}
        <br>
        <br>
        <br>
        <div v-if="isLoggedInUserAuthor && this.$route.name != 'comments'">
          <DeleteReview v-bind:review="review"></DeleteReview>
        </div>
      </h3>

    </div>

    <AddComment v-bind:review="review"></AddComment>
  </div>
</template>

<script>
import AddComment from './AddComment.vue';
import DeleteReview from './DeleteReview.vue';
import UserService from '../services/UserService';

export default {
  data() {
    return {
      user: {},
    };
  },
  components: {
    AddComment,
    DeleteReview
  },
  props: ['review'],
  computed: {
    isLoggedInUserAuthor() {
      return this.review.userId === this.$store.state.user.id;
    }
  },
  methods: {
    getUserById(id) {
      UserService.getUserById(id)
        .then(
          response => {
            this.user = response.data;
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
        this.$store.commit('SET_NOTIFICATION', `Could not get username from server.`);
      }
    }
  },
  created() {
    this.getUserById(this.review.userId);
  }
}

</script>

<style scoped>
* {
  box-sizing: border-box;
}


.title {
  margin-bottom: 20px;
}

.game_title {
  font-size: 24px;
  margin: 0;
}

.comment {
  display: flex;
  width: 50vh;
  height: fit-content;
  border-style: solid;
  margin-bottom: 20px;
  margin-top: 30px;
  padding: 20px;
  background-color: black;
  align-items: center;
  justify-content: center;
}

Form {
  text-align: center;
}


.button {
  clear: both;
}

html {
  height: 100%;
}

body {
  margin: 0;
}
</style>