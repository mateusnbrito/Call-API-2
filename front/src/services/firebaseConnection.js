import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";

const firebaseConfig = {
  apiKey: "AIzaSyC2xCdcG6Tm7NaI78apmAkeFM-ofQvFSBY",
  authDomain: "pweb-chamados-auth.firebaseapp.com",
  projectId: "pweb-chamados-auth",
  storageBucket: "pweb-chamados-auth.appspot.com",
  messagingSenderId: "753936869387",
  appId: "1:753936869387:web:8de66cfa0b04c689f93f0e",
  measurementId: "G-Y9GTRMVL1Y"
};

const app = initializeApp(firebaseConfig);
let auth = getAuth();

export default auth;