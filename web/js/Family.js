

function Update_profile()
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
        var url = "controller_servl?event=UPDATE_PROFILE&lastName=" + document.getElementById("lastnameeditdirector").value +
                "&firstName=" + document.getElementById("firstnameeditdirector").value +
                "&email=" + document.getElementById("emaileditdirector").value +
                "&birthdate=" + document.getElementById("birtheditdirector").value +
                "&town=" + document.getElementById("towndirector").value;
        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function () {
            profile_return(xmlHttp)
        };

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
        xmlHttp.onreadystatechange = function () {
            profile_return(xmlHttp)
        };

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }

}

function initFullFormAjaxUploadMemberProfile(username) {

    var last_mem = document.getElementById("lastnameeditmember").value;

    var first_mem = document.getElementById("firstnameeditmember").value;

    var mail_mem = document.getElementById("emaileditmember").value;

    var birth_mem = document.getElementById("birtheditmember").value;

    var success = false;

    success = (date_regex_validation(birth_mem) && notnull_validation(last_mem) && notnull_validation(first_mem) && email_val(mail_mem));

    if (success) {

        document.getElementById("memsaveup").setAttribute("data-dismiss", "modal");

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
        sendXHRequest2(formData, action, xhr);

        //document.getElementById('img'+username).src="img/Calendar.png";
        alert('apodw');
        //document.getElementById('img' + username).removeAttribute("src");
        // Avoid normal form submission
    } else {

        document.getElementById("memsaveup").removeAttribute("data-dismiss");


        document.getElementById("suc_todo_mes_valid_FamMem").style.display = "block";
        document.getElementById("suc_todo_mes_valid_FamMem").setAttribute("class", "alert alert-danger pull-left alert_messa");

        alert(document.getElementById("suc_todo_mes_valid_FamMem").innerHTML);
        document.getElementById("suc_todo_mes_valid_FamMem").innerHTML = "Fill The Red Required Inputs";


        alert("poulos");


    }





}

// Once the FormData instance is ready and we know
// where to send the data, the code is the same
// for both variants of this technique
function sendXHRequest2(formData, uri, xhr) {
    // Get an XMLHttpRequest instance

    // Set up events
    alert();
    xhr.onreadystatechange = function () {
        onreadystatechangeHandlerMemPr(xhr)
    };
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


function initFullFormAjaxUploadDirectorProfile(username) {

    var lastn_dir = document.getElementById("lastnameeditdirector").value;

    var first_dir = document.getElementById("firstnameeditdirector").value;

    var mail_di = document.getElementById("emaileditdirector").value;

    var birth_di = document.getElementById("birtheditdirector").value




    var success = false;

    success = (date_regex_validation(birth_di) && notnull_validation(lastn_dir) && notnull_validation(first_dir) && email_val(mail_di));

    if (success) {


        document.getElementById("edit_dir_button").setAttribute("data-dismiss", "modal");

        var xhr = new XMLHttpRequest();
        var form = document.getElementById('upload_form2');

        // FormData receives the whole form
        var formData = new FormData(form);

        // We send the data where the form wanted
        var action = "controller_servl?event=UPDATE_PROFILE&lastName=" + document.getElementById("lastnameeditdirector").value +
                "&firstName=" + document.getElementById("firstnameeditdirector").value +
                "&email=" + document.getElementById("emaileditdirector").value +
                "&birthdate=" + document.getElementById("birtheditdirector").value +
                "&town=" + document.getElementById("towndirector").value;

        //creating callback method.here countrychanged is callback method
        // Code common to both variants

        sendXHRequest24(formData, action, xhr);

        alert(username);


    } else {


        document.getElementById("edit_dir_button").removeAttribute("data-dismiss");
        
        
        
        alert("poulos");
        
        
        document.getElementById("suc_todo_mes_valid_FamDr").style.display = "block";
        document.getElementById("suc_todo_mes_valid_FamDr").setAttribute("class", "alert alert-danger pull-left alert_messa");

        alert(document.getElementById("suc_todo_mes_valid_FamDr").innerHTML);
        document.getElementById("suc_todo_mes_valid_FamDr").innerHTML = "Fill The Red Required Inputs";

    }



    //document.getElementById('imgs' + username).removeAttribute("src");
    //document.getElementById('img'+username).src="img/Calendar.png";


    // Avoid normal form submission


}

function sendXHRequest24(formData, uri, xhr) {
    // Get an XMLHttpRequest instance

    // Set up events
    alert();
    xhr.onreadystatechange = function () {
        onreadystatechangeHandlerMemPr4(xhr)
    };
    //xhr.onreadystatechange = wallPost_return
    // Set up request
    xhr.open('POST', uri, true);
    // Fire!
    xhr.send(formData);
}



// Handle the response from the server
function onreadystatechangeHandlerMemPr4(xhr) {

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

        xmlHttp.onreadystatechange = function () {
            familyreturn(xmlHttp)
        };
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
        xmlHttp.onreadystatechange = function () {
            member_return(xmlHttp)
        };

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
        xmlHttp.onreadystatechange = function () {
            editmember_return(xmlHttp)
        };

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
        xmlHttp.onreadystatechange = function () {
            load_member_return(xmlHttp)
        };

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
        xmlHttp.onreadystatechange = function () {
            profile_return(xmlHttp)
        };

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}

function AddMember() {

    alert();
    var lastn = document.getElementById("lastnameaddmember").value;
    alert(lastn);
    var firstn = document.getElementById("firstnameaddmember").value;
    alert(firstn);
    var bir_dat = document.getElementById("birthaddmember").value;
    alert(bir_dat);
    var success = false;

    success = (notnull_validation(lastn) && notnull_validation(firstn) && date_regex_validation(bir_dat));


    if (success) {

        document.getElementById("addmembut").setAttribute("data-dismiss", "modal");

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
            xmlHttp.onreadystatechange = function () {
                profile_return(xmlHttp)
            };
            xmlHttp.open("GET", url, true)
            xmlHttp.send(null)
        }

    } else {



        document.getElementById("addmembut").removeAttribute("data-dismiss");

        alert("poulos");


        document.getElementById("suc_todo_mes_valid_Fam").style.display = "block";
        document.getElementById("suc_todo_mes_valid_Fam").setAttribute("class", "alert alert-danger pull-left alert_messa");

        alert(document.getElementById("suc_todo_mes_valid_Fam").innerHTML);
        document.getElementById("suc_todo_mes_valid_Fam").innerHTML = "Fill The Red Required Inputs";





    }


}


function getDuplicateEmailsMyFam(registeremail)
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
        var url = "controller_servl?event=AJAX&Addregisteremail=" + registeremail;
        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = emailReturnfam

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }

}
function emailReturnfam()
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

        var json = JSON.parse(xmlHttp.responseText);



        //displaying response in select box by using that id
        document.getElementById("famemailret").innerHTML = json.message;
        document.getElementById("addmembut").setAttribute("class", json.disabled);
    }
}