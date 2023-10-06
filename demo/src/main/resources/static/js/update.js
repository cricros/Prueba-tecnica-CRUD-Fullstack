// Call the dataTables jQuery plugin
$(document).ready(function() {
  loadUser();
});

function validaVacio(valor) {
        valor = valor.replace("&nbsp;", "");
        valor = valor == undefined ? "" : valor;
        if (!valor || 0 === valor.trim().length) {
            return true;
            }
        else {
            return false;
            }
        }

function getLocalStorage(){
    return localStorage.getItem("id")
}

async function loadUser(){
  let idUser = getLocalStorage();
  console.log(idUser);
  const userResponses = await fetch('api/getUser/'+ idUser, {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token
    },
  });
  const userContents = await userResponses.json();
  console.log(userContents);
    for (let userContent of userContents) {
    let userDataRow =
    '<tr id = "dataRow"><td contenteditable="true">' + userContent.name + '</td><td contenteditable="true">' + userContent.lastName +
    '</td><td contenteditable="true">' + userContent.username + '</td><td contenteditable="true">' + '</td></tr>'
        document.querySelector('#userTable tbody').outerHTML = userDataRow;
        }
}

async function updateData(){
    let row = document.getElementById("dataRow");
    let rowElement = row.getElementsByTagName("td");

    let data = {};
        data.name = rowElement[0].innerHTML;
        data.lastName = rowElement[1].innerHTML;
        data.username = rowElement[2].innerHTML;
        data.password = rowElement[3].innerHTML

    const UpdateUser = await updateUser(data);
}

async function updateUser(data){
  let idUser = getLocalStorage();
  console.log(idUser);
   if (!confirm('Desea actualizar el usuario?')){
        return;
    }
     const userResponses = await fetch('api/updateUser/' + idUser, {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Authorization': localStorage.token
        },
        body: JSON.stringify(data)
      });
      const responseUpdate = await userResponses.text();
      if (responseUpdate == '200'){
        alert("Usuario Actualizado");
        return window.location.href = 'users.html'
      } if (responseUpdate == '409') {
        return alert("Usuario Ocupado. Intente uno diferente.");
      }
}
