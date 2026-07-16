import React from 'react';
import './App.css';
import Posts from './Posts';

class App extends React.Component {
  render() {
    return (
      <div className="App">
        <h1>Blog App</h1>
        <Posts />
      </div>
    );
  }
}

export default App;
