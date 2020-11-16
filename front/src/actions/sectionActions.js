import axios from "axios";
import { GET_SECTIONS, GET_SECTIONS_BY_COURSE } from "./types";

export const creatSection = (section, courseId, history) => async (
  dispatch
) => {
  const res = await axios.post("/api/v1/sections/" + courseId, section);

  //dispatch(getSections());

  history.push("/courseedit/" + courseId);
};

export const getSections = () => async (dispatch) => {
  const res = await axios.get("/api/v1/sections");

  dispatch({
    type: GET_SECTIONS,
    payload: res.data,
  });
};

export const deleteSection = (sectionId, courseId, history) => async (
  dispatch
) => {
  const res = await axios.delete("/api/v1/sections/" + sectionId);

  dispatch(getSections());

  dispatch(getSectionsByCourse(courseId));

  history.push("/courseedit/" + courseId);
};

export const editSection = (section, courseId, history) => async (dispatch) => {
  const res = await axios.patch("/api/v1/sections", section);

  history.push("/courseedit/" + courseId);
};

export const getSectionsByCourse = (courseId) => async (dispatch) => {
  const res = await axios.get("/api/v1/sections/" + courseId);

  dispatch({
    type: GET_SECTIONS_BY_COURSE,
    payload: res.data,
  });
};
