import axios from "axios";
import { REGISTER_AUTHOR, SET_CURRENT_USER } from "./types";
import setJWTToken from "../securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";

export const registerAuthor = (username) => async (dispatch) => {
  const url = "/api/v1/author/registration/" + username;
  const res = await axios.get(url);

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
};
