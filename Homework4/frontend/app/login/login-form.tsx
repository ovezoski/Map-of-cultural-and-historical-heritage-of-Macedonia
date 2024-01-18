"use client";

import Image from "next/image";
import Button from "../ui/components/Button";
import { useRouter } from "next/navigation";
import { useContext, useEffect, useState } from "react";
import axios from "axios";
import { AuthContext } from "../AuthContext";

export default function LoginForm() {
  const router = useRouter();
  const [usernameValue, setUsernameValue] = useState("");
  const [passwordValue, setPasswordValue] = useState("");
  const { authToken, setAuthToken } = useContext(AuthContext) as AuthContext;

  const handleLogin = async () => {
    const res = await axios.post("http://localhost:8080/auth/login", {
      username: `${usernameValue}`,
      password: `${passwordValue}`,
    });
    

    if (res == undefined || res.status != 200) {
      return;
    }

    setAuthToken(res.data.jwt);
    localStorage.setItem("authToken", res.data.jwt);
    localStorage.setItem("username", usernameValue);
    localStorage.setItem("newUser", "0");

    router.push("/");
  };

  return (
    <form
      onSubmit={(e) => e.preventDefault()}
      className="w-full lg:w-1/2  bg-gray-50 py-10 p-3 lg:p-10"
    >
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
          {localStorage.getItem("newUser") === "1" && <div className="text-2xl p-2 text-center">Login to your new account!</div>}
          <div className="text-2xl p-2 text-center">Welcome back!</div>
          <div className="p-2"> We are so excited to see you again! </div>

          <div className="mb-2">
            <input
              className="border rounded p-1 w-full"
              value={usernameValue}
              onChange={(e) => setUsernameValue(e.target.value)}
              placeholder="username"
            />
          </div>

          <div>
            <input
              type="password"
              className="border rounded p-1 w-full"
              value={passwordValue}
              onChange={(e) => setPasswordValue(e.target.value)}
              placeholder="password"
            />
          </div>

          <small onClick={() => router.push("/register")} className="text-emerald-400 cursor-pointer">{"Don't have an account?"}</small>

          <div>
            <Button title="Login" onClickFunc={handleLogin} />
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
