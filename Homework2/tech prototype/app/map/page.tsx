"use client";

import LeafletMap from "./leafletMap";
import { useCallback, useContext, useEffect, useState } from "react";
import LocationsList from "./LocationsList";
import axios from "axios";
import { AuthContext } from "../layout";
import { LatLngExpression } from "leaflet";

interface MapLocationResponse {
  content: MapLocation[];
}
export default function Map() {
  const { authToken } = useContext(AuthContext) as AuthContext;

  const [mapLocations, setMapLocations] = useState<MapLocationResponse>();
  const [userLocation, setUserLocation] = useState<LatLngExpression>([0, 0]);
  const [category, setCategory] = useState("");
  const [name, setName] = useState("");
  const [city, setCity] = useState("");

  useEffect(() => {
    if ("geolocation" in navigator) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          setUserLocation([
            position.coords.latitude,
            position.coords.longitude,
          ]);
        },
        (error) => {
          console.error("Error getting user location:", error);
        }
      );
    } else {
      console.log("Geolocation is not supported by your browser");
    }
  }, []);

  const fetchMapLocations = useCallback(async () => {
    try {
      const categoryColumn = category.split("=")[0] || "category";
      const categoryValue = category.split("=")[1] || null;

      const queryParams = new URLSearchParams({
        city: city || "",
        name: name || "",
        category: categoryValue || "",
      });

      const response = await axios.get(
        "http://localhost:8080/map-locations/?" + queryParams.toString(),
        {
          headers: { Authorization: `Bearer ${authToken}` },
        }
      );

      setMapLocations(response.data);
    } catch (error) {
      console.error("Error fetching map locations:", error);
    }
  }, [authToken, category, city, name]);

  useEffect(() => {
    fetchMapLocations();
  }, [fetchMapLocations]);

  return (
    <div className="py-20 p-1 flex-none lg:flex min-h-screen">
      <LocationsList
        mapLocations={mapLocations?.content}
        userLocation={userLocation}
        city={city}
        setCity={setCity}
        category={category}
        setCategory={setCategory}
        name={name}
        setName={setName}
      />
      <div className="w-full lg:w-2/3">
        <LeafletMap
          userLocation={userLocation}
          mapLocations={mapLocations?.content}
        />
      </div>
    </div>
  );
}
