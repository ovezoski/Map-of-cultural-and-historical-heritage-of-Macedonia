export default function ResultCard({
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
        <div className="flex flex-wrap gap-2">
          {badges.map((badge, i) => (
            <div
              key={i}
              className="bg-emerald-400 p-1 text-sm text-white rounded whitespace-nowrap"
            >
              {badge}
            </div>
          ))}
        </div>
      </div>
      <div className=" text-gray-500 bold text-right w-1/12">
        {distance + "km"}
      </div>
    </div>
  );
}