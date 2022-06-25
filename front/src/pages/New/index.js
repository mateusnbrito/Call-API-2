import { useState, useEffect } from 'react';
import Header from '../../components/Header';
import Title from '../../components/Title';
import { FiPlusCircle } from 'react-icons/fi';
import './new.css';
import * as apiAuth from '../../services/apiRequests';
import { useNavigate } from 'react-router-dom';

export default function New() {
  const [clientes, setClientes] = useState([1]);
  const [loadingClientes, setLoadingClientes] = useState(true);
  const [clienteSelecionado, setClienteSelecionado] = useState(0);
  const [assunto, setAssunto] = useState('Suporte');
  const [status, setStatus] = useState('Aberto');
  const [complemento, setComplemento] = useState('');

  const navigate = useNavigate();

  useEffect(() => {
    async function loadClientes() {
      let clientsArray = [];

      apiAuth.getLoggedUser().then(data => {
        (data.clientModels).forEach((item) => {
          clientsArray.push({
            "id": item.id,
            "nome": item.name,
          });
        });

        setClientes(clientsArray);
        setLoadingClientes();
      });
    }

    loadClientes();
  }, []);

  async function handleChamado(e) {
    e.preventDefault();

    apiAuth.postNewCall({
      "clientModelId": clientes[clienteSelecionado].id,
      "subject": assunto.toUpperCase(),
      "status": status.toUpperCase(),
      "text": complemento
    }).then(() => {
      navigateToDashboard();
    });
  }

  async function navigateToDashboard(){
    navigate("/dashboard");
  }

  return (
    <div>
      <Header />
      <div className="content">
        <Title nome="Novo chamado">
          <FiPlusCircle size={25} />
        </Title>
        <div className="container">
          <form onSubmit={(e) => { handleChamado(e) }} className="form-profile">
            <label>Cliente</label>
            {loadingClientes ?
              <input type="text" defaultValue={"Carregando..."} />
              :

              <select value={clienteSelecionado} onChange={(e) => setClienteSelecionado(e.target.value)}>
                {
                  clientes.map((item, index) => {
                    return (<option key={item.id} value={index}>{item.nome}</option>)
                  })
                }
              </select>
            }
            <label>Assunto</label>
            <select value={assunto} onChange={(e) => setAssunto(e.target.value)}>
              <option value="Suporte">Suporte</option>
              <option value="Financeiro">Financeiro</option>
              <option value="Visita">Visita</option>
            </select>
            <label>Status</label>
            <div className="status">
              <input
                type="radio"
                name="radio"
                value="Aberto"
                onChange={(e) => setStatus(e.target.value)}
                checked={status === "Aberto"} />
              <span>Em Aberto</span>
              <input
                type="radio"
                name="radio"
                value="Progresso"
                onChange={(e) => setStatus(e.target.value)}
                checked={status === "Progresso"} />
              <span>Em Progresso</span>
              <input 
                type="radio"
                name="radio"
                value="Atendido"
                onChange={(e) => setStatus(e.target.value)}
                checked={status === "Atendido"} />
              <span>Atendido</span>
            </div>
            <label>Complemento</label>
            <textarea type="text"
              placeholder="Descreva seu problema aqui"
              defaultValue={complemento}
              onChange={(e) => setComplemento(e.target.value)} />
            <button type="submit">Registrar</button>
          </form>
        </div>
      </div>
    </div>
  );
}