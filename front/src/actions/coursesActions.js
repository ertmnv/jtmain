import axios from "axios";
import {
  GET_COURSES,
  GET_COURSES_BY_AUTHOR,
  GET_COURSES_BY_STUDENT,
  GET_NUMBER_OF_COURSES,
  BASE_PATH,
} from "./types";

export const getCourses = () => async (dispatch) => {
  const res = await axios.get(BASE_PATH + "courses");

  dispatch({
    type: GET_COURSES,
    payload: res.data.content,
  });
};

export const creatCourse = (course, authorId, history) => async (dispatch) => {
  try {
    const res = await axios.post(
      BASE_PATH + "courses/" + authorId,
      course,
      history
    );
  } catch (e) {
    debugger;
  }

  dispatch(getCourses());

  history.push("/coursesmanagedbyme");
};

export const getCoursesManagedByAuthor = (authorId) => async (dispatch) => {
  const res = await axios.get(BASE_PATH + "courses/" + authorId);

  dispatch({
    type: GET_COURSES_BY_AUTHOR,
    payload: res.data,
  });

  /*
    dispatch({
      type: GET_COURSES,
      payload: res.data
    });
    */
};

export const editCourse = (course, authorId, history) => async (dispatch) => {
  const res = await axios.patch(
    BASE_PATH + "courses/" + authorId,
    course,
    history
  );

  // history.push('/coursesmanagedbyme');
};

export const deleteCourse = (courseId, history, authorId) => async (
  dispatch
) => {
  const res = await axios.delete(BASE_PATH + "courses/" + courseId);

  dispatch(getCoursesManagedByAuthor(authorId));

  history.push("/coursesmanagedbyme");
};

export const getCoursesInWhichIStudy = (studentId) => async (dispatch) => {
  const res = await axios.get(BASE_PATH + "coursesbystudent/" + studentId);

  dispatch({
    type: GET_COURSES_BY_STUDENT,
    payload: res.data,
  });
};

export const getCoursesByPage = (page, size) => async (dispatch) => {
  const res = await axios.get(
    BASE_PATH + "courses?page=" + page + "&size=" + size
  );

  dispatch({
    type: GET_COURSES,
    payload: res.data.content,
  });

  dispatch({
    type: GET_NUMBER_OF_COURSES,
    // payload: res.data.numberOfCourses
    payload: res.data.totalElements,
  });
};
