<template>
  <div class="profile">
    <h1>User Profile</h1>
    <UserInfo></UserInfo>
    <UserReviews></UserReviews>
    <UserComments></UserComments>
  </div>
</template>
  
<script>
import UserInfo from '../components/UserInfoCard.vue';
import UserReviews from '../components/UserReviewsCard.vue';
import UserComments from '../components/UserCommentsCard.vue';
import ReviewService from '../services/ReviewService';
import CommentService from '../services/CommentService';

export default {
  components :{
    UserInfo,
    UserReviews,
    UserComments
  },
  methods: {
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
        this.$store.commit('SET_NOTIFICATION', `Could not get data from server.`);
      }
    }
  },
  created() {
  }
};
</script>

<style>
</style>