import React, { Component } from "react";
import { connect } from 'react-redux';
import CourseForm from './CourseForm';
import { creatCourse, editCourse } from "../actions/coursesActions";
import { enrollCourse, leaveCourse } from "../actions/studentActions";
import { getSections, deleteSection, getSectionsByCourse } from "../actions/sectionActions";
import { getLessons, deleteLesson } from "../actions/lessonsActions"
import { Link } from "react-router-dom";

export class CourseDetail extends Component {


 
  componentDidMount() {
    this.props.getSectionsByCourse(this.props.match.params.id);
    // this.props.getLessons(this.props.course.id);
   }
    

  onSubmit = (course) => {
     const { user={}  } = this.props.security;
     debugger
    this.props.editCourse({...course, id:this.props.course.id} , user.authorId, this.props.history);
    //this.props.history.push('/');
  };

  render() {

    const { name : courseName = ""  } = this.props.course;

    const { sectionsByCourse=[] } = this.props.sections;

    const { validToken, user={}  } = this.props.security;

    const areStudentEnrolled = this.props.course.students.some(studentId => studentId == user.studentId);
    
   // sectionsByCourse.map(section => section.lessons = [{name: "first"}, {name: "second"}]);


    const sectionsList =  <div className="container">
        {sectionsByCourse.map(section => (
        <div className="p-3 mb-4  border border-success rounded">

          <div className="container">
            <div className="row align-items-center">
              <div className="col-lg-12">
                <div className="text-dark lead">{section.name}</div>
              </div>  
            </div>  
          </div>    

          <div class="border-top border-success my-3"></div>

            <div className="text-dark lead text-center my-2">lessons</div>

            <ul class="list-group">
            {
            section.lessonDtoList.map(lesson => (
                <li class="list-group-item">
                  <div className="container">
                    <div className="row">
                      <div className="col-lg-12">{lesson.name}</div>
                    </div>
                  </div>
                </li>
            ))}
            </ul>
        </div>    
        ))}
      </div>;

    return (
        <div className="container">
          
          <div className="row">
            <div className="col-lg-6">
             <div className="text-dark lead">{courseName}</div>
            </div>  
            <div className="col-lg-6">
              { !areStudentEnrolled ? 
             <div 
               className="btn btn-info btn-block"
               onClick={ () => this.props.enrollCourse(this.props.course.id, this.props.history) }
               >
                 enroll
              </div> :
              
              <div 
                className="btn btn-info btn-block"
                onClick={ () => this.props.leaveCourse(this.props.course.id, this.props.history) }
                >
                leave
                </div> 
              }
            </div>  
          </div>

          <div class="border-top border-success my-3"></div>

          <div className="text-dark lead text-center my-2">Sections</div>
          {sectionsList}   
        </div>
    );
  }
}

const mapStateToProps = (state, props) => {
    return {
        security: state.security,
        sections: state.sections,
        course: state.courses.courses.find((course) => course.id == props.match.params.id)
    }
}

const mapDispatchToProps = (dispatch) => ({
    editCourse: (course, authorId, history) => dispatch(editCourse(course, authorId, history)),
    getSections: () => dispatch(getSections()),
    deleteSection: (sectionId, courseId, history) => dispatch(deleteSection(sectionId, courseId, history)),
    getSectionsByCourse: (courseId) => dispatch(getSectionsByCourse(courseId)),
    getLessons: (sectionId) => dispatch(getLessons(sectionId)),
    deleteLesson: (lessonId, courseId, history) => dispatch(deleteLesson(lessonId, courseId, history)), 
    enrollCourse: (courseId) => dispatch(enrollCourse(courseId)),
    leaveCourse: (courseId) => dispatch(leaveCourse(courseId)),
});

export default connect(mapStateToProps, mapDispatchToProps)(CourseDetail);