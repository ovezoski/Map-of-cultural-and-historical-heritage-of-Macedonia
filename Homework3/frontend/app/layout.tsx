"use client";
import { Inter } from "next/font/google";
import "leaflet/dist/leaflet.css";
import "./globals.css";
import Footer from "./Footer";
import { useEffect, useState } from "react";
import { usePathname, useRouter } from "next/navigation";
import Menu from "./Menu";
import axios from "axios";
import Auth from "./AuthContext";

const inter = Inter({ subsets: ["latin"] });

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const [authToken, setAuthToken] = useState<string | undefined>(undefined);

  const router = useRouter();

  const pathname = usePathname();

  useEffect(() => {
    if (authToken === "" && pathname !== "login") {
      router.push("/login");
    }
  }, [authToken, pathname, router]);

  return (
    <html lang="en">
      <head></head>
      <body className={inter.className}>
        <Auth>{children}</Auth>
      </body>
    </html>
  );
}
