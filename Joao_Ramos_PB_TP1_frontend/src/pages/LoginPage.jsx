import { useAuth } from "../services/UserContext";
import { useLocation, useNavigate } from "react-router-dom";
import { useState } from "react";
import BoxButton from "../components/BoxButton";
import Centralized from "../layout/Centralized";

export default function LoginPage() {
  
  const authService = useAuth();
  const navigate = useNavigate();
  
  const [username, setUsername] = useState("");
  
  const login = () => {
    authService.login(username, "");
    
    navigate("/", { replace: true });
  };

  if(authService.user){
    navigate("/", { replace: true });
  }

  return (
    <Centralized>

      <h1>Login</h1>
      <input 
        value={username} 
        onChange={(e) => setUsername(e.target.value)}
        placeholder="Digite um nome qualquer"
        />
      <BoxButton onClick={login}>Login</BoxButton>
    </Centralized>
  );
}
