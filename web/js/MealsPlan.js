function getMealsPlan() {
    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=MEALSPLAN";
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            meals_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function UpdateMealsPlanEvent(id) {

    var title = document.forms.editmeal_form.editmealtitle.value;
    var dateadd = document.forms.editmeal_form.editmealdatefrom.value;
    var notif_number = document.forms.editmeal_form.editmealnotification_time.value;
    var start_date = document.forms.editmeal_form.editmealstartsat.value;
    var end_date = document.forms.editmeal_form.editmealexpiresat.value;
    var rpt_number = document.forms.editmeal_form.editmealrepeatevery.value;
    var checkbox_repeat = document.getElementById("editmealcheckbox_repeat").checked;

    var success = false;
    if (checkbox_repeat) {
        success = (notnull_validation(title) && date_regex_validation(dateadd) && date_regex_validation(start_date) && positive_number_validation(notif_number) && date_regex_validation(end_date) && positive_number_validation(rpt_number));
    } else {
        success = (notnull_validation(title) && date_regex_validation(dateadd) && positive_number_validation(notif_number));
    }

    if (success) {

        document.getElementById("editsavemealbutton").setAttribute("data-dismiss", "modal");

        xhr = GetXmlHttpObject();

        if (xhr == null)
        {
            alert("Browser does not support HTTP Request")
            return
        }
        else
        {

            var radios = document.getElementsByName('optradio');

            for (var i = 0, length = radios.length; i < length; i++) {
                if (radios[i].checked) {
                    status = radios[i].value;
                    break;
                }
            }

            

            //sending selected country to servlet
            var url = "controller_servl?event=UPDATEMEAL&title=" + document.getElementById("editmealtitle").value +
                    "&location=" + document.getElementById("editmeallocation").value +
                    "&start=" + document.getElementById("editmealdatefrom").value +
                    "&meal_type=" + status +
                    "&notification_time=" + document.getElementById("editmealnotification_time").value +
                    "&notification_date=" + document.getElementById("editmealnotification_period").value +
                    "&description=" + document.getElementById("editmealdesc").value +
                    "&repeat_time=" + document.getElementById("editmeal_repeat_time").value +
                    "&repeat_every=" + document.getElementById("editmealrepeatevery").value +
                    "&starts_at=" + document.getElementById("editmealstartsat").value +
                    "&expiration_date=" + document.getElementById("editmealexpiresat").value +
                    "&status=" + checkbox_repeat +
                    "&id=" + id;
            //creating callback method.here countrychanged is callback method
            xhr.onreadystatechange = function () {
                meals_return(xhr);
            };
            xhr.open("GET", url, true)
            xhr.send(null)
        }



    } else {

        document.getElementById("editsavemealbutton").removeAttribute("data-dismiss");
        
        
        document.getElementById("suc_todo_mes_valid_Med").style.display="block";
        document.getElementById("suc_todo_mes_valid_Med").setAttribute("class","alert alert-danger pull-left alert_messa");

        alert(document.getElementById("suc_todo_mes_valid_Med").innerHTML);
        document.getElementById("suc_todo_mes_valid_Med").innerHTML="Fill The Red Required Inputs";
        alert("poulos");

    }



}



function deleteMealsEvent(id) {
    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=EDITMEAL&id=" + id + "&tag=delete";
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            Mealmodal_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function deleteMealsEvent2(id) {
    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=DELETEMEALSPLANEVENT&id=" + id;
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            meals_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function viewMealsEvent(id) {
    xhr = GetXmlHttpObject();

    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {
        //sending selected country to servlet
        var url = "controller_servl?event=VIEWMEAL&id=" + id;
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            Mealmodal_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function editMealsEvent(id) {
    xhr = GetXmlHttpObject();

    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {



        //sending selected country to servlet
        var url = "controller_servl?event=EDITMEAL&id=" + id;
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            Mealmodal_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function Mealmodal_return(xhr)
{
    if (xhr.readyState == 4 || xhr.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xhr.responseText;

        document.getElementById("meal_modal").innerHTML = text;
        //fullFamCal();

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }
}

function meals_return(xhr)
{

    if (xhr.readyState == 4 || xhr.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xhr.responseText;

        document.getElementById("newpage").innerHTML = text;
        MealsPlan('controller_servl?event=MEALSPLANEVENTS');

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }
}


function viewMealsPlanByType(type) {
    xhr = GetXmlHttpObject();
    if (xhr == null)
    {
        alert("Browser does not support HTTP Request")
        return
    }
    else
    {




        //sending selected country to servlet
        var url = "controller_servl?event=MEALSPLAN";


        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            custom_view_meals_return(xhr, type);
        };

        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function custom_view_meals_return(xhr, type)
{

    if (xhr.readyState == 4 || xhr.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xhr.responseText;

        document.getElementById("newpage").innerHTML = text;
        MealsPlan('controller_servl?event=MEALSPLANEVENTS&type=' + type);

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }
}

function addMeal() {
    alert();
    var title = document.forms.addmealform.titleaddmeal.value;
    var dateadd = document.forms.addmealform.dateaddmeal.value;
    var notif_number = document.forms.addmealform.notificationnumberaddmeal.value;
    var start_date = document.forms.addmealform.startdateaddmeal.value;
    var end_date = document.forms.addmealform.enddateaddmeal.value;
    var rpt_number = document.forms.addmealform.repeatnumberaddmeal.value;
    var checkbox_repeat = document.getElementById("addmealcheckbox_repeat").checked;

    var success = false;
    if (checkbox_repeat) {
        success = (notnull_validation(title) && date_regex_validation(dateadd) && date_regex_validation(start_date) && positive_number_validation(notif_number) && date_regex_validation(end_date) && positive_number_validation(rpt_number));
    } else {
        success = (notnull_validation(title) && date_regex_validation(dateadd) && positive_number_validation(notif_number));
    }

    if (success) {
        document.getElementById("insertmealbutton").setAttribute("data-dismiss", "modal");
        xhr = GetXmlHttpObject();
        if (xhr == null)
        {
            alert("Browser does not support HTTP Request")
            return
        }
        else
        {
            var radios = document.getElementsByName('optradio');

            for (var i = 0, length = radios.length; i < length; i++) {
                if (radios[i].checked) {
                    status = radios[i].value;
                    break;
                }
            }



            //sending selected country to servlet
            var url = "controller_servl?event=ADDMEAL&title=" + document.getElementById("addmealtitle").value +
                    "&location=" + document.getElementById("addmeallocation").value +
                    "&start=" + document.getElementById("addmealdatefrom").value +
                    "&meal_type=" + status +
                    "&notification_time=" + document.getElementById("addmealnotification_time").value +
                    "&notification_date=" + document.getElementById("addmealnotification_period").value +
                    "&description=" + document.getElementById("addmealdesc").value +
                    "&repeat_time=" + document.getElementById("addmeal_repeat_time").value +
                    "&repeat_every=" + document.getElementById("addmealrepeatevery").value +
                    "&starts_at=" + document.getElementById("addmealstartsat").value +
                    "&expiration_date=" + document.getElementById("addmealexpiresat").value +
                    "&status=" + checkbox_repeat;
            //creating callback method.here countrychanged is callback method
            xhr.onreadystatechange = function () {
                meals_return(xhr);
            };
            xhr.open("GET", url, true)
            xhr.send(null)
        }
    } else {
        document.getElementById("insertmealbutton").removeAttribute("data-dismiss");
        
         document.getElementById("suc_todo_mes_valid_M").style.display="block";
        document.getElementById("suc_todo_mes_valid_M").setAttribute("class","alert alert-danger pull-left alert_messa");

        alert(document.getElementById("suc_todo_mes_valid_M").innerHTML);
        document.getElementById("suc_todo_mes_valid_M").innerHTML="Fill The Red Required Inputs";
        alert("poulos");
    }
}



function MealsPlan(src) {
    $("#addmealbutton").on("click", function () {

        $("#addmealmodal").modal('show')



    });


    var calendarOptions = {
        header: {
            left: '',
            center: 'title',
            right: ''
        },
        defaultView: 'agendaWeek',
        allDaySlot: false,
        slotDuration: '12:00:00',
        minTime: '00:00:00',
        maxTime: '24:00:00',
        contentHeight: 'auto',
        defaultTimedEventDuration: '12:00:00',
        editable: true,
        eventClick: function (calEvent, jsEvent, view) {
            viewMealsEvent(calEvent.id);
            $('#viewmealmodal').modal();
        },
        events: src

    };



    $("#mealscalendar").fullCalendar(calendarOptions);


    $('#mealcustom-next').click(function () {
        $('#mealscalendar').fullCalendar('next');
    });

    $('#mealcustom-prev').click(function () {
        $('#mealscalendar').fullCalendar('prev');
    });

    $('#mealscustom-current').click(function () {
        $('#mealscalendar').fullCalendar('today');
    });

    $('#mealsmonthlyView').click(function () {
        $('#mealscalendar').fullCalendar('changeView', 'month');
    });

    $('#mealsweeklyView').click(function () {
        $('#mealscalendar').fullCalendar('changeView', 'agendaWeek');
    });

    $('#mealsdailyView').click(function () {
        $('#mealscalendar').fullCalendar('changeView', 'agendaDay');
    });



    $('#addmealcheckbox_repeat').change(function () {
        $('#addmealrepeat').fadeToggle('slow');
    });


 






    $("#meal_delete").on("click", function (ev) {
        $('#deletemodal').modal('show');




        ev.preventDefault();


    });


}



function toggle_repeat_edit_meal() {

   $('#editmealrepeat').fadeToggle('slow');

}
//});