<template>
  <div class="sidebar" id="nav">
    <ul>
      <li><router-link class="router_link" v-bind:to="{ name: 'home' }">Home</router-link></li>
      <br><br>
      <li><router-link class="router_link" v-bind:to="{ name: 'games' }">My Games</router-link></li>
      <br><br>
      <li v-if="this.$route.name === 'random-game'"><a class="router_link" :href="$router.resolve({name:'random-game'}).href">Random Game</a></li>
      <li v-else><router-link class="router_link" v-bind:to="{ name: 'random-game' }">Random Game</router-link></li>
      <br><br>
      <div v-if="isAdmin">
        <li><router-link class="router_link" v-bind:to="{ name: 'profile' }">Profile</router-link></li>
        <br><br>
        <li><router-link class="router_link" v-bind:to="{ name: 'admin-tools' }">Admin</router-link></li>
        <br><br>
      </div>
      <li><router-link class="router_link" v-bind:to="{ name: 'logout' }">Logout </router-link></li>
    </ul>
  </div>
</template>
  
<script>

export default {
  computed: {
    isAdmin() {
      return "ROLE_ADMIN" === this.$store.state.user.authorities[0].name;
    }
  }
}
</script>

<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
}

#nav {
  background: #ba2fc6;
  border-style: solid;
  border-radius: 20px 0px 0px 20px;
  border-style: solid;
  color: black;
  min-width: 150px;
  overflow: auto;

}

.router_link {
  margin-left: 8px;
  margin-top: 8px;
  text-align: center;
  font-size: large;
  display: block;
  position: fixed;
  color: white;
  margin-right: auto;


}

.sidebar::-webkit-scrollbar {
  display: none;
}

/* Hide scrollbar for IE, Edge and Firefox */
.sidebar {
  -ms-overflow-style: none;
  /* IE and Edge */
  scrollbar-width: none;
  /* Firefox */
}
</style>
