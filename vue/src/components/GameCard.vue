<template>
  <div class="parent">
    <div class="title">
      <h1 class="game_title_card">{{ game.name }}</h1>
    </div>

    <div v-if="game.summary != null" class="summary">
      <div> {{ game.summary }} </div>
    </div>

    <div class="cover" v-if="game.coverURL != null">
      <img v-bind:src="game.coverURL" class="cover">
    </div>

    <div class="lists">
      <h1>Lists</h1>
      <div v-if="userLists.length">
        <div v-for="userList in userLists" v-bind:key="userList.id">
          <div v-if="includes(userList, game)">
            <RemoveGameFromList v-bind:userList="userList" v-bind:game="game"></RemoveGameFromList>
          </div>
          <div v-else>
            <AddGameToList v-bind:userList="userList" v-bind:game="game"></AddGameToList>
          </div>
        </div>
      </div>
      <div v-else>
        <h1>Error retrieving lists for user</h1>
      </div>
    </div>

    <AddReview v-bind:game="game"></AddReview>


    <div class="otherInfo">
      <div v-if="game.websites.length">
        <div v-if="game.websites[0].length">
          <span><a v-bind:href="game.websites[0].url" target="_blank">Game Website</a></span>
        </div>
      </div>
      <br>
      <div v-if="game.involved_companies.length">
        <p>{{ game.involved_companies[0].company.name }}</p>
      </div>

      <p> Release Date: {{ convertUnixTimestamp(game.releaseDate) }}</p>
    </div>




    <div class="stats">

      <div class="statTop">
        <p class="rated">Rated: </p>
        <img v-bind:src="convertEsrb(game.esrbRating)" id="esrb_rating">
        <br><br>
        <div v-if="game.platforms.length">
          <span>Platform: </span>
          <span>{{ createStringList(game.platforms) }}</span>
        </div>
      </div>
      <br><br>
      <div v-if="game.genres.length">
        <span>Genre: </span>
        <span>{{ createStringList(game.genres) }}</span>
      </div>

    </div>

    <!--
    <div class="company">
      <h3 class="company_title main_child">{{ game.involved_companies[0].company.name }}</h3>
      <img v-bind:src="game.involved_companies[0].company.logo" class="company_logo">
    </div>
-->

  </div>
</template>
  
<script>
import AddReview from './AddReview.vue';
import AddGameToList from './AddGameToList.vue';
import RemoveGameFromList from './RemoveGameFromList.vue';

export default {
  components: {
    AddReview,
    AddGameToList,
    RemoveGameFromList
  },
  props: ['game', 'userLists'],
  computed: {
  },
  methods: {
    convertUnixTimestamp(gameRelease) {
      let date = new Date(gameRelease * 1000);
      return date.toLocaleDateString("en-US");
    },
    includes(userList, game) {
      return userList.games.find((listGame) => listGame.id === game.id);
    },
    convertEsrb(gameRating) {
      if (gameRating === 10) {
        return "/src/images/Teen.png";
      }
      if (gameRating === 9) {
        return "/src/images/Everyone10.png";
      }
      if (gameRating === 8) {
        return "/src/images/Everyone.png";
      }
      if (gameRating === 7) {
        return "/src/images/EarlyChildhood.png";
      }
    },
    createStringList(objectArray) {
      let count = 0;
      let objectList = objectArray.reduce((objectListBuilder, object) => {
        count = count + 1;
        if (count >= 5){
          return objectListBuilder
        }
        return objectListBuilder + "" + object.name + ", ";
      }, "");
      objectList = objectList.substring(0, objectList.length - 2);
      if (count >= 5){
        objectList = objectList + "..."
      }
      return objectList;
    },
    myFunction() {
      var x = document.getElementById("myDIV");
      if (x.style.display === "none") {
        x.style.display = "block";
      } else {
        x.style.display = "none";
      }
    }


  }
}
</script>

<style>
.game_title_card {
  text-align: center;
}

.lists>div {
  width: 300px;
}

.reviewLink {
  border-style: solid;
}

a {
  align-self: center;
}

#esrb_rating {
  width: 20%;
}

.parent {
  display: grid;
  /* grid-template-columns: repeat(4, 1fr); */
  grid-template-rows: 550x 300px;
  grid-column-gap: 0px;
  grid-row-gap: 0px;
  border-style: solid;


}

.title {
  grid-area: 1 / 1 / 2 / 6;
}

.lists {
  grid-area: 2/ 1 / 4 / 2;
}

.stats {
  grid-area: 2 / 2 / 4 / 3;
}

.cover {
  grid-area: 2 / 3 / 4 / 4;
}

.review {
  grid-area: 2 / 4 / 4 / 6;
}

.summary {
  grid-area: 3 / 1 / 6 / 4;
}

.company {
  grid-area: 1 / 1 / 3 / 3;
}

.otherInfo {
  grid-area: 2 /1 / 4 /2;
  margin-top: 90%;
}

.otherInfo {
  margin-left: 50px;
}

.title {
  border-style: solid;
  border-left: 0px;

  background-color: rgb(229, 122, 206);
  text-align: center;
  height: fit-content;

}

.summary {
  border-style: solid;
  overflow: hidden;
  height: fit-content;
  background-color: rgb(221, 111, 245);
  width: fit-content;
  justify-self: center;
  padding: 15px;
  margin-top: 20%;
  border-radius: 0px 10px 0px 0px;


}

.cover {
  justify-self: center;
  width: 80;
  border-radius: 5%;
  height: fit-content;
  margin-top: 5px;
}

.cover>img {
  border-style: solid;
}

.lists {
  margin-left: 10px;
  display: grid;
  justify-content: center;
  justify-items: center;
  align-content: center;
  align-items: stretch;
  width: fit-content;
  height: fit-content;
}

.review {
  padding: 10%;
  border-radius: 30%;
  display: block;
  text-align: center;
  border-style: solid;
  background-color: rgb(128, 147, 255);
  height: fit-content;


}

.form-input-group {
  display: inline-block;
  justify-content: space-around;
  align-content: end;

}



.stats {
  text-align: center;
  height: 60%;

}



.company {
  text-align: center;
  padding-right: 5px;
  border-bottom: 3px;
  border-top: 0px;
  border-right: 3px;
  border-left: 0px;
  border-style: solid;
  margin-top: 150px;
}

.company>img {
  border-style: solid;
  border-radius: 15%;
}
</style>
  

