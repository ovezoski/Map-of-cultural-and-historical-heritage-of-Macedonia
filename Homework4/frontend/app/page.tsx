"use client";
import Image from "next/image";
import Link from "next/link";

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-center p-3 pt-24 w-full">
      <div className="flex flex-col lg:flex-row w-2/3 xl:w-2/3  justify-between align-center">
        <div className="lg:w-2/5">
          <div className="text-3xl my-2">
            Explore Macedonia&apos;s cultural and historical heritage.
          </div>

          <div className="text-sm my-2">
            Embark on a journey through the past and the present.
          </div>

          <div>
            <Link href="/map">
              <button className="bg-emerald-300 p-2 my-5 rounded-lg text-white">
                Start Now
              </button>
            </Link>
          </div>
        </div>
        <Image src="/home-map.png" alt="Map" width={355} height={326} />
      </div>

      <div className="mt-10">
        <div>Integrated with</div>
        <div>
          <Image src="/osm.png" alt="Openstreet map" width={150} height={79} />
        </div>
      </div>
    </main>
  );
}
