<%-- 
    Document   : PersonalCalendar
    Created on : Jun 22, 2015, 9:01:57 PM
    Author     : GVra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-sm-8 col-xs-12 " id="maincontent">


    <div class="col-sm-12" id="contentheader">
        <div class="media col-sm-4">
            <div class="media-left media-middle">
                <a href="#">
                    <img class="media-object" width="45" height="45" src="img/Calendar.png">
                </a>
            </div>
            <div class="media-body">
                <h4 class="media-heading">Personal Calendar</h4>
            </div>


        </div>


        <div id="suc_todo_mes" class="${noti_message.classs}">${noti_message.message}</div>


    </div>

    <div class="col-sm-12" id="contentcontent">


        <div class="btn-toolbar" role="toolbar">
            <div class="btn-group" role="group">
                <a class="btn btn-primary" id="addpersonalcalendareventbutton" href="#" role="button"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Create Event</a>
            </div>
            <div class="btn-group" role="group">
                <a class="btn btn-primary" id="custom-prevpersonalcalendarevent" href="#" role="button"><span class="glyphicon glyphicon-circle-arrow-left" aria-hidden="true"></span> Prev</a>

                <a class="btn btn-primary" id="custom-nextpersonalcalendarevent" href="#" role="button"><span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span> Next</a>
            </div>
            <div class="btn-group" role="group">
                <a class="btn btn-primary" id="custom-currentpersonalcalendar" href="#" role="button"><span class="glyphicon glyphicon-time" aria-hidden="true"></span> Current</a>
            </div>

            <div class="btn-group pull-right" role="group">
                <div class="dropdown">

                    <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" id="viewbypersonalcalendar">View by <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu" id="viewbypersonalcalendarlist">
                        <!-- dropdown menu links -->

                        <li><a href="#" id="monthlyViewpersonalcalendar">Month</a>
                        </li>
                        <li><a href="#" id="weeklyViewpersonalcalendar">Week</a>
                        </li>
                        <li><a href="#" id="dailyViewpersonalcalendar">Day</a>
                        </li>

                        <li class="divider"></li>
                        <li class="dropdown-submenu">
                            <a href="#">Family Member</a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Marios</a>
                                </li>
                                <li><a href="#">Giorgos</a>
                                </li>
                                <li><a href="#">Sofia</a>
                                </li>
                                <li><a href="#">Panagia</a>
                                </li>
                            </ul>
                        </li>

                        <li><a href="#">Category</a>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
        <div class="btn-toolbar" role="toolbar">
            <div class="btn-group" role="group">
                <a class="btn btn-primary" id="printpersonalcalendarbutton" href="javascript:window.print()" role="button"><span class="glyphicon glyphicon-print" aria-hidden="true"></span>Print Personal Calendar</a>
            </div>

            <div class="btn-group pull-right" role="group">
                <a class="btn btn-primary" id="import_cal" href="#" role="button" data-toggle="modal" data-target="#import_cal_modal" onclick="importfrom()"><span class="glyphicon glyphicon-import" aria-hidden="true"></span> Import from Family Calendar</a>
            </div>

        </div>



        <!--- CLASS CALENDAR ----->




        <div id='personalcalendar'></div>

    </div>


</div>




<div id="fullCalModalpersonal" class="modal fade">
    <div class="modal-dialog" id="percalendar_modal">

    </div>
</div>



