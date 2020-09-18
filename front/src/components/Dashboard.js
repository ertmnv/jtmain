import React, { Component } from "react";
import { connect } from "react-redux";
import { getCourses, getCoursesByPage } from "../actions/coursesActions";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import ReactPaginate from 'react-paginate';

class Dashboard extends Component {
  
  constructor() {
    super();
    this.state = {
      perPage: 2
    };

    this.handlePageClick = this.handlePageClick.bind(this);

  }

  componentDidMount() {
   //this.props.getCourses();
   this.props.getCoursesByPage(0,2);
  }

  handlePageClick = (e) => {
    const selectedPage = e.selected;

        this.props.getCoursesByPage(selectedPage, this.state.perPage)

  };

  render() {
    const { courses = [], numberOfCourses } = this.props.courses;

    
    const content = (
    <div className="container">{courses.map(course => (
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

      <div className="row mb-4">
      <ReactPaginate
          previousLabel={"prev"}
          nextLabel={"next"}
          breakLabel={"..."}
          breakClassName={"break-me"}
          pageCount={ Math.ceil(numberOfCourses/this.state.perPage) }
          onPageChange={this.handlePageClick}
          containerClassName={"pagination"}
          subContainerClassName={"pages pagination"}
          activeClassName={"active"}/>
      </div>

        {content}

    </div>
    );
  }
}

Dashboard.propTypes = {
  courses: PropTypes.object.isRequired,
  getCourses: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  courses: state.courses
});

export default connect(
  mapStateToProps,
  { getCourses, getCoursesByPage }
)(Dashboard);