import React from "react";
import { useLocation } from "react-router-dom";

export default function HomePage() {
  const location = useLocation();
  const username = location.state?.username || "User";

  const savedGenres = JSON.parse(localStorage.getItem("callerTuneGenres")) || [];

  return (
    <div className="home" style={{ textAlign: "center", padding: "30px" }}>
      <h1>Welcome, {username}! 🎶</h1>
      <p>You’ve successfully logged in.</p>

      {savedGenres.length > 0 ? (
        <>
          <h2>Your Favorite Genres:</h2>
          <ul style={{ listStyle: "none", padding: 0 }}>
            {savedGenres.map((genre, idx) => (
              <li
                key={idx}
                style={{
                  display: "inline-block",
                  margin: "5px",
                  padding: "8px 15px",
                  borderRadius: "20px",
                  backgroundColor: "#6a0dad",
                  color: "white",
                  fontWeight: "bold",
                }}
              >
                {genre}
              </li>
            ))}
          </ul>
        </>
      ) : (
        <p>No genres selected (you skipped).</p>
      )}
    </div>
  );
}
