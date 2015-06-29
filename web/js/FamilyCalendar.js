function getFamilyCalendar()
{

    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=FAMCAL";
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            calendar_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function addEvent() {
    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        var InvForm = document.forms.addevfamform;
        var SelBranchVal = "";
        var x = 0;
        var count=0;
        
        var temp=InvForm.addfamilyparticipating_members.length;
        for (x=0;x<InvForm.addfamilyparticipating_members.length;x++)
         {
            if(InvForm.addfamilyparticipating_members[x].selected )
            {
                if(temp==1){
                    
                    SelBranchVal = InvForm.addfamilyparticipating_members[x].value;
                }else{
                    
                    count=count+1;
                    
                    if(count==1){
                        SelBranchVal = InvForm.addfamilyparticipating_members[x].value + SelBranchVal ;
                        
                    }else{
                        
                        SelBranchVal = InvForm.addfamilyparticipating_members[x].value + "," + SelBranchVal ;
                        
                    }
                    
                    
                }
               
             //alert(InvForm.kb[x].value);
             
            }
         }
        
        
        
        var radios = document.getElementsByName('optradio');

        for (var i = 0, length = radios.length; i < length; i++) {
            if (radios[i].checked) {
                status = radios[i].value;
                break;
            }
        }
        
        
        var checkbox_repeat = document.getElementById("addfamilycheckbox_repeat").checked;

        //sending selected country to servlet
        var url = "controller_servl?event=INSERTFAMCALEVENT&title=" + document.getElementById("addfamilyevent-title").value +
                "&start=" + document.getElementById("datepickeraddeventfamilystart").value +
                "&end=" + document.getElementById("datepickeraddeventfamilyend").value +
                "&category=" + document.getElementById("addfamilyevent_categories").value +
                "&visibility=" + status+
                "&location=" + document.getElementById("addfamilyevent_location").value +
                "&part_members=" + SelBranchVal +
                "&description=" + document.getElementById("addfamilyevent_desc").value +
                "&repeat-time=" + document.getElementById("addevent_repeat_time").value +
                "&repeat_every=" + document.getElementById("addfamilyrepeatevery").value +
                "&starts_at=" + document.getElementById("addfamilyrepeatstart").value +
                "&expiration_date=" + document.getElementById("addfamilyexpiresat").value +
                "&notification_time=" + document.getElementById("addfamilyevent_notification_time").value +
                "&notification_date=" + document.getElementById("addfamilyevent_notification_period").value +
                "&status=" + checkbox_repeat;

        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            calendar_return(xhr);
        };

        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function viewFamCalbyMember(username) {
    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {




        //sending selected country to servlet
        var url = "controller_servl?event=FAMCAL";


        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            custom_view_calendar_return(xhr,username);
        };

        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function custom_view_calendar_return(xhr,username)
{

    if (xhr.readyState == 4 || xhr.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xhr.responseText;

        document.getElementById("newpage").innerHTML = text;
        fullFamCal('controller_servl?event=FAMCALEVENTS&username='+username);

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }
}

function calendar_return(xhr)
{

    if (xhr.readyState == 4 || xhr.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xhr.responseText;

        document.getElementById("newpage").innerHTML = text;
        fullFamCal('controller_servl?event=FAMCALEVENTS');

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }
}
function viewEvent(id) {
    xhr = GetXmlHttpObject();

    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=VIEWFAMCALEVENT&id=" + id;
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            modal_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function editEvent(id) {
    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=EDITFAMCALEVENT&id=" + id + "&tag=edit";
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            modal_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function UpdateFamCalEvent(id) {
    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        
        
        var InvForm = document.forms.editevfamform;
        var SelBranchVal = "";
        var x = 0;
        var count=0;
        
        var temp=InvForm.editfamilyparticipating_members.length;
        for (x=0;x<InvForm.editfamilyparticipating_members.length;x++)
         {
            if(InvForm.editfamilyparticipating_members[x].selected )
            {
                if(temp==1){
                    
                    SelBranchVal = InvForm.editfamilyparticipating_members[x].value;
                }else{
                    
                    count=count+1;
                    
                    if(count==1){
                        SelBranchVal = InvForm.editfamilyparticipating_members[x].value + SelBranchVal ;
                        
                    }else{
                        
                        SelBranchVal = InvForm.editfamilyparticipating_members[x].value + "," + SelBranchVal ;
                        
                    }
                    
                    
                }
               
             //alert(InvForm.kb[x].value);
             
            }
         }
        
        
        
        var radios = document.getElementsByName('optradio');

        for (var i = 0, length = radios.length; i < length; i++) {
            if (radios[i].checked) {
                status = radios[i].value;
                break;
            }
        }
        
        var checkbox_repeat = document.getElementById("editfamilycheckbox_repeat").checked;



        //sending selected country to servlet
        var url = "controller_servl?event=UPDATEFAMCALEVENT&id=" + id + "&title=" + document.getElementById("editfamilyevent-title").value +
                "&start=" + document.getElementById("datepickerediteventfamilystart").value +
                "&end=" + document.getElementById("datepickerediteventfamilyend").value +
                "&category=" + document.getElementById("editfamilyevent_categories").value +
                "&visibility=" + status+
                "&location=" + document.getElementById("editfamilyevent_location").value +
                "&part_members=" + SelBranchVal +
                "&description=" + document.getElementById("editfamilyevent_desc").value +
                "&repeat-time=" + document.getElementById("editevent_repeat_time").value +
                "&repeat_every=" + document.getElementById("editfamilyrepeatevery").value +
                "&starts_at=" + document.getElementById("editfamilyrepeatstart").value +
                "&expiration_date=" + document.getElementById("editfamilyexpiresat").value +
                "&notification_time=" + document.getElementById("editfamilyevent_notification_time").value +
                "&notification_date=" + document.getElementById("addfamilyevent_notification_period").value +
                "&status=" + checkbox_repeat;

        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            calendar_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function deleteEvent(id) {
    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=EDITFAMCALEVENT&id=" + id + "&tag=delete";
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            modal_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function deleteEvent2(id) {
    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=DELETEFAMCALEVENT&id=" + id;
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            calendar_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function modal_return(xhr)
{
    if (xhr.readyState == 4 || xhr.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xhr.responseText;

        document.getElementById("famcalendar_modal").innerHTML = text;
        //fullFamCal();

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }
}

function viewFamCalbyCategory(color){
    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=FAMCAL";
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            customCategory_view_calendar_return(xhr,color);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function customCategory_view_calendar_return(xhr, color){
    if (xhr.readyState == 4 || xhr.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xhr.responseText;

        document.getElementById("newpage").innerHTML = text;
        fullFamCal('controller_servl?event=FAMCALEVENTS&category='+color);

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }
}




function fullFamCal(src) {
    //alert('mpike');

    $('#familycalendar').fullCalendar({
        header: {
            center: 'title',
            left: '',
            right: '',
            slotDuration: '01:15:00'
        },
        events: src

        ,
        eventClick: function (calEvent) {
            viewEvent(calEvent.id);
            $('#fullCalModal').modal();
        },
        // put your options and callbacks here
    });

    $('#custom-nextfamilycalendarevent').click(function () {
        $('#familycalendar').fullCalendar('next');
    });

    $('#custom-prevfamilycalendarevent').click(function () {
        $('#familycalendar').fullCalendar('prev');
    });

    $('#custom-currentfamilycalendar').click(function () {
        $('#familycalendar').fullCalendar('today');
    });

    $('#monthlyViewfamilycalendar').click(function () {
        $('#familycalendar').fullCalendar('changeView', 'month');
    });

    $('#weeklyViewfamilycalendar').click(function () {
        $('#familycalendar').fullCalendar('changeView', 'agendaWeek');
    });

    $('#dailyViewfamilycalendar').click(function () {
        $('#familycalendar').fullCalendar('changeView', 'agendaDay');
    });

//    $("#event_delete").on("click", function (ev) {
//        $('#deletemodal').modal('show');
//        ev.preventDefault();
//
//
//    });


//    $("#event_edit").on("click", function (ev) {
//        $('#familyevent_edit_modal').modal('show');
//
//
//
//
//        ev.preventDefault();
//
//
//    });



    $("#addfamilycalendareventbutton").on("click", function (ev) {
        $('#addfamilyeventmodal').modal('show');




        ev.preventDefault();


    });






    $('#addfamilycheckbox_repeat').change(function () {
        $('#addfamilyrepeat').fadeToggle('slow');
    });








}

function toggle_repeat() {

    $('#editfamilyrepeat').fadeToggle('slow');

}
//});




var multiSelect = {};
function antegeia3() {
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
function changeMultiSelect() {
    var n = this.name;
    for (var i = 0; i < this.options.length; i++) {
        if (this.options[i].selected) {
            multiSelect[n][i] = !multiSelect[n][i];
        }
        this.options[i].selected = multiSelect[n][i];
    }
}
window.onload = init;