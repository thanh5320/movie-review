import { API, key } from '@/services/api';
import {getToken} from "@/services/jwt";

const language = 'en-US';

export default {
  login(username, password){
    const url = 'auth/login';
    return API.post(url, {username: username, password: password})
  },
  getMe(token){
    const url = `user/me`;
    return API.get(url, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  },
  checkReview(userId, movieId){
    const url = `review/check-review/${movieId}/${userId}`;
    return API.get(url);
  },
  search(query, page) {
    const url = `movie/search?search=${query}&page=${page}`;
    return API.get(url);
  },
  getItemInfo(id, type) {
    const url = `movie/detail/${id}?type=${type}`;
    return API.get(url);
  },
  getCast(id, type) {
    const url = `${type}/${id}/credits?api_key=${key}&language=${language}`;
    return API.get(url);
  },
  getTop(type) {
    const url = `movie/top/10?type=${type}`;
    return API.get(url);
  },
  getMoviesUpcoming(page){
    const url = `movie/upcoming?type=film&page=1&page_size=${page}`;
    return API.get(url);
  },
  getMoviesLatest(page){
    const url = `movie/latest?api_key=${key}&language=${language}&page=${page}`;
    return API.get(url);
  },
  getTvShowsOnAir(page){
    const url = `movie/upcoming?type=tv&page=1&page_size=${page}`;
    return API.get(url);
  },
  addComment(userId, movieId, comment){
    const url = `comment/store`;
    return API.post(url, {user_id: userId, movie_id: movieId, comment: comment},
        {headers: {Authorization: `Bearer ${getToken()}` }}
    );
  },
  addReview(userId, movieId, rating){
    const url = `review/store`;
    return API.post(url, {rating: rating, movie_id: movieId, user_id: userId},
        {headers: {Authorization: `Bearer ${getToken()}` }}
    );
  },
  register(params){
    const url = `user/register`;
    return API.post(url, params, {headers: {Authorization: `Bearer ${getToken()}` }});
  }
};
