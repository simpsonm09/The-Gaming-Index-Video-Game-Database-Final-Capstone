<template>

    <button v-on:click="addGame" >Add Game to {{ displayUserListName(userList) }}</button>
    
</template>

<script>

import UserListService from '../services/UserListService';

export default {
    
    props: ['game', 'userList'],
    methods: {
        addGame() {
            UserListService.addGameToUserList(this.userList.id, this.game.id);
            this.$router.push({name: "games"});
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
    },
};
</script>

<style>
</style>