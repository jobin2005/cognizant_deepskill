import React from 'react';
import './App.css';

const flightDetails = [
  {
    id: 1,
    airline: 'Air India',
    from: 'Mumbai',
    to: 'Delhi',
    departure: '10:00 AM',
    arrival: '12:15 PM',
    fare: 5400,
  },
  {
    id: 2,
    airline: 'IndiGo',
    from: 'Bangalore',
    to: 'Hyderabad',
    departure: '2:30 PM',
    arrival: '4:00 PM',
    fare: 6200,
  },
  {
    id: 3,
    airline: 'Vistara',
    from: 'Chennai',
    to: 'Kolkata',
    departure: '9:00 AM',
    arrival: '11:45 AM',
    fare: 7100,
  },
];

function GuestPage() {
  return (
    <div className="page guest-page">
      <h2>Guest View</h2>
      <p>Please log in to book your tickets.</p>
      <div className="flights">
        {flightDetails.map(flight => (
          <div key={flight.id} className="flight-card">
            <h3>{flight.airline}</h3>
            <p>{flight.from} → {flight.to}</p>
            <p>{flight.departure} - {flight.arrival}</p>
            <p>Fare: ₹{flight.fare}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

function UserPage({ onLogout }) {
  return (
    <div className="page user-page">
      <h2>User Booking Page</h2>
      <p>Welcome back! Choose a flight and continue to book.</p>
      <div className="flights">
        {flightDetails.map(flight => (
          <div key={flight.id} className="flight-card">
            <h3>{flight.airline}</h3>
            <p>{flight.from} → {flight.to}</p>
            <p>{flight.departure} - {flight.arrival}</p>
            <p>Fare: ₹{flight.fare}</p>
            <button className="book-button">Book Now</button>
          </div>
        ))}
      </div>
      <button className="logout-button" onClick={onLogout}>Logout</button>
    </div>
  );
}

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loggedIn: false,
    };
    this.handleLogin = this.handleLogin.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
  }

  handleLogin() {
    this.setState({ loggedIn: true });
  }

  handleLogout() {
    this.setState({ loggedIn: false });
  }

  render() {
    const { loggedIn } = this.state;
    const page = loggedIn ? <UserPage onLogout={this.handleLogout} /> : <GuestPage />;

    return (
      <div className="App">
        <header>
          <h1>Ticket Booking App</h1>
          {!loggedIn && (
            <button className="login-button" onClick={this.handleLogin}>
              Login
            </button>
          )}
        </header>
        {page}
      </div>
    );
  }
}

export default App;
