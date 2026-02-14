// import axios from "axios";

// const publicApi = axios.create({
//   baseURL: "http://localhost:8080",
// });

// export default publicApi;


import axios from "axios";

const publicApi = axios.create({
  baseURL: process.env.REACT_APP_BACKEND_URL,
});

export default publicApi;
