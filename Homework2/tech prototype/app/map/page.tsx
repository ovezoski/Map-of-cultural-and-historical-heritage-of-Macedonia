import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet";
import LeafletMap from "../ui/map/leafletMap";

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

        <ResultCard
          title="Куршумли-Ан"
          badges={["Музеј", "Центар за уметност"]}
          distance={4}
        />

        <ResultCard
          distance={2}
          title="Скопско Кале"
          badges={["Замок", "Атракција"]}
        />
        <ResultCard
          distance={15}
          title="Аквадукт Скопје"
          badges={["Аквадукт", "Атракција"]}
        />
      </div>
      <div className="w-full lg:w-2/3">
        <LeafletMap />
      </div>
    </div>
  );
}
