import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axiosConfig";
import "../styles/ManageAddress.css";   // reuse same UI

const CheckoutAddress = () => {
  const navigate = useNavigate();
  const [addresses, setAddresses] = useState([]);
  const [formData, setFormData] = useState({
    name: "",
    phone: "",
    address: "",
    city: "",
    state: "",
    pincode: ""
  });

  // 🔥 Load user's existing address (if any)
  // useEffect(() => {
  //   const load = async () => {
  //     try {
  //       const res = await api.get("/api/user/address");
  //       if (res.data) {
  //         setAddresses([res.data]);
  //       }
  //     } catch {}
  //   };
  //   load();
  // }, []);

  useEffect(() => {
  const load = async () => {
    try {
      const res = await api.get("/api/user/addresses");
      setAddresses(res.data);
    } catch {
      setAddresses([]);
    }
  };
  load();
}, []);


  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const selectAddress = async (addressId) => {
  await api.post("/api/cart/select-address/" + addressId);
  navigate("/payment");
};

  // 🔥 Save address & continue to payment
  const addAddress = async (e) => {
    e.preventDefault();

    try {
      const res = await api.post("/api/user/address", formData);
setAddresses([...addresses, res.data]);   // append new address

    } catch {
      alert("Failed to save address");
    }
  };

  return (
    <div className="address-page">
      <h2>Select / Add Delivery Address</h2>

      {/* If no address exists, show form */}
      {addresses.length === 0 && (
        <form className="address-form" onSubmit={addAddress}>
          <div className="form-row">
            <input
              type="text"
              name="name"
              placeholder="Full Name"
              value={formData.name}
              onChange={handleChange}
              required
            />
            <input
              type="tel"
              name="phone"
              placeholder="Phone Number"
              value={formData.phone}
              onChange={handleChange}
              required
            />
          </div>

          <textarea
            name="address"
            placeholder="Full Address"
            value={formData.address}
            onChange={handleChange}
            required
          />

          <div className="form-row">
            <input
              type="text"
              name="city"
              placeholder="City"
              value={formData.city}
              onChange={handleChange}
              required
            />
            <input
              type="text"
              name="state"
              placeholder="State"
              value={formData.state}
              onChange={handleChange}
              required
            />
            <input
              type="text"
              name="pincode"
              placeholder="Pincode"
              value={formData.pincode}
              onChange={handleChange}
              required
            />
          </div>

          <button type="submit" className="save-btn">
            Save & Continue
          </button>
        </form>
      )}

      {/* If address exists, show it */}
      {addresses.length > 0 && (
        <div className="address-list">
          {addresses.map((addr) => (
            <div className="address-card" key={addr.addressId}>
              <div className="address-info">
                <h4>{addr.name}</h4>
                <p>{addr.address}</p>
                <p>
                  {addr.city}, {addr.state} - {addr.pincode}
                </p>
                <p>📞 {addr.phone}</p>
              </div>
              

              <button
  className="save-btn"
  onClick={() => selectAddress(addr.addressId)}
>
  Deliver Here
</button>

            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default CheckoutAddress;
