// App.jsx (updated with API integration)
import React, { useState } from 'react';
import './App.css';

const AuthForm = () => {
  const [containerActive, setContainerActive] = useState(false);
  const [loginContact, setLoginContact] = useState('');
  const [loginPassword, setLoginPassword] = useState('');
  const [name, setName] = useState('');
  const [registerContact, setRegisterContact] = useState('');
  const [registerPassword, setRegisterPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState('');

  const API_BASE_URL = 'http://localhost:5000/api'; // Update with your backend URL

  const handleLoginContactChange = (e) => {
    const value = e.target.value.replace(/\D/g, '').slice(0, 10);
    setLoginContact(value);
  };

  const handleRegisterContactChange = (e) => {
    const value = e.target.value.replace(/\D/g, '').slice(0, 10);
    setRegisterContact(value);
  };

  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setMessage('');
    
    try {
      const response = await fetch(`${API_BASE_URL}/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          contact: loginContact,
          password: loginPassword
        }),
      });
      
      const data = await response.json();
      
      if (response.ok) {
        setMessage('Login successful! Redirecting...');
        // Store token in localStorage
        localStorage.setItem('authToken', data.token);
        // You can redirect the user here
        console.log('Login successful:', data);
      } else {
        setMessage(data.error || 'Login failed');
      }
    } catch (error) {
      setMessage('Network error. Please try again.');
    } finally {
      setIsLoading(false);
    }
  };

  const handleRegisterSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setMessage('');
    
    try {
      const response = await fetch(`${API_BASE_URL}/register`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          name,
          contact: registerContact,
          password: registerPassword
        }),
      });
      
      const data = await response.json();
      
      if (response.ok) {
        setMessage('Registration successful! You can now login.');
        // Clear form
        setName('');
        setRegisterContact('');
        setRegisterPassword('');
        // Switch to login form after a delay
        setTimeout(() => {
          setContainerActive(false);
        }, 2000);
      } else {
        setMessage(data.error || 'Registration failed');
      }
    } catch (error) {
      setMessage('Network error. Please try again.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="app">
      <div className={`container ${containerActive ? 'active' : ''}`} id="container">
        <div className="form-container sign-up">
          <form onSubmit={handleRegisterSubmit}>
            <h1>Create Account</h1>
            <div className="social-icons">
              <a href="#" className="icon"><i className="fa-brands fa-google-plus-g"></i></a>
              <a href="#" className="icon"><i className="fa-brands fa-facebook-f"></i></a>
              <a href="#" className="icon"><i className="fa-brands fa-github"></i></a>
              <a href="#" className="icon"><i className="fa-brands fa-linkedin-in"></i></a>
            </div>
            <span>or use your contact for registration</span>
            <input 
              type="text" 
              placeholder="Name" 
              value={name}
              onChange={(e) => setName(e.target.value)}
              required 
            />
            <input 
              type="tel" 
              placeholder="Contact (10 digits)" 
              value={registerContact}
              onChange={handleRegisterContactChange}
              pattern="[0-9]{10}"
              required 
            />
            <input 
              type="password" 
              placeholder="Password" 
              value={registerPassword}
              onChange={(e) => setRegisterPassword(e.target.value)}
              required 
            />
            <button type="submit" disabled={isLoading}>
              {isLoading ? 'Signing Up...' : 'Sign Up'}
            </button>
            {message && <div className="message">{message}</div>}
          </form>
        </div>
        <div className="form-container sign-in">
          <form onSubmit={handleLoginSubmit}>
            <h1>Login</h1>
            <div className="social-icons">
              <a href="#" className="icon"><i className="fa-brands fa-google-plus-g"></i></a>
              <a href="#" className="icon"><i className="fa-brands fa-facebook-f"></i></a>
              <a href="#" className="icon"><i className="fa-brands fa-github"></i></a>
              <a href="#" className="icon"><i className="fa-brands fa-linkedin-in"></i></a>
            </div>
            <span>or use your contact and password</span>
            <input 
              type="tel" 
              placeholder="Contact (10 digits)" 
              value={loginContact}
              onChange={handleLoginContactChange}
              pattern="[0-9]{10}"
              required 
            />
            <input 
              type="password" 
              placeholder="Password" 
              value={loginPassword}
              onChange={(e) => setLoginPassword(e.target.value)}
              required 
            />
            <a href="#">Forget Your Password?</a>
            <button type="submit" disabled={isLoading}>
              {isLoading ? 'Logging In...' : 'Login'}
            </button>
            {message && <div className="message">{message}</div>}
          </form>
        </div>
        <div className="toggle-container">
          <div className="toggle">
            <div className="toggle-panel toggle-left">
              <h1>Welcome Back!</h1>
              <p>Enter your personal details to use all of site features</p>
              <button className="hidden" onClick={() => setContainerActive(false)}>Login</button>
            </div>
            <div className="toggle-panel toggle-right">
              <h1>Hello, Friend!</h1>
              <p>Register with your personal details to use all of site features</p>
              <button className="hidden" onClick={() => setContainerActive(true)}>Sign Up</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AuthForm;