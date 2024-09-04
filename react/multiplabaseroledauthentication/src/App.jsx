import {Route, Routes} from "react-router-dom";
import Tarifler from "./pages/Tarifler.jsx";
import Register from "./pages/Register.jsx";
import Login from "./pages/Login.jsx";
import Anasayfa from "./pages/Anasayfa.jsx";
import Header from "./component/Header.jsx";
import Footer from "./component/Footer.jsx";
import {useJwtContext} from "./context/JwtContext.jsx";
import ProtectedRoute from "./component/ProtectedRoute.jsx";
import AddRole from "./pages/AddRole.jsx";



function App() {



  return (
    <>
      <Header/>
    <Routes>
      <Route path="/" element={<ProtectedRoute element={Anasayfa}/>}/>
      <Route path="/home" element={<ProtectedRoute element={Anasayfa}/>}/>
      <Route path="/login" element={<Login/>}/>
      <Route path="/register" element={<Register/>}/>
      <Route path="/addrole" element={<AddRole/>}/>
      <Route path="/tarifler" element={<ProtectedRoute element={Tarifler}/>}/>


    </Routes>
      <Footer/>


    </>
  )
}

export default App
