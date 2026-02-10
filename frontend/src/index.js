import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
// import App from "./App";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import AppRoutes from "./routes/AppRouter";
import { CartProvider } from "./context/CartContext";

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <CartProvider>
  <BrowserRouter>
    <AppRoutes />
  </BrowserRouter>
  </CartProvider>
);
