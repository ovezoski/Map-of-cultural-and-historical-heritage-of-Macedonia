"use client";

import Image from "next/image";
import Link from "next/link";
import { usePathname } from "next/navigation";

export default function Menu() {
  const pathname = usePathname();

  const navigationItems = [
    { title: "Home", href: "/" },
    { title: "Map", href: "/map" },
    { title: "About Us", href: "/about-us" },
  ];
  return (
    <nav className="fixed w-full p-3 drop-shadow-md bg-slate-50 flex justify-between">
      <div className="flex">
        <div className="flex justify-center items-center">
          <Image src="/logo.png" width={30} height={30} alt="logo" />
        </div>
        <div className="text-lg px-2 hidden lg:block">ByteVenture</div>
      </div>
      <div className="flex">
        {navigationItems.map((item) => (
          <div
            className={
              pathname === item.href
                ? "px-2 mx-2 bg-emerald-300 rounded p-1 text-white"
                : "px-2 mx-2 rounded p-1"
            }
            key={item.title}
          >
            <Link href={item.href}>{item.title}</Link>
          </div>
        ))}
      </div>
    </nav>
  );
}
