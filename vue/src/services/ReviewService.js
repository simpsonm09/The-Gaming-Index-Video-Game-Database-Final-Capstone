import axios from 'axios';

export default {

  getReviewsByGameId(gameId){
    return axios.get(`/game/${gameId}/reviews`)
  },

  getReviewById(id) {
    return axios.get(`/review/${id}`);
  },

  addReview(review){
    return axios.post(`/review/`, review)
  },

  editReview(id, review){
    return axios.put(`/review/${id}`, review)
  },

  deleteReview(id) {
    return axios.delete(`/review/${id}`);
  }
}