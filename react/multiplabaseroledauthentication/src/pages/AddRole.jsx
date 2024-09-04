import React, { useState, useEffect, useCallback } from 'react';
import api from "../context/AxiosGenel.jsx";

const AddRole = () => {
    const [roleName, setRoleName] = useState("ROLE_USER");
    const [roleDescription, setRoleDescription] = useState("USER hesabı");
    const [userId, setUserId] = useState("3");
    const [message, setMessage] = useState("");
    const [error, setError] = useState(null);
    const [isLoading, setIsLoading] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage("");
        setError(null);
        setIsLoading(true);

        try {
            console.log("İstek gönderiliyor...");
            const response = await api.post('/add-role', {
                roleName: roleName,
                roleDescription: roleDescription,
                user: {
                    id: userId
                }
            });
            console.log("Yanıt alındı:", response);
            setMessage("Rol başarıyla eklendi: " + JSON.stringify(response.data));
        } catch (error) {
            console.error("Rol eklenirken bir hata oluştu:", error);
            setError("Hata: " + (error.response?.data?.message || error.message));
        } finally {
            setIsLoading(false);
        }
    };

    if (isLoading) {
        return <div>Yükleniyor...</div>;
    }

    if (error) {
        return <div>Hata oluştu: {error}</div>;
    }

    return (
        <div>
            <h2>Rol Ekle</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="roleName">Rol Adı:</label>
                    <input
                        type="text"
                        id="roleName"
                        value={roleName}
                        onChange={(e) => setRoleName(e.target.value)}
                    />
                </div>
                <div>
                    <label htmlFor="roleDescription">Rol Açıklaması:</label>
                    <input
                        type="text"
                        id="roleDescription"
                        value={roleDescription}
                        onChange={(e) => setRoleDescription(e.target.value)}
                    />
                </div>
                <div>
                    <label htmlFor="userId">Kullanıcı ID:</label>
                    <input
                        type="text"
                        id="userId"
                        value={userId}
                        onChange={(e) => setUserId(e.target.value)}
                    />
                </div>
                <button type="submit" disabled={isLoading}>Rol Ekle</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
};

export default AddRole;