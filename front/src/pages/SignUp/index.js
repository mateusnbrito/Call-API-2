import logo from '../../assets/login.png'
import { Link } from 'react-router-dom'
import { useState, useContext } from 'react';
import { AuthContext } from '../../contexts/auth';
import { useNavigate } from 'react-router-dom'

function SignUp() {
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const { signUp } = useContext(AuthContext);
  const navigate = useNavigate();

  function handleSubmit(e){
    e.preventDefault();

    let userData = [];

    ((e.target).querySelectorAll("input")).forEach(item => {
      userData.push(item.value);
    });

    signUp(userData[0], userData[1], userData[2]);

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
          <h1>Nova Conta</h1>
          <input type="text" value={nome} placeholder="Seu nome"  onChange={(e)=>{setNome(e.target.value)}} />
          <input type="text" value={email} placeholder="email@email.com"  onChange={(e)=>{setEmail(e.target.value)}} />
          <input type="password" value={senha} placeholder="*****" onChange={(e)=>{setSenha(e.target.value)}}/>
          <button type="submit">Cadastrar</button>
        </form>
        <Link to="/">Já possui uma conta? Entre aqui!</Link>
      </div>
    </div>
  );
}

  export default SignUp;