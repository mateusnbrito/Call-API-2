import { useState, createContext, useEffect } from 'react'
import { toast } from 'react-toastify';
import * as apiAuth from '../services/apiRequests'
import { logInUser, logOutUser, setUserFirebaseId } from '../services/authentication';

export const AuthContext = createContext({});

function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    function loadUser() {
      const storagedUser = localStorage.getItem("usuarioLogado");

      if (storagedUser) {
        setUserFirebaseId(storagedUser);
        setUser(storagedUser);
        setLoading(true);
      }

      setLoading(false);
    }

    loadUser();
  }, []);

  async function signUp(nome, email, password) {
    apiAuth.postNewUser({
      "name": nome,
      "email": email,
      "password": password,
      "picture": "",
    }).then(response => {
      setLocalUser(response.firebaseId);
      setUser(localStorage.getItem("usuarioLogado"));
      setLoading(true);

      return response;
    });
  }

  async function signIn(email, password) {
    logInUser(email, password)
    .then(response => response)
    .then(data => {
      setLocalUser(data.user.uid);
      setUser(localStorage.getItem("usuarioLogado"));
      setLoading(true);
      toast.success('Bem-vindo de volta!!');
    });
  }

  async function signOut() {
    logOutUser()
      .then(() => {
        localStorage.removeItem("usuarioLogado");
      });
  }

  function setLocalUser(data){
    localStorage.setItem('usuarioLogado', data);
  }

  return (
    <AuthContext.Provider value={{
        signed: !!user,
        user,
        signUp,
        signOut,
        signIn,
        loading,
        setUser,
        setLocalUser
    }}>
        {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;