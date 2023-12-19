<template>
  <div class="comment" v-bind:key="comment.id">
    <h3 class="review">
      #{{ commentCount }}
      <br>
      Comment by: {{ user.username }}
      <br><br>
      {{ comment.commentText }}
      <br>
      <br>
      <div v-if="isLoggedInUserAuthor">
      <DeleteComment v-bind:comment="comment"></DeleteComment>
    </div>
    </h3>

  </div>
</template>
  
<script>
import UserService from '../services/UserService';
import DeleteComment from './DeleteComment.vue';

export default {
  data() {
    return {
      user: {},
    };
  },
  components: {
    DeleteComment
  },
  props: ['comment', 'commentCount'],
  computed: {
    isLoggedInUserAuthor() {
      return this.comment.userId === this.$store.state.user.id;
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
    this.getUserById(this.comment.userId)
  }
}

</script>
  
<style scoped>
* {
  box-sizing: border-box;
}

.cool {
  width: 100vw;
}

.title {
  margin-bottom: 20px;
}

.game_title {
  font-size: 24px;
}


.comment {
  display: flex;
  height: fit-content;
}

.showReview {
  border-style: solid;
  height: fit-content;
  width: 100vw;
  display: block;
  float: right;
  margin-bottom: 30px;
  padding: 10px;
  background-color: aliceblue;
  box-shadow: 10px;
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