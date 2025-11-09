import React from "react";
import api from "../api/axios";

export default function EquipmentCard({ item }) {
  const userId = localStorage.getItem("userId");

  const requestBooking = async () => {
    const qty = prompt("Enter quantity:", 1);
    if (!qty) return;
    await api.post("/bookings/request", {
      userId,
      equipmentId: item.id,
      quantity: qty,
    });
    alert("Request submitted!");
  };

  return (
    <div className="card">
      <h3>{item.name}</h3>
      <p>Category: {item.category}</p>
      <p>Condition: {item.conditionStatus}</p>
      <p>Available: {item.availableQuantity}</p>
      <button onClick={requestBooking}>Request</button>
    </div>
  );
}
