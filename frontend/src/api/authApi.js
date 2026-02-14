// import axios from "axios";

// const AUTH_BASE = "http://localhost:5000/api/auth";

// // 🔐 LOGIN
// export const loginUser = (data) => {
//   return axios.post(`${AUTH_BASE}/login`, data);
// };

// // 📝 REGISTER
// export const registerUser = (data) => {
//   return axios.post(`${AUTH_BASE}/register`, data);
// };

import axios from "axios";

const authApi = axios.create({
  baseURL: process.env.REACT_APP_AUTH_URL,
  withCredentials: true
});

export const loginUser = (data) => {
  return authApi.post("/api/auth/login", data);
};

export const registerUser = (data) => {
  return authApi.post("/api/auth/register", data);
};

// FORGOT PASSWORD
export const forgotPassword = (email) => {
  return authApi.post("/api/auth/forgot-password", { email });
};

// RESET PASSWORD
export const resetPassword = (token, newPassword) => {
  return authApi.post("/api/auth/reset-password", {
    token: token,
    newPassword: newPassword
  });
};
