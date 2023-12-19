<template>
    <div id="game-search">
      <form v-on:submit.prevent="search">
        <div class="form-input-group">
          <div class="search"> 
          <label for="search">Game Search      </label>
          <input type="text" id="search" v-model="searchBox">
          
        </div>
        <button type="submit">Submit</button>
        </div>
        <p></p>
        
      </form>
    </div>
  </template>
  
<script>
export default {
  data() {
    return {
      searchBox: '',
    };
  },
  methods: {
    search(){
      this.$router.push({ name: 'game-search', query: { stringSearch: this.searchBox } })
    }
  }
};
</script>
  
<style scoped>

#game-search {
  background-color: rgb(215, 173, 230);
  text-align: center;
  border-style: solid;
}




</style>