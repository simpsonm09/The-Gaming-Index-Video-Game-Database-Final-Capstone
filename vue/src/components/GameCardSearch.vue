<template>
    <router-link v-bind:to="{ name: 'game', params: { gameId: game.id } }">
      <div class="searchCard" v-bind:key="game.id">
        <h1 class="game_title">{{ game.name }} ({{ convertUnixTimestamp(game.releaseDate) }}) </h1>
        <div class="small-image"><img v-bind:src="game.coverURL"></div></div>
    </router-link>


  </template>
    
  <script>
  
  
  export default {
    props: ['game'],
    computed: {
    },
    methods: {
      convertUnixTimestamp(gameRelease) {
        let date = new Date(gameRelease * 1000);
        return date.toLocaleDateString("en-US", { year: 'numeric' });
      },
    }
  }
  </script>
    
  
  <style>
  
  .game_title {
    text-align: left;
  }


  .searchCard {
    border-style: solid;
    background-color: #2196F3;
    display: grid;
    border-color: white;

    align-items: center;
    justify-items: center;

}


  .searchCard > img {


  }
  
  body {
    font-family: Arial, Helvetica, sans-serif;
    background: rgb(238, 174, 202);
    background: radial-gradient(circle, rgba(238, 174, 202, 1) 0%, rgba(148, 187, 233, 1) 100%);
  }
  
  .game_logo {
    height: 40vh;
    width: 30vw;
    border-radius: 18% 0% 6% 18%;
    display: flex;
  }
  
  .images {
    display: inline-flex;
    justify-content: right;
    height: 100%;
  }
  
  .small-image {
    display: inline-flex;
    justify-content: space-between;
    height: 200px;
  }
  
  
  
  @-webkit-keyframes AnimateBG {
    0% {
      background-position: 0% 50%;
    }
  
    50% {
      background-position: 100% 50%;
    }
  
    100% {
      background-position: 0% 50%;
    }
  }
  
  @keyframes AnimateBG {
    0% {
      background-position: 0% 50%;
    }
  
    50% {
      background-position: 100% 50%;
    }
  
    100% {
      background-position: 0% 50%;
    }
  }
  </style>