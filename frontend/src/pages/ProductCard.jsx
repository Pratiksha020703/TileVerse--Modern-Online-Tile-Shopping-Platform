// import { useNavigate } from "react-router-dom";
// import { useCart } from "../context/CartContext";
// import "../styles/ProductCard.css";

// const ProductCard = ({ product }) => {
//   const { addToCart } = useCart();
//   const navigate = useNavigate();

//   const openProduct = () => {
//     navigate("/productdetails", {
//       state: { product },
//     });
//   };

//   return (
//     <article className="product-card" onClick={openProduct}>
//       <img
//         className="product-image"
//         src={product.image}
//         alt={product.name}
//       />

//       <div className="product-body">
//         <p className="product-name">{product.name}</p>

//         <div className="product-meta">
//           <div className="size-badge">{product.size}</div>
//           <small className="text-muted">{product.category}</small>
//         </div>

//         <div className="product-footer">
//           <div className="price">{product.price}</div>

//           {/* stop click from bubbling */}
//           <button
//             className="btn"
//             onClick={(e) => {
//               e.stopPropagation();
//               addToCart(product);
//             }}
//           >
//             Add
//           </button>
//         </div>
//       </div>
//     </article>
//   );
// };

// export default ProductCard;


// import { useNavigate } from "react-router-dom";
// import { useCart } from "../context/CartContext";
// import { useEffect, useState } from "react";
// import "../styles/ProductCard.css";

// const ProductCard = ({ product }) => {
//   const {
//     addToCart,
//     addToWishlist,
//     removeFromWishlist,
//     wishlist
//   } = useCart();

//   const navigate = useNavigate();
//   const [isWishlisted, setIsWishlisted] = useState(false);

//   /* ================= WISHLIST CHECK ================= */
//   useEffect(() => {
//     const exists = (wishlist || []).some(
//   (item) => item.productId === product.productId

//     );
//     setIsWishlisted(exists);
//   }, [wishlist, product.productId]);

//   /* ================= NAVIGATION ================= */
//   // const openProduct = () => {
//   //   navigate("/productdetails", {
//   //     state: { product }   // ✅ CORRECT PLACE
//   //   });
//   // };

//   const openProduct = () => {
//   navigate(`/productdetails/${product.productId}`);
// };


//   /* ================= WISHLIST TOGGLE ================= */
//   const toggleWishlist = (e) => {
//     e.stopPropagation();

//     if (isWishlisted) {
//       removeFromWishlist(product.productId);
//     } else {
//       addToWishlist(product);
//     }
//   };

//   return (
//     <article className="product-card" onClick={openProduct}>
//       {/* IMAGE */}
//       <img
//         className="product-image"
//         src={product.imageUrl}
//         alt={product.productName}
//       />

//       <div className="product-body">
//         {/* NAME */}
//         <p className="product-name">{product.productName}</p>

//         {/* META */}
//         <div className="product-meta">
//           <div className="size-badge">{product.size}</div>
//           <small className="text-muted">
//             {product.category?.categoryName}
//           </small>
//         </div>

//         {/* MATERIAL */}
//         {product.material && (
//           <p className="product-desc">
//             Material: {product.material}
//           </p>
//         )}

//         {/* FOOTER */}
//         <div className="product-footer">
//           <div className="price">₹{product.pricePerBox}</div>

//           <div className="buttons">
//             {/* ADD TO CART */}
//             <button
//               className="btn add-cart-btn"
//               onClick={(e) => {
//                 e.stopPropagation();
//                 addToCart(product.productId);
//               }}
//             >
//               Add
//             </button>

            

//             {/* WISHLIST */}
//             <button
//               className={`wishlist-btn ${isWishlisted ? "active" : ""}`}
//               onClick={toggleWishlist}
//             >
//               {isWishlisted ? "♥" : "♡"}
//             </button>
//           </div>
//         </div>
//       </div>
//     </article>
//   );
// };

// export default ProductCard;



// import { useNavigate } from "react-router-dom";
// import { useEffect, useState } from "react";
// import api from "../api/axiosConfig";              // ✅ ADDED
// import "../styles/ProductCard.css";

// const ProductCard = ({ product }) => {
//   const navigate = useNavigate();
//   const [isWishlisted, setIsWishlisted] = useState(false);

//   /* ================= NAVIGATION ================= */
//   const openProduct = () => {
//     navigate(`/productdetails/${product.productId}`);   // ✅ FIXED
//   };

//   /* ================= LOAD WISHLIST STATUS ================= */
//   useEffect(() => {
//     api.get("/api/wishlist")
//       .then(res => {
//         const exists = res.data.some(
//           w => w.product.productId === product.productId
//         );
//         setIsWishlisted(exists);
//       })
//       .catch(() => {});
//   }, [product.productId]);

