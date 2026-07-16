import React from 'react';

class Post extends React.Component {
  render() {
    const { title, body } = this.props;
    return (
      <div className="post">
        <h2 className="post-title">{title}</h2>
        <p className="post-body">{body}</p>
      </div>
    );
  }
}

export default Post;
