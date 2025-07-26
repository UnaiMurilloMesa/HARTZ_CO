import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import MobileFrame from '../components/MobileFrame';
import { registerUser } from '../api';

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
      const res = await registerUser(formData);
      if (res.status !== 200) { // TODO: handle specific error cases
        throw new Error('Registration failed');
      }
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
