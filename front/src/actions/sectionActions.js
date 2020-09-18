import axios from "axios";
import { GET_SECTIONS, GET_SECTIONS_BY_COURSE } from "./types";


export const creatSection = (section, courseId, history) => async dispatch => {
    
    const res = await axios.post("/api/v1/sections/" + courseId, section);
    debugger

    //dispatch(getSections());

    history.push('/courseedit/' + courseId);
  };



export const getSections = () => async dispatch => {
  const res = await axios.get("/api/v1/sections");
  debugger
  dispatch({
    type: GET_SECTIONS,
    payload: res.data
  });
};



export const deleteSection = (sectionId, courseId, history) => async dispatch => {
    
  const res = await axios.delete("/api/v1/sections/" + sectionId);
  debugger

  dispatch(getSections());

  dispatch(getSectionsByCourse(courseId));

  history.push("/courseedit/" + courseId);
};


export const editSection = (section, courseId, history) => async dispatch => {
    
  const res = await axios.patch("/api/v1/sections", section);
  debugger

  history.push("/courseedit/" + courseId);

};


export const getSectionsByCourse = (courseId) => async dispatch => {
  const res = await axios.get("/api/v1/sections/" + courseId);
  debugger


  dispatch({
    type: GET_SECTIONS_BY_COURSE,
    payload: res.data
  });

};





/*
export const getCourses = () => async dispatch => {
    const res = await axios.get("/api/v1/courses");
    debugger
    dispatch({
      type: GET_COURSES,
      payload: res.data
    });
  };


  export const creatCourse = (course, authorId, history) => async dispatch => {
    
    const res = await axios.post("/api/v1/courses/" + authorId, course, history);
    debugger

    dispatch(getCourses());

    history.push('/coursesmanagedbyme');
  };

  export const getCoursesManagedByAuthor = (authorId) => async dispatch => {
    const res = await axios.get("/api/v1/courses/" + authorId);
    debugger


    dispatch({
      type: GET_COURSES_BY_AUTHOR,
      payload: res.data
    });

  };


  export const editCourse = (course, authorId, history) => async dispatch => {
    
    const res = await axios.patch("/api/v1/courses/" + authorId, course, history);
    debugger

  };

  export const deleteCourse = (courseId, history, authorId) => async dispatch => {
    
    const res = await axios.delete("/api/v1/courses/" + courseId);
    debugger

    dispatch(getCoursesManagedByAuthor(res.data.authorId));

    history.push('/coursesmanagedbyme');
  };
*/
  