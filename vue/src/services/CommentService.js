import axios from 'axios';

export default {

  getCommentsByReviewId(reviewId) {
    return axios.get(`/review/${reviewId}/comments`);
  },

  getCommentById(id){
    return axios.get(`/comment/${id}`);
  },

  addComment(comment){
    return axios.post(`/comment`, comment)
  },

  editComment(id, comment){
    return axios.put(`/comment/${id}`, comment)
  },

  deleteCommentById(id){
    return axios.delete(`/comment/${id}`)
  }
}