import React, { useState } from "react";
import "../styles/RegisterModal.css";
import { useNavigate } from "react-router-dom";

export default function RegisterModal({ open, onClose, onSuccess }) {
  const [isSignUp, setIsSignUp] = useState(false);
  const [formData, setFormData] = useState({ name: "", email: "", password: "" });
  const navigate = useNavigate();

  if (!open) return null;

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSignup = (e) => {
    e.preventDefault();

    // ✅ simple dummy check
    if (formData.name && formData.email && formData.password) {
      console.log("Signup successful:", formData);
      localStorage.setItem("callerTuneUser", JSON.stringify({
      username: formData.name,
      email: formData.email
    }));
      onSuccess?.();
      navigate("/genres",{ state: { username: formData.name } }); // redirect
      onClose();
    } else {
      alert("Please fill all fields!");
    }
  };

  const handleLogin = (e) => {
    e.preventDefault();

    // ✅ simple dummy check
    if (formData.email && formData.password) {
      console.log("Login successful:", formData);
       localStorage.setItem("callerTuneUser", JSON.stringify({
      username: formData.email.split("@")[0], // just a dummy username
      email: formData.email
    }));
      onSuccess?.();
      navigate("/genres",{ state: { username: formData.email.split("@")[0] } }); // redirect
      onClose();
    } else {
      alert("Please enter email and password!");
    }
  };

  return (
    <div className="modal-overlay">
      <div className={`modal-container ${isSignUp ? "right-panel-active" : ""}`}>
        {/* === Close Button === */}
        <span className="close-btn" onClick={onClose}>
          &times;
        </span>

        {/* === Sign Up Form === */}
        <div className="form-container sign-up-container">
          <form onSubmit={handleSignup}>
            <h1>Create Account</h1>
            <input type="text" name="name" placeholder="Name" value={formData.name} onChange={handleChange} required />
            <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} required />
            <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} required />
            <button type="submit">Sign Up</button>
          </form>
        </div>

        {/* === Login Form === */}
        <div className="form-container sign-in-container">
          <form onSubmit={handleLogin}>
            <h1>Sign in</h1>
            <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} required />
            <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} required />
            <button type="submit">Login</button>
          </form>
        </div>

        {/* === Overlay Section === */}
        <div className="overlay-container">
          <div className="overlay">
            <div className="overlay-panel overlay-left">
              <h1>Welcome Back!</h1>
              <p>Glad to see you again! Please log in to continue.</p>
              <button className="ghost" onClick={() => setIsSignUp(false)}>Login</button>
            </div>
            <div className="overlay-panel overlay-right">
              <h1>Welcome to Our App!</h1>
              <p>Create your account and explore all features.</p>
              <button className="ghost" onClick={() => setIsSignUp(true)}>Sign Up</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
