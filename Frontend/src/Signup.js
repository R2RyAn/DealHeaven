// src/Signup.js
import React, { useState } from "react";
import { createUserWithEmailAndPassword } from "firebase/auth";
import { auth } from "./firebase";
import { Link } from "react-router-dom";
import logo from "./logo.png";

const Signup = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSignup = async (e) => {
    e.preventDefault();
    try {
      await createUserWithEmailAndPassword(auth, email, password);
      alert("Account created successfully!");
    } catch (error) {
      console.error("Error signing up:", error.message);
      alert("Failed to create account");
    }
  };

  return (
    <div className="container mt-5 d-flex flex-column align-items-center">
      <nav className="navbar navbar-light w-100 mb-4 d-flex justify-content-between">
        <a className="navbar-brand" href="/">
          <img src={logo} alt="Logo" width="100" />
        </a>
        <div>
          <Link to="/login" className="btn btn-outline-primary me-2" style={{ color: "#800000", borderColor: "#800000" }}>Log In</Link>
          <Link to="/signup" className="btn btn-outline-secondary" style={{ color: "#800000", borderColor: "#800000" }}>Sign Up</Link>
        </div>
      </nav>
      <div className="card p-4 shadow" style={{ maxWidth: "400px", width: "100%" }}>
        <h2 className="text-center">Sign Up</h2>
        <form onSubmit={handleSignup}>
          <div className="mb-3">
            <input
              type="email"
              className="form-control"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <div className="mb-3">
            <input
              type="password"
              className="form-control"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button type="submit" className="btn btn-primary w-100" style={{ backgroundColor: "#800000", borderColor: "#800000" }}>
            Sign Up
          </button>
        </form>
      </div>
    </div>
  );
};

export default Signup;