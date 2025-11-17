import { BrowserRouter, Routes, Route } from "react-router-dom";

import { UserProvider } from "./services/UserContext";
import RequireAuth from "./layout/RequireAuth";

import BaseLayout from "./layout/BaseLayout";
import Home from "./pages/HomePage";
import Login from "./pages/LoginPage";

export default function Routing() {
  return (
    <BrowserRouter>
      <UserProvider>

        <Routes>
          
          <Route element={<BaseLayout />} >
            <Route path="/" element={ <RequireAuth> <Home/> </RequireAuth> }/>
            <Route path="/login" element={<Login />} />
          </Route>

        </Routes>

      </UserProvider>
    </BrowserRouter>
  );
}
