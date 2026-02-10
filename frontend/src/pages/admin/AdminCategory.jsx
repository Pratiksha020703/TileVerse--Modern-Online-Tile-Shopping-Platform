import { useState, useEffect } from "react";
import api from "../../api/axiosConfig";

const AdminCategories = () => {
  const [name, setName] = useState("");
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    api.get("/api/categories").then(res => setCategories(res.data));
  }, []);

  const addCategory = async (e) => {
    e.preventDefault();
    await api.post("/api/categories", { categoryName: name });
    setName("");
    const res = await api.get("/api/categories");
    setCategories(res.data);
  };

  return (
    <div>
      <h2>Manage Categories</h2>

      <form onSubmit={addCategory}>
        <input
          placeholder="Category name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
        <button>Add</button>
      </form>

      <ul>
        {categories.map(c => (
          <li key={c.categoryId}>
            {c.categoryName} (ID: {c.categoryId})
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AdminCategories;
