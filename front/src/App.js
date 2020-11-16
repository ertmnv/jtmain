import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import AddGame from "./components/AddGame";
import GameChat from "./components/GameChat";
import Profile from "./components/Profile";
import CreateCourse from "./components/CreateCourse";
import EditCourse from "./components/EditCourse";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { Provider } from "react-redux";
import store from "./store";
import Landing from "./components/Layout/Landing";
import Register from "./components/UserManagement/Register";
import Login from "./components/UserManagement/Login";
import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/securityActions";
import SecuredRoute from "./securityUtils/SecureRoute";
import Becomeanauthor from "./components/Becomeanauthor";
import { connect } from "react-redux";
import Coursesmanagedbyme from "./components/Coursesmanagedbyme";
import CreateSection from  "./components/CreateSection";
import EditSection from "./components/EditSection";
import CreateLesson from "./components/CreateLesson";
import EditLesson from "./components/EditLesson";
import CourseDetail from "./components/CourseDetail";
import Coursesinwhichistudy from "./components/Coursesinwhichistudy";


const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken
  });

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }

/* start of socket stuff */   
/* subscribe to channels */ 



/* end of socket stuff */

}

class App extends Component {
  render() {
    
    
    const { user = {} } = this.props.security;


    return (
    
        <Router>
          <div className="App">
            <Header />
            {
              //Public Routes
            }

            <Route exact path="/" component={Landing} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/login" component={Login} />

            {
              //Private Routes
            }
            <Switch>
              <SecuredRoute exact path="/dashboard" component={Dashboard} />
              <SecuredRoute exact path="/addgame" component={AddGame} />
              <SecuredRoute exact path="/gamechat" component={GameChat} />
              <SecuredRoute exact path="/profile" component={Profile} />
              <SecuredRoute exact path="/becomeanauthor" component={Becomeanauthor} /> 
              <SecuredRoute exact path="/coursesmanagedbyme" component={Coursesmanagedbyme} />
              <SecuredRoute exact path="/createcourse" component={CreateCourse} />
              <SecuredRoute path="/courseedit/:id" component={EditCourse} />
              <SecuredRoute path="/createsection/:id" component={CreateSection} />
              <SecuredRoute path="/sectionedit/:sectionId/:courseId" component={EditSection} />
              <SecuredRoute path="/createlesson/:sectionId/:courseId" component={CreateLesson} />
              <SecuredRoute path="/lessonedit/:lessonId/:sectionId/:courseId" component={EditLesson} />
              <SecuredRoute path="/coursedetail/:id" component={CourseDetail} />
              <SecuredRoute path="/coursestakenbyme" component={Coursesinwhichistudy} />
              
              
            </Switch>
          </div>
        </Router>

    );
  }
}

const mapStateToProps = state => ({
  security: state.security
});

export default connect(
  mapStateToProps
)(App);




