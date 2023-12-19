import axios from 'axios';

export default {

  getUserListsByUserId(userId){
    return axios.get(`/user/${userId}/userlists`)
  },

  getUserListByGameIdUserId(userId, gameId) {
    return axios.get(`/user/${userId}/game/${gameId}/userlists`);
  },

  getUserListById(id){
    return axios.get(`userlist/${id}`)
  },

  createUserList(userlist){
    return axios.post(`/userlist/`, userlist)
  },

  editUserList(id, userlist){
    return axios.put(`/userlist/${id}`, userlist)
  },

  addGameToUserList(listId, gameId){
    return axios.post(`userlistAdd/${listId}/${gameId}`)
  },

  removeGameFromUserList(listId, gameId){
    return axios.delete(`userlistRemove/${listId}/${gameId}`)
  },

  deleteUserList(id) {
    return axios.delete(`/userlist/${id}`);
  }
}