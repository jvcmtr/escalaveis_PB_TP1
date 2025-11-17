import { createContext, useContext, useState, useEffect } from "react";

const UserContext = createContext(null);

export function UserProvider({ children }) {
    const [user, setUser] = useState("")
    const USER_LOCAL_STORAGE_KEY = "username"

    useEffect(() => {
        const existente = localStorage.getItem(USER_LOCAL_STORAGE_KEY)
        if (existente) setUser(JSON.parse(existente))
    }, [])

    useEffect(() => {
        user?
            localStorage.setItem(USER_LOCAL_STORAGE_KEY, JSON.stringify(user))
            :localStorage.removeItem(USER_LOCAL_STORAGE_KEY)
    }, [user])

    const login = (username) => {
        if (username.trim()) setUser({ username })
    }
    const logout = () => setUser("")

    return (
        <UserContext.Provider value={{ user, login, logout }}>
            {children}
        </UserContext.Provider>
    );
}

export function useAuth() {
    return useContext(UserContext);
}
