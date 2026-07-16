import React from 'react';
import './App.css';

const office = {
  name: 'Downtown Business Suite',
  rent: 55000,
  address: '123 Central Avenue, Tech Park, Mumbai',
};

const offices = [
  { name: 'Green Tower', rent: 72000, address: '5 Corporate Plaza, Bangalore' },
  { name: 'Ocean View', rent: 59000, address: '17 Marine Drive, Chennai' },
  { name: 'Skyline Hub', rent: 68000, address: '22 Business Street, Pune' },
  { name: 'Metro Office', rent: 54000, address: '41 City Center, Hyderabad' },
];

function App() {
  return (
    <div className="App">
      <h1>Office Space Rental</h1>

      <img
        className="office-image"
        src="https://images.unsplash.com/photo-1517245386807-bb43f82c33c4?auto=format&fit=crop&w=900&q=80"
        alt="Office space"
      />

      <div className="office-info">
        <h2>{office.name}</h2>
        <p>Rent: <span style={{ color: office.rent < 60000 ? 'red' : 'green' }}>₹{office.rent}</span></p>
        <p>Address: {office.address}</p>
      </div>

      <div className="office-list">
        <h2>Available Office Spaces</h2>
        {offices.map((item, index) => (
          <div className="office-card" key={index}>
            <h3>{item.name}</h3>
            <p>
              Rent: <span style={{ color: item.rent < 60000 ? 'red' : 'green' }}>₹{item.rent}</span>
            </p>
            <p>Address: {item.address}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
