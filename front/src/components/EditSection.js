import React, { Component } from "react";
import { connect } from 'react-redux';
import SectionForm from './SectionForm';
import { editSection } from "../actions/sectionActions";

export class EditSection extends Component {

  onSubmit = (section) => {
    this.props.editSection({...section, id:this.props.section.id}, this.props.course.id, this.props.history);
  };

  render() {
    return (

        <div className="container">
            
          <SectionForm
            onSubmit={this.onSubmit}
            section={this.props.section}
          />
          
        </div>

    );
  }
}

const mapStateToProps = (state, props) => {
    return {
        security: state.security,
        course: state.courses.courses.find((course) => course.id == props.match.params.courseId),
        section: state.sections.sectionsByCourse.find((section) => section.id == props.match.params.sectionId)
    }
}

const mapDispatchToProps = (dispatch) => ({
    editSection: (section, courseId, history) => dispatch(editSection(section, courseId, history))
});

export default connect(mapStateToProps, mapDispatchToProps)(EditSection);