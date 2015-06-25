




    function initFullFormAjaxUpload() {
        
        var xhr = new XMLHttpRequest();
        var form = document.getElementById('formpost');

        // FormData receives the whole form
        var formData = new FormData(form);
        
        // We send the data where the form wanted
        var action = 'controller_servl?event=INSERTWALLPOST';
        // Code common to both variants
        sendXHRequest(formData, action,xhr);
        // Avoid normal form submission


    }
    
// Once the FormData instance is ready and we know
// where to send the data, the code is the same
// for both variants of this technique
    function sendXHRequest(formData, uri,xhr) {
        // Get an XMLHttpRequest instance

        // Set up events

        xhr.onreadystatechange = function(){onreadystatechangeHandler(xhr)};
        //xhr.onreadystatechange = wallPost_return
        // Set up request
        xhr.open('POST', uri, true);
        // Fire!
        xhr.send(formData);
    }



// Handle the response from the server
    function onreadystatechangeHandler(xhr) {
        //alert("prwti")
        if (xhr.readyState == 4 || xhr.readyState == "complete")
        {
            //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);
            var text = xhr.responseText;
            
            document.getElementById("newpage").innerHTML = text;
        }
    }
