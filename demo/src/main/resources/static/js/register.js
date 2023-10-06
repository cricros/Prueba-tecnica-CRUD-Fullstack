// Call the dataTables jQuery plugin
$(document).ready(function() {

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
async function newUsers(){
let data = {};
data.name = document.getElementById("txtFirstName").value;
data.lastName = document.getElementById("txtLastName").value;
data.username = document.getElementById("txtInputUsername").value;
data.password = document.getElementById("txtInputPassword").value;
    //COMPRUEBA CAMPOS VACIOS
    if ( validaVacio(data.name) || validaVacio(data.lastName) || validaVacio(data.username) || validaVacio(data.password)){
        alert("Los campos no pueden quedar vacios");
        return false;
    }

// validar que el user no exista
if (data.password != document.getElementById("txtRepeatPassword").value){
    alert("Las contraseñas no son iguales");
    return location.reload();
} else {
     const userResponses = await fetch('api/newUser', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
        });
        const responseCreateUser = await userResponses.text();
      if (responseCreateUser == "201"){
        console.log(responseCreateUser)
        alert("Usuario Registrado");
        window.location.href = 'login.html'
      } else {
            console.log(responseCreateUser)
            alert("El usuario ya existe");
      }
}

}
