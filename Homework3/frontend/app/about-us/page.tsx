function About({ title }: { title: String }) {
  return (
    <div className="bg-emerald-100 rounded-lg border-4 border-emerald-300 p-5 m-4">
      <div className="text-xl text-center font-bold"> {title}</div>
      <div className="p-1 text-center text-sm">
        Welcome to ByteVentureMK, a collaborative endeavor by five passionate
        students – Tamara Stojanova, Filip Samardjiski, Viktor Vasilev, Blagoja
        Ovezoski, and Martin Patchev. As part of our university course in Design
        and Architecture of Software, we embarked on the journey to create a
        unique application focused on showcasing the rich cultural and
        historical heritage of Macedonia. ByteVentureMK is not just an app; its
        a testament to our commitment to innovation and education. Join us as we
        navigate the digital realm to bring Macedonias cultural tapestry closer
        to you.
      </div>
    </div>
  );
}

function Mission({ title }: { title: String }) {
  return (
    <div className="bg-emerald-100 rounded-lg border-4 border-emerald-300 p-5 m-4">
      <div className="text-xl text-center font-bold"> {title}</div>
      <div className="p-1 text-center text-sm">
        At ByteVentureMK, our mission is to seamlessly connect people with
        Macedonias cultural and historical wonders. Through our application, we
        aim to provide users with an immersive experience, exploring the diverse
        heritage that our country holds. We strive to make ByteVentureMK a
        platform that not only educates but also fosters a sense of pride and
        appreciation for Macedonias rich history. Our mission is fueled by a
        passion for technology, education, and cultural preservation, and we
        invite you to be a part of this exciting journey.
      </div>
    </div>
  );
}

function Contact({ title }: { title: String }) {
  return (
    <div className="bg-emerald-100 rounded-lg border-4 border-emerald-300 p-5 m-4">
      <div className="text-xl text-center font-bold"> {title}</div>
      <div className="p-1 text-center text-sm">
        We value your feedback and engagement. For any inquiries, suggestions,
        or collaboration opportunities, feel free to reach out to us. Connect
        with the ByteVentureMK team via email at byteventuremk@email.com or
        through our social media channels. Your input is crucial in shaping the
        future of ByteVentureMK, and we look forward to hearing from you as we
        continue to enhance and expand our application. Thank you for being a
        part of our venture!
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
        <About title="About us" />
        <Mission title="Our mission" />
        <Contact title="Contact us" />
      </div>
    </div>
  );
}
