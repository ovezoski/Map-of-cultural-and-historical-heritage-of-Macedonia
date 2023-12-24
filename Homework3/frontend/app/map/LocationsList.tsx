"use client";

import { LatLngExpression } from "leaflet";
import ResultCard from "./ResultCard";
import { calculateDistance, capitalizeAndReplace } from "./map.functions";
import { cities } from "./cities";
import { categories } from "./categories";

export default function LocationsList({
  mapLocations,
  userLocation,
  city,
  setCity,
  category,
  setCategory,
  name,
  setName,
}: {
  mapLocations: MapLocation[] | undefined;
  userLocation: LatLngExpression;
  city: string;
  setCity: (city: string) => void;
  category: string;
  setCategory: (category: string) => void;
  name: string;
  setName: (name: string) => void;
}) {
  return (
    <div className=" w-full lg:w-1/3 p-1">
      <div className="px-2">
        <input
          className="rounded p-2 bg-slate-100 w-full"
          placeholder="Search..."
          value={name}
          onChange={(e) => {
            setName(e.target.value);
          }}
        />
      </div>
      <div className="flex">
        <div className="p-2">
          <select
            className="rounded w-full p-2 bg-slate-100 text-gray-500"
            name="category-filter"
            id="category-filter"
            placeholder="Category"
            value={category}
            onChange={(e) => setCategory(e.target.value)}
          >
            <option value="">Category</option>

            {categories.map((category) => {
              const categoryString = `${category.column}=${category.category}`;
              return (
                <option key={categoryString} value={categoryString}>
                  {capitalizeAndReplace(category.category)}
                </option>
              );
            })}
          </select>
        </div>
        <div className="p-2">
          <select
            className="rounded w-full p-2 bg-slate-100 text-gray-500"
            name="city-filter"
            id="city-filter"
            placeholder="City"
            value={city}
            onChange={(e) => setCity(e.target.value)}
          >
            <option value="">City</option>

            {cities.map((city) => (
              <option key={city} value={city}>
                {city}
              </option>
            ))}
          </select>
        </div>
      </div>

      <div className="rounded m-1 p-2 bg-emerald-400 text-white text-center hover:bg-emerald-300 active:bg-emerald-200 ">
        Apply
      </div>

      <div style={{ height: "75vh", overflow: "scroll", overflowX: "hidden" }}>
        {mapLocations?.map((m) => {
          let badges: string[] = [];

          const categories: (keyof MapLocation)[] = [
            "tourism",
            "historic",
            "religion",
            "building",
            "amenity",
            "denomination",
            "memorial",
            "museum",
            "attraction",
            "tomb",
            "placeOfWorship",
            "ruins",
          ];

          categories.forEach((category) => {
            if (m[category] == null) return;

            badges.push(capitalizeAndReplace(m[category]));
          });

          const distance = calculateDistance(
            userLocation[0 as keyof LatLngExpression],
            userLocation[1 as keyof LatLngExpression],
            m.latitude,
            m.longitude
          );

          return (
            <div key={mapLocations.indexOf(m)}>
              <ResultCard title={m.name} badges={badges} distance={distance} mid={m.id} />
            </div>
          );
        })}
      </div>
    </div>
  );
}
