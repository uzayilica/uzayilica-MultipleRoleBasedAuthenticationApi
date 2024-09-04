import React from 'react';
import {Link, useNavigate} from "react-router-dom";
import {useJwtContext} from "../context/JwtContext.jsx";

const Header = () => {
    const { auth,logout} = useJwtContext();
    var navigate = useNavigate();
    return (

        <nav className="navbar navbar-expand-lg navbar-dark bg-dark p-4">
            <div className="container-fluid">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                    <li className="nav-item">
                        <Link className="nav-link text-white" to="/">Home</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link text-white" to="/tarifler">Tarifler</Link>
                    </li>

                    {
                        auth.isAuthenticated ?
                            (<li className="nav-item">


                                <button
                                    className="btn btn-warning "
                                    onClick={(e) => {
                                        e.preventDefault();
                                        logout();
                                    }}
                                >
                                    Logout
                                </button>
                            </li>)
                            :
                            (<li className="nav-item">
                                <li className="nav-item">
                                    <button
                                        className="btn btn-warning "
                                        onClick={(e) => {
                                            e.preventDefault();
                                            navigate("/login")
                                        }}
                                    >
                                        Login
                                    </button>
                                </li>
                            </li>)


                    }


                    {


                        !auth.isAuthenticated &&
                        <li className="nav-item mx-2">

                            <button
                                className="btn btn-warning "
                                onClick={(e) => {
                                    e.preventDefault();
                                    navigate("/register")
                                }}
                            >
                                Register
                            </button>

                        </li>


                    }
                    <li className="nav-item">
                        <Link className="nav-link text-white" to="/addrole">AddRole</Link>
                    </li>

                </ul>
            </div>
        </nav>

    );
};

export default Header;