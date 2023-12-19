<template>
  <div id="capstone-app">
    <Header id="header"></Header>
    <GameSearch v-if="$store.state.token != ''" id="game-search"></GameSearch>
    <SideBar v-if="$store.state.token != ''" id="nav"></SideBar>
    
    <Footer id="footer"></Footer>
    <router-view id="main-content"></router-view>
  </div>
</template>

<script>
import Header from "./components/Header.vue";
import GameSearch from './components/GameSearch.vue';
import SideBar from './components/SideBar.vue';
import Footer from "./components/Footer.vue";

export default {
  components: {
    Header,
    GameSearch,
    SideBar,
    Footer
  }
};



</script>

<style>
* {
  font-family:Verdana, Geneva, Tahoma, sans-serif;
  font-size: xx-large;
}

#capstone-app {
  position: relative;
  display: grid;
  /* grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;*/
  grid-template-areas:
    "header header header header header  "
    "nav search search search search "
    "nav content content content content "
    "nav content content content content " 
    "nav content content content content "
    "footer footer footer footer footer ";
}


#header {
  grid-area: header;
}

#game-search {
  grid-area: search;
}

#nav {
  grid-area: nav;
}

#main-content {
  grid-area: content;
}

#footer {
  grid-area: footer;
}


.content {
  background-color: rgba(255, 255, 255, .8);
  border-radius: .25em;
  box-shadow: 0 0 .25em rgba(0, 0, 0, .25);
  box-sizing: border-box;
  padding: 10vmin;
  text-align: center;
  top: 50%;
  transform: translate(-50%, -50%);
}


.bg {
  animation: slide 30s ease-in-out infinite alternate;
  background-image: linear-gradient(-60deg, rgb(255, 51, 218) 50%, rgb(229, 255, 0) 60%);
  bottom: 0;
  left: -50%;
  opacity: .5;
  position: fixed;
  right: -50%;
  top: 0;
  z-index: -1;
}

button {
  background-color: #F084E4;
  border: solid;
  border-radius: 8px;
  padding: 10px 24px;
  font-size: 20px;
  color: black;
  font-weight: bold;
  display: inline-flex;
  align-self: center;
  cursor: pointer;
  transition-duration: 0.4s;
}

button:active {
  transform: scale(0.90);
}

button:hover {
  background-color: cyan
}


@keyframes slide {
  0% {
    transform: translateX(-25%);
  }

  100% {
    transform: translateX(25%);
  }
}
</style>

