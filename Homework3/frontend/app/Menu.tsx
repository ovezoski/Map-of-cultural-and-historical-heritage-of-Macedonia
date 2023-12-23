"use client";

import { AppRouterInstance } from "next/dist/shared/lib/app-router-context.shared-runtime";
import Image from "next/image";
import Link from "next/link";
import { usePathname } from "next/navigation";
import { useContext } from "react";
import { AuthContext } from "./AuthContext";

export default function Menu({ router }: { router: AppRouterInstance }) {
  const pathname = usePathname();

  const { authToken, setAuthToken } = useContext(AuthContext) as AuthContext;

  function logout() {
    setAuthToken("");
    router.push("/");
  }

  const navigationItems = [
    { title: "Home", href: "/" },
    { title: "Map", href: "/map" },
    { title: "About Us", href: "/about-us" },
  ];
  return (
    <nav className="fixed w-full p-3 drop-shadow-md bg-slate-50 flex justify-between">
      <div className="flex">
        <div className="flex justify-center items-center">
          <Image src="/logo.png" width={30} height={31} alt="logo" />
        </div>
        <div className="text-lg px-2 hidden lg:block">ByteVenture</div>
      </div>
      <div className="flex">
        {navigationItems.map((item) => (
          <div
            className={
              "px-2 mx-2 p-1 rounded" +
              (pathname === item.href ? " bg-emerald-300  text-white" : "")
            }
            key={item.title}
          >
            <Link href={item.href}>{item.title}</Link>
          </div>
        ))}

        {authToken && (
          <button onClick={logout} className={"px-2 mx-2 p-1 rounded"}>
            Logout
          </button>
        )}
      </div>
    </nav>
  );
}
