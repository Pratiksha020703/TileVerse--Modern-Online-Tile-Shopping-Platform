// import { useEffect, useState } from "react";
// import api from "../api/axiosConfig";
// import "../styles/AdminInventory.css";

// const AdminInventory = () => {
//   const [lowStock, setLowStock] = useState([]);
//   const restock = async (productId) => {
//   const qty = prompt("Enter quantity to add:");

//   if (!qty || qty <= 0) return;

//   await api.put(`/api/admin/inventory/restock/${productId}?quantity=${qty}`);
//   loadLowStock();   // reload table
// };


//   useEffect(() => {
//     api.get("/api/admin/inventory/low")
//       .then(res => setLowStock(res.data))
//       .catch(err => {
//         console.error(err);
//         alert("Unauthorized – Admin only");
//       });
//   }, []);

//   return (
//     <div className="admin-inventory">
//       <h2>Low Stock Products ⚠️</h2>

//       {lowStock.length === 0 ? (
//         <p className="ok-stock">All products have sufficient stock 🎉</p>
//       ) : (
//         <table>
//           <thead>
//             <tr>
//               <th>Image</th>
//               <th>Product</th>
//               <th>Stock Left</th>
//               <th>Price</th>
//             </tr>
//           </thead>
//           <tbody>
//             {lowStock.map(inv => (
//               <tr key={inv.inventoryId}>
//                 <td>
//                   <img src={inv.product.imageUrl} alt="" />
//                 </td>
//                 <td>{inv.product.productName}</td>
//                  {/* <td className="danger">{inv.stockQuantity}</td> */}
//                  <td>
//   <span style={{ color: "red", fontWeight: "bold" }}>
//     {inv.stockQuantity}
//   </span>

//   <button
//     style={{
//       marginLeft: "10px",
//       padding: "5px 10px",
//       background: "#28a745",
//       color: "white",
//       border: "none",
//       borderRadius: "5px",
//       cursor: "pointer"
//     }}
//     onClick={() => restock(inv.product.productId)}
//   >
//     + Restock
//   </button>
// </td>

//                 <td>₹{inv.product.pricePerBox}</td>
//               </tr>
//             ))}
//           </tbody>
//         </table>
//       )}
//     </div>
//   );
// };

// export default AdminInventory;




// import { useEffect, useState } from "react";
// import api from "../api/axiosConfig";
// import "../styles/AdminInventory.css";

// const AdminInventory = () => {
//   const [lowStock, setLowStock] = useState([]);

//   // 🔥 Load low stock products
//   const loadLowStock = () => {
//     api.get("/api/admin/inventory/low")
//       .then(res => setLowStock(res.data))
//       .catch(err => {
//         console.error(err);
//         alert("Unauthorized – Admin only");
//       });
//   };

//   // 🔥 Restock button
//   const restock = async (productId) => {
//     const qty = prompt("Enter quantity to add:");

//     if (!qty || qty <= 0) return;

//     try {
//       await api.put(`/api/admin/inventory/restock/${productId}?quantity=${qty}`);
//       alert("Stock updated successfully ✅");
//       loadLowStock();   // 🔥 reload table
//     } catch (err) {
//       console.error(err);
//       alert("Failed to restock");
//     }
//   };

//   // 🔥 Load when page opens
//   useEffect(() => {
//     loadLowStock();
//   }, []);

//   return (
//     <div className="admin-inventory">
//       <h2>Low Stock Products ⚠️</h2>

//       {lowStock.length === 0 ? (
//         <p className="ok-stock">All products have sufficient stock 🎉</p>
//       ) : (
//         <table>
//           <thead>
//             <tr>
//               <th>Image</th>
//               <th>Product</th>
//               <th>Stock Left</th>
//               <th>Price</th>
//             </tr>
//           </thead>
//           <tbody>
//             {lowStock.map(inv => (
//               <tr key={inv.inventoryId}>
//                 <td>
//                   <img src={inv.product.imageUrl} alt="" />
//                 </td>
//                 <td>{inv.product.productName}</td>

//                 <td>
//                   <span style={{ color: "red", fontWeight: "bold" }}>
//                     {inv.stockQuantity}
//                   </span>

//                   <button
//                     style={{
//                       marginLeft: "10px",
//                       padding: "5px 10px",
//                       background: "#28a745",
//                       color: "white",
//                       border: "none",
//                       borderRadius: "5px",
//                       cursor: "pointer"
//                     }}
//                     onClick={() => restock(inv.product.productId)}
//                   >
//                     + Restock
//                   </button>
//                 </td>

//                 <td>₹{inv.product.pricePerBox}</td>
//               </tr>
//             ))}
//           </tbody>
//         </table>
//       )}
//     </div>
//   );
// };

// export default AdminInventory;


import { useEffect, useState } from "react";
import api from "../../api/axiosConfig";
import "../../styles/AdminInventory.css";

const AdminInventory = ({ goProducts }) => {
  const [products, setProducts] = useState([]);
  const [stockMap, setStockMap] = useState({});
  const [success, setSuccess] = useState("");


  useEffect(() => {
    loadInventory();
  }, []);

  const loadInventory = async () => {
    const res = await api.get("/api/products");
    const products = res.data;

    setProducts(products);

    // fetch stock for each product
    const map = {};
    for (let p of products) {
      try {
        const r = await api.get(`/api/inventory/product/${p.productId}`);
        map[p.productId] = r.data.stockQuantity;
      } catch {
        map[p.productId] = 0;
      }
    }
    setStockMap(map);
  };

 const updateStock = async (productId, newStock) => {
  try {
    await api.put(
      `/api/admin/inventory/restock/${productId}?quantity=${newStock}`
    );

    // ✅ Use React message instead of browser popup
    setSuccess("Stock updated successfully");

    // hide after 2 seconds
    setTimeout(() => setSuccess(""), 2000);

    loadInventory();
  } catch (err) {
    console.error(err);
    setSuccess("Stock update failed");

    setTimeout(() => setSuccess(""), 3000);
  }
};


  return (

    <div className="inventory-page">
      <h2>Inventory Management</h2>

      <button className="back-btn" onClick={goProducts}>
        ← Add Products
      </button>

    {success && (
  <div className="success-msg">
    {success}
  </div>
)}

      <table className="inventory-table">
        <thead>
          <tr>
            <th>Product</th>
            <th>Current Stock</th>
            <th>Update</th>
          </tr>
        </thead>

        <tbody>
          {products.map(p => (
            <tr key={p.productId}>
              <td>{p.productName}</td>

              <td>
                {stockMap[p.productId] ?? "Loading..."}
              </td>

              <td>
                <input
                  type="number"
                  min="0"
                  defaultValue={stockMap[p.productId] || 0}
                  onBlur={(e) =>
                    updateStock(p.productId, e.target.value)
                  }
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminInventory;
