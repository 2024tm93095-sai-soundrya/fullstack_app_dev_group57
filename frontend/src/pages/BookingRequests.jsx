import React, { useEffect, useState } from "react";
import api from "../api/axios";

export default function BookingRequests() {
  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    api.get("/bookings").then((res) => setBookings(res.data));
  }, []);

  const approve = (id) => {
    api.put(`/bookings/${id}/approve`).then(() => window.location.reload());
  };

  const markReturned = (id) => {
    api.put(`/bookings/${id}/return`).then(() => window.location.reload());
  };

  return (
    <div>
      <h2>Booking Requests (Admin)</h2>
      {bookings.map((b) => (
        <div key={b.id} className="card">
          <p>User: {b.user.name}</p>
          <p>Equipment: {b.equipment.name}</p>
          <p>Status: {b.status}</p>
          {b.status === "REQUESTED" && (
            <button onClick={() => approve(b.id)}>Approve</button>
          )}
          {b.status === "ISSUED" && (
            <button onClick={() => markReturned(b.id)}>Mark Returned</button>
          )}
        </div>
      ))}
    </div>
  );
}
