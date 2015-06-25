function getWallPost()
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
        var url = "controller_servl?event=WALLPOST";

        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){wallPost_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }

}
function wallPost_return(xmlHttp)
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

function Post_Wall(form) {
    xmlHttp = new XMLHttpRequest();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        alert()
        var formData = new FormData(form);

        var files = document.querySelector('input[type="file"]').files;
        //alert(files)

        for (var i = 0, file; file = files[i]; ++i) {
            formData.append(file.name, file);
            alert("dsdsd=="+file.name)
            alert(form)
        }
        //alert(formData.valueOf("file"))
        //sending selected country to servlet
        var url = "post_servl";
        
        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){wallPost_return(xmlHttp)};
        
        xmlHttp.open("GET", url, true)
        xmlHttp.setRequestHeader("Content-Type", "multipart/form-data")
        xmlHttp.send(formData)
        
    }
}

function deletePost(id){
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        //sending selected country to servlet
        var url = "controller_servl?event=WALLPOSTDETAILS&id="+id;

        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){modalPost_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}


function modalPost_return(xmlHttp)
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);


        var text = xmlHttp.responseText;

        document.getElementById("deletepost_modal").innerHTML = text;
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}

function deletePost2(id){
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        alert(id);
        //sending selected country to servlet
        var url = "controller_servl?event=DELETEWALLPOST&id="+id;

        //creating callback method.here countrychanged is callback method
        xmlHttp.onreadystatechange = function(){wallPost_return(xmlHttp)};

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}