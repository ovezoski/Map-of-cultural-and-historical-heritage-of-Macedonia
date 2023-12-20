"use client";
import { Inter } from "next/font/google";
import "leaflet/dist/leaflet.css";
import "./globals.css";
import Footer from "./Footer";
import { createContext, useCallback, useEffect, useState } from "react";
import { usePathname, useRouter } from "next/navigation";
import Menu from "./Menu";
import axios from "axios";

const inter = Inter({ subsets: ["latin"] });

export const AuthContext = createContext<AuthContext | null>(null);

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const [authToken, setAuthToken] = useState<string | undefined>(undefined);

  const router = useRouter();

  const pathname = usePathname();

  const fetchAuth = useCallback(() => {
    axios
      .get("http://localhost:8080/user/", {
        headers: { Authorization: `Bearer ${authToken}` },
      })
      .catch(() => {
        router.push("/login");
        setAuthToken("");
        localStorage.setItem("authToken", "");
      });
  }, [authToken, router]);

  useEffect(() => {
    if (authToken !== "" && authToken !== undefined) {
      fetchAuth();
    }
  }, [authToken, fetchAuth]);

  useEffect(() => {
    const authTokenStorage = localStorage.getItem("authToken");

    if (authTokenStorage) {
      setAuthToken(authTokenStorage);
    } else {
      setAuthToken("");
    }
  }, []);

  useEffect(() => {
    if (authToken === "" && pathname !== "login") {
      router.push("/login");
    }
  }, [authToken, pathname, router]);

  return (
    <AuthContext.Provider value={{ authToken, setAuthToken }}>
      <html lang="en">
        <head></head>
        <body className={inter.className}>
          <Menu router={router} />
          {authToken === undefined ? <>Loading...</> : children}
          <Footer />
        </body>
      </html>
    </AuthContext.Provider>
  );
}
