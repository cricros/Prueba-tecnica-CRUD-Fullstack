// Call the dataTables jQuery plugin
$(document).ready(function() {
  loadUsers();
  $('#usersTable').DataTable();
  updateEmailName();
});

function updateEmailName(){
    document.getElementById('txt-email-user').outerHTML = localStorage.username;
}

// async function to load all users using AJAX to consume an REST endpoint with fetch
async function loadUsers(){
  const userResponses = await fetch('api/getUsers', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token
    },
  });
  const userContents = await userResponses.json();
  let userDataRowHTML = '';

  for (let userContent of userContents) {
  let btnDelete = '<a href="#" onclick ="deleteUser(' + userContent.id_user + ')"class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
  let btnUpdate = '<a href="update.html" onclick ="saveIDStorage(' + userContent.id_user + ')"class="btn btn-warning btn-circle btn-sm"><i class="fas fa-exclamation-triangle"></i></a>';

  // declaring a new variable to concat HTML with the values from userResponses
      let userDataRow =
      '<tr><td>' + userContent.id_user + '</td><td>' + userContent.name + ' ' + userContent.lastName +
      '</td><td>' + userContent.username + '</td><td>' + userContent.creation_date + '</td><td><div style="text-align: center;">'
      + btnUpdate + '    ' + btnDelete + '</div></td></tr>'
           userDataRowHTML += userDataRow
      }
      // declaring a querySelector to be able to see all values in the same table
  document.querySelector('#usersTable tbody').outerHTML = userDataRowHTML;

}

async function deleteUser(id){
  if (!confirm('Desea eliminar el usuario?')){
    return;
  }
  const userResponses = await fetch('api/deleteUser/'+id, {
    method: 'Delete',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token
    },
  });
  alert("Usuario Elimnado");
  location.reload();
}

async function saveIDStorage(id){
  if (!confirm('Desea actualizar el usuario?')){
    return;
  }
  let idUser = id;
  console.log(id);
  localStorage.setItem("id", idUser);
}