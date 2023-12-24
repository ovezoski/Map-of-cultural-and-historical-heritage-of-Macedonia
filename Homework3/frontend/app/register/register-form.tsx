"use client";

import Image from "next/image";
import Button from "../ui/components/Button";
import { useRouter } from "next/navigation";
import { useContext, useState } from "react";
import axios from "axios";
import { AuthContext } from "../AuthContext";

export default function RegisterForm() {
  const router = useRouter();
  const [usernameValue, setUsernameValue] = useState("");
  const [passwordValue, setPasswordValue] = useState("");
  const { setAuthToken } = useContext(AuthContext) as AuthContext;

  const handleRegister = async () => {
    const res = await axios.post("http://localhost:8080/auth/register", {
      username: `${usernameValue}`,
      password: `${passwordValue}`,
    });
    

    if (res == undefined || res.status != 200) {
      return;
    }

    localStorage.setItem("newUser", "1");
    router.push("/login");
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
          <div className="text-2xl p-2 text-center">Create an account now!</div>

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

          <div className="mt-2">
            <Button title="Register" onClickFunc={handleRegister} />
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
