// src/api/ecommerceApi.js
import api from "./axiosConfig";
import publicApi from "./publicApi";

/* ================= PUBLIC ================= */
export const getProducts = () =>
  publicApi.get("/api/products");

/* ================= ADMIN ================= */
export const addProduct = (data) =>
  api.post("/api/products", data);

export const updateProduct = (id, data) =>
  api.put(`/api/products/${id}`, data);

export const deleteProduct = (id) =>
  api.delete(`/api/products/${id}`);

/* ================= WISHLIST (LOGIN REQUIRED) ================= */
export const addToWishlist = (productId) =>
  api.post(`/api/wishlist/${productId}`);

export const removeFromWishlist = (productId) =>
  api.delete(`/api/wishlist/${productId}`);

/* ================= CART (LOGIN REQUIRED) ================= */
export const addToCart = (productId) =>
  api.post(`/api/cart/add/${productId}`);

export const getCart = () =>
  api.get("/api/cart");

//export const getCart = () => api.get("/api/cart");
//export const addToCart = (productId) => api.post(`/api/cart/add/${productId}`);
export const removeFromCart = (itemId) =>
  api.delete(`/api/cart/remove/item/${itemId}`);


export const updateCartQuantity = (itemId, quantity) =>
  api.put(`/api/cart/update/${itemId}/${quantity}`);


/* ================= ORDERS (LOGIN REQUIRED) ================= */
export const placeOrder = () =>
  api.post("/api/orders/place");
