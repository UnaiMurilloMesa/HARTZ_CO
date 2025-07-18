import React from 'react';
import { useNavigate } from 'react-router-dom';
import MobileFrame from '../components/MobileFrame';

const WelcomePage: React.FC = () => {
  const navigate = useNavigate();
  return (
    <MobileFrame>
      <div className="container">
        <div className="logo">🐻</div>
        <h2>Hey there!!<br />Welcome to Hartz</h2>
        <button className="primary-btn" onClick={() => navigate('/register')}>Get Started</button>
        <button className="link-btn" onClick={() => navigate('/login')}>I already have an account</button>
      </div>
    </MobileFrame>
  );
};

export default WelcomePage;