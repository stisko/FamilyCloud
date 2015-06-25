
function RefreshMessage(receiver)
{
    
    xmlHttp= new XMLHttpRequest();
//     = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {

        //sending selected country to servlet
        var url = "controller_servl?event=REFRMESSAGE&receiver=" + receiver;
        //creating callback method.here countrychanged is callback method

        xmlHttp.onreadystatechange = function(){message_return(receiver);};
        
        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }

}
function message_return(receiver)
{

    if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete")
    {
        
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;
        var id= "chat_body"+receiver;
        
        document.getElementById(id).innerHTML = text;
//document.getElementById("sidebarleft").innerHTML = text;
        //setTimeout(function(){RefreshMessage(receiver);}, 10000);
        setTimeout(function(){RefreshMessage(receiver);}, 5000);
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}