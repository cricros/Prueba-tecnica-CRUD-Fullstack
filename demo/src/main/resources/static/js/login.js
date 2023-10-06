// Call the dataTables jQuery plugin
$(document).ready(function() {
    // on ready
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

// async function to create a new users an REST endpoint with fetch
async function loginUser(){
let data = {};
data.username = document.getElementById("txtInputUsername").value;
data.password = document.getElementById("txtInputPassword").value;
// validar ambos campos vacios y cada uno de ellos
if (validaVacio(data.username) && validaVacio(data.password) ) {
    return alert("Los campos se encuentran vacios");
}
if (validaVacio(data.username)){
    return alert("El campo se username se encuentra vacio");
} if  (validaVacio(data.password)){
    return alert("El campo se password se encuentra vacio");
}
const userResponses = await fetch('api/loginUser', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });
    const responseLogin = await userResponses.text();
    if (responseLogin != '400' && responseLogin != '401'){
            // saving token in the server memory
            localStorage.token = responseLogin;
            localStorage.username = data.username;
            window.location.href = 'users.html'
    }
    if (responseLogin == '400') {
        alert("El usuario no existe");
        return location.reload();
    }
    if  (responseLogin == '401'){
            // no entra a esta parte del codigo
        alert("Intente nuevamente. Credenciales incorrectas");
        return location.reload();
    }
}

