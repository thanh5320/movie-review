import axios from "axios";
import {getToken} from "@/services/jwt";

export const key = "<YOUR_API_KEY>";
export const API = axios.create({
  baseURL: `http://localhost:8082/api/`,
  headers: {
    Authorization: `Bearer ${getToken()}`
  }
});

