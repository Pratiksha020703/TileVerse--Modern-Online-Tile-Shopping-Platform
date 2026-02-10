import React from "react";
import api from "../api/axiosConfig";
import { useNavigate } from "react-router-dom";

const Payment = () => {
  const navigate = useNavigate();

  const handlePayment = async () => {
    try {
      // 1️⃣ Get cart total
      const cartRes = await api.get("/api/cart/total");
      const amount = cartRes.data;   // eg 1700

      // 2️⃣ Create Razorpay order
      const res = await api.post("/api/payment/create", { amount });

      const order = res.data.order;
const key = res.data.key;
const internalOrderId = res.data.internalOrderId;

localStorage.setItem("orderId", internalOrderId);


      // 🔥 Store internal order id if your backend created one
      // localStorage.setItem("orderId", order.internalOrderId);

      const options = {
        key: key,
        amount: order.amount,
        currency: order.currency,
        name: "Tileverse",
        description: "Tile Purchase",
        order_id: order.id,

        // handler: async function (response) {
        //   try {
        //     await api.post("/api/payment/verify", {
        //       razorpay_order_id: response.razorpay_order_id,
        //       razorpay_payment_id: response.razorpay_payment_id,
        //       razorpay_signature: response.razorpay_signature,
        //       orderId: localStorage.getItem("orderId") // if using Orders table
        //     });

        //     alert("Payment Successful 🎉");
        //     navigate("/order-success");

        //   } catch (err) {
        //     alert("Payment verification failed ❌");
        //   }
        // },

     handler: async function (response) {
  try {
    const res = await api.post("/api/payment/verify", {
      razorpay_order_id: response.razorpay_order_id,
      razorpay_payment_id: response.razorpay_payment_id,
      razorpay_signature: response.razorpay_signature,
      orderId: localStorage.getItem("orderId")
    });

    // 🔥 This line fixes everything
    window.location.href = `/order-success?orderId=${res.data.orderId}`;

  } catch (err) {
    console.error(err);
    alert("Payment verification failed ❌");
  }
},



        prefill: {
          email: "user@gmail.com",
          contact: "9999999999"
        },

        theme: {
          color: "#1e293b"
        }
      };

      const rzp = new window.Razorpay(options);
      rzp.open();

    } catch (err) {
      console.error(err);
      alert("Payment Failed ❌");
    }
  };

  return (
    <div style={{ textAlign: "center", padding: "50px" }}>
      <h2>Complete Payment</h2>
      <button
        onClick={handlePayment}
        style={{
          padding: "15px 30px",
          background: "#1e293b",
          color: "white",
          border: "none",
          fontSize: "18px",
          cursor: "pointer"
        }}
      >
        Pay with Razorpay
      </button>
    </div>
  );
};

export default Payment;
