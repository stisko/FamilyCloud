function getPersonalCalendar() {


    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=PERCAL";
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            personalcalendar_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }







}


function personalcalendar_return(xhr) {


    if (xhr.readyState == 4 || xhr.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xhr.responseText;

        document.getElementById("newpage").innerHTML = text;
        fullPerCal('controller_servl?event=PERCALEVENTS');

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }



}


function fullPerCal(src) {


    $('#personalcalendar').fullCalendar({
        header: {
            center: 'title',
            left: '',
            right: '',
            slotDuration: '01:15:00'
        },
        events: src

        ,
        eventClick: function (calEvent) {
            viewEventper(calEvent.id);


            $('#fullCalModalpersonal').modal();
        },
    });



    $('#custom-nextpersonalcalendarevent').click(function () {
        $('#personalcalendar').fullCalendar('next');
    });

    $('#custom-prevpersonalcalendarevent').click(function () {
        $('#personalcalendar').fullCalendar('prev');
    });

    $('#custom-currentpersonalcalendar').click(function () {
        $('#personalcalendar').fullCalendar('today');
    });

    $('#monthlyViewpersonalcalendar').click(function () {
        $('#personalcalendar').fullCalendar('changeView', 'month');
    });

    $('#weeklyViewpersonalcalendar').click(function () {
        $('#personalcalendar').fullCalendar('changeView', 'agendaWeek');
    });

    $('#dailyViewpersonalcalendar').click(function () {
        $('#personalcalendar').fullCalendar('changeView', 'agendaDay');
    });





    $("#addpersonalcalendareventbutton").on("click", function (ev) {
        $('#addpersonalcalendarmodal').modal('show');

        ev.preventDefault();


    });






    $('#addpersonalcheckbox_repeat').change(function () {
        $('#addpersonalrepeat').fadeToggle('slow');
    });






}



