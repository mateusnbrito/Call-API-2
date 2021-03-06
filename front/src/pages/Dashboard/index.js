import { useState, useEffect } from 'react';
import { FiMessageSquare, FiPlus, FiSearch, FiEdit2 } from 'react-icons/fi';
import { Link } from 'react-router-dom';

import Header from '../../components/Header';
import Title from '../../components/Title';

import * as apiRequests from '../../services/apiRequests';

import './dashboard.css';

export default function Dashboard(){
  const [chamados, setChamados] = useState([]);
  const [clientes, setClientes] = useState([]);
  const [usuario, setUsuario] = useState();

  const [dataChanged, setDataChanged] = useState(false);

  useEffect(() => {
    console.log("Renderizando Dashboard...");

    loadData().then((user) => {
      setUsuario(user);
      setClientes(user.clientModels);
      setChamados((user.clientModels).map(clientModel => {
        return clientModel.callModels;
      })[0]);
    });
  }, [dataChanged]);

  async function loadData(){
    return apiRequests.getLoggedUser().then(user => user);
  }

  console.log(chamados);

  return (
    <div>
      <Header/>
      <div className="content">
        <Title nome="Atendimentos"><FiMessageSquare size={25} /></Title>
        {
          chamados.length === 0 ? (
            <div className="container dashboard">
              <span>Nenhum chamado registrado...</span>
              <Link to="/new" className="new"><FiPlus size={25} color="#FFF" />Novo chamado</Link>
            </div>
          ) : (
            <Link to="/new" className="new"><FiPlus size={25} color="#FFF" />Novo chamado</Link>
          )
        }
        <table>
          <thead>
            <tr>
              <th scope="col">Cliente</th>
              <th scope="col">Assunto</th>
              <th scope="col">Status</th>
              <th scope="col">Cadastrado em</th>
              <th scope="col">#</th>
            </tr>
          </thead>
          <tbody>
            {
              chamados.map(
                ({clientModels: cliente, subject:assunto, status, timestamp}, index) => {
                return(
                  <tr key={index}>
                    <td data-label="Cliente">{cliente}</td>
                    <td data-label="Assunto">{assunto}</td>
                    <td data-label="Status">
                      <span className="badge" style={{backgroundColor: '#5cb85c' }}>{status}</span>
                    </td>
                    <td data-label="Cadastrado">{timestamp}</td>
                    <td data-label="#">
                      <button className="action" style={{backgroundColor: '#3583f6' }}>
                        <FiSearch color="#FFF" size={17} />
                      </button>
                      <button className="action" style={{backgroundColor: '#F6a935' }}>
                        <FiEdit2 color="#FFF" size={17} />
                      </button>
                    </td>
                  </tr>
                )
              })
            }
          </tbody>
        </table>
      </div>
    </div>
  )
}