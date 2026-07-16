import React, { useState } from 'react';
import './App.css';
import ListofPlayers from './ListofPlayers';
import IndianPlayers from './IndianPlayers';

function App() {
  const [flag, setFlag] = useState(true);

  return (
    <div className="App">
      <h1>Cricket App</h1>
      <button className="toggle-button" onClick={() => setFlag(prev => !prev)}>
        Show {flag ? 'Indian Players' : 'List of Players'}
      </button>
      {flag ? <ListofPlayers /> : <IndianPlayers />}
    </div>
  );
}

export default App;
