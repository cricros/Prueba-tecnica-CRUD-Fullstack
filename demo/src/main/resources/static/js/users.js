// Call the dataTables jQuery plugin
$(document).ready(function() {
  loadUsers();
  $('#usersTable').DataTable();
});

// async function to load all users using AJAX to consume an REST endpoint with fetch
async function loadUsers(){
  const userResponses = await fetch('api/getUsers', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  });
  const userContents = await userResponses.json();
  let userDataRowHTML = '';

  for (let userContent of userContents) {
  let btnDelete = '<a href="#" onclick ="deleteUser(' + userContent.id + ')"class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
  let telephoneNumber = userContent.telephone == null ? '-' : userContent.telephone

  // declaring a new variable to concat HTML with the values from userResponses
      let userDataRow = '<tr><td>' + userContent.id + '</td><td>' + userContent.name + ' ' + userContent.lastName +
      '</td><td>' + userContent.email + '</td><td>' + telephoneNumber + '</td><td><div style="text-align: center;">'
      + btnDelete + '</div></td></tr>'
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
      'Content-Type': 'application/json'
    },
  });
  alert("Usuario Elimnado");
  location.reload();
}

