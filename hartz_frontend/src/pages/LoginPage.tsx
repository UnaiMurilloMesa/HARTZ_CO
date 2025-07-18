import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import MobileFrame from '../components/MobileFrame';

const LoginPage: React.FC = () => {
  const [formData, setFormData] = useState({ email: '', password: '' });
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      const res = await axios.post('http://localhost:8080/api/auth/login', formData);
      localStorage.setItem('token', res.data.token);
      navigate('/home');
    } catch (err) {
      alert('Login failed');
    }
  };

  return (
    <MobileFrame>
      <div className="container">
        <button className="back-btn" onClick={() => navigate(-1)}>←</button>
        <div className="logo">🐻</div>
        <h2>Login to your account</h2>
        <input name="email" placeholder="email@domain.com" onChange={handleChange} />
        <input name="password" placeholder="password" type="password" onChange={handleChange} />
        <button className="primary-btn" onClick={handleSubmit}>Continue</button>
        <div className="separator">or</div>
        <button className="oauth-btn">Continue with Google</button>
        <button className="oauth-btn">Continue with Apple</button>
        <small>By clicking continue, you agree to our Terms of Service and Privacy Policy</small>
      </div>
    </MobileFrame>
  );
};

export default LoginPage;