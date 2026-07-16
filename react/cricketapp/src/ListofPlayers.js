import React from 'react';

const players = [
  { name: 'Virat Kohli', score: 75 },
  { name: 'Rohit Sharma', score: 65 },
  { name: 'Jasprit Bumrah', score: 82 },
  { name: 'KL Rahul', score: 90 },
  { name: 'Hardik Pandya', score: 55 },
  { name: 'Shreyas Iyer', score: 72 },
  { name: 'Rishabh Pant', score: 68 },
  { name: 'Ravindra Jadeja', score: 80 },
  { name: 'Shubman Gill', score: 59 },
  { name: 'Washington Sundar', score: 70 },
  { name: 'Yuzvendra Chahal', score: 62 },
];

function ListofPlayers() {
  const filtered = players.filter(player => player.score >= 70);

  return (
    <div className="section">
      <h2>List of Players</h2>
      <p>Players with score 70 or above:</p>
      <ul>
        {filtered.map(player => (
          <li key={player.name}>{player.name}: {player.score}</li>
        ))}
      </ul>
    </div>
  );
}

export default ListofPlayers;
