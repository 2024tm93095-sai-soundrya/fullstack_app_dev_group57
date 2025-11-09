import React, { useEffect, useState } from "react";
import api from "../api/axios";

export default function MyBookings() {
  const [bookings, setBookings] = useState([]);
  const userId = localStorage.getItem("userId");

  useEffect(() => {
    api.get(`/bookings/user/${userId}`).then((res) => setBookings(res.data));
  }, []);

  return (
    <div>
      <h2>My Bookings</h2>
      {bookings.map((b) => (
        <div key={b.id} className="card">
          <p>Equipment: {b.equipment.name}</p>
          <p>Quantity: {b.quantity}</p>
          <p>Status: {b.status}</p>
        </div>
      ))}
    </div>
  );
}