//   /* ================= WISHLIST TOGGLE ================= */
//   const toggleWishlist = async (e) => {
//     e.stopPropagation();   // ✅ stop product page opening

//     try {
//       if (isWishlisted) {
//         await api.delete("/api/wishlist/remove/" + product.productId);
//         setIsWishlisted(false);
//       } else {
//         await api.post("/api/wishlist/add/" + product.productId);
//         setIsWishlisted(true);
//       }
//     } catch (err) {
//       console.error(err);
//       alert("Wishlist failed");
//     }
//   };

//   return (
//     <article className="product-card" onClick={openProduct}>
//       {/* IMAGE */}
//       <img
//         className="product-image"
//         src={product.imageUrl}
//         alt={product.productName}
//       />

//       <div className="product-body">
//         {/* NAME */}
//         <p className="product-name">{product.productName}</p>

//         {/* META */}
//         <div className="product-meta">
//           <div className="size-badge">{product.size}</div>
//           <small className="text-muted">
//             {product.category?.categoryName}
//           </small>
//         </div>

//         {/* MATERIAL */}
//         {product.material && (
//           <p className="product-desc">
//             Material: {product.material}
//           </p>
//         )}

//         {/* FOOTER */}
//         <div className="product-footer">
//           <div className="price">₹{product.pricePerBox}</div>

//           <div className="buttons">
//             {/* ADD TO CART */}
//             <button
//               className="btn add-cart-btn"
//               onClick={(e) => {
//                 e.stopPropagation();                // ✅ stop navigation
//                 api.post("/api/cart/add/" + product.productId);
//                 alert("Product added to cart");
//               }}
//             >
//               Add
//             </button>

//             {/* WISHLIST */}
//             <button
//               className={`wishlist-btn ${isWishlisted ? "active" : ""}`}
//               onClick={toggleWishlist}
//             >
//               {isWishlisted ? "♥" : "♡"}
//             </button>
//           </div>
//         </div>
//       </div>
//     </article>
//   );
// };

// export default ProductCard;




// import { useNavigate } from "react-router-dom";
// import { useEffect, useState } from "react";
// import api from "../api/axiosConfig";
// import "../styles/ProductCard.css";

// const ProductCard = ({ product }) => {
//   const navigate = useNavigate();

//   const [isWishlisted, setIsWishlisted] = useState(false);
//   const [stock, setStock] = useState(null);   // 🔥 inventory state

//   /* ================= NAVIGATION ================= */
//   const openProduct = () => {
//     navigate(`/productdetails/${product.productId}`);
//   };

//   /* ================= LOAD WISHLIST STATUS ================= */
//   useEffect(() => {
//     api.get("/api/wishlist")
//       .then(res => {
//         const exists = res.data.some(
//           w => w.product.productId === product.productId
//         );
//         setIsWishlisted(exists);
//       })
//       .catch(() => {});
//   }, [product.productId]);

//   /* ================= LOAD INVENTORY ================= */
//   useEffect(() => {
//     api.get("/api/inventory/product/" + product.productId)
//       .then(res => {
//         setStock(res.data.stockQuantity);
//       })
//       .catch(() => setStock(0));
//   }, [product.productId]);

//   /* ================= WISHLIST TOGGLE ================= */
//   const toggleWishlist = async (e) => {
//     e.stopPropagation();

//     try {
//       if (isWishlisted) {
//         await api.delete("/api/wishlist/remove/" + product.productId);
//         setIsWishlisted(false);
//       } else {
//         await api.post("/api/wishlist/add/" + product.productId);
//         setIsWishlisted(true);
//       }
//     } catch (err) {
//       console.error(err);
//       alert("Wishlist failed");
//     }
//   };

//   return (
//     <article className="product-card" onClick={openProduct}>
//       {/* IMAGE */}
//       <img
//         className="product-image"
//         src={product.imageUrl}
//         alt={product.productName}
//       />

//       <div className="product-body">
//         {/* NAME */}
//         <p className="product-name">{product.productName}</p>

//         {/* META */}
//         <div className="product-meta">
//           <div className="size-badge">{product.size}</div>
//           <small className="text-muted">
//             {product.category?.categoryName}
//           </small>
//         </div>

//         {/* 🔥 INVENTORY STATUS */}
//         {stock !== null && (
//           <p
//             className="stock-text"
//             style={{
//               color: stock === 0 ? "red" : stock < 5 ? "orange" : "green",
//               fontWeight: "bold"
//             }}
//           >
//             {stock === 0
//               ? "Out of Stock"
//               : stock < 5
//               ? `Only ${stock} left`
//               : "In Stock"}
//           </p>
//         )}

//         {/* MATERIAL */}
//         {product.material && (
//           <p className="product-desc">
//             Material: {product.material}
//           </p>
//         )}

