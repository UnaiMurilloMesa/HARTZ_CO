import React from 'react';
import { useNavigate } from 'react-router-dom';
import MobileFrame from '../components/MobileFrame';

const HomePage: React.FC = () => {
  const navigate = useNavigate();

  return (
    <MobileFrame>
      <div className="container">
        <button className="back-btn" onClick={() => navigate(-1)}>←</button>
        <h2>Welcome to Hartz 🐻</h2>
      </div>
    </MobileFrame>
  );
};

export default HomePage;