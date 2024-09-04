import React, {useState} from 'react';
import {useJwtContext} from "../context/JwtContext.jsx";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import api from "../context/AxiosGenel.jsx";

const Register = () => {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    var navigate = useNavigate();


    async function KayitOl(e) {
        e.preventDefault();

        try {
            const response = await api.post("/public/register", {
                username: username,
                password: password,
            });
            console.log("Kayıt başarılı:", response.data);
            navigate("/login");
        } catch (error) {
            console.error("Kayıt sırasında bir hata oluştu:", error);
        }
    }


    function Degisim(e) {
        switch(e.target.id) {
            case "username":
                setUsername(e.target.value);
                break;

            case "password":
                setPassword(e.target.value)
        }
    }
    return (
        <>
            <form onSubmit={KayitOl}>
                <label className={"form-label"} htmlFor="username">username:</label>
                <input className={"form-control"} required value={username} id="username" onChange={Degisim}/>
                <label className={"form-label"} htmlFor="password">Password:</label>
                <input className={"form-control"} required value={password} id="password" onChange={Degisim}/>
                <input type={"submit"} className={"btn btn-primary"} value="Register"/>
            </form>
        </>
    );
};

export default Register;