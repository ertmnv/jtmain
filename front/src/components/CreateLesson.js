import React, { Component } from "react";
import { connect } from 'react-redux';
import LessonForm from './LessonForm';
import { creatLesson } from "../actions/lessonsActions";

export class CreateLesson extends Component {

  onSubmit = (lesson) => {
    this.props.creatLesson(lesson, this.props.section.id, this.props.match.params.courseId, this.props.history);
  };

  render() {
    return (

        <div className="container">
            
          <LessonForm
            onSubmit={this.onSubmit}
          />
          
        </div>

    );
  }
}

const mapStateToProps = (state, props) => {
    return {
        security: state.security,
        section: state.sections.sectionsByCourse.find((section) => section.id == props.match.params.sectionId)
    }
}

const mapDispatchToProps = (dispatch) => ({
    creatLesson: (section, sectionId, courseId, history) => dispatch(creatLesson(section, sectionId, courseId, history))
});

export default connect(mapStateToProps, mapDispatchToProps)(CreateLesson);