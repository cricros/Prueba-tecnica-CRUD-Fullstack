// Call the dataTables jQuery plugin
$(document).ready(function() {
    // on ready
});

// async function to create a new users an REST endpoint with fetch
async function newUsers(){
let data = {};
data.name = document.getElementById("txtFirstName").value;
data.lastName = document.getElementById("txtLastName").value;
data.email = document.getElementById("txtInputEmail").value;
data.password = document.getElementById("txtInputPassword").value;

if (data.password != document.getElementById("txtRepeatPassword").value){
    alert("Las contraseñas no son iguales");
    return location.reload();
}


  const userResponses = await fetch('api/newUser', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  });
  const userContents = await userResponses.json();
}
