async function logout () {
    localStorage.removeItem('token')
    localStorage.removeItem('username')
    localStorage.removeItem('id')
    location.reload();
    window.location.href = 'login.html'
}



