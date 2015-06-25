
function RefreshMembers()
{

    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        //sending selected country to servlet
        var url = "controller_servl?event=REFRMEMBERS";
        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){members_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }

}
function members_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("chatlist").innerHTML = text;
        setTimeout(function(){RefreshMembers();},10000);
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}