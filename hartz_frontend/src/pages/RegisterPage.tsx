import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import MobileFrame from '../components/MobileFrame';

const RegisterPage: React.FC = () => {
  const [formData, setFormData] = useState({
    email: '',
    username: '',
    password: '',
    confirmPassword: '',
    mascot: 'bear'
  });
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    if (formData.password !== formData.confirmPassword) return alert('Passwords do not match');
    try {
      const res = await axios.post('http://localhost:8080/api/auth/register', {
        email: formData.email,
        password: formData.password,
        username: formData.username,
        mascot: formData.mascot,
      });
      localStorage.setItem('token', res.data.token);
      navigate('/home');
    } catch (err) {
      alert('Registration failed');
    }
  };

  return (
    <MobileFrame>
      <div className="container">
        <button className="back-btn" onClick={() => navigate(-1)}>←</button>
        <div className="logo">🐻</div>
        <h2>Create your account</h2>
        <input name="username" placeholder="username" onChange={handleChange} />
        <input name="email" placeholder="email@domain.com" onChange={handleChange} />
        <input name="password" placeholder="new password" type="password" onChange={handleChange} />
        <input name="confirmPassword" placeholder="repeat password" type="password" onChange={handleChange} />
        <button className="primary-btn" onClick={handleSubmit}>Continue</button>
        <div className="separator">or</div>
        <button className="oauth-btn">Continue with Google</button>
        <button className="oauth-btn">Continue with Apple</button>
        <small>By clicking continue, you agree to our Terms of Service and Privacy Policy</small>
      </div>
    </MobileFrame>
  );
};

export default RegisterPage;
