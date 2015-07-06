function show_changepassword(id) {
    //alert(id);
    if (document.getElementById(id).style.display == 'block') {
        document.getElementById(id).style.display = 'none';
        document.getElementById('savepassmodal_prof').style.display = 'none';
    } else {
        document.getElementById(id).style.display = 'block';
        document.getElementById('savepassmodal_prof').style.display = 'block';
    }
}

function getMyprofile(username) {

    xmlHttpmyProf = GetXmlHttpObject();
    if (xmlHttpmyProf == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {

        //sending selected country to servlet
        var url = "controller_servl?event=MEMBER_PROFILE&username=" + username + "&tag=viewMYprofile";
        //creating callback method.here countrychanged is callback method
        xmlHttpmyProf.onreadystatechange = function () {
            myprofile_return(xmlHttpmyProf)
        };

        xmlHttpmyProf.open("GET", url, true)
        xmlHttpmyProf.send(null)
    }

}

function myprofile_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("my_profile_modal_").innerHTML = text;
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);
    }
}

function ChangePassword() {




    var npass = document.getElementById("newpass").value;
    var oldpass = document.getElementById("oldpass").value;
    var retpass = document.getElementById("retypenewpass").value;
    var checkpassword = document.getElementById("checkpasswords").innerHTML;

    var chcc = (npass == retpass);
    alert(checkpassword);




    if (chcc) {

        style_inp("newpass");
        style_inp("retypenewpass");
    }
    else {

        style_abstract_valid("retypenewpass");

    }


    if (password_valid(oldpass))
    {
        style_inp("oldpass");
    } else {

        style_abstract_valid("oldpass");

    }

    if (password_valid(npass))
    {
        style_inp("newpass");
    } else {

        style_abstract_valid("newpass");
    }





    //alert(chcc);

    var success = ((checkpassword == 1) && chcc && password_valid(npass) && password_valid(oldpass));




    if (success) {

        document.getElementById("savepassmodal_prof").setAttribute("data-dismiss", "modal");
        document.getElementById("suc_todo_mes").innerHTML = "<span class=\"glyphicon glyphicon-ok-circle\"></span>  Your password changed succeswsfully";
        document.getElementById("suc_todo_mes").setAttribute("class", "alert alert-success alert_messa col-sm-8 ");
        xmlHttppswd = GetXmlHttpObject();
        if (xmlHttppswd == null)
        {
            alert("Browser does not support HTTP Request")
            return
        }
        else
        {

            //sending selected country to servlet
            var url = "controller_servl?event=CHANGEPSWD&password=" + document.getElementById("newpass").value +
                    "&old_password=" + document.getElementById("oldpass").value;
            //creating callback method.here countrychanged is callback method
            //xmlHttppswd.onreadystatechange = function(){myprofile_return(xmlHttppswd)};

            xmlHttppswd.open("GET", url, true)
            xmlHttppswd.send(null)
        }


    } else {

        document.getElementById("savepassmodal_prof").removeAttribute("data-dismiss");
        alert("poulos");


        var htmlString = "";

        htmlString =
                '<div>' + '<div class = "glyphicon glyphicon-remove-circle">' + '</div>' + '  Fill The Red Required Inputs' + '</div>';

        document.getElementById("suc_todo_mes_valid_MYPROF").style.display = "block";
        document.getElementById("suc_todo_mes_valid_MYPROF").setAttribute("class", "alert alert-danger pull-left alert_messa_danger");

        alert(document.getElementById("suc_todo_mes_valid_MYPROF").innerHTML);
        document.getElementById("suc_todo_mes_valid_MYPROF").innerHTML = htmlString;





    }










}