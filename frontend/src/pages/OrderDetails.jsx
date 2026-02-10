// import { useEffect, useState } from "react";
// import { useParams } from "react-router-dom";
// import api from "../api/axiosConfig";
// import "../styles/ViewOrders.css";

// const OrderDetails = () => {
//   const { orderId } = useParams();
//   const [items, setItems] = useState([]);

//   useEffect(() => {
//     api.get(`/api/orders/${orderId}/items`)
//       .then(res => setItems(res.data))
//       .catch(err => console.error("Failed to load order items", err));
//   }, [orderId]);

//   return (
//     <div className="orders-page">
//       <h2 className="orders-title">Order #{orderId}</h2>

//       {items.length === 0 && <p>No items found</p>}

//       {items.map(item => (
//         <div className="order-card" key={item.orderItemId}>
//           <div className="order-items">
//             <div className="order-item">
//               <img src={item.product.imageUrl} alt="" />

//               <div className="item-info">
//                 <h5>{item.product.productName}</h5>
//                 <p>₹{item.price}</p>
//                 <span>Qty: {item.quantity}</span>
//               </div>
//             </div>
//           </div>
//         </div>
//       ))}
//     </div>
//   );
// };

// export default OrderDetails;


import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../api/axiosConfig";
import "../styles/ViewOrders.css";

const OrderDetails = () => {
  const { orderId } = useParams();
  const [items, setItems] = useState([]);

  useEffect(() => {
    api.get(`/api/orders/${orderId}/items`)
      .then(res => setItems(res.data))
      .catch(err => console.error("Failed to load order items", err));
  }, [orderId]);

  return (
    <div className="orders-page">
      <h2 className="orders-title">Order #{orderId}</h2>

      {items.length === 0 && <p>No items found</p>}

      {items.map(item => (
        <div className="order-card" key={item.orderItemId}>
          <div className="order-items">
            <div className="order-item">
              
              {/* 🔥 FIXED */}
              <img src={item.imageUrl} alt={item.productName} />

              <div className="item-info">
                <h5>{item.productName}</h5>
                <p>₹{item.price}</p>
                <span>Qty: {item.quantity}</span>
              </div>

            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default OrderDetails;
