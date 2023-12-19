"use client";

import LeafletMap from "../ui/map/leafletMap";
import { useCallback, useContext, useEffect, useState } from "react";
import LocationsList from "./LocationsList";
import axios from "axios";
import { AuthContext } from "../layout";

interface MapLocationResponse {
  content: MapLocation[];
}
export default function Map() {
  const { authToken } = useContext(AuthContext) as AuthContext;

  const [mapLocations, setMapLocations] = useState<MapLocationResponse>();

  const fetchMapLocations = useCallback(async () => {
    try {
      const response = await axios.get("http://localhost:8080/map-locations/", {
        headers: { Authorization: `Bearer ${authToken}` },
      });
      setMapLocations(response.data);
    } catch (error) {
      console.error("Error fetching map locations:", error);
    }
  }, [authToken]);

  useEffect(() => {
    fetchMapLocations();
  }, [fetchMapLocations]);

  return (
    <div className="py-20 p-1 flex-none lg:flex min-h-screen">
      <LocationsList mapLocations={mapLocations?.content} />
      <div className="w-full lg:w-2/3">
        <LeafletMap mapLocations={mapLocations?.content} />
      </div>
    </div>
  );
}
