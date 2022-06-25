import auth from "./firebaseConnection";
import * as firebase from "firebase/auth";

let firebaseId;

export async function setUserFirebaseId(id){
  firebaseId = id;
}

export async function getUserFirebaseId(){
  return firebaseId;
}

export async function createNewUser(user){
  return await firebase
    .createUserWithEmailAndPassword(auth, user.email, user.password)
      .then(data => {
        setUserFirebaseId(data.user.uid);

        return data;
      });
}

export async function logInUser(email, password){
  return await firebase
    .signInWithEmailAndPassword(auth, email,password)
    .then(data => {
      setUserFirebaseId(data.user.uid);

        return data;
    });
}

export async function logOutUser(){
  return await firebase
    .signOut(auth);
}

export async function removeUser(){
  return await firebase
    .deleteUser(auth.currentUser);
}