import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../context/AuthContext";
import "./Register.css";

function Register() {

    const navigate = useNavigate();

    const [userName, setUserName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("USER");

    const [showPassword, setShowPassword] = useState(false);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const handleSubmit = async (e: React.FormEvent) => {

        e.preventDefault();

        setError("");
        setLoading(true);

        try {

            const requestBody = {
                userName: userName,
                password: password,
                role: role,
                email: email
            };

            await axiosInstance.post(
                "api/public/register",
                requestBody
            );

            setLoading(false);

            // navigate to login
            navigate("/login");

        } catch (err: any) {

            setLoading(false);

            if (err.response?.data?.message) {
                setError(err.response.data.message);
            } else {
                setError("Registration failed. Please try again.");
            }

        }
    };

    return (
        <div className="register-container">

            <h2>Register</h2>

            {error && (
                <div className="register-error">
                    {error}
                </div>
            )}

            <form
                className="register-form"
                onSubmit={handleSubmit}
            >

                <input
                    type="text"
                    placeholder="Username"
                    className="register-input"
                    value={userName}
                    onChange={(e) => setUserName(e.target.value)}
                    required
                />

                <input
                    type="email"
                    placeholder="Email"
                    className="register-input"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />

                <div className="password-field">

                    <input
                        type={showPassword ? "text" : "password"}
                        placeholder="Password"
                        className="register-input"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />

                    <button
                        type="button"
                        className="password-toggle"
                        onClick={() => setShowPassword(!showPassword)}
                    >
                        {showPassword ? "🙈" : "👁"}
                    </button>

                </div>

                <select
                    className="register-input-list"
                    value={role}
                    onChange={(e) => setRole(e.target.value)}
                >
                    <option value="USER">USER</option>
                    <option value="ADMIN">ADMIN</option>
                </select>

                <button
                    className="register-submit"
                    disabled={loading}
                >
                    {loading ? "Registering..." : "Register"}
                </button>

            </form>

        </div>
    );
}

export default Register;