import axios from 'axios';

export default {

  getGameById(id) {
    return axios.get(`/game/${id}`);
  },

  getGamesByString(searchString) {
    return axios.get(`/game?stringSearch=${searchString}`)
  },

  deleteGameById(id){
    return axios.delete(`/game/${id}`);
  },

  getRandomGame(){
    return axios.get(`/randomGame`);
  }

}