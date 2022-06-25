const [chamados, setChamados] = useState([1]);

useEffect(()=>{
  async function loadCalls() {
    apiAuth.getLoggedUser().then(data => {
      let clientCalls = [];

      (data.clientModels).forEach((item) => {
        (item.callModels).forEach((call) => {
          clientCalls.push({
            "id": call.id,
            "cliente": item.name,
            "assunto": call.subject,
            "status": call.status,
            "timestamp": call.timestamp
          });
        });
      });

      setChamados(clientCalls);
    });
  }

  loadCalls();
}, []);

return(
  <div>
    <Header/>

    <div className="content">
      <Title nome="Atendimentos">
        <FiMessageSquare size={25} />
      </Title>

      {chamados.length === 0 ? (
        <div className="container dashboard">
          <span>Nenhum chamado registrado...</span>

          <Link to="/new" className="new">
            <FiPlus size={25} color="#FFF" />
            Novo chamado
          </Link>
        </div>
      ) : (
        <Link to="/new" className="new">
          <FiPlus size={25} color="#FFF" />
          Novo chamado
        </Link>
      )}

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
            chamados.map((chamado, index) => {
              return(
                <tr key={index}>
                  <td data-label="Cliente">{chamado.cliente}</td>
                  <td data-label="Assunto">{(chamado.assunto)}</td>
                  <td data-label="Status">
                    <span className="badge" style={{backgroundColor: '#5cb85c' }}>{(chamado.status)}</span>
                  </td>
                  <td data-label="Cadastrado">{chamado.timestamp}</td>
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