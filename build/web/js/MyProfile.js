function show_changepassword(id) {
    //alert(id);
    if (document.getElementById(id).style.display == 'block') {
        document.getElementById(id).style.display = 'none';
        document.getElementById('savepassmodal').style.display = 'none';
    } else {
        document.getElementById(id).style.display = 'block';
        document.getElementById('savepassmodal').style.display = 'block';
    }
}

function getMyprofile(username){
    
      xmlHttpmyProf=GetXmlHttpObject();
    if (xmlHttpmyProf == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        //sending selected country to servlet
        var url = "controller_servl?event=MEMBER_PROFILE&username="+username+"&tag=viewMYprofile";
        //creating callback method.here countrychanged is callback method
        xmlHttpmyProf.onreadystatechange = function(){myprofile_return(xmlHttpmyProf)};

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

function ChangePassword(){
      xmlHttppswd=GetXmlHttpObject();
    if (xmlHttppswd == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        //sending selected country to servlet
        var url = "controller_servl?event=CHANGEPSWD&password="+document.getElementById("newpass").value+
                "&old_password="+document.getElementById("oldpass").value;
        //creating callback method.here countrychanged is callback method
        //xmlHttppswd.onreadystatechange = function(){myprofile_return(xmlHttppswd)};

        xmlHttppswd.open("GET", url, true)
        xmlHttppswd.send(null)
    }

}