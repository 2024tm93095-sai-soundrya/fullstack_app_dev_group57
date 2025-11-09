import React from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";

export default function Navbar() {
  return (
    <nav className="navbar">
      <h3>Equipment Lending Portal</h3>
      <div className="nav-links">
        <Link to="/">Login</Link>
        <Link to="/equipment">Equipment</Link>
        <Link to="/mybookings">My Bookings</Link>
        <Link to="/requests">Requests</Link>
      </div>
    </nav>
  );
}
