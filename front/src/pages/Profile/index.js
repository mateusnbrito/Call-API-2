import { useState, useContext, useEffect } from 'react';
import { FiSettings, FiUpload } from 'react-icons/fi';
import { useNavigate } from 'react-router-dom';

import { AuthContext } from '../../contexts/auth';
import * as apiRequests from '../../services/apiRequests';

import Header from '../../components/Header';
import Title from '../../components/Title';

import './profile.css';

export default function Profile(){
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [avatar, setAvatar] = useState("");
  const [imageFile, setimageFile] = useState();

  const [usuario, setUsuario] = useState();

  const [dataChanged, setDataChanged] = useState(false);

  const { signOut } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    console.log("Renderizando Profile...");

    loadData().then((user) => {
      setUsuario(user);
      setNome(user.name);
      setEmail(user.email);
      setAvatar(user.picture);
    });
  }, [dataChanged]);

  async function loadData(){
    return apiRequests.getLoggedUser().then(user => user);
  }

  async function formSubmit(e){
    e.preventDefault();

    apiRequests.postNewImage(imageFile)
      .then(() => {
        setDataChanged(!dataChanged);

        // let newClient = {
        //   "name": nome,
        //   "email": usuario.email,
        //   "password": usuario.password,
        //   "picture": usuario.picture,
        //   "firebaseId": usuario.firebaseId
        // }

        // apiRequests.putUser(newClient)
        //   .then(() => {
        //     setDataChanged(!dataChanged);
        //   })
      });
  }

  async function navigateToLogin(){
    navigate("/");
  }

  return (
    <div>
      <Header/>
      <div className="content">
        <Title nome="Meu perfil"><FiSettings size={25} /></Title>
        <div className="container">
          <form onSubmit={(e) => formSubmit(e)} className="form-profile">
            <label className="label-avatar">
              <span><FiUpload color="#000" size={25} /></span>
              <input type="file" id="file" name="file" onChange={(e) => {setimageFile(e.target.files[0])}}/><br/>
              { avatar === null ? 
                <img src={avatar} width="250" height="250" alt="Foto de perfil do usuario" />
                :
                <img src={avatar} width="250" height="250" alt="Foto de perfil do usuario" />
              }
            </label>
            <label>Nome</label>
            <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} />
            <label>Email</label>
            <input type="text" value={email} disabled={true} />     
            <button type="submit">Salvar</button>
          </form>
        </div>
        <div className="container">
          <button className="logout-btn" onClick={() => {
            signOut().then(() => {navigateToLogin()})
          }}> Sair</button>
        </div>
      </div>
    </div>
  );
}