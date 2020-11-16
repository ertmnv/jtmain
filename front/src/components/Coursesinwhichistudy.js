import React, { Component } from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import { getCoursesInWhichIStudy } from "../actions/coursesActions";


class Coursesinwhichistudy extends Component {
  
  constructor() {
    super();
  }
  
  componentDidMount() {
    const { user={}  } = this.props.security;
    this.props.getCoursesInWhichIStudy(user.studentId);
}  




render() {
    const { coursesByStudent = [] } = this.props.courses;

    
    const content = (
    <div className="container">{coursesByStudent.map(course => (
      <div className="p-3 mb-2  border border-success rounded">
        <div className="row align-items-center">
          <div className="col-lg-10">
            <div className="text-dark lead">
              {course.name}
            </div>
          </div>
          <div className="col-lg-2">
            <Link className="btn btn-info" to={`/coursedetail/${course.id}`}>
              course detail
            </Link> 
          </div>
        </div>        
      </div>
    ))}
    </div>);

    return (
      <div>
        {content}
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
  getCoursesInWhichIStudy: (studentId) => dispatch(getCoursesInWhichIStudy(studentId))
});

export default connect(
  mapStateToProps, mapDispatchToProps
)(Coursesinwhichistudy);