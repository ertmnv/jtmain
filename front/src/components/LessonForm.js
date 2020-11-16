import React, { Component } from "react";



export default class LessonForm extends Component {

  constructor(props) {
    super(props);

    this.state = {
      name: props.lesson ? props.lesson.name : '',
      
    };
  }

  onNameChange = (e) => {
    const name = e.target.value;
    this.setState(() => ({ name }));
  };
  onSubmit = (e) => {
    e.preventDefault();

      this.props.onSubmit({
        name: this.state.name
      });
  };

  render() {
    return (
      <form className="form" onSubmit={this.onSubmit}>
        <div className="form-group">
         <input
          type="text"
          placeholder="name"
          autoFocus
          className="form-control form-control-lg"
          value={this.state.name}
          onChange={this.onNameChange}
        />
        </div>
          <input type="submit" className="btn btn-info btn-block mt-4" value="change lesson name" />
      </form>
    )
  }
}