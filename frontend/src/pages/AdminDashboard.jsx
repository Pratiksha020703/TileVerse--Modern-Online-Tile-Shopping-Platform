// import { useState } from "react";
// import AdminProducts from "./admin/AdminProducts";
// import AdminInventory from "./admin/AdminInventory";
// import "../styles/AdminDashboard.css";

// const AdminDashboard = () => {
//   const [tab, setTab] = useState("products");

//   return (
//     <div className="admin-layout">

//       {/* LEFT MENU */}
//       <div className="admin-sidebar">
//         <button
//           className={tab === "products" ? "active" : ""}
//           onClick={() => setTab("products")}
//         >
//           Add Products
//         </button>

//         <button
//           className={tab === "inventory" ? "active" : ""}
//           onClick={() => setTab("inventory")}
//         >
//           Inventory
//         </button>
//       </div>

//       {/* RIGHT CONTENT */}
//       <div className="admin-content">
//         {tab === "products" && (
//           <AdminProducts goInventory={() => setTab("inventory")} />
//         )}

//         {tab === "inventory" && (
//           <AdminInventory goProducts={() => setTab("products")} />
//         )}
//       </div>

//     </div>
//   );
// };

// export default AdminDashboard;


import { useState } from "react";
import AdminProducts from "./admin/AdminProducts";
import AdminInventory from "./admin/AdminInventory";
import "../styles/AdminDashboard.css";

const AdminDashboard = () => {
  const [tab, setTab] = useState("products");

  return (
    <div className="admin-layout">

      {/* ❌ REMOVE SIDEBAR COMPLETELY */}
      {/* We use only in-page buttons now */}

      <div className="admin-content">
        {tab === "products" && (
          <AdminProducts goInventory={() => setTab("inventory")} />
        )}

        {tab === "inventory" && (
          <AdminInventory goProducts={() => setTab("products")} />
        )}
      </div>

    </div>
  );
};

export default AdminDashboard;
