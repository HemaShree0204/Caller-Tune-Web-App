import React, { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import "../styles/GenrePreference.css";

export default function GenrePreference() {
  const navigate = useNavigate();
  const location = useLocation();
  const username = location.state?.username || "User"; // fallback

  const [selectedGenres, setSelectedGenres] = useState([]);

  const genres = [
    "Pop", "Rock", "Jazz", "Classical",
    "Hip-Hop", "Country", "Electronic", "R&B"
  ];

  const toggleGenre = (genre) => {
    if (selectedGenres.includes(genre)) {
      setSelectedGenres(selectedGenres.filter((g) => g !== genre));
    } else {
      setSelectedGenres([...selectedGenres, genre]);
    }
  };

  const handleContinue = () => {
    localStorage.setItem("callerTuneGenres", JSON.stringify(selectedGenres));
    navigate("/home", { state: { username } });
  };

  const handleSkip = () => {
    navigate("/home", { state: { username } });
  };

  return (
    <div className="genre-page">
      <h1>Hello, {username}! 🎵</h1>
      <p>Select your favorite music genres:</p>

      <div className="genres-grid">
        {genres.map((genre) => (
          <div
            key={genre}
            className={`genre-card ${selectedGenres.includes(genre) ? "selected" : ""}`}
            onClick={() => toggleGenre(genre)}
          >
            {genre}
          </div>
        ))}
      </div>

      <div className="genre-actions">
        <button onClick={handleContinue} className="continue-btn">
          Continue
        </button>
        <button onClick={handleSkip} className="skip-btn">
          Skip
        </button>
      </div>
    </div>
  );
}
