import React from 'react';

const blogs = [
  { id: 1, title: 'React Conditional Rendering', author: 'Jane Doe' },
  { id: 2, title: 'Using Keys in Lists', author: 'John Smith' },
  { id: 3, title: 'Extracting Components', author: 'Sara Lee' },
];

function BlogDetails() {
  return (
    <div className="details-card">
      <h2>Blog Details</h2>
      <ul>
        {blogs.map(blog => (
          <li key={blog.id}>
            <strong>{blog.title}</strong> by {blog.author}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default BlogDetails;
