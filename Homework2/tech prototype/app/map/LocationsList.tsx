import { LatLngExpression } from "leaflet";
import ResultCard from "./ResultCard";
import { calculateDistance, capitalizeAndReplace } from "./map.functions";

export default function LocationsList({
  mapLocations,
  userLocation,
}: {
  mapLocations: MapLocation[] | undefined;
  userLocation: LatLngExpression;
}) {
  return (
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
              <ResultCard title={m.name} badges={badges} distance={distance} />
            </div>
          );
        })}
      </div>
    </div>
  );
}
