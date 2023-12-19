<template>
    <button v-on:click="removeGame">Remove Game from {{ displayUserListName(userList) }}</button>
</template>

<script>

import UserListService from '../services/UserListService';

export default {
    
    props: ['game', 'userList'],
    computed: {
        
    },
    methods: {
        removeGame() {
            UserListService.removeGameFromUserList(this.userList.id, this.game.id);
            window.location.reload();
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
};
</script>

<style>
</style>