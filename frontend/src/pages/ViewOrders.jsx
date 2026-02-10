import React, { useEffect, useState } from "react";
import api from "../api/axiosConfig";
import { useNavigate } from "react-router-dom";
import "../styles/ViewOrders.css";

const ViewOrders = () => {
  const [orders, setOrders] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    api.get("/api/orders/my")
      .then(res => {
        console.log("Orders:", res.data);
        setOrders(res.data);
      })
      .catch(err => console.error(err));
  }, []);

  return (
    <div className="orders-page">
      <h2 className="orders-title">My Orders</h2>

      {orders.length === 0 ? (
        <p className="empty-text">You have no orders yet</p>
      ) : (
        orders.map(order => (
          <div className="order-card" key={order.orderId}>
            <div className="order-header">
              <div>
                <h4>Order ID: {order.orderId}</h4>
                <p className="order-date">
                  Placed on{" "}
                  {order.orderDate
                    ? new Date(order.orderDate).toLocaleDateString()
                    : "N/A"}
                </p>
              </div>

              <span className={`order-status ${(order.orderStatus || "pending").toLowerCase()}`}>
                {order.orderStatus || "PENDING"}
              </span>
            </div>

            <div className="order-footer">
              <h4>Total: ₹{order.totalAmount}</h4>
              <button
                className="order-btn"
                onClick={() => navigate(`/order/${order.orderId}`)}
              >
                View Details
              </button>
            </div>
          </div>
        ))
      )}
    </div>
  );
};

export default ViewOrders;
