<template>
  <div class="home">
    <h1>Home</h1>
    <p>Welcome to our website, {{ $store.state.user.username }}!</p>
    <p>Begin by searching for games to document ones you've played, are playing, and want to play.</p>
  </div>
  <!-- <div class="bg"></div>
  <div class="bg bg2"></div>
  <div class="bg bg3"></div> -->
</template>
<script>
export default {
  components: {
  }
};
</script>
<style>


.home {
  text-align: center;

}
</style>