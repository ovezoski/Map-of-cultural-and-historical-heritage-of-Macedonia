"use client";

import axios from "axios";
import { useRouter } from "next/navigation";
import {
  MutableRefObject,
  useContext,
  useEffect,
  useRef,
  useState,
} from "react";
import { AuthContext } from "../AuthContext";

export default function ResultCard({
  title,
  badges,
  distance,
  mid,
}: {
  title: String;
  badges: String[];
  distance: Number;
  mid: string;
}) {
  const router = useRouter();
  const reviewsDiv = useRef() as MutableRefObject<HTMLDivElement>;
  const { authToken } = useContext(AuthContext) as AuthContext;
  const [reviews, setReviews] = useState<Review[]>([]);
  const [scoreValue, setScoreValue] = useState("");
  const [descriptionValue, setDescriptionValue] = useState("");
  const [roles, setRoles] = useState<string[]>([]);
  const [editMode, setEditMode] = useState(false);
  const [editTitleValue, setEditTitleValue] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8080/user/roles", {
        headers: { Authorization: `Bearer ${authToken}` },
      })
      .then((res) => {
        setRoles(res.data);
      })
      .catch((e) => console.log(e));
  }, [authToken]);

  const handleReviews = async () => {
    reviewsDiv.current.style.display = "block";

    const reviewsFetch = await axios.get(
      `http://localhost:8080/map-locations/${mid}/reviews`,
      {
        headers: { Authorization: `Bearer ${authToken}` },
      }
    );

    setReviews(reviewsFetch.data);
  };

  const handleAddReview = async () => {
    let currScoreValue = parseFloat(parseFloat(scoreValue).toFixed(1));
    if (currScoreValue > 10) currScoreValue = 10.0;
    if (currScoreValue < 0) currScoreValue = 0.0;

    await axios.post(
      `http://localhost:8080/map-locations/${mid}/addReview`,
      {
        score: currScoreValue,
        description: descriptionValue,
      },
      {
        headers: { Authorization: `Bearer ${authToken}` },
      }
    );

    handleReviews();
  };

  const handleDeleteReview = async (reviewId: number) => {
    await axios.delete(
      `http://localhost:8080/map-locations/${mid}/deleteReview/${reviewId}`,
      {
        headers: { Authorization: `Bearer ${authToken}` },
      }
    );

    handleReviews();
  };

  const handleEdit = (title: string) => {
    setEditMode(!editMode);
    setEditTitleValue(title);
  };

  const handleEditSubmit = async () => {
    await axios.put(
      `http://localhost:8080/map-locations/${mid}`,
      {
        title: `${editTitleValue}`,
      },
      {
        headers: { Authorization: `Bearer ${authToken}` },
      }
    );

    setEditMode(false);
    window.location.reload();
  };

  return (
    <div className="m-2 rounded bg-slate-50 p-5 flex items-center justify-between">
      <div>
        <div className="text-lg">
          {!editMode && title}
          {editMode && (
            <div className="inline">
              <input
                type="text"
                className="border rounded p-1 my-2"
                value={editTitleValue}
                onChange={(e) => setEditTitleValue(e.target.value)}
              />
              <button
                onClick={handleEditSubmit}
                className="ml-2 bg-slate-700 text-white text-sm p-1 h-9 rounded"
              >
                Submit
              </button>
            </div>
          )}
          {roles.includes("ADMIN") && (
            <small
              onClick={() => handleEdit(title as string)}
              className="ml-2 underline cursor-pointer"
            >
              Edit
            </small>
          )}
        </div>
        <div className="flex flex-wrap gap-2">
          {badges.map((badge, i) => (
            <div
              key={i}
              className="bg-emerald-400 p-1 text-sm text-white rounded whitespace-nowrap"
            >
              {badge}
            </div>
          ))}
          <div
            className="bg-slate-700 p-1 text-sm text-white rounded whitepsace-nowrap cursor-pointer"
            onClick={handleReviews}
          >
            Reviews
          </div>
        </div>
        <div id={mid} ref={reviewsDiv} style={{ display: "none" }}>
          {reviews.map((rev) => (
            <div className="my-2" key={rev.id}>
              <h1>Score: {rev.score}</h1>
              <h2>{rev.description}</h2>
              <small>
                Posted by: {rev.postedBy}
                {localStorage.getItem("username") === rev.postedBy && (
                  <span
                    onClick={() => handleDeleteReview(rev.id)}
                    className="ml-5 underline cursor-pointer"
                  >
                    Delete
                  </span>
                )}
              </small>
            </div>
          ))}
          <div className="mt-10">
            <h1>New review</h1>
            <input
              type="number"
              placeholder="score"
              min="0"
              max="10"
              step="0.1"
              className="border rounded p-1 w-20"
              value={scoreValue}
              onChange={(e) => setScoreValue(e.target.value)}
            />
            <br />
            <textarea
              placeholder="description"
              className="border rounded p-1 mt-2"
              style={{ width: "20vw" }}
              value={descriptionValue}
              onChange={(e) => setDescriptionValue(e.target.value)}
            />

            <div
              className="bg-emerald-400 p-1 text-sm text-white rounded whitespace-nowrap cursor-pointer"
              style={{ width: "4vw", fontSize: "0.68vw" }}
              onClick={handleAddReview}
            >
              Add review
            </div>
          </div>
        </div>
      </div>
      <div className=" text-gray-500 bold text-right w-1/12">
        {distance + "km"}
      </div>
    </div>
  );
}
