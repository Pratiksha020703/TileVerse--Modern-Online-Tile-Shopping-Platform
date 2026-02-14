import { useParams, useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
import { forgotPassword } from "../api/authApi";


const ResetPassword = () => {
  const { token } = useParams();
  const navigate = useNavigate();
  const [password, setPassword] = useState("");

  const handleReset = async () => {
    try {
      await forgotPassword(email);
      alert("Password reset successful ✅");
      navigate("/auth");
    } catch (err) {
      alert(err.response?.data || "Invalid or expired token ❌");
    }
  };

  return (
    <div className="auth-container">
      <h2>Reset Password</h2>
      <input
        type="password"
        placeholder="New password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleReset}>Reset Password</button>
    </div>
  );
};

export default ResetPassword;
