function Card({ title }: { title: String }) {
  return (
    <div className="bg-emerald-100 rounded-lg border-4 border-emerald-300 p-5 m-4">
      <div className="text-xl text-center font-bold"> {title}</div>
      <div className="p-1 text-center text-sm">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
        veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
        commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
        velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
        occaecat cupidatat non proident, sunt in culpa qui officia deserunt
        mollit anim id est laborum.
      </div>
    </div>
  );
}

export default function AboutUs() {
  return (
    <div className="min-h-screen w-full py-20 flex flex-col items-center">
      <div className="text-center w-full lg:w-1/2">
        <div className="my-3">
          <div className="text-xl lg:text-4xl font-bold">Who we are?</div>
          <div className="text-xl lg:text-4xl font-bold">What is our goal?</div>
          <div className="text-xl lg:text-4xl font-bold">
            Where can you contact us?
          </div>
        </div>
        <div className="text-sm my-3">
          A large number of tourists as well as residents of Macedonia, often
          wish to visit attractions that will allow them to explore the rich
          culture and history of the country more deeply.
        </div>
      </div>

      <div className="lg:flex justify-around w-full xl:w-3/4">
        <Card title="About us" />
        <Card title="Our mission" />
        <Card title="Contact us" />
      </div>
    </div>
  );
}
