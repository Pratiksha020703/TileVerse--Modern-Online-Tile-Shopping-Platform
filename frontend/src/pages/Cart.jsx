import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axiosConfig";
import { updateCartQuantity } from "../api/ecommerceApi";


import "../styles/Checkout.css";

const Checkout = () => {
  const [cart, setCart] = useState(null);
  const navigate = useNavigate();

  const loadCart = async () => {
    try {
      const res = await api.get("/api/cart");
      
      setCart(res.data);
    } catch {
      setCart({ items: [] });
    }
  };

  useEffect(() => {
    loadCart();
  }, []);

  // 🔥 FIXED REMOVE FUNCTION
  const handleRemove = async (itemId) => {
  try {
    await api.delete(`/api/cart/remove/item/${itemId}`);
    loadCart();
  } catch (err) {
    console.log(err);
    alert("Failed to remove item");
  }
};

  const checkAddressAndProceed = async () => {
    try {
      const res = await api.get("/api/user/address");

      if (!res.data) {
        navigate("/checkout/address");
      } else {
        navigate("/payment");
      }
    } catch {
      navigate("/checkout/address");
    }
  };

  const updateQty = async (itemId, qty) => {
    if (qty < 1) return;
  try {
    //await api.put(`/api/cart/update/${itemId}/${qty}`);
    await updateCartQuantity(itemId, qty);

    loadCart();
  } catch {
    alert("Failed to update");
  }
};


  const total =
    cart?.items?.reduce(
      (sum, i) => sum + i.product.pricePerBox * i.quantity,
      0
    ) || 0;

  return (
    <div className="checkout-page">
      <h2>My Cart</h2>

      {(!cart || cart.items.length === 0) && <p>Your cart is empty</p>}

      {cart?.items.map((item) => (
        <div className="checkout-item" key={item.cartItemId}>
          <img src={item.product.imageUrl} alt="" />

          <div className="checkout-info">
            <h4>{item.product.productName}</h4>
            <p>₹{item.product.pricePerBox}</p>
            <div className="qty-control">
  <button onClick={() => updateQty(item.cartItemId, item.quantity - 1)}>-</button>
  <span>{item.quantity}</span>
  <button onClick={() => updateQty(item.cartItemId, item.quantity + 1)}>+</button>
</div>


            <div className="checkout-actions">
              <span
                className="remove-btn"
               onClick={() => handleRemove(item.cartItemId)}
  // 🔥 THIS WAS WRONG EARLIER
              >
                REMOVE
              </span>
            </div>
          </div>
        </div>
      ))}

      {cart?.items.length > 0 && (
        <div className="checkout-summary">
          <h3>Total: ₹{total}</h3>

          <button className="buy-now-btn" onClick={checkAddressAndProceed}>
            Proceed To Checkout
          </button>
        </div>
      )}
    </div>
  );
};

export default Checkout;
