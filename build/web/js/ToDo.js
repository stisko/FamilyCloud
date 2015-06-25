function getToDoList(){
    
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return 
    }
    else
    {
        //
        
        //sending selected country to servlet
        var url = "controller_servl?event=TODO";
        xmlHttp.onreadystatechange = todolist_return

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}

function insertToDoItem(){
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        var InvForm = document.forms.formaddToDo;
        var SelBranchVal = "";
        var x = 0;
        var count=0;
        
        var temp=InvForm.assignedToDo.length;
        
        for (x=0;x<InvForm.assignedToDo.length;x++)
         {
            if(InvForm.assignedToDo[x].selected )
            {
                if(temp==1){
                    
                    SelBranchVal = InvForm.assignedToDo[x].value;
                }else{
                    
                    count=count+1;
                    
                    if(count==1){
                        SelBranchVal = InvForm.assignedToDo[x].value + SelBranchVal ;
                        
                    }else{
                        
                        SelBranchVal = InvForm.assignedToDo[x].value + "," + SelBranchVal ;
                        
                    }
                    
                    
                }
               
             //alert(InvForm.kb[x].value);
             
            }
         }
        
        //sending selected country to servlet
        var radios = document.getElementsByName('statusradio');
        
        for(var i=0, length=radios.length;i<length; i++){
            if(radios[i].checked){
                status=radios[i].value;
                break;
            }
        }

        var url = "controller_servl?event=INSERTTODO&title="+document.getElementById("titleaddtask").value
                +"&status="+status
                +"&assignedTo="+SelBranchVal
                +"&dueDate="+document.getElementById("duedateaddtask").value;
                
        xmlHttp.onreadystatechange = todolist_return

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }    
}


function todolist_return()
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

function editToDoItem(itemID){
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //alert(itemID)
        //sending selected country to servlet
        var url = "controller_servl?event=TODODETAILS&itemID="+itemID+"&tag=edit";
        xmlHttp.onreadystatechange = edit_details_return

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}

function edit_details_return()
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("editToDo_modal_body").innerHTML = text;
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}

function Save_changes_todo(itemID){
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        var InvForm = document.forms.edittodoform;
        var SelBranchVal = "";
        var x = 0;
        var count=0;
        
        var temp=InvForm.assignedTo_edit.length;
        
        for (x=0;x<InvForm.assignedTo_edit.length;x++)
         {
            if(InvForm.assignedTo_edit[x].selected )
            {
                if(temp==1){
                    
                    SelBranchVal = InvForm.assignedTo_edit[x].value;
                }else{
                    
                    count=count+1;
                    
                    if(count==1){
                        SelBranchVal = InvForm.assignedTo_edit[x].value + SelBranchVal ;
                        
                    }else{
                        
                        SelBranchVal = InvForm.assignedTo_edit[x].value + "," + SelBranchVal ;
                        
                    }
                    
                    
                }
               
             //alert(InvForm.kb[x].value);
             
            }
         }
         
        var radios = document.getElementsByName('statusradio');
        
        for(var i=0, length=radios.length;i<length; i++){
            if(radios[i].checked){
                status=radios[i].value;
                break;
            }
        }
        //alert(itemID)
        //sending selected country to servlet
        var url = "controller_servl?event=UPDATETODO&itemID="+itemID+
                "&title="+document.getElementById("titleedittask").value+
                "&dueDate="+document.getElementById("duedateedittask").value+
                "&assignedTo="+SelBranchVal+
                "&status="+status;
        xmlHttp.onreadystatechange = todolist_return

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}

function deleteToDoItem(itemID){
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //alert(itemID)
        //sending selected country to servlet
        var url = "controller_servl?event=TODODETAILS&itemID="+itemID+"&tag=delete";
        xmlHttp.onreadystatechange = delete_details_return

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}

function delete_details_return()
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("delete_to_do_modalBody").innerHTML = text;
        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }

}

function deleteToDoItem_save(itemID){
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //alert(itemID)
        //sending selected country to servlet
        var url = "controller_servl?event=DELETETODO&itemID="+itemID;
        xmlHttp.onreadystatechange = todolist_return

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}


function sorttableshop(sortedTable){
    
    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        var url = "controller_servl?event=TODOSORT&sortedTag="+sortedTable;
        xmlHttp.onreadystatechange = sortedfuncTable;

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
        
        
    }
    
}


function sortedfuncTable(){
    
    
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xmlHttp.responseText;

        document.getElementById("changedTable").innerHTML = text;
      

    }
    
    
    
}


var multiSelect = {};
    function antegeia2() {      
      var s = document.getElementsByTagName('select');
      for (var i = 0; i < s.length; i++) {
        if (s[i].multiple) {
          var n = s[i].name;
          multiSelect[n] = [];
          for (var j = 0; j < s[i].options.length; j++) {
            multiSelect[n][j] = s[i].options[j].selected;
          }
          s[i].onchange = changeMultiSelect2;
        }
      }
    }
    function changeMultiSelect2() {
      var n = this.name;
      for (var i=0; i < this.options.length; i++) {
        if (this.options[i].selected) {
          multiSelect[n][i] = !multiSelect[n][i];
        }
        this.options[i].selected = multiSelect[n][i];
      }
    }
    window.onload = init;