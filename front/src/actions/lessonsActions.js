import axios from "axios";
import { GET_LESSONS } from "./types";
import { getSectionsByCourse } from "./sectionActions";

export const creatLesson = (lesson, sectionId, courseId, history) => async (
  dispatch
) => {
  const res = await axios.post("/api/v1/lessons/" + sectionId, lesson);

  history.push("/courseedit/" + courseId);
};

export const getLessons = (sectionId) => async (dispatch) => {
  const res = await axios.get("/api/v1/lessons/" + sectionId);

  dispatch({
    type: GET_LESSONS,
    payload: res.data,
  });
};

export const deleteLesson = (lessonId, courseId, history) => async (
  dispatch
) => {
  const res = await axios.delete("/api/v1/lessons/" + lessonId);

  dispatch(getSectionsByCourse(courseId));

  history.push("/courseedit/" + courseId);
};

export const editLesson = (lesson, courseId, history) => async (dispatch) => {
  const res = await axios.patch("/api/v1/lessons", lesson);

  dispatch(getSectionsByCourse(courseId));

  history.push("/courseedit/" + courseId);
};
