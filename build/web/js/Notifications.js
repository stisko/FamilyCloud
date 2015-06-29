


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
        var url = "controller_servl?event=NOTIFICATIONS&tag=notifications";
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

function getMessageNotifications()
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
        var url = "controller_servl?event=NOTIFICATIONS&tag=messages";
        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){notificationsMessage_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }

}
function notificationsMessage_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("dropdown_messages").innerHTML = text;
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}

function getUnreadNotificationsCount(){
     xmlHttp1=GetXmlHttpObject();
    if (xmlHttp1 == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        //sending selected country to servlet
        var url = "controller_servl?event=GETUNREADCOUNT&tag=notifications";
        //creating callback method.here countrychanged is callback method
        xmlHttp1.onreadystatechange = function(){unread_counter_return(xmlHttp1)};

        xmlHttp1.open("GET", url, true)
        xmlHttp1.send(null)
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

function getUnreadMessageNotificationsCount(){
     xmlHttp2=GetXmlHttpObject();
    if (xmlHttp2 == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        //sending selected country to servlet
        var url = "controller_servl?event=GETUNREADCOUNT&tag=messages";
        //creating callback method.here countrychanged is callback method
        xmlHttp2.onreadystatechange = function(){unreadMessage_counter_return(xmlHttp2)};

        xmlHttp2.open("GET", url, true)
        xmlHttp2.send(null)
    }
}

function unreadMessage_counter_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;
        
        document.getElementById("badge_counter_msg").innerHTML = text;
        setTimeout(function(){getUnreadMessageNotificationsCount();},10000);
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
    if((type=="famcalevents") || (type=="famcalevents_update") || (type=="famcalevents_delete")){
        alert("FAMCAL");
        getFamilyCalendar();
    }else if((type==="todo_list") || (type==="todo_list_update") || (type==="todo_list_delete")){
        alert("TODO");
        getToDoList();
    }else if((type==="shopping_list") || (type==="shopping_list_update") || (type==="shopping_list_delete")){
        alert("SHOP");
        getShoppingList();
    }else if(type==="wall_post"){
        alert("WALLPOST");
        getWallPost();
    }
}