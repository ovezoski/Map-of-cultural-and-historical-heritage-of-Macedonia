import Image from "next/image";

import "@/app/ui/login/login.css";

import LoginForm from "../ui/login/login-form";

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-center w-full login-page">
      <div className="clouds">
        <Image
          alt="cloud"
          className="cloud1"
          src="/cloud1.png"
          width={369}
          height={124}
        />
        <Image
          alt="cloud"
          className="cloud2"
          src="/cloud2.png"
          width={250}
          height={47}
        />
        <Image
          alt="cloud"
          className="cloud3"
          src="/cloud3.png"
          width={250}
          height={83}
        />
        <Image
          alt="cloud"
          className="cloud4"
          src="/cloud4.png"
          width={295}
          height={37}
        />
        <Image
          alt="cloud"
          className="cloud5"
          src="/cloud5.png"
          width={474}
          height={110}
        />
      </div>
      <LoginForm />
    </main>
  );
}
