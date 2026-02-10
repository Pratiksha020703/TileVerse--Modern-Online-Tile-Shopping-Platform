import { jwtDecode } from "jwt-decode";

export const getUserFromToken = () => {
  const token = localStorage.getItem("token");
  if (!token) return null;

  try {
    const decoded = jwtDecode(token);

    return {
      email:
        decoded.email ||
        decoded["http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress"],

      role:
        decoded.role ||
        decoded["http://schemas.microsoft.com/ws/2008/06/identity/claims/role"],

      fullName: decoded.fullName || decoded.name || null
    };
  } catch (err) {
    console.error("Invalid token", err);
    return null;
  }
};

export const logout = () => {
  localStorage.removeItem("token");
  window.location.href = "/";
};
