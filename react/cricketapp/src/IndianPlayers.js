import React from 'react';

const allPlayers = ['Rahul', 'Virat', 'Rohit', 'Jadeja', 'Pant', 'Bumrah'];
const t20players = ['Iyer', 'Gill', 'Shami'];
const ranjiPlayers = ['Pujara', 'Rahane', 'Jaiswal'];
const mergedPlayers = [...t20players, ...ranjiPlayers];

function IndianPlayers() {
  const [oddTeam, evenTeam] = [allPlayers.filter((_, index) => index % 2 === 0), allPlayers.filter((_, index) => index % 2 !== 0)];

  return (
    <div className="section">
      <h2>Indian Players</h2>
      <div>
        <p><strong>Odd Team Players:</strong> {oddTeam.join(', ')}</p>
        <p><strong>Even Team Players:</strong> {evenTeam.join(', ')}</p>
      </div>
      <div>
        <h3>Merged Players</h3>
        <p>{mergedPlayers.join(', ')}</p>
      </div>
    </div>
  );
}

export default IndianPlayers;
