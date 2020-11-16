import React, { Component } from "react";
import { connect } from "react-redux";
import { registerAuthor } from "../actions/authorActions";
 
class Becomeanauthor extends Component {
  
  constructor() {
    super();

    this.handleClick = this.handleClick.bind(this);
  }
  
  componentDidMount() {
  }

  handleClick() {
    this.props.registerAuthor(this.props.security.user.sub);
  }

  render() {

    const { user={}  } = this.props.security;

    const content = (
        user.authorId ?
        <div className="container">
        <div className="lead">You are author!</div> 
        </div> :    
        <div className="container">
            <div className="row">
              <div className="col-md-12">
                  <div className="lead text-left mt-4 mb-2">
                    <div className="mb-2">Hit button in order to become an author</div>  
                    <button onClick={this.handleClick} type="button" class="btn btn-primary">Become an author</button>
                  </div>
              </div>
            </div>
        </div>
        )

    return (
        <div>
         {content}
        </div>
    );
  }
}

const mapStateToProps = state => {
    return {
        security: state.security
    }
}

export default connect(
    mapStateToProps,
    { registerAuthor }
)(Becomeanauthor);