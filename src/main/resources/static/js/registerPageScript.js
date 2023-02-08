function registration()
{
    var userName= document.getElementById("inputUsername").value;
    var password= document.getElementById("inputPassword").value;
    var confirmPassword= document.getElementById("confirmPassword").value;
    var email= document.getElementById("inputEmail").value;
    // var confirmPassword= document.getElementById("textBox3").value;

    if(userName=='')
    {
        alert('Please enter user name');
    }
    else if(password=='')
    {
        alert('Please enter password');
    }
    else if(email=='')
    {
        alert('Please enter email');
    }
}

function clearFunction()
{
    document.getElementById("inputUsername").value="";
    document.getElementById("inputPassword").value="";
    document.getElementById("confirmPassword").value="";
    document.getElementById("inputEmail").value="";
}