function viewEventper(id) {
    xhr = GetXmlHttpObject();

    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=VIEWPERCALEVENT&id=" + id;
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            modal_return_per(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}


function modal_return_per(xhr)
{
    if (xhr.readyState == 4 || xhr.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xhr.responseText;

        document.getElementById("percalendar_modal").innerHTML = text;
        //fullFamCal();

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }
}


function addperEvent() {





    var title = document.forms.addpersonalcalendarform.addpersonalevent_title.value;

    alert(title);
    var dateadd = document.forms.addpersonalcalendarform.datepickeraddeventpersonalstart.value;
    alert(dateadd);
    var dateaddend = document.forms.addpersonalcalendarform.datepickeraddeventpersonalend.value;
    alert(dateaddend);


    var notif_number = document.forms.addpersonalcalendarform.addpersonalevent_notification_time.value;
    alert(notif_number);
    var start_date = document.forms.addpersonalcalendarform.addpersonalrepeatstart.value;
    alert(start_date);
    var end_date = document.forms.addpersonalcalendarform.addpersonalexpiresat.value;
    alert(end_date);
    var rpt_number = document.forms.addpersonalcalendarform.addpersonalrpeatevery.value;
    alert(rpt_number);
    var checkbox_repeat = document.getElementById("addpersonalcheckbox_repeat").checked;
    
    
    
    
    if(notnull_validation(title)){
       
        style_inp("addpersonalevent-title");
        
    }else{
        style_abstract_valid("addpersonalevent-title");
    }
    
    
    if(datetime_regex_validation(dateadd)){
       
        style_inp("datepickeraddeventpersonalstart");
        
    }else{
        style_abstract_valid("datepickeraddeventpersonalstart");
    }
    
    
     if(datetime_regex_validation(dateaddend)){
       
        style_inp("datepickeraddeventpersonalend");
        
    }else{
        style_abstract_valid("datepickeraddeventpersonalend");
    }
    
    
    
    
     if(datetime_regex_validation(start_date)){
       
        style_inp("addpersonalrepeatstart");
        
    }else{
        style_abstract_valid("addpersonalrepeatstart");
    }
    
    
    
    if(positive_number_validation(notif_number)){
       
        style_inp("addpersonalevent_notification_time");
        
    }else{
        style_abstract_valid("addpersonalevent_notification_time");
    }
    
    
    
     if( date_regex_validation(end_date)){
       
        style_inp("addpersonalexpiresat");
        
    }else{
       
        style_abstract_valid("addpersonalexpiresat");
    }
    
    
    
     if(  positive_number_validation(rpt_number)){
       
        style_inp("addpersonalrpeatevery");
        
    }else{
        style_abstract_valid("addpersonalrpeatevery");
    }
    


    var success = false;
    if (checkbox_repeat) {
        success = (notnull_validation(title) && datetime_regex_validation(dateadd) && datetime_regex_validation(dateaddend) && datetime_regex_validation(start_date) && positive_number_validation(notif_number) && datetime_regex_validation(end_date) && positive_number_validation(rpt_number));
    } else {
        success = (notnull_validation(title) && datetime_regex_validation(dateadd) && datetime_regex_validation(dateaddend) && positive_number_validation(notif_number));
    }

    if (success) {


        document.getElementById("addpercalbut").setAttribute("data-dismiss", "modal");


        xhr = GetXmlHttpObject();
        if (xhr == null)
        {
            alert("Browser does not support HTTP Request")
            return
        }
        else
        {




            //sending selected country to servlet
            var url = "controller_servl?event=INSERTPERCALEVENT&title=" + document.getElementById("addpersonalevent-title").value +
                    "&start=" + document.getElementById("datepickeraddeventpersonalstart").value +
                    "&end=" + document.getElementById("datepickeraddeventpersonalend").value +
                    "&category=" + document.getElementById("addpersonalevent_categories").value +
                    "&location=" + document.getElementById("addpersonalevent_location").value +
                    "&description=" + document.getElementById("addpersonalevent_desc").value +
                    "&repeat-time=" + document.getElementById("addeventpersonal_repeat_time").value +
                    "&repeat_every=" + document.getElementById("addpersonalrpeatevery").value +
                    "&starts_at=" + document.getElementById("addpersonalrepeatstart").value +
                    "&expiration_date=" + document.getElementById("addpersonalexpiresat").value +
                    "&notification_time=" + document.getElementById("addpersonalevent_notification_time").value +
                    "&notification_date=" + document.getElementById("addpersonalevent_notification_period").value +
                    "&status=" + checkbox_repeat;

            //creating callback method.here countrychanged is callback method
            xhr.onreadystatechange = function () {
                personalcalendar_return(xhr);
            };

            xhr.open("GET", url, true)
            xhr.send(null)
        }

    } else {

        document.getElementById("addpercalbut").removeAttribute("data-dismiss");
        
        
        var htmlString = "";  

        htmlString =
                '<div>'+'<div class = "glyphicon glyphicon-remove-circle">'+'</div>'+'  Fill The Red Required Inputs'+'</div>';

        document.getElementById("suc_todo_mes_valid_P").style.display = "block";
        document.getElementById("suc_todo_mes_valid_P").setAttribute("class", "alert alert-danger pull-left alert_messa_danger");

        alert(document.getElementById("suc_todo_mes_valid_P").innerHTML);
        document.getElementById("suc_todo_mes_valid_P").innerHTML = htmlString;


        alert("poulos");


    }






}


function editPersonalEvent(id) {

    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=EDITPERCALEVENT&id=" + id + "&tag=edit";
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            modal_return_per(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }




}
function deletePersonalEvent(id) {


    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=EDITPERCALEVENT&id=" + id + "&tag=delete";
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            modal_return_per(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }



}

function deletedeletePersonalEvent(id) {


    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=DELETEPERCALEVENT&id=" + id;
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            personalcalendar_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }

}


