function registration()
{
    var userName= document.getElementById("textBox1").value;
    var password= document.getElementById("textBox2").value;
    // var confirmPassword= document.getElementById("textBox3").value;

    if(userName=='')
    {
        alert('Please enter user name');
    }
    else if(password=='')
    {
        alert('Please enter password');
    }
    else if(confirmPassword=='')
    {
        alert('Confirm password');
    }
/*    else if(password != confirmPassword)
    {
        alert ('Password not matched');
    }*/
    else if(document.getElementById("textBox3").value.length < 5)
    {
        alert ('Password minimum length is 5');
    }
    else if(document.getElementById("textBox3").value.length > 20)
    {
        alert ('Password maximum length is 20');
    }
    else
    {
        alert('Registration is successful');
    }
}
function clearFunction()
{
    document.getElementById("textBox1").value="";
    document.getElementById("textBox2").value="";
    // document.getElementById("textBox3").value="";
}