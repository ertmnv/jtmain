import axios from "axios";
import { GET_LESSONS } from "./types";
import { getSectionsByCourse } from "./sectionActions";

export const creatLesson = (lesson, sectionId, courseId, history) => async dispatch => {
    
    const res = await axios.post("/api/v1/lessons/" + sectionId, lesson);
    debugger

    history.push('/courseedit/' + courseId);
  };

  export const getLessons = (sectionId) => async dispatch => {
    const res = await axios.get("/api/v1/lessons/" + sectionId);
    debugger
    dispatch({
      type: GET_LESSONS,
      payload: res.data
    });
  };



export const deleteLesson = (lessonId, courseId, history) => async dispatch => {
    
    const res = await axios.delete("/api/v1/lessons/" + lessonId);
    debugger
  
    dispatch(getSectionsByCourse(courseId));
  
    history.push("/courseedit/" + courseId);
  };

  export const editLesson = (lesson, courseId, history) => async dispatch => {
    
    const res = await axios.patch("/api/v1/lessons", lesson);
    debugger
    
    dispatch(getSectionsByCourse(courseId));

    history.push("/courseedit/" + courseId);
  
  };

  

  
  //lessonId, courseId, history

/*
import axios from "axios";
import { GET_SECTIONS, GET_SECTIONS_BY_COURSE } from "./types";


export const creatSection = (section, courseId, history) => async dispatch => {
    
    const res = await axios.post("/api/v1/sections/" + courseId, section);
    debugger

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
*/