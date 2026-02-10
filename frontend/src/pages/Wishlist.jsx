// import React from "react";
// import { useCart } from "../context/CartContext";
// import "../styles/Wishlist.css";
// import toast from "react-hot-toast";


// const Wishlist = () => {
//   const { wishlist, addToCart, removeFromWishlist } = useCart();

//   return (
//     <div className="wishlist-page">
//       <h2>My Wishlist</h2>

//       {wishlist.length === 0 && (
//         <p className="empty-text">Your wishlist is empty</p>
//       )}

//       <div className="wishlist-grid">
//         {wishlist.map((item) => (
//           <div className="wishlist-card" key={item.id}>
//             <img src={item.imageUrl} alt={item.productName} />

//             <div className="wishlist-info">
//               <h4>{item.productName}</h4>
//               <p>₹ {item.pricePerBox}</p>

//               <div className="wishlist-actions">
//                 <button
//                     className="move-btn"
//                     onClick={() => {
//                       addToCart(item.productId);
//                       removeFromWishlist(item.productId);
//                       toast.success("Product added to cart 🛒");
//                     }}
//                   >
//                     Move to Cart
//                   </button>


//                 <button
//                   className="remove-btn"
//                   onClick={() => removeFromWishlist(item.productId)}
//                 >
//                   Remove
//                 </button>
//               </div>
//             </div>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// };

// export default Wishlist;


import { useEffect, useState } from "react";
import api from "../api/axiosConfig";
import "../styles/Wishlist.css";

const Wishlist = () => {
  const [wishlist, setWishlist] = useState([]);

  const load = async () => {
    const res = await api.get("/api/wishlist");
    setWishlist(res.data);
  };

  useEffect(() => {
    load();
  }, []);

  const remove = async (productId) => {
    await api.delete("/api/wishlist/remove/" + productId);
    load();
  };

  const moveToCart = async (productId) => {
    await api.post("/api/cart/add/" + productId);
    await remove(productId);
  };

  return (
    <div className="wishlist-page">
      <h2>My Wishlist</h2>

      {wishlist.length === 0 && <p>No items</p>}

      <div className="wishlist-grid">
        {wishlist.map(w => (
          // <div className="wishlist-card" key={w.wishlistId}>
          //   <img src={w.product.imageUrl} />

          //   <h4>{w.product.productName}</h4>
          //   <p>₹{w.product.pricePerBox}</p>

          //   <button onClick={() => moveToCart(w.product.productId)}>
          //     Move to Cart
          //   </button>

          //   <button onClick={() => remove(w.product.productId)}>
          //     Remove
          //   </button>
          // </div>
          <div className="wishlist-card" key={w.wishlistId}>
  <img src={w.product.imageUrl} alt="" />

  <div className="wishlist-info">
    <h4>{w.product.productName}</h4>
    <div className="price">₹{w.product.pricePerBox}</div>

    <div className="wishlist-actions">
      <button
        className="move-btn"
        onClick={() => moveToCart(w.product.productId)}
      >
        Move to Cart
      </button>

      <button
        className="remove-btn"
        onClick={() => remove(w.product.productId)}
      >
        Remove
      </button>
    </div>
  </div>
</div>

        ))}
      </div>
    </div>
  );
};

export default Wishlist;

