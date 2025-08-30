import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import WelcomePage from "./pages/Welcomepage.jsx";
import GenrePreference from "./pages/GenrePreference.jsx";
import HomePage from "./pages/HomePage.jsx";
import RegisterModal from "./components/RegisterModal.jsx";

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<WelcomePage />} />
       <Route path="/register" element={<RegisterModal />} />
      <Route path="/genres" element={<GenrePreference />} />
      <Route path="/home" element={<HomePage />} />
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
}
