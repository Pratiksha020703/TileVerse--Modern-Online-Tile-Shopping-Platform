import { useState } from "react";
import axios from "axios";

const ForgotPassword = () => {
  const [email, setEmail] = useState("");

  const handleSubmit = async () => {
    try {
      await axios.post("http://localhost:5000/api/auth/forgot-password", {
        email,
      });
      alert("Password reset link sent to your email ✅");
    } catch (err) {
      alert(err.response?.data || "Something went wrong ❌");
    }
  };

  return (
    <div className="auth-container">
      <h2>Forgot Password</h2>
      <input
        type="email"
        placeholder="Enter registered email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <button onClick={handleSubmit}>Send Reset Link</button>
    </div>
  );
};

export default ForgotPassword;
