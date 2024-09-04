import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import {BrowserRouter} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css"
import {JwtProvider} from "./context/JwtContext.jsx";

createRoot(document.getElementById('root')).render(
    <JwtProvider>
  <BrowserRouter>
    <App />
  </BrowserRouter>
    </JwtProvider>
)
