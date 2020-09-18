import { GET_COURSES, GET_COURSES_BY_AUTHOR, GET_COURSES_BY_STUDENT, GET_NUMBER_OF_COURSES } from "../actions/types";

const initialState = {
  courses: [],
  coursesByAuthor: [],
  coursesByStudent: [],
  numberOfCourses: 0
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_COURSES:
      return {
        ...state,
        courses: action.payload
      };
    case GET_COURSES_BY_AUTHOR: 
      return {
        ...state,
        coursesByAuthor: action.payload
    };  
    case GET_COURSES_BY_STUDENT: 
    return {
      ...state,
      coursesByStudent: action.payload
  };  
    case GET_NUMBER_OF_COURSES: 
      return {
        ...state,
        numberOfCourses: action.payload
      }
    default:
      return state;
  }
}