function registration()
{
    var userName= document.getElementById("textBox1").value;
    var password= document.getElementById("textBox2").value;
    var email= document.getElementById("textBox3").value;
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
/*    else if(confirmPassword=='')
    {
        alert('Confirm password');
    }*/
/*    else if(password != confirmPassword)
    {
        alert ('Password not matched');
    }*/
}
function clearFunction()
{
    document.getElementById("textBox1").value="";
    document.getElementById("textBox2").value="";
    document.getElementById("textBox3").value="";
}