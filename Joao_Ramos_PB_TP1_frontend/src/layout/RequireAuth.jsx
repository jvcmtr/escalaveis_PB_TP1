import { Navigate, Outlet, useLocation } from "react-router-dom";
import { useAuth } from "../services/UserContext";

export default function RequireAuth(props) {

  const { user } = useAuth();
  const location = useLocation();

  if (!user) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  return props.children
}
