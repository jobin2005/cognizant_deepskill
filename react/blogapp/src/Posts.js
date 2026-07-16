import React from 'react';
import Post from './Post';

class Posts extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      error: null,
    };
  }

  async loadPosts() {
    try {
      const response = await fetch('https://jsonplaceholder.typicode.com/posts');
      if (!response.ok) {
        throw new Error(`HTTP error ${response.status}`);
      }
      const posts = await response.json();
      this.setState({ posts });
    } catch (error) {
      this.setState({ error });
    }
  }

  componentDidMount() {
    this.loadPosts();
  }

  componentDidCatch(error, info) {
    alert(`An error occurred while rendering posts: ${error.message}`);
    console.error('Posts error boundary:', error, info);
  }

  render() {
    const { posts, error } = this.state;

    if (error) {
      return <div className="error-alert">Error loading posts: {error.message}</div>;
    }

    return (
      <div className="posts-list">
        {posts.slice(0, 10).map(post => (
          <Post key={post.id} title={post.title} body={post.body} />
        ))}
      </div>
    );
  }
}

export default Posts;
