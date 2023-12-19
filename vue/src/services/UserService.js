import axios from 'axios';

export default {

  getUserById(id) {
    return axios.get(`/user/${id}`)
  }
}