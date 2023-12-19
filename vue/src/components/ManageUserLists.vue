<template>
    <div class="custom">
        <div v-if="userLists.length < 6">
            <h1>Manage Custom Lists</h1>
            <form v-on:submit.prevent="addNewList">Add New List
                <input type="text" v-model="newList.customType">
                <button type="submit">Add New Custom List</button>
            </form>
        </div>
        <div v-else class="custom-list-warning">
            <h1>Custom List limit met</h1>
        </div>
        <div v-show="userLists.length > 3">
            <h2>Current Lists</h2>
            <div>Rename <input type="text" v-model="renamedList.customType"></div>
            <div v-for="userList in userLists" v-bind:key="userList.id">
                <div v-if="UserListTypeIsCustom(userList)">
                    <h3>{{ displayUserListName(userList) }}</h3>
                    <button v-on:click="renameList(userList.id)">Rename List</button>
                    <button v-on:click="deleteSelectedList(userList.id)">Delete {{ displayUserListName(userList) }}</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import UserListService from '../services/UserListService';

export default {
    data() {
        return {
            newList: {},
            renamedList: {}
        };
    },
    props: ['userLists'],
    methods: {
        addNewList() {
            if (!this.newList.customType) {
                this.newList.customType = "Custom List"
            }
            this.newList.listType = 4;
            this.newList.userId = this.$store.state.user.id;
            UserListService.createUserList(this.newList);
            this.clearNewList();
            window.location.reload();
        },
        clearNewList() {
            this.newList = {};
        },
        renameList(userListId) {
            if (!this.renamedList.customType) {
                this.renamedList.customType = "Custom List"
            }
            this.renamedList.listType = 4;
            this.renamedList.userId = this.$store.state.user.id;
            this.renamedList.id = userListId;
            UserListService.editUserList(userListId, this.renamedList);
            this.clearNewList();
            window.location.reload();
        },
        deleteSelectedList(userListId) {
            UserListService.deleteUserList(userListId);
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
        },
        UserListTypeIsCustom(userList) {
            return (userList.listType === 4);
        }

    }
}

</script>

<style>
.together {
    display: flex;


}
.custom {

    border-left: 0;
    border-top: 0;
    max-width: 250px;
    min-width: 200px;
}

.custom-list-warning {
    color: blue;
}


</style>