<div class="modal fade" id="addpersonalcalendarmodal" tabindex="-1" role="dialog" aria-labelledby="addpersonalcallabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" id="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Create Event</h4>
            </div>
            <form id="addpersonalcalendarform" name="addpersonalcalendarform">
                <div class="modal-body">




                    <div class="row">
                        <div class="col-sm-6">
                            <label for="addpersonalevent-title">Event Title</label>
                            <input type="text" class="form-control" id="addpersonalevent-title" oninput="style_inp('addpersonalevent-title')" name="addpersonalevent_title" placeholder="Enter Title" required><span>You have to enter a title</span>
                        </div>
                        <div class="col-sm-6">
                            <label for="addpersonalevent_location">Location</label>
                            <input type="text" class="form-control" id="addpersonalevent_location" placeholder="Location Name">
                        </div>
                    </div>



                    <div class="row">
                        <div class="col-sm-6">
                            <label for="datepickeraddeventpersonal">Event Range</label>
                            <div class="input-daterange input-group" id="datepickeraddeventpersonal">
                                <input type="datetime" class="input-sm form-control"  id="datepickeraddeventpersonalstart" oninput="style_inp('datepickeraddeventpersonalstart')" name="datepickeraddeventpersonalstart" placeholder="YYYY-MM-DD HH:MM:SS" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required /><span>Invalid Datetime input</span>
                                <span class="input-group-addon">to</span>
                                <input type="datetime" class="input-sm form-control"  id="datepickeraddeventpersonalend" oninput="style_inp('datepickeraddeventpersonalend')" name="datepickeraddeventpersonalend" placeholder="YYYY-MM-DD HH:MM:SS" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required/><span>Invalid Datetime input</span>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <label for="addpersonalevent_categories">Category</label>
                            <select class="form-control" id="addpersonalevent_categories">
                                <option class="red" value="red">Red</option>
                                <option class="green" value="green">Green</option>
                                <option class="blue" value="blue">Blue</option>
                                <option class="purple" value="purple">Purple</option>
                            </select>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-sm-6">
                            <label for="addpersonalevent_notification">Notifications before</label>
                            <div class="input-group" role="group" id="addpersonalevent_notification">
                                <input type="number" class="form-control input-sm" id="addpersonalevent_notification_time" oninput="style_inp('addpersonalevent_notification_time')" name="addpersonalevent_notification_time" placeholder="Enter a number" min="1" required><span>Invalid Input number</span>
                                <span class="input-group-btn" style="width:0px;"></span>
                                <select class="form-control input-sm" id="addpersonalevent_notification_period" name="addpersonalevent_notification_period">
                                    <option>Minutes</option>
                                    <option>Hours</option>
                                    <option>Days</option>
                                    <option>Weeks</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <label for="addpersonalevent_desc">Description</label>
                            <textarea class="form-control" id="addpersonalevent_desc" rows="2" placeholder="Enter description"></textarea>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-sm-6">
                            <div class="checkbox_repeat">
                                <label>
                                    <input type="checkbox" id="addpersonalcheckbox_repeat" value="0">Repeat</label>
                            </div>
                        </div>
                    </div>


                    <div class="repeat" id="addpersonalrepeat" style="display:none">
                        <div class="row">
                            <div class="col-sm-6">

                                <div class="control-group">
                                    <label class="control-label">Repeat time</label>
                                    <div class="controls">
                                        <select class="form-control" id="addeventpersonal_repeat_time">
                                            <option>Daily</option>
                                            <option>Weekly</option>
                                            <option>Monthly</option>
                                        </select>
                                    </div>
                                </div>

                            </div>

                            <div class="col-sm-6">
                                <label>Starts at:</label>
                                <input type="datetime" class="form-control" id="addpersonalrepeatstart" oninput="style_inp('addpersonalrepeatstart')"  placeholder="YYYY-MM-DD HH:MM:SS" name="addpersonalrepeatstart" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <label>Repeat every:</label>
                                <input type="number" class="form-control input-mir" id="addpersonalrpeatevery" oninput="style_inp('addpersonalrpeatevery')" name="addpersonalrpeatevery" placeholder="0" min="1" required><span>Invalid number input</span>
                                <!--- prepei na prosthesw days/ months/ weeks analoga me tin proigoumeni epilogi ---->
                            </div>

                            <div class="col-sm-6">

                                <label>Expiration:</label>
                                <input type="datetime" class="form-control" id="addpersonalexpiresat" oninput="style_inp('addpersonalexpiresat')" name="addpersonalexpiresat" placeholder="YYYY-MM-DD HH:MM:SS" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required>
                            </div>
                        </div>
                    </div>
                </div>
                <!---  edw kleinei kai kala to repeat---->
                <div class="modal-footer">
                    <div id="suc_todo_mes_valid_P" style="font-size:120%"></div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" id="addpercalbut" class="btn btn-primary" data-dismiss="modal" onclick="addperEvent()">Add</button>
                </div>
            </form>


        </div>

    </div>
</div>



<div class="modal fade " id="import_cal_modal" tabindex="-1" role="dialog" aria-labelledby="addtasklabel" aria-hidden="true">
    <div class="modal-dialog" id="imp_cale_per">



    </div>

</div>