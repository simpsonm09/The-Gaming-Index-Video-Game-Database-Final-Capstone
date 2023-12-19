<template>
    <div id="profile-search">
      <form v-on:submit.prevent="search">
        <div class="form-input-group">
          <label for="search">Profile Search      </label>
          <input type="text" id="search" v-model="searchBox"/>
        </div>
        <p></p>
        <button type="submit">Submit</button>
      </form>
      <p v-if="currentSearchString">
        You last searched for {{ currentSearchString }}!
      </p>
    </div>
  </template>
  
<script>
export default {
  data() {
    return {
      searchBox: '',
      currentSearchString: ''
    };
  },
  methods: {
    search(){
      this.currentSearchString = this.searchBox;
      this.searchBox = '';

    }
  }
};
</script>
  
<style scoped>

</style>