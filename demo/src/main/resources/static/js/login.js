// Call the dataTables jQuery plugin
$(document).ready(function() {
    // on ready
});

// async function to create a new users an REST endpoint with fetch
async function loginUser(){
let data = {};
data.email = document.getElementById("txtInputEmail").value;
data.password = document.getElementById("txtInputPassword").value;

     const userResponses = await fetch('api/loginUser', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });
    const responseLogin = await userResponses.text();
    if (responseLogin != 'Fail') {
        // saving token in the server memory
        localStorage.token = responseLogin;
        localStorage.email = data.email;
        window.location.href = 'users.html'
    } else {
        alert("Intente nuevamente. Credenciales incorrectas")
    }
}