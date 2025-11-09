import React, { useEffect, useState } from "react";
import api from "../api/axios";
import EquipmentCard from "../components/EquipmentCard";

export default function EquipmentList() {
  const [equipment, setEquipment] = useState([]);

  useEffect(() => {
    api.get("/equipment").then((res) => setEquipment(res.data));
  }, []);

  return (
    <div className="grid">
      {equipment.map((item) => (
        <EquipmentCard key={item.id} item={item} />
      ))}
    </div>
  );
}