function UpdatePerCalEvent(id) {

    var title = document.forms.editpersonalcalendarform.editpersonalevent_title.value;

    alert(title);
    var dateadd = document.forms.editpersonalcalendarform.datepickerediteventpersonalstart.value;
    alert(dateadd);
    var dateaddend = document.forms.editpersonalcalendarform.datepickerediteventpersonalend.value;
    alert(dateaddend);


    var notif_number = document.forms.editpersonalcalendarform.editpersonalevent_notification_time.value;
    alert(notif_number);
    var start_date = document.forms.editpersonalcalendarform.editpersonalrepeatstart.value;
    alert(start_date);
    var end_date = document.forms.editpersonalcalendarform.editpersonalexpiresat.value;
    alert(end_date);
    var rpt_number = document.forms.editpersonalcalendarform.editpersonalrpeatevery.value;
    alert(rpt_number);
    var checkbox_repeat = document.getElementById("editpersonalcheckbox_repeat").checked;
    
    
    
    if(notnull_validation(title)){
       
        style_inp("editpersonalevent-title");
        
    }else{
        style_abstract_valid("editpersonalevent-title");
    }
    
    
    if(datetime_regex_validation(dateadd)){
       
        style_inp("datepickerediteventpersonalstart");
        
    }else{
        style_abstract_valid("datepickerediteventpersonalstart");
    }
    
    
     if(datetime_regex_validation(dateaddend)){
       
        style_inp("datepickerediteventpersonalend");
        
    }else{
        style_abstract_valid("datepickerediteventpersonalend");
    }
    
    
    
    
     if(datetime_regex_validation(start_date)){
       
        style_inp("editpersonalrepeatstart");
        
    }else{
        style_abstract_valid("editpersonalrepeatstart");
    }
    
    
    
    if(positive_number_validation(notif_number)){
       
        style_inp("editpersonalevent_notification_time");
        
    }else{
        style_abstract_valid("editpersonalevent_notification_time");
    }
    
    
    
     if( datetime_regex_validation(end_date)){
       
        style_inp("editpersonalexpiresat");
        
    }else{
       
        style_abstract_valid("editpersonalexpiresat");
    }
    
    
    
     if(  positive_number_validation(rpt_number)){
       
        style_inp("editpersonalrpeatevery");
        
    }else{
        style_abstract_valid("editpersonalrpeatevery");
    }


    var success = false;
    if (checkbox_repeat) {
        success = (notnull_validation(title) && datetime_regex_validation(dateadd) && datetime_regex_validation(dateaddend) && datetime_regex_validation(start_date) && positive_number_validation(notif_number) && datetime_regex_validation(end_date) && positive_number_validation(rpt_number));
    } else {
        success = (notnull_validation(title) && datetime_regex_validation(dateadd) && datetime_regex_validation(dateaddend) && positive_number_validation(notif_number));
    }

    if (success) {


        document.getElementById("editsaveupdatebut").setAttribute("data-dismiss", "modal");

        xhr = GetXmlHttpObject();
        if (xhr == null)
        {
            alert("Browser does not support HTTP Request")
            return
        }
        else
        {




            //sending selected country to servlet
            var url = "controller_servl?event=UPDATEPERCALEVENT&id=" + id + "&title=" + document.getElementById("editpersonalevent-title").value +
                    "&start=" + document.getElementById("datepickerediteventpersonalstart").value +
                    "&end=" + document.getElementById("datepickerediteventpersonalend").value +
                    "&category=" + document.getElementById("editpersonalevent_categories").value +
                    "&location=" + document.getElementById("editpersonalevent_location").value +
                    "&description=" + document.getElementById("editpersonalevent_desc").value +
                    "&repeat-time=" + document.getElementById("editeventpersonal_repeat_time").value +
                    "&repeat_every=" + document.getElementById("editpersonalrpeatevery").value +
                    "&starts_at=" + document.getElementById("editpersonalrepeatstart").value +
                    "&expiration_date=" + document.getElementById("editpersonalexpiresat").value +
                    "&notification_time=" + document.getElementById("editpersonalevent_notification_time").value +
                    "&notification_date=" + document.getElementById("editpersonalevent_notification_period").value +
                    "&status=" + checkbox_repeat;

            //creating callback method.here countrychanged is callback method
            xhr.onreadystatechange = function () {
                personalcalendar_return(xhr);
            };

            xhr.open("GET", url, true)
            xhr.send(null)
        }

    } else {


        document.getElementById("editsaveupdatebut").removeAttribute("data-dismiss");
        
        var htmlString = "";  

        htmlString =
                '<div>'+'<div class = "glyphicon glyphicon-remove-circle">'+'</div>'+'  Fill The Red Required Inputs'+'</div>';



        document.getElementById("suc_todo_mes_valid_Ped").style.display = "block";
        document.getElementById("suc_todo_mes_valid_Ped").setAttribute("class", "alert alert-danger pull-left alert_messa_danger");

        alert(document.getElementById("suc_todo_mes_valid_Ped").innerHTML);
        document.getElementById("suc_todo_mes_valid_Ped").innerHTML = htmlString;
        alert("poulos");

    }









}

