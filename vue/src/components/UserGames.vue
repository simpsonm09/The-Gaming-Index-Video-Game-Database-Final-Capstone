<template>
    <div class="list">
        <div v-for="userList in userLists" v-bind:key="userList.id" class="ok">
            <h1 class="userListTitle">{{ displayUserListName(userList) }}</h1>
            <div v-for="game in userList.games" v-bind:key="game.id" >
                <router-link v-bind:to="{ name: 'game', params: { gameId: game.id } }">
                    <div class="card" v-bind:key="game.id">
                        
                        <h1 class="game_title_list">{{ game.name }} ({{ convertUnixTimestamp(game.releaseDate) }}) </h1>
                        <div >
                            <img v-bind:src="game.coverURL"  class="small-image"></div>
                        <RemoveGameFromList v-bind:userList="userList" v-bind:game="game"></RemoveGameFromList>
                    </div>
                </router-link>
            </div>
        </div>
    </div>
</template>
    
<script>
import RemoveGameFromList from './RemoveGameFromList.vue';

export default {

    components: {
        RemoveGameFromList
    },
    props: ['userLists'],
    computed: {
    },
    methods: {
        convertUnixTimestamp(gameRelease) {
            let date = new Date(gameRelease * 1000);
            return date.toLocaleDateString("en-US", { year: 'numeric' });
        },
        displayUserListName(userList) {
            const listType = userList.listType;
            let output = "";
            if (listType === 1) {
                output = "Played";
            }
            if (listType === 2) {
                output = "Currently Playing";
            }
            if (listType === 3) {
                output = "Want to Play";
            }
            if (listType === 4) {
                output = userList.customType;
            }

            return output;
        }
    }
}
</script>
    
  
<style>
input {
    width: 80%;
}

.ok {
    max-width: 240px;
    min-width: 240px;

}

.list {
    display: flex;
}

.userListTitle {
    background-color: rgb(225, 149, 237);
    text-align: center;
    border-style: solid;
    margin: 0;
    height: 8vh;
    display: grid;
    align-items: center;
}

.small-image {
    border-radius: 5%;
    border-style: solid;
    max-width: 230px;
}

.card {
    border-style: solid;
    border-radius: 5%;
    border-color: rgb(0, 0, 0);
    background-color: #78b9ef;
    text-align: center;
    grid-template-columns: 1fr 1fr;


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

.game_title_list {
    color: black;
    display: inline-flex;
    text-decoration: none;
}

.images {
    display: inline-flex;
    justify-content: space-between;
    height: 100%;
}

.small-image {
    display: inline-flex;
    justify-content: space-between;
    height: 200px;
}




/* The grid container */

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