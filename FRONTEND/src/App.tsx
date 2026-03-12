import { BrowserRouter, Routes, Route } from "react-router-dom";

import Home from "./pages/Home"
import Login from "./pages/Login";
import Register from "./pages/Register";
import Navbar from "./components/Navbar";
import UserPage from "./pages/UserPage";
import AdminPage from "./pages/AdminPage";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>

        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/user" element={< UserPage />} />
        <Route path="/admin" element={<AdminPage />} />


      </Routes>
    </BrowserRouter>
  );
}

export default App;
