import React, { useState } from 'react';
import api from "../context/AxiosGenel.jsx"; // Axios instance'ınızı buraya import edin
import { useJwtContext } from '../context/JwtContext.jsx';

const Login = () => {
    const { login } = useJwtContext(); // `logout` burada gerekli değil, `login` yeterli
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    async function GirisYap(e) {
        e.preventDefault();
        try {
            const response = await api.post("/public/login", {
                username: username,
                password: password,
            });

            // Yanıtı kontrol edin ve token'ı doğru şekilde alın
            if (response.data) {
                // Token doğrudan response.data'nın kendisi
                login(response.data);
            } else {
                console.error("Geçersiz yanıt:", response);
                // Kullanıcıya bir hata mesajı gösterebilirsiniz
            }
        } catch (error) {
            console.error("Giriş sırasında bir hata oluştu:", error);
            // Kullanıcıya bir hata mesajı gösterebilirsiniz
        }
    }
    function Degisim(e) {
        const { id, value } = e.target;
        if (id === "username") {
            setUsername(value);
        } else if (id === "password") {
            setPassword(value);
        }
    }

    return (
        <form onSubmit={GirisYap}>
            <label className={"form-label"} htmlFor="username">Username:</label>
            <input
                className={"form-control"}
                required
                value={username}
                id="username"
                onChange={Degisim}
            />
            <label className={"form-label"} htmlFor="password">Password:</label>
            <input
                className={"form-control"}
                required
                value={password}
                id="password"
                onChange={Degisim}
                type="password"
            />
            <input type={"submit"} className={"btn btn-primary"} value="Login" />
        </form>
    );
}

export default Login;
