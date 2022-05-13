import axios from "axios";

export const key = "<YOUR_API_KEY>";
export const API = axios.create({
  baseURL: `http://localhost:8082/api/`
});