function toggle_repeat2() {

    $('#editpersonalrepeat').fadeToggle('slow');



}


function importfrom() {



    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=PERIMPORT";
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            importfrom_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }


}



function importfrom_return(xhr) {


    if (xhr.readyState == 4 || xhr.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xhr.responseText;

        document.getElementById("imp_cale_per").innerHTML = text;
        //impevents('controller_servl?event=PERIMPORTEVENTS');
        impevents('controller_servl?event=FAMCALEVENTS');

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }



}



function impevents(src) {

    alert("mphka3");


    $("#calendar_imp").fullCalendar({
        header: {
            left: 'prev,today,next',
            center: 'title',
            right: 'month,agendaWeek,agendaDay',
            slotDuration: '01:15:00'
                
        },
        
//        defaultView: 'month',
        
        events: src
        ,

        
        eventClick: function (calEvent) {

            choosedevent(calEvent.id)


        },
    });
   
}


function choosedevent(id) {


    //document.getElementById("well_eventsImp").innerHTML = "Pick Events From Checkbox To Import";

    alert("MPHKA");

    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=PERIMPORTCHECKED&id=" + id;
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            wellevents(xhr, id);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}


function wellevents(xhr, id) {



    if (xhr.readyState == 4 || xhr.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);
//        var labels = document.getElementsByTagName('work_checked');
//
//        for(var i = 0; i < labels.length; i++)
//        {
//                
//                alert(labels[i].innerHTML);
//        }



        var text = xhr.responseText;

        var checkboxes = document.getElementsByName("work_checked");
        var flag = true;
        for (var i = 0, length = checkboxes.length; i < length; i++) {
            if (checkboxes[i].value == id) {
                flag = false;
                break;
            }
        }
        if (flag) {
            var newElement = document.createElement('div');
            newElement.innerHTML = text;
            document.getElementById("well_eventsImp").appendChild(newElement);
        }


        // old_html = document.getElementById("imp_cale_per").innerHTML;
        // document.getElementById("imp_cale_per").innerHTML = old_html+text;


        //   document.getElementById("well_events").innerHTML = text;
        //  impevents('controller_servl?event=PERIMPORTEVENTS');

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }



}


function sendchecked() {



    var SelBranchVal = "";

    alert("GAMW TO XRISTO SOU");

    var inputs = document.getElementsByName("work_checked");

    var temp = inputs.length;

    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].type == "checkbox") {
            if (inputs[i].checked) {

                alert("CHECKED AND VALUE");
                
                alert(inputs[i].value);

            SelBranchVal = inputs[i].value + "," + SelBranchVal;



            }
        }
    }


    // alert(SelBranchVal);

    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=PERIMPORTCHECKEDINSERTED&idpar=" + SelBranchVal;
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            personalcalendar_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }




}

function EnableImportBut() {



    var inputs = document.getElementsByName("work_checked");

    var sbmt = document.getElementById("importcheck");

    var oo = false;
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].type == "checkbox") {
            if (inputs[i].checked) {

                oo = true;
            }

        }

    }

    if (oo) {


        sbmt.setAttribute("class", "btn btn-primary");


    }
    else {


        sbmt.setAttribute("class", "btn btn-primary disabled");

    }


}


function viewPerCalbyCategory(color){
    
    
    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        alert(color);
        //sending selected country to servlet
        var url = "controller_servl?event=PERCAL";
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            customPerCategory_view_calendar_return(xhr, color);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
    
    
}


function customPerCategory_view_calendar_return(xhr, color) {
    if (xhr.readyState == 4 || xhr.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xhr.responseText;

        document.getElementById("newpage").innerHTML = text;
        fullPerCal('controller_servl?event=PERCALEVENTS&category=' + color);

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }
}