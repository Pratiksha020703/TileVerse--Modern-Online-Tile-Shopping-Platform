import React, { useState } from "react";
import "../styles/EditProfile.css";

const EditProfile = () => {
  const [formData, setFormData] = useState({
    fullName: "John Doe",
    email: "johndoe@email.com",
    phone: "",
    address: "",
    city: "",
    state: "",
    pincode: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Updated Profile:", formData);
    alert("Profile updated successfully!");
  };

  return (
  <div className="container py-5">
    <div className="edit-profile-container">

      <h2 className="edit-profile-title">Edit Profile</h2>

      <form onSubmit={handleSubmit} className="row g-3">

        {/* Full Name */}
        <div className="col-md-6">
          <label className="form-label">Full Name</label>
          <input
            type="text"
            className="form-control"
            name="fullName"
            value={formData.fullName}
            onChange={handleChange}
            required
          />
        </div>

        {/* Email */}
        <div className="col-md-6">
          <label className="form-label">Email</label>
          <input
            type="email"
            className="form-control"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </div>

        {/* Phone */}
        <div className="col-md-6">
          <label className="form-label">Phone Number</label>
          <input
            type="tel"
            className="form-control"
            name="phone"
            value={formData.phone}
            onChange={handleChange}
          />
        </div>

        {/* Address */}
        <div className="col-md-6">
          <label className="form-label">Address</label>
          <input
            type="text"
            className="form-control"
            name="address"
            value={formData.address}
            onChange={handleChange}
          />
        </div>

        {/* City */}
        <div className="col-md-4">
          <label className="form-label">City</label>
          <input
            type="text"
            className="form-control"
            name="city"
            value={formData.city}
            onChange={handleChange}
          />
        </div>

        {/* State */}
        <div className="col-md-4">
          <label className="form-label">State</label>
          <input
            type="text"
            className="form-control"
            name="state"
            value={formData.state}
            onChange={handleChange}
          />
        </div>

        {/* Pincode */}
        <div className="col-md-4">
          <label className="form-label">Pincode</label>
          <input
            type="text"
            className="form-control"
            name="pincode"
            value={formData.pincode}
            onChange={handleChange}
          />
        </div>

        {/* Buttons */}
        <div className="col-12 mt-4">
          <button type="submit" className="btn btn-primary me-3">
            Save Changes
          </button>

          <button type="reset" className="btn btn-outline-secondary">
            Cancel
          </button>
        </div>

      </form>

    </div>
  </div>
);

};

export default EditProfile;