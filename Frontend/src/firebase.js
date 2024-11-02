// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyCOXn6PYdh_e-aRsTf3bF0G6JSbcYhBCAo",
  authDomain: "deal-heaven.firebaseapp.com",
  projectId: "deal-heaven",
  storageBucket: "deal-heaven.firebasestorage.app",
  messagingSenderId: "588865851796",
  appId: "1:588865851796:web:c8b263f8f340baed51ff81",
  measurementId: "G-G9GS7CGVG7"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
// Export Firebase authentication instance
export const auth = getAuth(app);
