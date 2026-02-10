// import ProductCard from "./ProductCard";
// import "../styles/Products.css"

// const Products = () => {
//   const products = [
//     {
//       id: 1,
//       name: "Sunrise Cotton Tee",
//       image: "/Images/Kitchen.jpg",
//       size: "M",
//       category: "Men's casual",
//       price: "₹799",
//       desc: "Soft cotton tee with a subtle gradient print — breathable for daily wear."
//     },
//     {
//       id: 2,
//       name: "Coral Slip-On Sneakers",
//       image: "/Images/bathroom.jpg",
//       size: "9 (UK)",
//       category: "Unisex",
//       price: "₹2,499",
//       desc: "Lightweight sneakers with flexible sole — great for everyday comfort."
//     },
//     {
//       id: 3,
//       name: "Amber Tote Bag",
//       image:
//         "https://images.unsplash.com/photo-1545239351-1141bd82e8a6",
//       size: "One Size",
//       category: "Canvas",
//       price: "₹1,099",
//       desc: "Roomy canvas tote with reinforced handles."
//     },
//     {
//       id: 4,
//       name: "Peachy Lip Balm Set",
//       image: "/Images/outdoor_image.png",
//       size: "3 pcs",
//       category: "Skincare",
//       price: "₹349",
//       desc: "Nourishing lip balms with a hint of peach."
//     },
//     {
//       id: 1,
//       name: "Sunrise Cotton Tee",
//       image: "/Images/Kitchen.jpg",
//       size: "M",
//       category: "Men's casual",
//       price: "₹799",
//       desc: "Soft cotton tee with a subtle gradient print — breathable for daily wear."
//     },
//     {
//       id: 2,
//       name: "Coral Slip-On Sneakers",
//       image: "/Images/bathroom.jpg",
//       size: "9 (UK)",
//       category: "Unisex",
//       price: "₹2,499",
//       desc: "Lightweight sneakers with flexible sole — great for everyday comfort."
//     },
//     {
//       id: 3,
//       name: "Amber Tote Bag",
//       image:
//         "https://images.unsplash.com/photo-1545239351-1141bd82e8a6",
//       size: "One Size",
//       category: "Canvas",
//       price: "₹1,099",
//       desc: "Roomy canvas tote with reinforced handles."
//     },
//     {
//       id: 4,
//       name: "Peachy Lip Balm Set",
//       image: "/Images/outdoor_image.png",
//       size: "3 pcs",
//       category: "Skincare",
//       price: "₹349",
//       desc: "Nourishing lip balms with a hint of peach."
//     }
//   ];

//   return (
//     <section className="products-wrap">
//       {products.map((product) => (
//         <ProductCard key={product.id} product={product} />
//       ))}
//     </section>
//   );
// };

// export default Products;


import { useEffect, useState } from "react";
import { getProducts } from "../api/ecommerceApi";
import ProductCard from "./ProductCard";
import "../styles/Products.css";

const Products = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    getProducts()
      .then(res => {
        console.log("Products from backend:", res.data);
        setProducts(res.data);
      })
      .catch(err => console.error("Error loading products", err));
  }, []);

  return (
    <div className="products-wrap">
      {products.length === 0 && (
        <p style={{ textAlign: "center", width: "100%" }}>
          No products found
        </p>
      )}

      {products.map(product => (
        <ProductCard
          key={product.productId}
          product={product}
        />
      ))}
    </div>
  );
};

export default Products;
