"use client";

import axios from "axios";
import { usePathname, useRouter } from "next/navigation";
import { createContext, useCallback, useEffect, useState } from "react";
import Menu from "./Menu";
import Footer from "./Footer";

export interface AuthContext {
  authToken: string | undefined;
  setAuthToken: (o: any) => any;
}

export const AuthContext = createContext<AuthContext | null>(null);

export default function Auth({ children }: { children: React.ReactNode }) {
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
      <Menu router={router} />
      {authToken === undefined ? (
        <div className="flex flex-col justify-center">Loading...</div>
      ) : (
        children
      )}
      <Footer />
    </AuthContext.Provider>
  );
}
