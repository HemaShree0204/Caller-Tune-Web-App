import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import girlImg from "../assets/girl-music.png";
import RegisterModal from "../components/RegisterModal.jsx";

export default function WelcomePage() {
  const [openRegister, setOpenRegister] = useState(false);
  const navigate = useNavigate();

  const handleSuccess = (user) => {
    // Optionally persist
    localStorage.setItem("callerTuneUser", JSON.stringify(user));
    navigate("/home", { replace: true });
  };

 

  return (
    <div className="welcome-container">
      {/* NAVBAR */}
      <nav className="navbar">
        <div className="logo">CallerTune</div>
        <ul className="nav-links">
          <li><a href="#my profile">My Profile</a></li>
          <li><a href="#about">About</a></li>
          <li><a href="#contact">Contact</a></li>
          <li>
            <button className="nav-register" onClick={() => setOpenRegister(true)}>
              Register
            </button>
          </li>
        </ul>
      </nav>

      {/* HERO */}
      <section className="hero">
        <div className="hero-text">
          <h3 className="welcome">Welcome to CRBT Portal</h3>
          <h1><span>Redefine Every Call</span> with Music That Speaks for You</h1>
          <p className="subline">Transform your caller tune into an identity, not just a sound.</p>
          
        </div>

        <div className="hero-image">
          <img src={girlImg} alt="Girl with headphones" />
        </div>

        {/* Floating music notes */}
        <div className="notes">
          <span className="note purple n1">♪</span>
          <span className="note orange n2">♫</span>
          <span className="note purple n3">♪</span>
          <span className="note orange n4">♬</span>
          <span className="note purple n5">♫</span>
          <span className="note orange n6">♪</span>
          <span className="note purple n7">♩</span>
          <span className="note orange n8">♪</span>
          <span className="note purple n9">♫</span>
          <span className="note orange n10">♪</span>
        </div>
      </section>

      {/* Dummy sections so the top links have anchors */}
      <section id="My Profile" className="spacer" />
      <section id=" About" className="spacer" />
      <section id="contact" className="spacer" />
      {/* REGISTER MODAL */}
      {openRegister && (
        <div className="modal-overlay" onClick={() => setOpenRegister(false)}>
        <div onClick={(e) => e.stopPropagation()}>
        <RegisterModal
          open={openRegister}
          onClose={() => setOpenRegister(false)}
          onSuccess={handleSuccess}
        />
        </div>
       </div>
      )}
    </div>
  );
}
