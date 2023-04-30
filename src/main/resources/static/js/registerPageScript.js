function clearFunction()
{
    document.getElementById("inputUsername").value="";
    document.getElementById("inputPassword").value="";
    //document.getElementById("confirmPassword").value="";
    document.getElementById("inputEmail").value="";
}

function validateForm() {
    var form = document.getElementById("register-form");
    if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
    }
    form.classList.add('was-validated');

}