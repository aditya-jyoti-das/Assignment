import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../context/AuthContext";
import "./Login.css";

function Login() {

    const navigate = useNavigate();

    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e: React.FormEvent) => {

        e.preventDefault();

        setError("");
        setLoading(true);

        try {

            const requestBody = {
                userName: userName,
                password: password
            };

            const response = await axiosInstance.post(
                "api/public/login",
                requestBody
            );

            const data = response.data.data;

            // store token
            localStorage.setItem("authToken", data.jwtToken);

            setLoading(false);

            // navigate based on role
            if (data.role === "ADMIN") {
                navigate("/admin");
            } else {
                navigate("/user");
            }

        } catch (err: any) {

            setLoading(false);

            if (err.response?.data?.message) {
                setError(err.response.data.message);
                setTimeout(() => {
                    setError("Try Again");
                }, 3000)

            } else {
                setError("Invalid username or password");
                setTimeout(() => {
                    setError("Try Again")
                }, 3000)
            }

        }

    };

    return (

        <div className="login-container">

            <h2>Login</h2>

            {error && (
                <div className="login-error">
                    {error}
                </div>
            )}

            <form
                className="login-form"
                onSubmit={handleSubmit}
            >

                <input
                    type="text"
                    placeholder="Username"
                    className="login-input"
                    value={userName}
                    onChange={(e) => setUserName(e.target.value)}
                    required
                />

                <input
                    type="password"
                    placeholder="Password"
                    className="login-input"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />

                <button
                    className="login-btn-submit"
                    disabled={loading}
                >
                    {loading ? "Logging in..." : "Login"}
                </button>

            </form>

        </div>

    );

}

export default Login;