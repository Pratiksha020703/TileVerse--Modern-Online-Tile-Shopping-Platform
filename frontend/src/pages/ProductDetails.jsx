import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { useCart } from "../context/CartContext";
import api from "../api/axiosConfig";
import "../styles/ProductDetails.css";

const ProductDetails = () => {
  const { id } = useParams();       // productId from URL
  const { addToCart } = useCart();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    api.get(`/api/products/${id}`)
      .then(res => setProduct(res.data))
      .catch(() => setProduct(null));
  }, [id]);

  if (!product) return <h2>Product not found</h2>;

  return (
    <div className="product-details">
      <img src={product.imageUrl} alt={product.productName} />

      <h2>{product.productName}</h2>
      <p>Category: {product.category?.categoryName}</p>
      <p>Material: {product.material}</p>
      <p>Size: {product.size}</p>
      <h3>₹{product.pricePerBox}</h3>

      <button onClick={() => addToCart(product.productId)}>
        Add to Cart
      </button>
    </div>
  );
};

export default ProductDetails;


