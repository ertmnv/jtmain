import React, { Component } from "react";
import { connect } from 'react-redux';
import SectionForm from './SectionForm';
import { creatSection } from "../actions/sectionActions";

export class CreateSection extends Component {

  onSubmit = (section) => {
    this.props.creatSection(section, this.props.course.id, this.props.history);
  };

  render() {
    return (

        <div className="container">
            
          <SectionForm
            onSubmit={this.onSubmit}
          />
          
        </div>

    );
  }
}

const mapStateToProps = (state, props) => {
    return {
        security: state.security,
        course: state.courses.coursesByAuthor.find((course) => course.id == props.match.params.id)
    }
}

const mapDispatchToProps = (dispatch) => ({
    creatSection: (section, courseId, history) => dispatch(creatSection(section, courseId, history))
});

export default connect(mapStateToProps, mapDispatchToProps)(CreateSection);