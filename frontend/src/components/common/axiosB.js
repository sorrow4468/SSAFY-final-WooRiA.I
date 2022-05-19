import axios from "axios";

let token = localStorage.getItem('accessToken') || '';
// axios 객체 생성
export default axios.create({
  //baseURL: "http://localhost:8080/",
  baseURL: "",
  headers: {
    "Content-type": "application/json",
    "Authorization": token
  },
  withCredentials: true
});