// import { useState } from "react";
// import "../styles/ManageAddress.css";

// const ManageAddress = () => {
//   const [addresses, setAddresses] = useState([]);
//   const [formData, setFormData] = useState({
//     name: "",
//     phone: "",
//     address: "",
//     city: "",
//     state: "",
//     pincode: ""
//   });

//   const handleChange = (e) => {
//     setFormData({ ...formData, [e.target.name]: e.target.value });
//   };

//   const addAddress = (e) => {
//     e.preventDefault();

//     setAddresses([
//       ...addresses,
//       { ...formData, id: Date.now() }
//     ]);

//     setFormData({
//       name: "",
//       phone: "",
//       address: "",
//       city: "",
//       state: "",
//       pincode: ""
//     });
//   };

//   const deleteAddress = (id) => {
//     setAddresses(addresses.filter((addr) => addr.id !== id));
//   };

//   return (
//     <div className="address-page">
//       <h2>Manage Addresses</h2>

//       {/* ADD ADDRESS FORM */}
//       <form className="address-form" onSubmit={addAddress}>
//         <div className="form-row">
//           <input
//             type="text"
//             name="name"
//             placeholder="Full Name"
//             value={formData.name}
//             onChange={handleChange}
//             required
//           />
//           <input
//             type="tel"
//             name="phone"
//             placeholder="Phone Number"
//             value={formData.phone}
//             onChange={handleChange}
//             required
//           />
//         </div>

//         <textarea
//           name="address"
//           placeholder="Full Address"
//           value={formData.address}
//           onChange={handleChange}
//           required
//         />

//         <div className="form-row">
//           <input
//             type="text"
//             name="city"
//             placeholder="City"
//             value={formData.city}
//             onChange={handleChange}
//             required
//           />
//           <input
//             type="text"
//             name="state"
//             placeholder="State"
//             value={formData.state}
//             onChange={handleChange}
//             required
//           />
//           <input
//             type="text"
//             name="pincode"
//             placeholder="Pincode"
//             value={formData.pincode}
//             onChange={handleChange}
//             required
//           />
//         </div>

//         <button type="submit" className="save-btn">
//           Save Address
//         </button>
//       </form>

//       {/* SAVED ADDRESSES */}
//       <div className="address-list">
//         {addresses.length === 0 && (
//           <p className="empty-text">No addresses added yet</p>
//         )}

//         {addresses.map((addr) => (
//           <div className="address-card" key={addr.id}>
//             <div className="address-info">
//               <h4>{addr.name}</h4>
//               <p>{addr.address}</p>
//               <p>
//                 {addr.city}, {addr.state} - {addr.pincode}
//               </p>
//               <p>📞 {addr.phone}</p>
//             </div>

//             <button
//               className="delete-btn"
//               onClick={() => deleteAddress(addr.id)}
//             >
//               Delete
//             </button>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// };

// export default ManageAddress;

// import { useEffect, useState } from "react";
// import api from "../api/axiosConfig";
// import "../styles/ManageAddress.css";

// const ManageAddress = () => {
//   const [addresses, setAddresses] = useState([]);
//   const [form, setForm] = useState({
//     name:"", phone:"", address:"", city:"", state:"", pincode:""
//   });

//   useEffect(() => {
//     api.get("/api/user/addresses").then(res => setAddresses(res.data));
//   }, []);

//   const save = async (e) => {
//     e.preventDefault();
//     const res = await api.post("/api/user/address", form);
//     setAddresses([...addresses, res.data]);
//     setForm({name:"",phone:"",address:"",city:"",state:"",pincode:""});
//   };

//   const remove = async (id) => {
//     await api.delete("/api/user/address/" + id);
//     setAddresses(addresses.filter(a => a.addressId !== id));
//   };

//   return (
//     <div className="address-page">
//       <h2>My Address Book</h2>

//       <form onSubmit={save} className="address-form">
//         <input placeholder="Name" onChange={e=>setForm({...form,name:e.target.value})}/>
//         <input placeholder="Phone" onChange={e=>setForm({...form,phone:e.target.value})}/>
//         <input placeholder="Address" onChange={e=>setForm({...form,address:e.target.value})}/>
//         <input placeholder="City" onChange={e=>setForm({...form,city:e.target.value})}/>
//         <input placeholder="State" onChange={e=>setForm({...form,state:e.target.value})}/>
//         <input placeholder="Pincode" onChange={e=>setForm({...form,pincode:e.target.value})}/>
//         <button>Save</button>
//       </form>

//       {addresses.map(a => (
//         <div key={a.addressId} className="address-card">
//           <p>{a.name}</p>
//           <p>{a.address}, {a.city}</p>
//           <button onClick={()=>remove(a.addressId)}>Delete</button>
//         </div>
//       ))}
//     </div>
//   );
// };

// export default ManageAddress;

import { useEffect, useState } from "react";
import api from "../api/axiosConfig";
import "../styles/ManageAddress.css";

const ManageAddress = () => {
  const [addresses, setAddresses] = useState([]);
  const [form, setForm] = useState({
    name: "",
    phone: "",
    address: "",
    city: "",
    state: "",
    pincode: "",
  });

  useEffect(() => {
    api.get("/api/user/addresses").then((res) => setAddresses(res.data));
  }, []);

  const save = async (e) => {
    e.preventDefault();
    const res = await api.post("/api/user/address", form);
    setAddresses([...addresses, res.data]);
    setForm({
      name: "",
      phone: "",
      address: "",
      city: "",
      state: "",
      pincode: "",
    });
  };

  const remove = async (id) => {
    await api.delete("/api/user/address/" + id);
    setAddresses(addresses.filter((a) => a.addressId !== id));
  };

  return (
    <div className="address-page">
      <h2>My Address Book</h2>

      <form onSubmit={save} className="address-form">
        <div className="form-row">
          <input
            placeholder="Name"
            value={form.name}
            onChange={(e) => setForm({ ...form, name: e.target.value })}
          />
          <input
            placeholder="Phone"
            value={form.phone}
            onChange={(e) => setForm({ ...form, phone: e.target.value })}
          />
        </div>

        <textarea
          placeholder="Address"
          value={form.address}
          onChange={(e) => setForm({ ...form, address: e.target.value })}
        />

        <div className="form-row">
          <input
            placeholder="City"
            value={form.city}
            onChange={(e) => setForm({ ...form, city: e.target.value })}
          />
          <input
            placeholder="State"
            value={form.state}
            onChange={(e) => setForm({ ...form, state: e.target.value })}
          />
          <input
            placeholder="Pincode"
            value={form.pincode}
            onChange={(e) => setForm({ ...form, pincode: e.target.value })}
          />
        </div>

        <button className="save-btn">Save</button>
      </form>

      <div className="address-list">
        {addresses.map((a) => (
          <div key={a.addressId} className="address-card">
            <div className="address-info">
              <h4>{a.name}</h4>
              <p>{a.address}</p>
              <p>
                {a.city}, {a.state} - {a.pincode}
              </p>
            </div>

            <button className="delete-btn" onClick={() => remove(a.addressId)}>
              Delete
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ManageAddress;
