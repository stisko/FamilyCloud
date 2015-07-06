function getToDoList() {

    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {

        //sending selected country to servlet
        var url = "controller_servl?event=TODO";
        xmlHttp.onreadystatechange = todolist_return

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}

function insertToDoItem() {

    var title = document.forms.formaddToDo.addtodotitle.value;
    var duedate = document.forms.formaddToDo.addtododuedatetask.value;
    var radios = document.getElementsByName('statusradio');


    
    
    
    
    if(notnull_validation(title)){
       
        style_inp("titleaddtask");
        
    }else{
        style_abstract_valid("titleaddtask");
    }
    
    
    if(date_regex_validation(duedate)){
       
        style_inp("duedateaddtask");
        
    }else{
        style_abstract_valid("duedateaddtask");
    }
    
    
    if(radio_validation(radios)){
       
       for (var i = 0, length = radios.length; i < length; i++) {
               
                    radios[i].required=false;
                    
            }
      
        
    }else{
        for (var i = 0, length = radios.length; i < length; i++) {
               
                    radios[i].required=true;
                    
            }
    }


    if (notnull_validation(title) && date_regex_validation(duedate) && radio_validation(radios)) {
        document.getElementById("addtodobutton").setAttribute("data-dismiss", "modal");
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
            var count = 0;

            var temp = InvForm.assignedToDo.length;

            for (x = 0; x < InvForm.assignedToDo.length; x++)
            {
                if (InvForm.assignedToDo[x].selected)
                {
                    if (temp == 1) {

                        SelBranchVal = InvForm.assignedToDo[x].value;
                    } else {

                        count = count + 1;

                        if (count == 1) {
                            SelBranchVal = InvForm.assignedToDo[x].value + SelBranchVal;

                        } else {

                            SelBranchVal = InvForm.assignedToDo[x].value + "," + SelBranchVal;

                        }


                    }

                    //alert(InvForm.kb[x].value);

                }
            }

            //sending selected country to servlet

            for (var i = 0, length = radios.length; i < length; i++) {
                if (radios[i].checked) {
                    status = radios[i].value;
                    break;
                }
            }
            alert(document.getElementById("duedateaddtask").value)
            var url = "controller_servl?event=INSERTTODO&title=" + document.getElementById("titleaddtask").value
                    + "&status=" + status
                    + "&assignedTo=" + SelBranchVal
                    + "&dueDate=" + document.getElementById("duedateaddtask").value;

            xmlHttp.onreadystatechange = todolist_return

            xmlHttp.open("GET", url, true)
            xmlHttp.send(null)
        }
    } else {
        
        
        
        var htmlString = "";  

        htmlString =
                '<div>'+'<div class = "glyphicon glyphicon-remove-circle">'+'</div>'+'  Fill The Red Required Inputs'+'</div>';
        
        alert("MPHKA STO POULO");
        document.getElementById("addtodobutton").removeAttribute("data-dismiss");
        
        document.getElementById("suc_todo_mes_valid").style.display="block";
        document.getElementById("suc_todo_mes_valid").setAttribute("class","alert alert-danger pull-left alert_messa_danger");

        alert(document.getElementById("suc_todo_mes_valid").innerHTML);
        document.getElementById("suc_todo_mes_valid").innerHTML=htmlString;
        
        
        
//        
        
       // alert("poulos");
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

function editToDoItem(itemID) {
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
        var url = "controller_servl?event=TODODETAILS&itemID=" + itemID + "&tag=edit";
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

function Save_changes_todo(itemID) {

    var title = document.forms.edittodoform.titleedittodo.value;
    var duedate = document.forms.edittodoform.duedateedittodo.value;
    var radios = document.getElementsByName('statusradio');
    
    
    if(notnull_validation(title)){
       
        style_inp("titleedittask");
        
    }else{
        style_abstract_valid("titleedittask");
    }
    
    
    if(date_regex_validation(duedate)){
       
        style_inp("duedateedittask");
        
    }else{
        style_abstract_valid("duedateedittask");
    }
    
    
    if(radio_validation(radios)){
       
       for (var i = 0, length = radios.length; i < length; i++) {
               
                    radios[i].required=false;
                    
            }
      
        
    }else{
        for (var i = 0, length = radios.length; i < length; i++) {
               
                    radios[i].required=true;
                    
            }
    }
    
    
    

    if (radio_validation(radios) && notnull_validation(title) && date_regex_validation(duedate)) {
        document.getElementById("edittodobutton").setAttribute("data-dismiss", "modal");
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
            var count = 0;

            var temp = InvForm.assignedTo_edittodo.length;

            for (x = 0; x < InvForm.assignedTo_edittodo.length; x++)
            {
                if (InvForm.assignedTo_edittodo[x].selected)
                {
                    if (temp == 1) {

                        SelBranchVal = InvForm.assignedTo_edittodo[x].value;
                    } else {

                        count = count + 1;

                        if (count == 1) {
                            SelBranchVal = InvForm.assignedTo_edittodo[x].value + SelBranchVal;

                        } else {

                            SelBranchVal = InvForm.assignedTo_edittodo[x].value + "," + SelBranchVal;

                        }


                    }

                    //alert(InvForm.kb[x].value);

                }
            }



            for (var i = 0, length = radios.length; i < length; i++) {
                if (radios[i].checked) {
                    statuss = radios[i].value;
                    break;
                }
            }

            //alert(itemID)
            //sending selected country to servlet
            var url = "controller_servl?event=UPDATETODO&itemID=" + itemID +
                    "&title=" + document.getElementById("titleedittask").value +
                    "&dueDate=" + document.getElementById("duedateedittask").value +
                    "&assignedTo=" + SelBranchVal +
                    "&status=" + statuss;

            xmlHttp.onreadystatechange = todolist_return


            xmlHttp.open("GET", url, true)
            xmlHttp.send(null)
        }
    } else {
        document.getElementById("edittodobutton").removeAttribute("data-dismiss");
        
       var htmlString = "";  

        htmlString =
                '<div>'+'<div class = "glyphicon glyphicon-remove-circle">'+'</div>'+'  Fill The Red Required Inputs'+'</div>';
        
        
        document.getElementById("suc_todo_mes_valid_ed").style.display="block";
        document.getElementById("suc_todo_mes_valid_ed").setAttribute("class","alert alert-danger pull-left alert_messa_danger");

        alert(document.getElementById("suc_todo_mes_valid_ed").innerHTML);
        document.getElementById("suc_todo_mes_valid_ed").innerHTML= htmlString;
        
        
        alert("poulos");
    }
}

function deleteToDoItem(itemID) {
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
        var url = "controller_servl?event=TODODETAILS&itemID=" + itemID + "&tag=delete";
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

function deleteToDoItem_save(itemID) {
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
        var url = "controller_servl?event=DELETETODO&itemID=" + itemID;
        xmlHttp.onreadystatechange = todolist_return

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)
    }
}

var asc_desc = false;

function sorttableshop(sortedTable) {

    xmlHttp = GetXmlHttpObject();
    if (xmlHttp == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {

        asc_desc = !asc_desc;

        // alert(asc_desc);

        var url = "controller_servl?event=TODOSORT&sortedTag=" + sortedTable + "&ssa=" + asc_desc;
        xmlHttp.onreadystatechange = sortedfuncTable;

        xmlHttp.open("GET", url, true)
        xmlHttp.send(null)


    }

}


function sortedfuncTable() {


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
    for (var i = 0; i < this.options.length; i++) {
        if (this.options[i].selected) {
            multiSelect[n][i] = !multiSelect[n][i];
        }
        this.options[i].selected = multiSelect[n][i];
    }
}
window.onload = init;