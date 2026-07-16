import React from 'react';

function PressButton({ onPress, children }) {
  return (
    <button className="secondary" onClick={onPress}>
      {children}
    </button>
  );
}

export default PressButton;
