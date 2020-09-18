import React, { Component } from "react";
import { connect } from 'react-redux';
import CourseForm from './CourseForm';
import { creatCourse } from "../actions/coursesActions";

export class CreateCourse extends Component {


 
    

  onSubmit = (course) => {
     const { user={}  } = this.props.security;
     debugger
    this.props.creatCourse(course, user.authorId, this.props.history);
    //this.props.history.push('/');
  };

  render() {
    return (

        <div className="container">
            
          <CourseForm
            onSubmit={this.onSubmit}
          />
          
        </div>

    );
  }
}

const mapStateToProps = state => {
    return {
        security: state.security
    }
}

const mapDispatchToProps = (dispatch) => ({
    creatCourse: (course, authorId, history) => dispatch(creatCourse(course, authorId, history))
});

export default connect(mapStateToProps, mapDispatchToProps)(CreateCourse);