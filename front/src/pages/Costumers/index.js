import { useEffect, useState } from "react";
import { FiUser, FiDelete, FiEdit2 } from 'react-icons/fi';

import Header from '../../components/Header';
import Title from '../../components/Title';

import * as apiRequests from '../../services/apiRequests';

import './costumers.css'

export default function Costumers(){
  const [nome, setNome] = useState("");
  const [cnpj, setCnpj] = useState();
  const [endereco, setEndereco] = useState("");

  const [usuario, setUsuario] = useState();
  const [cliente, setCliente] = useState();
  const [clientes, setClientes] = useState([]);

  const [dataChanged, setDataChanged] = useState(false);

  useEffect(() => {
    console.log("Renderizando Costumers...");

    loadData().then((user) => {
      setUsuario(user);
      setClientes(user.clientModels);
    });

  }, [cliente, dataChanged]);

  async function loadData(){
    return apiRequests.getLoggedUser().then(user => user);
  }

  async function formSubmit(e){
    e.preventDefault();

    let newClient = {
      "userModelId": usuario.id,
      "name": nome,
      "cnpj": cnpj,
      "address": endereco
    }

    apiRequests.postNewClient(newClient)
      .then(() => {
        setCliente(newClient);
      });
  }

  async function removeClient(id){
    apiRequests.deleteClient(id)
      .then(() => {
        setDataChanged(!dataChanged);
      });
  }

  return(
    <div>
      <Header />
      <div className="content">
        <Title nome="Clientes"><FiUser size={25}/></Title>
        <div className="container">
          <form onSubmit={(e) => {formSubmit(e)}} className="form-profile costumers">
            <label>Nome</label>
            <input placeholder="Digite o Nome Fantasia" type="text" value={nome} onChange={(e) => setNome(e.target.value)} />

            <label>CNPJ</label>
            <input placeholder="Digite o CNPJ" type="text" value={cnpj} onChange={(e) => { setCnpj(e.target.value) }} />

            <label>Endereço</label>
            <input placeholder="Digite o seu Endereço" type="text" value={endereco} onChange={(e) => { setEndereco(e.target.value) }} />

            <button className="button-costumers" type="submit">Salvar</button>
          </form>
        </div>
        <table>
          <thead>
            <tr>
              <th scope="col">Cliente</th>
              <th scope="col">CNPJ</th>
              <th scope="col">Endereço</th>
              <th scope="col">Cadastrado em</th>
              <th scope="col">#</th>
            </tr>
          </thead>
          <tbody>
            {
              clientes.map(
                ({id, name: nome, cnpj, address: endereco, timestamp}, index) => {
                  return(
                    <tr key={index}>
                      <td data-label="Cliente">{nome}</td>
                      <td data-label="CNPJ">{cnpj}</td>
                      <td data-label="Endereço">{endereco}</td>
                      <td data-label="Cadastrado">{timestamp}</td>
                      <td data-label="#">
                        <button onClick={() => {removeClient(id)}} className="action" style={{backgroundColor: '#3583f6' }}>
                          <FiDelete color="#FFF" size={17} />
                        </button>
                        <button className="action" style={{backgroundColor: '#F6a935' }}>
                          <FiEdit2 color="#FFF" size={17} />
                        </button>
                      </td>
                    </tr>
                  );
                }
              )
            }
          </tbody>
        </table>
      </div>
    </div>
  );
}