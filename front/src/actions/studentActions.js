import axios from "axios";
import { ENROLL_COURSE, SET_CURRENT_USER, REGISTER_AUTHOR } from "./types";
import setJWTToken from "../securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";
import { getCourses } from "./coursesActions";

export const enrollCourse = (courseId) => async (dispatch) => {
  const res = await axios.get("/api/v1/students/" + courseId);

  // extract token from res.data
  const { token } = res.data;
  // store the token in the localStorage
  localStorage.setItem("jwtToken", token);
  // set our token in header ***
  setJWTToken(token);
  // decode token on React
  const decoded = jwt_decode(token);
  // dispatch to our securityReducer
  dispatch({
    type: SET_CURRENT_USER,
    payload: decoded,
  });

  dispatch({
    type: REGISTER_AUTHOR,
    payload: res.data,
  });

  dispatch(getCourses());
};

export const leaveCourse = (courseId) => async (dispatch) => {
  const res = await axios.get("/api/v1/studentsleavecourse/" + courseId);

  dispatch(getCourses());
};
