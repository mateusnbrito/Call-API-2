import { useState } from 'react';
import './header.css'
import { Link } from 'react-router-dom'
import { FiHome, FiUser, FiSettings } from 'react-icons/fi'
import * as apiAuth from '../../services/apiRequests';

function Header() {
  const [avatar, setAvatar] = useState(0);

  apiAuth.getLoggedUser()
    .then(data => {
      setAvatar(data.picture);
    });

    return (
        <div className="sidebar">
            <div>
                <img alt="Foto Avatar" src={avatar} />
            </div>
            <Link to="/dashboard">
                <FiHome color="#FFF" size={24} />
            Chamados
        </Link>
            <Link to="/costumers">
                <FiUser color="#FFF" size={24} />
            Clientes
        </Link>
            <Link to="/profile">
                <FiSettings color="#FFF" size={24} />
            Configurações
        </Link>

        </div>
    );
}

export default Header;