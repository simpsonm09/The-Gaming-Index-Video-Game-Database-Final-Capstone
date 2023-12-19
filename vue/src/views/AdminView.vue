<template>
    <div class="admin">
        <h1>Profile</h1>
        <p>Welcome to your admin settings {{ $store.state.user.username }}!</p>
        <p>Your current token: {{ $store.state.token }}</p>
        <p>Your current role: {{ $store.state.user.authorities[0].name}}</p>
    </div>
    <div>
<ProfileSearch></ProfileSearch>
  </div>
</template>

<script>
import ProfileSearch from '../components/ProfileSearch.vue';

export default {
  components: {
    ProfileSearch
  }
};
</script>