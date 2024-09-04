    import {createContext, useContext, useState} from "react";


    var Jwtcontext = createContext();

    export const JwtProvider = ({ children }) => {

        const [auth, setAuth] = useState({
            token: localStorage.getItem("jwttoken"),
            isAuthenticated:!! localStorage.getItem("jwttoken")

        })

        const logout =()=>{
            localStorage.removeItem("jwttoken");
            setAuth({token: null,isAuthenticated: false})

        }

        const login=(token) =>{
            localStorage.setItem("jwttoken",token);
            setAuth({token:token,isAuthenticated: true})
        }




        return(
            <Jwtcontext.Provider value={{auth, setAuth,login,logout}}>
                {children}
            </Jwtcontext.Provider>
        )
    }

    export const useJwtContext=()=> {
        var context = useContext(Jwtcontext);
        return context;
    }