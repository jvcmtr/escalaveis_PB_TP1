import { Outlet, useNavigate } from "react-router-dom";
import { useAuth } from "../services/UserContext";
import { COLORS } from "../services/StyleService";
import InlineButton from "../components/InlineButton";
import BoxButton from "../components/BoxButton";

export default function BaseLayout({ children }) {
  const navigate = useNavigate();
  const { user, logout } = useAuth();

  return (
    <div>
      <header style={{ padding: "0.5rem", background: COLORS.bg2 , display:"flex", justifyContent:"space-between"}}>
        <BoxButton onClick={() => navigate("/", {replace:true})}>⬅ HOME</BoxButton>
        
        { user && (
            <span style={{color:COLORS.txt2, textAlign:"center"}}>
                Bem vindo,<br/>
                <b>{user.username}</b>
            </span>
        )}
        
        {user ? 
            (<InlineButton style={{ marginLeft: 10 }} onClick={logout}> Logout </InlineButton>) 
            
            : (<InlineButton style={{ marginLeft: 10 }} onClick={()=>navigate("/login", { replace: true }) }> 
                Login 
                </InlineButton>
            )
        }

      </header>

      <Outlet />
    </div>
  );
}
