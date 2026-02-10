
import { useEffect, useState } from "react";
import api from "../../api/axiosConfig";
import "../../styles/AdminProducts.css";


const AdminProducts = ({ goInventory }) => {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [brands, setBrands] = useState([]);
  const [showProducts, setShowProducts] = useState(true);
  const [editingId, setEditingId] = useState(null);

  const [formData, setFormData] = useState({
    productName: "",
    imageUrl: "",
    size: "",
    material: "",
    pricePerBox: "",
    categoryId: "",
    brandId: ""
  });

  useEffect(() => {
    loadCategories();
    loadBrands();
    loadProducts();
  }, []);

  const loadCategories = async () => {
    const res = await api.get("/api/categories");
    setCategories(res.data);
  };

  const loadBrands = async () => {
    const res = await api.get("/api/brands");
    setBrands(res.data);
  };

  const loadProducts = async () => {
    const res = await api.get("/api/products");
    setProducts(res.data);
  };

  const handleChange = (e) => {
    setFormData(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      productName: formData.productName,
      imageUrl: formData.imageUrl,
      size: formData.size,
      material: formData.material,
      pricePerBox: formData.pricePerBox,
      category: { categoryId: Number(formData.categoryId) },
      brand: { brandId: Number(formData.brandId) }
    };

    try {
      if (editingId) {
        const res = await api.put(`/api/products/${editingId}`, payload);
        setProducts(products.map(p => p.productId === editingId ? res.data : p));
        alert("Product updated");
      } else {
        const res = await api.post("/api/products", payload);
        setProducts([...products, res.data]);
        alert("Product added");
      }
      resetForm();
    } catch {
      alert("Error saving product");
    }
  };

  const handleEdit = (product) => {
    setEditingId(product.productId);
    setFormData({
      productName: product.productName,
      imageUrl: product.imageUrl,
      size: product.size,
      material: product.material,
      pricePerBox: product.pricePerBox,
      categoryId: product.category?.categoryId || "",
      brandId: product.brand?.brandId || ""
    });
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Delete this product?")) return;

    await api.delete(`/api/products/admin/${id}`);
    setProducts(products.filter(p => p.productId !== id));
    alert("Product deleted");
  };

  const resetForm = () => {
    setEditingId(null);
    setFormData({
      productName: "",
      imageUrl: "",
      size: "",
      material: "",
      pricePerBox: "",
      categoryId: "",
      brandId: ""
    });
  };

  return (
    <div className="admin-container">
      <h2>Admin – Manage Products</h2>

      {/* FORM */}
      <form className="admin-form" onSubmit={handleSubmit}>
        <input name="productName" value={formData.productName} onChange={handleChange} placeholder="Product Name" required />
        <input name="imageUrl" value={formData.imageUrl} onChange={handleChange} placeholder="Image URL" required />
        {formData.imageUrl && (
          <div className="image-preview">
            <img src={formData.imageUrl} alt="Preview" />
            </div>
          )}

        <input name="size" value={formData.size} onChange={handleChange} placeholder="Size" />
        <input name="material" value={formData.material} onChange={handleChange} placeholder="Material" />
        <input name="pricePerBox" value={formData.pricePerBox} onChange={handleChange} placeholder="Price" required />

        <select name="categoryId" value={formData.categoryId} onChange={handleChange} required>
          <option value="">Select Category</option>
          {categories.map(c => <option key={c.categoryId} value={c.categoryId}>{c.categoryName}</option>)}
        </select>

        <select name="brandId" value={formData.brandId} onChange={handleChange} required>
          <option value="">Select Brand</option>
          {brands.map(b => <option key={b.brandId} value={b.brandId}>{b.brandName}</option>)}
        </select>

        <button type="submit">{editingId ? "Update" : "Add"} Product</button>

        <button type="button" className="secondary-btn" onClick={goInventory}>
          Manage Inventory →
        </button>
      </form>

      {/* PRODUCT LIST */}
      {showProducts && (
        <div className="admin-list">
          {products.map(p => (
            <div className="admin-item" key={p.productId}>
              <img src={p.imageUrl} alt={p.productName} />
              <div className="admin-info">
                <h4>{p.productName}</h4>
                <p>{p.category?.categoryName}</p>
                <p>{p.brand?.brandName}</p>
                <p>₹{p.pricePerBox}</p>
              </div>
              <div className="admin-actions">
                <button onClick={() => handleEdit(p)}>Edit</button>
                <button className="danger" onClick={() => handleDelete(p.productId)}>Delete</button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default AdminProducts;
