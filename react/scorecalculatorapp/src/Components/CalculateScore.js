import React from 'react';
import '../Stylesheets/mystyle.css';

function CalculateScore({ name, school, total, goal }) {
  const average = total ? (total / goal).toFixed(2) : 'N/A';

  return (
    <div className="score-card">
      <h2>Student Score</h2>
      <p><strong>Name:</strong> {name}</p>
      <p><strong>School:</strong> {school}</p>
      <p><strong>Total:</strong> {total}</p>
      <p><strong>Goal:</strong> {goal}</p>
      <p className="average"><strong>Average:</strong> {average}</p>
    </div>
  );
}

export default CalculateScore;
