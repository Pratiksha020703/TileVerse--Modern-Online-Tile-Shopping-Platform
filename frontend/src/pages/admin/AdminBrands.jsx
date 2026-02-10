import { useState, useEffect } from "react";
import api from "../../api/axiosConfig";

const AdminBrands = () => {
  const [name, setName] = useState("");
  const [brands, setBrands] = useState([]);

  useEffect(() => {
    api.get("/api/brands").then(res => setBrands(res.data));
  }, []);

  const addBrand = async (e) => {
    e.preventDefault();
    await api.post("/api/brands", { brandName: name });
    setName("");
    const res = await api.get("/api/brands");
    setBrands(res.data);
  };

  return (
    <div>
      <h2>Manage Brands</h2>

      <form onSubmit={addBrand}>
        <input
          placeholder="Brand name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
        <button>Add</button>
      </form>

      <ul>
        {brands.map(b => (
          <li key={b.brandId}>
            {b.brandName} (ID: {b.brandId})
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AdminBrands;
