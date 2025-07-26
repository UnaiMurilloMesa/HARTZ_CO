import React, { useState } from 'react';

import { useNavigate } from 'react-router-dom';
import MobileFrame from '../components/MobileFrame';
import { logInUser } from '../api';
import InputWrapper from '../components/InputFieldHartz';
import ButtonHartz from '../components/ButtonHartz';

const LoginPage: React.FC = () => {
  const [formData, setFormData] = useState({ email: '', password: '' });
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      const res = await logInUser(formData);
      if (res.status !== 200) { // TODO: handle specific error cases
        throw new Error('Login failed');
      }
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
        <InputWrapper name="email" placeholder="email@domain.com" onChange={handleChange} />
        <InputWrapper name="password" placeholder="password" type="password" onChange={handleChange} />
        <ButtonHartz variant="principal" onClick={handleSubmit}>Continue</ButtonHartz>
        <div className="separator">or</div>
        <ButtonHartz variant="secondary">Continue with Google</ButtonHartz>
        <ButtonHartz variant="secondary">Continue with Apple</ButtonHartz>
        <small>By clicking continue, you agree to our Terms of Service and Privacy Policy</small>
      </div>
    </MobileFrame>
  );
};

export default LoginPage;