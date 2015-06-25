
    function initFullFormAjaxUploadChat(username) {
        alert(username);
        var xhr = new XMLHttpRequest();
        var form = document.getElementById("formmessage"+username);

        // FormData receives the whole form
        var formData = new FormData(form);
        
        // We send the data where the form wanted
        var action = 'controller_servl?event=ADDMESSAGE&username='+username;
        // Code common to both variants
        sendXHRequestCHAT(formData, action,xhr,username);
        // Avoid normal form submission


    }
    
// Once the FormData instance is ready and we know
// where to send the data, the code is the same
// for both variants of this technique
    function sendXHRequestCHAT(formData, uri,xhr,username) {
        // Get an XMLHttpRequest instance

        // Set up events

        xhr.onreadystatechange = onreadystatechangeHandlerCHAT(xhr, username);
        //xhr.onreadystatechange = wallPost_return
        // Set up request
        xhr.open('POST', uri, true);
        // Fire!
        xhr.send(formData);
    }



// Handle the response from the server
    function onreadystatechangeHandlerCHAT(xhr, username) {
        //alert("prwti")
        if (xhr.readyState == 4 || xhr.readyState == "complete")
        {
            //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);
            var text = xhr.responseText;
            //document.getElementById("chat_body"+username).innerHTML = text;
        }
    }
