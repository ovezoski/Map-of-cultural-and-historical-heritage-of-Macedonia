import { HTMLInputTypeAttribute } from "react";

export default function TextInput({
  label,
  name,
  type = "text",
}: {
  label?: string;
  name: string;
  type?: HTMLInputTypeAttribute;
}) {
  return (
    <div>
      <div>
        <label className="uppercase " htmlFor={name}>
          {label || name}
        </label>
      </div>
      <div>
        <input
          id={name}
          name={name}
          className="border rounded p-1 w-full"
          type={type}
        />
      </div>
    </div>
  );
}
