

function Update_profile()
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
        var url = "controller_servl?event=UPDATE_PROFILE&lastName=" + document.getElementById("lastnameeditdirector").value +
                "&firstName=" + document.getElementById("firstnameeditdirector").value +
                "&email=" + document.getElementById("emaileditdirector").value +
                "&birthdate=" + document.getElementById("birtheditdirector").value +
                "&town=" + document.getElementById("towndirector").value;
        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){profile_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }

}
function profile_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("newpage").innerHTML = text;
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}

function UpdateMemberProfile(username)
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
alert();
        var url = "controller_servl?event=UPDATE_MEMBER_PROFILE&username=" + username + "&lastName=" + document.getElementById("lastnameeditmember").value +
                "&firstName=" + document.getElementById("firstnameeditmember").value +
                "&email=" + document.getElementById("emaileditmember").value +
                "&birthdate=" + document.getElementById("birtheditmember").value +
                "&town=" + document.getElementById("towneditmember").value;

        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){profile_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }

}

function initFullFormAjaxUploadMemberProfile(username) {
        
        var xhr = new XMLHttpRequest();
        var form = document.getElementById('upload_form');

        // FormData receives the whole form
        var formData = new FormData(form);
        
        // We send the data where the form wanted
        var action = "controller_servl?event=UPDATE_MEMBER_PROFILE&username=" + username + "&lastName=" + document.getElementById("lastnameeditmember").value +
                "&firstName=" + document.getElementById("firstnameeditmember").value +
                "&email=" + document.getElementById("emaileditmember").value +
                "&birthdate=" + document.getElementById("birtheditmember").value +
                "&town=" + document.getElementById("towneditmember").value;

        //creating callback method.here countrychanged is callback method
        // Code common to both variants
        sendXHRequest2(formData, action,xhr);
        // Avoid normal form submission


    }
    
// Once the FormData instance is ready and we know
// where to send the data, the code is the same
// for both variants of this technique
    function sendXHRequest2(formData, uri,xhr) {
        // Get an XMLHttpRequest instance

        // Set up events
alert();
        xhr.onreadystatechange = function(){onreadystatechangeHandlerMemPr(xhr)};
        //xhr.onreadystatechange = wallPost_return
        // Set up request
        xhr.open('POST', uri, true);
        // Fire!
        xhr.send(formData);
    }



// Handle the response from the server
    function onreadystatechangeHandlerMemPr(xhr) {
        
        if (xhr.readyState == 4 || xhr.readyState == "complete")
        {
            //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);
            var text = xhr.responseText;
            
            document.getElementById("newpage").innerHTML = text;
        }
    }




function getMyFamily()
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
        var url = "controller_servl?event=MYFAMILY"

        //creating callback method.here countrychanged is callback method
        
xmlHttp.onreadystatechange = function(){familyreturn(xmlHttp)};
        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }

}

function familyreturn(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("newpage").innerHTML = text;

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}



function viewMemberProfile(username) {
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //alert(username)
        //sending selected country to servlet
        var url = "controller_servl?event=MEMBER_PROFILE&username=" + username + "&tag=view";

        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){member_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}

function member_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

        //var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("modal_body").innerHTML = text;
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}


function editMemberProfile(username) {
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //alert(username)
        //sending selected country to servlet
        var url = "controller_servl?event=MEMBER_PROFILE&username=" + username + "&tag=go";

        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange =function(){ editmember_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}

function editmember_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

        //var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("edit_modal_body").innerHTML = text;
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}

function load_member(username) {
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //alert(username)
        //sending selected country to servlet
        var url = "controller_servl?event=MEMBER_PROFILE&username=" + username + "&tag=delete";


        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){load_member_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}


function load_member_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

        //var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("modal_body_delete").innerHTML = text;
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}

function delete_member(username) {
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //alert(username)
        //sending selected country to servlet
        var url = "controller_servl?event=DELETE_MEMBER&username=" + username;


        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){profile_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}

function AddMember() {
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        var url = "controller_servl?event=INSERT_INACTIVE_USER&lastName=" + document.getElementById("lastnameaddmember").value +
                "&firstName=" + document.getElementById("firstnameaddmember").value +
                "&email=" + document.getElementById("emailaddmember").value +
                "&birthdate=" + document.getElementById("birthaddmember").value;
        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){profile_return(xmlHttp)};
        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}