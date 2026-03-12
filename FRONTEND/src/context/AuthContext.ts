import axios, { AxiosError } from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/",
  headers: {
    "Content-Type": "application/json",
  },
});

// Request Interceptor (attach token)
axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("authToken");

    if (token && config.headers) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error: AxiosError) => Promise.reject(error)
);


axiosInstance.interceptors.response.use(
  (response) => response,
  (error: AxiosError) => {
    if (error.response?.status === 401) { 
      console.warn("Token expired or invalid, redirecting...");

      localStorage.removeItem("authToken");
      window.location.href = "/login";
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;