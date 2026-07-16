import React from 'react';

class CurrencyConvertor extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      rupees: 0,
      euro: 0,
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({ rupees: Number(event.target.value) });
  }

  handleSubmit(event) {
    event.preventDefault();
    const { rupees } = this.state;
    const euro = rupees * 0.011;
    this.setState({ euro: euro.toFixed(2) });
  }

  render() {
    const { rupees, euro } = this.state;

    return (
      <div className="card">
        <h2>Currency Convertor</h2>
        <form onSubmit={this.handleSubmit}>
          <label>
            Rupees:
            <input type="number" value={rupees} onChange={this.handleChange} />
          </label>
          <button type="submit" className="primary">
            Convert
          </button>
        </form>
        <p>{rupees} INR = {euro} EUR</p>
      </div>
    );
  }
}

export default CurrencyConvertor;
