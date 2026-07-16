import React from 'react';
import './App.css';
import CohortDetails from './CohortDetails';

const cohorts = [
  {
    name: 'Cohort Alpha',
    status: 'ongoing',
    startDate: '2026-06-10',
    endDate: '2026-09-10',
    completed: false,
  },
  {
    name: 'Cohort Beta',
    status: 'completed',
    startDate: '2026-02-01',
    endDate: '2026-05-01',
    completed: true,
  },
  {
    name: 'Cohort Gamma',
    status: 'completed',
    startDate: '2025-10-15',
    endDate: '2026-01-15',
    completed: true,
  },
];

function App() {
  return (
    <div className="App">
      <h1>Cohort Details Dashboard</h1>
      <div className="cohort-container">
        {cohorts.map(cohort => (
          <CohortDetails key={cohort.name} cohort={cohort} />
        ))}
      </div>
    </div>
  );
}

export default App;
