<template>

    <div class="together">

    <ManageUserLists v-bind:userLists="userLists" class="manage"></ManageUserLists>
 
    <UserGames class="userLists" v-bind:userLists="userLists"></UserGames>
</div>
</template>

<script>
import UserGames from '../components/UserGames.vue';
import ManageUserLists from '../components/ManageUserLists.vue';
import UserListService from '../services/UserListService';

export default {
  data() {
    return {
      userLists: []
    };
  },
  components: {
    UserGames,
    ManageUserLists
  },
  methods: {
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
    this.getUserLists(this.$store.state.user.id);
  }
};
</script>

<style>
.manage {

  border-style:solid;
}

</style>