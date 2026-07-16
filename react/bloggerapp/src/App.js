import React, { useState } from 'react';
import './App.css';
import BookDetails from './BookDetails';
import BlogDetails from './BlogDetails';
import CourseDetails from './CourseDetails';

const components = {
  book: <BookDetails />,
  blog: <BlogDetails />,
  course: <CourseDetails />,
};

function App() {
  const [view, setView] = useState('book');

  const componentToRender = view === 'book' ? components.book : view === 'blog' ? components.blog : components.course;

  return (
    <div className="App">
      <h1>Blogger App</h1>
      <div className="button-group">
        <button className={view === 'book' ? 'active' : ''} onClick={() => setView('book')}>
          Book Details
        </button>
        <button className={view === 'blog' ? 'active' : ''} onClick={() => setView('blog')}>
          Blog Details
        </button>
        <button className={view === 'course' ? 'active' : ''} onClick={() => setView('course')}>
          Course Details
        </button>
      </div>
      {view === 'book' && <h2>Rendering using inline conditional</h2>}
      {view === 'blog' ? <h2>Rendering using ternary expression</h2> : null}
      {componentToRender}
    </div>
  );
}

export default App;
