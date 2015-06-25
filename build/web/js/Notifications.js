


function getNotifications()
{

    xmlHttp=GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        //sending selected country to servlet
        var url = "controller_servl?event=NOTIFICATIONS";
        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){notifications_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }

}
function notifications_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("dropdown_notifications").innerHTML = text;
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}

function getUnreadNotificationsCount(){
     xmlHttp=GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        //sending selected country to servlet
        var url = "controller_servl?event=GETUNREADCOUNT";
        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){unread_counter_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}

function unread_counter_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("badge_counter").innerHTML = text;
        setTimeout(function(){getUnreadNotificationsCount();},10000);
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);
    }
}

function showSelectedNotification(notifID){
     xmlHttp=GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        alert(notifID);
        //sending selected country to servlet
        var url = "controller_servl?event=SHOWNOTIFICATION&id="+notifID;
        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){notifications_return_after_select(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}

function notifications_return_after_select(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("dropdown_notifications").innerHTML = text;
        getUnreadNotificationsCount();
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}

function redirectTo(type){
    if(type==="famcalevents"){
        getFamilyCalendar();
    }
}