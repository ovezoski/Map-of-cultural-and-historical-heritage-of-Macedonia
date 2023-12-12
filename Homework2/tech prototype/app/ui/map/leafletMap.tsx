"use client";

import { MapContainer, TileLayer } from "react-leaflet";
import "leaflet/dist/leaflet.css";

import { createContext, useEffect } from "react";
import { LatLngTuple } from "leaflet";

const LeafletMap = () => {
  useEffect(() => {
    createContext("da");
  }, []);

  const macedoniaPosition: LatLngTuple = [41.6086, 21.7453];

  return (
    <>
      <MapContainer
        center={macedoniaPosition}
        zoom={9}
        style={{ height: "100%", width: "100%" }}
      >
        <TileLayer
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        />
      </MapContainer>
    </>
  );
};

export default LeafletMap;
