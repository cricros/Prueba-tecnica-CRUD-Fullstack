// Call the dataTables jQuery plugin
$(document).ready(function() {
  loadUser();
  $('#usersTable').DataTable();
});

async function loadUser(){
  const userResponse = await fetch('getUser/2221', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
  });
  const userContent = await userResponse.json();
  console.log(userContent);
}

