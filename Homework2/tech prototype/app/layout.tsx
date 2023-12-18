"use client"
import { Inter } from "next/font/google";
import "./globals.css";
import Footer from "./ui/footer/footer";
import Menu from "./ui/menu/menu";
import { createContext, useEffect, useState } from "react";
import { usePathname, useRouter } from "next/navigation";
import axios from 'axios';

const inter = Inter({ subsets: ["latin"] });

export const AuthContext = createContext<AuthContext | null>(null);

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const [authToken, setAuthToken] = useState("");
  const router = useRouter();
  const pathname = usePathname();

  const fetchAuth = async () => {
    if (pathname == "/login") return;
    const res = await axios.get("http://localhost:8080/user/", {
       headers: {Authorization: `Bearer ${authToken}`}
    })
    .catch((e) => {
      router.push("/login");
    })
  }

  useEffect(() => {
    fetchAuth();
  })

  return (
    <>
      <html lang="en">
        <head></head>
        <body className={inter.className}>
          <AuthContext.Provider value={{authToken, setAuthToken}}>
            <Menu />
              {children}
            <Footer />
          </AuthContext.Provider>
        </body>
      </html>
    </>
  );
}
