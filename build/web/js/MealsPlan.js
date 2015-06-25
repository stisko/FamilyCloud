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

        var checkbox_repeat = document.getElementById("editmealcheckbox_repeat").checked;

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
                "&status=" + checkbox_repeat+
                "&id="+id;
        //creating callback method.here countrychanged is callback method
        xhr.onreadystatechange = function () {
            meals_return(xhr);
        };
        xhr.open("GET", url, true)
        xhr.send(null)
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
            custom_view_meals_return(xhr,type);
        };

        xhr.open("GET", url, true)
        xhr.send(null)
    }
}

function custom_view_meals_return(xhr,type)
{

    if (xhr.readyState == 4 || xhr.readyState == "complete")
    {
        //getting response from server(Servlet)

//                    var json = JSON.parse(xmlHttp.responseText);

        var text = xhr.responseText;

        document.getElementById("newpage").innerHTML = text;
        MealsPlan('controller_servl?event=MEALSPLANEVENTS&type='+type);

        //displaying response in select box by using that id
//                    document.getElementById("apotelesma2").innerHTML = json.message;
//                    document.getElementById("signup_btn").setAttribute("class", json.disabled);

    }
}

function addMeal() {

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

        var checkbox_repeat = document.getElementById("addmealcheckbox_repeat").checked;

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


    $('#editmealcheckbox_repeat').change(function () {
        $('#editmealrepeat').fadeToggle('slow');
    });



    


    $("#meal_delete").on("click", function (ev) {
        $('#deletemodal').modal('show');




        ev.preventDefault();


    });
    

}