import { createNewUser, getUserFirebaseId } from "./authentication";

export function postNewUser(newUser){
  return createNewUser(newUser)
    .then((data) => {
      newUser.firebaseId = data.user.uid;

      const options = {
        method: "POST",
        headers: {
          "Authorization": "Basic YWRtaW46YWRtaW4=",
          "Content-Type": "application/json"
        },
        body: JSON.stringify(newUser)
      }

      return fetch("http://localhost:8080/usuarios/criar-usuario", options)
        .then(response => response.json());
    });
}

export function putUser(newUser){
  return getLoggedUser().then((data) => {
    data.name = newUser.name;

    const options = {
      method: "PUT",
      headers: {
        "Authorization": "Basic YWRtaW46YWRtaW4=",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    }
  
    return fetch("http://localhost:8080/usuarios/atualizar-usuario/"+(data.id), options);
  });

}

export function getLoggedUser(){
  const options = {
    method: "GET",
    headers: {
      "Authorization": "Basic YWRtaW46YWRtaW4=",
      "Content-Type": "application/json",
    },
  }

  return getUserFirebaseId()
    .then(data => {
      return fetch("http://localhost:8080/usuarios/login/todos-os-usuarios/"+data, options)
        .then(response => response.json());
    });
}

export function postNewClient(newClient){
  const options = {
    method: "POST",
    headers: {
      "Authorization": "Basic YWRtaW46YWRtaW4=",
      "Content-Type": "application/json"
    },
    body: JSON.stringify(newClient)
  }

  return fetch("http://localhost:8080/clientes/criar-cliente", options)
    .then(response => response.json());
}

export function postNewCall(newCall){
  const options = {
    method: "POST",
    headers: {
      "Authorization": "Basic YWRtaW46YWRtaW4=",
      "Content-Type": "application/json"
    },
    body: JSON.stringify(newCall)
  }

  return fetch("http://localhost:8080/chamados/criar-chamado", options);
}

export function deleteClient(id){
  const options = {
    method: "DELETE",
    headers: {
      "Authorization": "Basic YWRtaW46YWRtaW4=",
      "Content-Type": "application/json",
    },
  }
  
  return fetch("http://localhost:8080/clientes/deletar-cliente/"+id, options);
}

export function postNewImage(newImage){
  return getLoggedUser().then((data) => {
    const form = new FormData();
    var xhr = new XMLHttpRequest();

    form.append("file", newImage);
    xhr.open("POST", "http://localhost:8080/usuarios/"+(data.id)+"/salvar-imagem");
    xhr.setRequestHeader("Authorization", "Basic YWRtaW46YWRtaW4=");
    xhr.send(form);

    return data;
  });
}