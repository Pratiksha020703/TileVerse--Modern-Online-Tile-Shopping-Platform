import axios from "axios";

const AUTH_BASE = "http://localhost:5000/api/auth";

// 🔐 LOGIN
export const loginUser = (data) => {
  return axios.post(`${AUTH_BASE}/login`, data);
};

// 📝 REGISTER
export const registerUser = (data) => {
  return axios.post(`${AUTH_BASE}/register`, data);
};
