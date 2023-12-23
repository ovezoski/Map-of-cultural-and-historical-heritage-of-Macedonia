export default function Button(
  { title, onClickFunc }: { title: string; onClickFunc?: () => void },
) {
  return (
    <button className="bg-green-400 rounded p-3 text-white w-full hover:bg-green-300 active:bg-green-200" onClick={onClickFunc}>
      {title}
    </button>
  );
}
