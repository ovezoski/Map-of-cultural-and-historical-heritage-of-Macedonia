"use client";

import customPinIcon from "/public/pin.png";
import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import { createContext, useEffect, useState } from "react";
import L, { LatLngExpression, LatLngTuple } from "leaflet";

const customIcon = L.icon({
  iconUrl: customPinIcon.src,
  iconSize: [20, 30],
  iconAnchor: [20, 40],
  popupAnchor: [0, -40],
});

function LeafletMap({
  mapLocations,
  userLocation,
}: {
  mapLocations: MapLocation[] | undefined;
  userLocation: LatLngExpression;
}) {
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
        {mapLocations?.map((location) => (
          <Marker
            position={[Number(location.latitude), Number(location.longitude)]}
            key={location.id}
          />
        ))}

        <Marker position={userLocation} icon={customIcon}></Marker>
      </MapContainer>
    </>
  );
}

export default LeafletMap;
