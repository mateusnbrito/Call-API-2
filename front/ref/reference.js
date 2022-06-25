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