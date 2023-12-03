import Image from "next/image";

import "@/app/ui/login/login.css";

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
      <div className="form w-full lg:w-1/2  bg-gray-50 py-10 p-3 lg:p-10">
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

            <div>
              <div className="uppercase text-xs">
                <label htmlFor="email">Email or phone number</label>
              </div>
              <div>
                <input
                  id="email"
                  className="border rounded  p-1 w-full"
                  type="text"
                />
              </div>
            </div>

            <div>
              <div>
                <label htmlFor="password">Password</label>
              </div>
              <div>
                <input
                  id="password"
                  className="border rounded p-1 w-full"
                  type="text"
                />
              </div>
              <div>
                <small className="p-1 text-emerald-400">
                  Forgot your password?
                </small>
              </div>
            </div>
            <div className="">
              <button className="bg-green-400 rounded p-3 text-white w-full">
                Log In
              </button>
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
      </div>
    </main>
  );
}
