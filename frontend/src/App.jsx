import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Login from "./pages/Login";
import EquipmentList from "./pages/EquipmentList";
import MyBookings from "./pages/MyBookings";
import BookingRequests from "./pages/BookingRequests";
import "./styles/app.css";

function App() {
  return (
    <Router>
      <Navbar />
      <div className="container">
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/equipment" element={<EquipmentList />} />
          <Route path="/mybookings" element={<MyBookings />} />
          <Route path="/requests" element={<BookingRequests />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
