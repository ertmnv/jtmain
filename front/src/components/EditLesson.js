import React, { Component } from "react";
import { connect } from "react-redux";
import LessonForm from "./LessonForm";
import { editLesson } from "../actions/lessonsActions";

export class EditLesson extends Component {
  onSubmit = (lesson) => {
    this.props.editLesson(
      { ...lesson, id: this.props.lesson.id },
      this.props.course.id,
      this.props.history
    );
  };

  render() {
    return (
      <div className="container">
        <LessonForm onSubmit={this.onSubmit} lesson={this.props.lesson} />
      </div>
    );
  }
}

const mapStateToProps = (state, props) => {
  return {
    security: state.security,
    course: state.courses.courses.find(
      (course) => course.id == props.match.params.courseId
    ),
    lesson: state.sections.sectionsByCourse
      .find((section) => section.id == props.match.params.sectionId)
      .lessons.find((lesson) => lesson.id == props.match.params.lessonId),
  };
};

const mapDispatchToProps = (dispatch) => ({
  editLesson: (section, courseId, history) =>
    dispatch(editLesson(section, courseId, history)),
});

export default connect(mapStateToProps, mapDispatchToProps)(EditLesson);
