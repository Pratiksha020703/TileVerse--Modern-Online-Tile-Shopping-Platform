// import { useNavigate } from "react-router-dom";
//  import "../styles/OrderPlaced.css";

// const OrderSuccess = () => {
//   const navigate = useNavigate();

//   return (
//     <div className="order-success-wrapper">
//       <div className="order-success-card">
//         <div className="success-icon">✔</div>

//         <h1>Order Placed Successfully!</h1>
//         <p className="success-text">
//           Thank you for your purchase. Your payment was successful and your
//           order is being processed.
//         </p>

//         <div className="order-info">
//           <p>
//             <span>Order ID:</span> #TV{Math.floor(Math.random() * 1000000)}
//           </p>
//           <p>
//             <span>Payment Status:</span> Paid
//           </p>
//           <p>
//             <span>Estimated Delivery:</span> 3–5 Business Days
//           </p>
//         </div>

//         <div className="success-actions">
//           <button
//             className="btn-outline"
//             onClick={() => navigate("/vieworders")}
//           >
//             View Orders
//           </button>

//           <button
//             className="btn-primary"
//             onClick={() => navigate("/products")}
//           >
//             Continue Shopping
//           </button>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default OrderSuccess;


import { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import api from "../api/axiosConfig";
import "../styles/OrderPlaced.css";

const OrderSuccess = () => {
  const navigate = useNavigate();
  const [params] = useSearchParams();
  const orderId = params.get("orderId");
  const [order, setOrder] = useState(null);

  useEffect(() => {
    api.get(`/api/orders/${orderId}`)
      .then(res => setOrder(res.data))
      .catch(() => navigate("/"));
  }, []);

  if (!order) return <h3>Loading...</h3>;

  return (
    <div className="order-success-wrapper">
      <div className="order-success-card">
        <div className="success-icon">✔</div>

        <h1>Order Placed Successfully!</h1>
        <p className="success-text">
          Thank you for your purchase. Your payment was successful and your
          order is being processed.
        </p>

        <div className="order-info">
          <p><span>Order ID:</span> #{order.orderId}</p>
          <p><span>Status:</span> {order.orderStatus}</p>
          <p><span>Order Date:</span> {new Date(order.orderDate).toLocaleDateString()}</p>
          <p><span>Total:</span> ₹{order.totalAmount}</p>
        </div>

        <div className="success-actions">
          <button className="btn-outline" onClick={() => navigate("/vieworders")}>
            View Orders
          </button>

          <button className="btn-primary" onClick={() => navigate("/products")}>
            Continue Shopping
          </button>
        </div>
      </div>
    </div>
  );
};

export default OrderSuccess;
