import React from 'react';
import {useJwtContext} from "../context/JwtContext.jsx";
import {Navigate} from "react-router-dom";

const ProtectedRoute = ({ element: Component }) => {
    const { auth } = useJwtContext();

    // Eğer kullanıcı oturum açmamışsa login sayfasına yönlendir
    if (!auth.isAuthenticated) {
        return <Navigate to="/login" />;
    }

    // Kullanıcı oturum açmışsa istenen bileşeni render et
    return <Component />;
};

export default ProtectedRoute;