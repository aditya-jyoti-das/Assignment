import { Link } from "react-router-dom";
import "./Home.css";

function Home() {
    return (
        <div className="home-container">

            <h1 className="home-title">Welcome to E-Commerce App</h1>

            <div className="home-buttons">
                <Link to="/login">
                    <button className="home-btn login-btn">Login</button>
                </Link>

                <Link to="/register">
                    <button className="home-btn register-btn">Register</button>
                </Link>
            </div>
        </div>
    );
}

export default Home;