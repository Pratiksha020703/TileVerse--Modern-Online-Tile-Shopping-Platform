import { Navigate } from "react-router-dom";
import { getUserFromToken } from "../utils/auth";

const AdminRoute = ({ children }) => {
  const user = getUserFromToken();

  if (!user) {
    return <Navigate to="/auth" replace />;
  }

  if (user.role !== "ADMIN") {
    return <Navigate to="/admin" replace />;
  }

  return children;
};

export default AdminRoute;
