import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../context/AuthContext";
import "./AdminPage.css";

function AdminPage() {

    const navigate = useNavigate();

    const [name, setName] = useState("");
    const [price, setPrice] = useState("");
    const [currency, setCurrency] = useState("INR");
    const [rating, setRating] = useState("");
    const [imageUrl, setImageUrl] = useState("");

    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    const handleSubmit = async (e: React.FormEvent) => {

        e.preventDefault();

        setLoading(true);
        setMessage("");

        try {

            const requestBody = {
                name: name,
                price: Number(price),
                currency: currency,
                rating: Number(rating),
                imageUrl: imageUrl
            };

            await axiosInstance.post(
                "api/admin/add",
                requestBody
            );

            setLoading(false);

            setMessage("Product added successfully");

            // clear form
            setName("");
            setPrice("");
            setRating("");
            setImageUrl("");

        } catch (error) {

            setLoading(false);

            setMessage("Unauthorized access. Redirecting to login...");

            setTimeout(() => {
                navigate("/login");
            }, 2000);

        }

    };

    return (

        <div className="admin-container">

            <h2>Add Product</h2>

            {message && (
                <div className="admin-message">
                    {message}
                </div>
            )}

            <form
                className="admin-form"
                onSubmit={handleSubmit}
            >

                <input
                    type="text"
                    placeholder="Product Name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    required
                />

                <input
                    type="number"
                    placeholder="Price"
                    value={price}
                    onChange={(e) => setPrice(e.target.value)}
                    required
                />

                <input
                    type="text"
                    placeholder="Currency"
                    value={currency}
                    onChange={(e) => setCurrency(e.target.value)}
                    required
                />

                <input
                    type="number"
                    step="0.1"
                    placeholder="Rating"
                    value={rating}
                    onChange={(e) => setRating(e.target.value)}
                    required
                />

                <input
                    type="text"
                    placeholder="Image URL"
                    value={imageUrl}
                    onChange={(e) => setImageUrl(e.target.value)}
                    required
                />

                <button
                    type="submit"
                    disabled={loading}
                >
                    {loading ? "Adding..." : "Add Product"}
                </button>
            </form>
        </div>
    );
}

export default AdminPage;