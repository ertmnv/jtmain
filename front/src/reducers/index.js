import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import securityReducer from "./securityReducer";
import gameReducer from "./gameReducer";
import chatReducer from "./chatReducer";
import courseReducer from "./courseReducer";
import sectionReducer from "./sectionReducer";

export default combineReducers({
  errors: errorReducer,
  security: securityReducer,
  games: gameReducer,
  messages: chatReducer,
  courses: courseReducer,
  sections: sectionReducer
});
