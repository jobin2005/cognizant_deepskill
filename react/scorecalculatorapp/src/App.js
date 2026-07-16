import React from 'react';
import './App.css';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div className="App">
      <CalculateScore name="John Doe" school="Central High" total={420} goal={500} />
    </div>
  );
}

export default App;
