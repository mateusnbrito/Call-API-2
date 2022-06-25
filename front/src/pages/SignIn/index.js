import './signin.css'
import logo from '../../assets/login.png'
import { Link, useNavigate } from 'react-router-dom'
import { useState, useContext } from 'react';
import { AuthContext } from '../../contexts/auth';

function SignIn() {
  const navigate = useNavigate();

  const [email, setEmail]=useState('');
  const [senha, setSenha]=useState('');
  const { signIn } = useContext(AuthContext);

  runNavigate();

  function handleSubmit(e){
    e.preventDefault();

    let userData = [];

    ((e.target).querySelectorAll("input")).forEach(item => {
      userData.push(item.value);
    });

    signIn(userData[0], userData[1]);

    runNavigate();
  }

  async function runNavigate(){
    let navigateInterval = setInterval(() => {
      if(localStorage.getItem('usuarioLogado')){
        clearInterval(navigateInterval);
        navigate("/dashboard");
      }
    }, 250);
  }

  return (
    <div className="conteiner-center">
      <div className="login">
        
        <div className="login-area">
          <img src={logo} alt="Logo do Sistema"/>
        </div>
        <form onSubmit={handleSubmit}>
          <h1>Entrar</h1>
          <input type="text" value={email} placeholder="email@email.com"  onChange={(e)=>{setEmail(e.target.value)}} />

          <input type="password" value={senha} placeholder="*****" onChange={(e)=>{setSenha(e.target.value)}}/>
          <button type="submit">Acessar</button>
        </form>
        <Link to="/register">Criar uma conta</Link>
      </div>
    </div>
  );
}

export default SignIn;