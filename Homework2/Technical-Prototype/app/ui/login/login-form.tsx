"use client";

import Image from "next/image";
import { useFormState } from "react-dom";
import Button from "../components/Button";
import TextInput from "../components/TextInput";

export default function LoginForm() {
  return (
    <form className="form w-full lg:w-1/2  bg-gray-50 py-10 p-3 lg:p-10">
      <div className="flex flex-col lg:flex-row justify-between items-center gap-5">
        <div className="block lg:hidden align-center">
          <Image
            src="/logo.png"
            alt="Logo picture with a map and location pin"
            width={200}
            height={200}
          />
        </div>

        <div>
          <div className="text-2xl p-2 text-center">Welcome back!</div>
          <div className="p-2"> We are so excited to see you again! </div>

          <TextInput name="email" />

          <div className="mb-2">
            <TextInput name="password" />

            <small className="text-emerald-400">Forgot your password?</small>
          </div>

          <div>
            <Button title="Login" />
          </div>
        </div>

        <div className="hidden lg:block">
          <Image
            src="/logo.png"
            alt="Logo picture with a map and location pin"
            width={225}
            height={225}
          />
        </div>
      </div>
    </form>
  );
}
