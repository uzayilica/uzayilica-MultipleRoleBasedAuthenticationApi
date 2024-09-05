import React, {useState} from 'react';
import api from "../context/AxiosGenel.jsx"; // Axios instance'ınızı buraya import edin
const Tarifler = () => {

    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [authorities, setAuthorities] = useState([])



    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            console.log("İstek gönderiliyor...");
            const response = await api.get('/giris-yapmis-kisi');
            console.log("Yanıt alındı:", response.data.username);
            setUsername(response.data.username);
            setPassword(response.data.password);
            setAuthorities(response.data.authorities);

        } catch (error) {
            console.error("Rol eklenirken bir hata oluştu:", error);
            setError("Hata: " + (error.response?.data?.message || error.message));
        }
    };


    const handleSubmit2 = async (e) => {
        e.preventDefault();
        try {
            console.log("İstek gönderiliyor...");
            const response = await api.get('/giris-yapmis-kisi2');
            console.log("Yanıt alındı:", response.data.username);
            setUsername(response.data.username);
            setPassword(response.data.password);
            setAuthorities(response.data.authorities);

        } catch (error) {
            console.error("Rol eklenirken bir hata oluştu:", error);
            setError("Hata: " + (error.response?.data?.message || error.message));
        }
    };







    return (


        <>
            <br/>
            <input type="button" name="id" value={"Giriş Yapmış Kişi Bilgisi @Autehntication Principal ile"}
                   onClick={handleSubmit}/>
            <input type="button" name="id" value={"Giriş Yapmış Kişi Bilgisi SecurityContextHolder İle "}
                   onClick={handleSubmit2}/>
            <br/>
            {username &&
                <>
                    <h1> Kullanıcı Adı: {username}  </h1>
                    <h1> Şifre: {password}  </h1>

                    <ul>
                        {authorities.map((authority, index) => (
                            <li key={index}>{authority}</li>
                        ))}
                    </ul>


                </>
            }

            <br/>
        </>
    );
};

export default Tarifler;