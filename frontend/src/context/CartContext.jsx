import { createContext, useContext, useEffect, useState } from "react";
import api from "../api/axiosConfig";

const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState([]);

  // 🧡 Wishlist (localStorage)
  const [wishlist, setWishlist] = useState(() => {
    const saved = localStorage.getItem("wishlist");
    return saved ? JSON.parse(saved) : [];
  });

  useEffect(() => {
    localStorage.setItem("wishlist", JSON.stringify(wishlist));
  }, [wishlist]);

  /* ============ CART (BACKEND) ============ */

  const loadCart = async () => {
    try {
      const res = await api.get("/api/cart");
      setCart(res.data.items || []);
    } catch {
      setCart([]);
    }
  };

  const addToCart = async (productId) => {
    try {
      await api.post(`/api/cart/add/${productId}`);
      await loadCart();
      //alert("Product added to cart");
    } catch {
      alert("Failed to add product to cart");
    }
  };

  const removeFromCart = async (itemId) => {
    try {
      await api.delete(`/api/cart/remove/${itemId}`);
      await loadCart();
    } catch {
      alert("Failed to remove item");
    }
  };

  /* ============ WISHLIST (LOCAL) ============ */

  const addToWishlist = (product) => {
    const exists = wishlist.some(
      (item) => item.productId === product.productId
    );
    if (!exists) {
      setWishlist([...wishlist, product]);
      //alert("Added to wishlist");
    }
  };

  const removeFromWishlist = (productId) => {
    setWishlist(wishlist.filter((p) => p.productId !== productId));
  };

  useEffect(() => {
    loadCart();
  }, []);

  return (
    <CartContext.Provider
      value={{
        cart,
        addToCart,
        removeFromCart,
        wishlist,
        addToWishlist,
        removeFromWishlist
      }}
    >
      {children}
    </CartContext.Provider>
  );
};

export const useCart = () => useContext(CartContext);
