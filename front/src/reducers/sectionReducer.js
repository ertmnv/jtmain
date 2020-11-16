import { GET_SECTIONS, GET_SECTIONS_BY_COURSE } from "../actions/types";

const initialState = {
    sections: [],
    sectionsByCourse: []
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_SECTIONS:
      return {
        ...state,
        sections: action.payload
      }; 
    case GET_SECTIONS_BY_COURSE:
      return {
        ...state,
        sectionsByCourse: action.payload
      }; 
    default:
      return state;
  }
}