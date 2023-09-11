// Call the dataTables jQuery plugin
$(document).ready(function() {
  loadUsers();
  $('#usersTable').DataTable();
});

// async function to load all users using AJAX to consume an REST endpoint with fetch
async function loadUsers(){
  const userResponses = await fetch('getUsers', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  });
  const userContents = await userResponses.json();
  let userDataRowHTML = '';
  for (let userContent of userContents) {
  // declaring a new variable to concat HTML with the values from userResponses
      let userDataRow = '<tr><td>' + userContent.id + '</td><td>' + userContent.name + ' ' + userContent.lastName +
      '</td><td>' + userContent.email + '</td><td>' + userContent.telephone + '</td><td><div style="text-align: center;">'
      + '<a href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></div></td></tr>'
      userDataRowHTML += userDataRow
      }
      // declaring a querySelector to be able to see all values in the same table
  document.querySelector('#usersTable tbody').outerHTML = userDataRowHTML;

}

