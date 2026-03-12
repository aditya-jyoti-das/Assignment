import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../context/AuthContext";
import "./UserPage.css";

interface Product {
  name: string;
  price: number;
  imageUrl: string;
  rating: number;
  currency: string;
}

function UserPage() {

  const navigate = useNavigate();

  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {

    const fetchContent = async () => {

      try {

        const response = await axiosInstance.get("api/user/products");
        setProducts(response.data.data);
        setLoading(false);

      } catch (err: any) {
        setLoading(false);
        setError("You are not authorized to view this content");
        setTimeout(() => {
          navigate("/admin");
        }, 2000);

      }

    };

    fetchContent();

  }, [navigate]);

  if (loading) {
    return (
      <div className="loading">
        Loading products...
      </div>
    );
  }

  if (error) {
    return (
      <div className="error-message">
        {error}
      </div>
    );
  }

  return (

    <div className="user-container">

      <h2>User Product Content</h2>

      <div className="product-grid">

        {products.map((product, index) => (

          <div key={index} className="product-card">

            <img src={product.imageUrl} alt={product.name} />

            <h3>{product.name}</h3>

            <p>
              {product.currency} {product.price}
            </p>

            <p>⭐ {product.rating}</p>

          </div>

        ))}

      </div>

    </div>

  );
}

export default UserPage;