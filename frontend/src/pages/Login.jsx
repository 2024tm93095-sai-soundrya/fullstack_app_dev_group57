import React, { useState } from "react";
import api from "../api/axios";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault(); // ⛔️ prevents the default GET submit behavior
    try {
      const res = await api.post("/auth/login", { email, password });
      if (res.data.role) {
        localStorage.setItem("role", res.data.role);
        localStorage.setItem("userId", res.data.userId);
        alert("Login successful as " + res.data.role);
        window.location.href = "/equipment";
      } else {
        alert("Invalid credentials");
      }
    } catch (err) {
      console.error(err);
      alert("Login failed – check backend connection");
    }
  };

  return (
    <div className="form-container">
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <input
          type="text"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Login</button>
      </form>
    </div>
  );
}