//         {/* FOOTER */}
//         <div className="product-footer">
//           <div className="price">₹{product.pricePerBox}</div>

//           <div className="buttons">
//             {/* ADD TO CART */}
//             {/* <button
//               className="btn add-cart-btn"
//               disabled={stock === 0}
//               style={{ opacity: stock === 0 ? 0.5 : 1 }}
//               onClick={(e) => {
//                 e.stopPropagation();
//                 if (stock === 0) return;
//                 api.post("/api/cart/add/" + product.productId);
//                 alert("Product added to cart");
//               }}
//             >
//               {stock === 0 ? "Out of Stock" : "Add"}
//             </button> */}
//             <button
//   className="btn add-cart-btn"
//   disabled={product.stock === 0}
//   style={{ opacity: product.stock === 0 ? 0.5 : 1 }}
//   onClick={(e) => {
//     e.stopPropagation();
//     if (product.stock === 0) return;
//     api.post("/api/cart/add/" + product.productId);
//     alert("Product added");
    
//   }}
// > 
//   {product.stock === 0 ? "Out of Stock" : "Add"}
  
// </button>

// <p
//   style={{
//     color: product.stock === 0 ? "red" : product.stock < 5 ? "orange" : "green",
//     fontWeight: "bold"
//   }}
// >
//   {product.stock === 0
//     ? "Out of Stock"
//     : product.stock < 5
//     ? `Only ${product.stock} left`
//     : "In Stock"}
// </p>



//             {/* WISHLIST */}
//             <button
//               className={`wishlist-btn ${isWishlisted ? "active" : ""}`}
//               onClick={toggleWishlist}
//             >
//               {isWishlisted ? "♥" : "♡"}
//             </button>
//           </div>
//         </div>
//       </div>
//     </article>
//   );
// };

// export default ProductCard;


import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../api/axiosConfig";
import "../styles/ProductCard.css";

const ProductCard = ({ product }) => {
  const navigate = useNavigate();

  const [isWishlisted, setIsWishlisted] = useState(false);

  // 🔥 Single source of truth for stock (from ProductStockDTO)
  const stock = product.stock;

  /* ================= NAVIGATION ================= */
  const openProduct = () => {
    navigate(`/productdetails/${product.productId}`);
  };

  /* ================= LOAD WISHLIST ================= */
  useEffect(() => {
    api.get("/api/wishlist")
      .then(res => {
        const exists = res.data.some(
          w => w.product.productId === product.productId
        );
        setIsWishlisted(exists);
      })
      .catch(() => {});
  }, [product.productId]);

  /* ================= WISHLIST TOGGLE ================= */
  const toggleWishlist = async (e) => {
    e.stopPropagation();

    try {
      if (isWishlisted) {
        await api.delete("/api/wishlist/remove/" + product.productId);
        setIsWishlisted(false);
      } else {
        await api.post("/api/wishlist/add/" + product.productId);
        setIsWishlisted(true);
      }
    } catch (err) {
      alert("Wishlist failed");
    }
  };

  return (
    <article className="product-card" onClick={openProduct}>
      <img
        className="product-image"
        src={product.imageUrl}
        alt={product.productName}
      />

      <div className="product-body">
        <p className="product-name">{product.productName}</p>

        <div className="product-meta">
          <div className="size-badge">{product.size}</div>
          <small className="text-muted">
            {product.categoryName}
          </small>
        </div>

        {/* 🔥 STOCK STATUS */}
        <p
          style={{
            color: stock === 0 ? "red" : stock <= 5 ? "orange" : "green",
            fontWeight: "bold"
          }}
        >
          {stock === 0
            ? "Out of Stock"
            : stock <= 5
            ? `Only ${stock} left`
            : "In Stock"}
        </p>

        {product.material && (
          <p className="product-desc">Material: {product.material}</p>
        )}

        <div className="product-footer">
          <div className="price">₹{product.pricePerBox}</div>

          <div className="buttons">
            {/* 🔥 ADD TO CART – only disabled if stock = 0 */}
            <button
              className="btn add-cart-btn"
              disabled={stock === 0}
              style={{ opacity: stock === 0 ? 0.5 : 1 }}
              onClick={(e) => {
                e.stopPropagation();
                if (stock === 0) return;
                api.post("/api/cart/add/" + product.productId);
                alert("Product added");
              }}
            >
              {stock === 0 ? "Out of Stock" : "Add"}
            </button>

            {/* ❤️ WISHLIST */}
            <button
              className={`wishlist-btn ${isWishlisted ? "active" : ""}`}
              onClick={toggleWishlist}
            >
              {isWishlisted ? "♥" : "♡"}
            </button>
          </div>
        </div>
      </div>
    </article>
  );
};

export default ProductCard;
