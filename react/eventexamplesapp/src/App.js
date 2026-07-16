import React from 'react';
import './App.css';
import CurrencyConvertor from './CurrencyConvertor';
import PressButton from './PressButton';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      counter: 0,
      message: '',
      pressMessage: '',
    };

    this.increment = this.increment.bind(this);
    this.decrement = this.decrement.bind(this);
    this.sayHello = this.sayHello.bind(this);
    this.handlePress = this.handlePress.bind(this);
  }

  increment() {
    this.setState(prevState => ({ counter: prevState.counter + 1 }));
  }

  sayHello() {
    this.setState({ message: 'Hello! Counter updated successfully.' });
  }

  handleIncrease() {
    this.increment();
    this.sayHello();
  }

  decrement() {
    this.setState(prevState => ({ counter: prevState.counter - 1 }));
  }

  sayWelcome(text) {
    this.setState({ message: `${text} to the Event Examples App` });
  }

  handlePress(event) {
    event.preventDefault();
    this.setState({ pressMessage: 'I was clicked' });
  }

  render() {
    const { counter, message, pressMessage } = this.state;

    return (
      <div className="App">
        <h1>Event Examples App</h1>

        <div className="card">
          <h2>Counter Demo</h2>
          <p>Counter value: {counter}</p>
          <div className="button-group">
            <button className="primary" onClick={() => this.handleIncrease()}>
              Increase
            </button>
            <button className="secondary" onClick={this.decrement}>
              Decrement
            </button>
          </div>
          <p>{message}</p>
        </div>

        <div className="card">
          <h2>Say Welcome</h2>
          <button className="primary" onClick={() => this.sayWelcome('Welcome')}>
            Say Welcome
          </button>
        </div>

        <div className="card">
          <h2>Press Button</h2>
          <PressButton onPress={this.handlePress}>Press Me</PressButton>
          <p>{pressMessage}</p>
        </div>

        <CurrencyConvertor />
      </div>
    );
  }
}

export default App;
