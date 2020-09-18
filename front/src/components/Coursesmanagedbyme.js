import React, { Component } from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import { getCoursesManagedByAuthor, deleteCourse } from "../actions/coursesActions";


class Coursesmanagedbyme extends Component {
  
  constructor() {
    super();
  }
  
  componentDidMount() {
    const { user={}  } = this.props.security;
    this.props.getCoursesManagedByAuthor(user.authorId);
}  




  render() {

    const { coursesByAuthor = [] } = this.props.courses;
    const { user={}  } = this.props.security;

    
    const courses = <div className="container">{coursesByAuthor.map(course => (
        <div className="p-3 mb-2  border border-success rounded">
          <div className="row align-items-center">
            <div className="col-lg-8">
              <div className="text-dark lead">{course.name}</div>  
            </div>
            <div className="col-lg-2">
              <Link className="btn btn-info btn-block" to={`/courseedit/${course.id}`}>
                edit course
              </Link>  
            </div>
            <div className="col-lg-2">
              <div 
                className="btn btn-info btn-block" 
                onClick={ () => this.props.deleteCourse(course.id, this.props.history, user.authorId) } > 
                delete course 
              </div>
            </div>
          </div>
        </div>
    ))}</div>;




    return (
    <div className="container">
        <div className="row">
          <div className="col-md-12">
              <div className="lead text-left mt-4 mb-2">
                <div class="btn btn-primary">Course managed by me</div>
              </div>
              <Link className="nav-link" to="/createcourse">
              Create course
            </Link>
            {courses}
          </div>
        </div>
    </div>
    );
  }
}


const mapStateToProps = state => {
  return {
      security: state.security,
      courses: state.courses
  }
}

const mapDispatchToProps = (dispatch) => ({
  getCoursesManagedByAuthor: (authorId) => dispatch(getCoursesManagedByAuthor(authorId)),
  deleteCourse: (courseId, history, authorId) => dispatch(deleteCourse(courseId, history, authorId))
});

export default connect(
  mapStateToProps, mapDispatchToProps
)(Coursesmanagedbyme);