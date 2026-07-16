import React from 'react';

const courses = [
  { id: 1, title: 'React for Beginners', duration: '4 weeks' },
  { id: 2, title: 'Advanced React', duration: '6 weeks' },
  { id: 3, title: 'React with Redux', duration: '5 weeks' },
];

function CourseDetails() {
  return (
    <div className="details-card">
      <h2>Course Details</h2>
      <ul>
        {courses.map(course => (
          <li key={course.id}>
            <strong>{course.title}</strong> - {course.duration}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default CourseDetails;
