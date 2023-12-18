"use client"
import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet";
import LeafletMap from "../ui/map/leafletMap";
import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../layout";
import axios from "axios";

function ResultCard({
  title,
  badges,
  distance,
}: {
  title: String;
  badges: String[];
  distance: Number;
}) {
  return (
    <div className="m-2 rounded bg-slate-50 p-5 flex items-center justify-between">
      <div>
        <div className="text-lg">{title}</div>
        <div className="flex gap-2">
          {badges.map((badge, i) => (
            <div key={i} className="bg-emerald-200 p-2 rounded">
              {badge}
            </div>
          ))}
        </div>
      </div>
      <div className=" text-gray-500 bold text-right w-1/12">
        {" "}
        {distance + "km"}
      </div>
    </div>
  );
}

export default function Map() {
  const { authToken, setAuthToken } = useContext(AuthContext) as AuthContext;
  const [mapLocations, setMapLocations] = useState<MapLocation[]>([]);

  const fetchMapLocations = () => {
    const res = axios.get("http://localhost:8080/user/mapLocations", {
      headers: { Authorization: `Bearer ${authToken}` }
    })
    .then((res) => {
      setMapLocations(res.data);
    })
    .catch((e) => { })
  }

  useEffect(() => {
    fetchMapLocations();
    
  }, []);

  return (
    <div className="py-20 p-1 flex-none lg:flex min-h-screen">
      <div className=" w-full lg:w-1/3 p-1">
        <div className="px-2">
          <input
            className="rounded p-2 bg-slate-100 w-full"
            placeholder="Search..."
          />
        </div>
        <div className="flex justify-between px-1 g-1">
          <div className="rounded grow m-1 p-2 bg-slate-100 text-gray-500">
            Category
          </div>
          <div className="rounded grow m-1 p-2 bg-slate-100 text-gray-500">
            City
          </div>
          <div className="rounded grow m-1 p-2 bg-emerald-400 text-white text-center hover:bg-emerald-300 active:bg-emerald-200 ">
            Apply
          </div>
        </div>

        <div style={{height: "75vh", overflow: "scroll", overflowX: "hidden"}}>
            {mapLocations.map((m) => (
              <div key={mapLocations.indexOf(m)}>
                <ResultCard title={m.name} badges={["abc", "aASD"]} distance={12} />
              </div>
            ))}
          </div>
      </div>
      <div className="w-full lg:w-2/3">
        <LeafletMap />
      </div>
    </div>
  );
}
