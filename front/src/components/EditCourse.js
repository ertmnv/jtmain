import React, { Component } from "react";
import { connect } from "react-redux";
import CourseForm from "./CourseForm";
import { creatCourse, editCourse } from "../actions/coursesActions";
import {
  getSections,
  deleteSection,
  getSectionsByCourse,
} from "../actions/sectionActions";
import { getLessons, deleteLesson } from "../actions/lessonsActions";
import { Link } from "react-router-dom";

export class EditCourse extends Component {
  componentDidMount() {
    this.props.getSectionsByCourse(this.props.course.id);
    // this.props.getLessons(this.props.course.id);
  }

  onSubmit = (course) => {
    const { user = {} } = this.props.security;

    this.props.editCourse(
      { ...course, id: this.props.course.id },
      user.authorId,
      this.props.history
    );
    //this.props.history.push('/');
  };

  render() {
    const { sectionsByCourse = [] } = this.props.sections;

    // sectionsByCourse.map(section => section.lessons = [{name: "first"}, {name: "second"}]);

    const sectionsList = (
      <div className="container">
        {sectionsByCourse.map((section) => (
          <div className="p-3 mb-4  border border-success rounded">
            <div className="container">
              <div className="row align-items-center">
                <div className="col-lg-8">
                  <div className="text-dark lead">{section.name}</div>
                </div>
                <div className="col-lg-2">
                  <Link
                    className="btn btn-info btn-block"
                    to={`/sectionedit/${section.id}/${this.props.course.id}`}
                  >
                    edit section
                  </Link>
                </div>
                <div className="col-lg-2">
                  <div
                    className="btn btn-info btn-block"
                    onClick={() =>
                      this.props.deleteSection(
                        section.id,
                        this.props.course.id,
                        this.props.history
                      )
                    }
                  >
                    delete section
                  </div>
                </div>
              </div>
            </div>

            <div class="border-top border-success my-3"></div>

            <div className="text-dark lead text-center my-2">lessons</div>
            <Link
              className="nav-link"
              to={`/createlesson/${section.id}/${this.props.course.id}`}
            >
              Create lesson
            </Link>

            <ul class="list-group">
              {section.lessons.map((lesson) => (
                <li class="list-group-item">
                  <div className="container">
                    <div className="row">
                      <div className="col-lg-8">{lesson.name}</div>
                      <div className="col-lg-2">
                        <Link
                          className="btn btn-info"
                          to={`/lessonedit/${lesson.id}/${section.id}/${this.props.course.id}`}
                        >
                          edit lesson
                        </Link>
                      </div>
                      <div className="col-lg-2">
                        <div
                          className="btn btn-info"
                          onClick={() =>
                            this.props.deleteLesson(
                              lesson.id,
                              this.props.course.id,
                              this.props.history
                            )
                          }
                        >
                          delete lesson
                        </div>
                      </div>
                    </div>
                  </div>
                </li>
              ))}
            </ul>
          </div>
        ))}
      </div>
    );

    return (
      <div className="container">
        <CourseForm onSubmit={this.onSubmit} course={this.props.course} />
        <div className="lead text-center mt-4">Sections</div>
        <Link
          className="nav-link"
          to={`/createsection/${this.props.course.id}`}
        >
          Create section
        </Link>
        {sectionsList}
      </div>
    );
  }
}

const mapStateToProps = (state, props) => {
  return {
    security: state.security,
    sections: state.sections,
    course: state.courses.coursesByAuthor.find(
      (course) => course.id == props.match.params.id
    ),
  };
};

const mapDispatchToProps = (dispatch) => ({
  editCourse: (course, authorId, history) =>
    dispatch(editCourse(course, authorId, history)),
  getSections: () => dispatch(getSections()),
  deleteSection: (sectionId, courseId, history) =>
    dispatch(deleteSection(sectionId, courseId, history)),
  getSectionsByCourse: (courseId) => dispatch(getSectionsByCourse(courseId)),
  getLessons: (sectionId) => dispatch(getLessons(sectionId)),
  deleteLesson: (lessonId, courseId, history) =>
    dispatch(deleteLesson(lessonId, courseId, history)),
});

export default connect(mapStateToProps, mapDispatchToProps)(EditCourse);
