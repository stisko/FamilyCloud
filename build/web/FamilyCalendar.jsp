<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-sm-8 col-xs-12 " id="maincontent">
    <div class="col-sm-12" id="contentheader">
        <div class="media col-sm-4">
            <div class="media-left media-middle">
                <a href="#">
                    <img class="media-object" width="45" height="45" src="img/Calendar.png">
                </a>
            </div>
            <div class="media-body">
                <h4 class="media-heading">Family Calendar</h4>
            </div>
        </div>
        
        <div id="suc_todo_mes" class="${noti_message.classs}">${noti_message.message}</div>
    </div>
    <div class="col-sm-12" id="contentcontent">
        <div class="btn-toolbar" role="toolbar">
            <div class="btn-group" role="group">
                <a class="btn btn-primary" id="addfamilycalendareventbutton" href="#" role="button"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Create Event</a>
            </div>
            <div class="btn-group" role="group">
                <a class="btn btn-primary" id="custom-prevfamilycalendarevent" href="#" role="button"><span class="glyphicon glyphicon-circle-arrow-left" aria-hidden="true"></span> Prev</a>

                <a class="btn btn-primary" id="custom-nextfamilycalendarevent" href="#" role="button"><span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span> Next</a>
            </div>
            <div class="btn-group" role="group">
                <a class="btn btn-primary" id="custom-currentfamilycalendar" href="#" role="button"><span class="glyphicon glyphicon-time" aria-hidden="true"></span> Current</a>
            </div>

            <div class="btn-group pull-right" role="group">
                <div class="dropdown">

                    <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" id="viewbycalendar">View by <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu" id="viewbycalendarlist">
                        <!-- dropdown menu links -->

                        <li><a href="#" id="monthlyViewfamilycalendar">Month</a>
                        </li>
                        <li><a href="#" id="weeklyViewfamilycalendar">Week</a>
                        </li>
                        <li><a href="#" id="dailyViewfamilycalendar">Day</a>
                        </li>

                        <li class="divider"></li>
                        <li class="dropdown-submenu">
                            <a href="#">Family Member</a>
                            <ul class="dropdown-menu">
                                <li><a onclick="getFamilyCalendar()">All Family Members</a></li>
                                <c:forEach items="${fmembers}" var="fmembers">
                                    <li><a  onclick="viewFamCalbyMember('${fmembers.username}')"><img class="img-rounded" alt="Notifications" width="25" height="25" src="controller_servl?event=GETPIC&username=${fmembers.username}" ><span> ${fmembers.lastName} ${fmembers.firstName}</span></a></li>
                                    <!--<li><a href="">${fmembers.firstName} ${fmembers.lastName}</a></li>-->

                                </c:forEach>

                            </ul>
                        </li>

                        <li class="dropdown-submenu">
                            <a href="#">Category</a>
                            <ul class="dropdown-menu">
                                <li><a onclick="getFamilyCalendar()">All Categories</a></li>
                                <li><a onclick="viewFamCalbyCategory('red')"><div class="color-box" style="background-color:red;"></div><span>Red</span></a></li>
                                <li><a onclick="viewFamCalbyCategory('green')"><div class="color-box" style="background-color:green;"></div>Green</a></li>
                                <li><a onclick="viewFamCalbyCategory('blue')"><div class="color-box" style="background-color:blue;"></div>Blue</a></li>
                                <li><a onclick="viewFamCalbyCategory('purple')"><div class="color-box" style="background-color:purple;"></div>Purple</a></li>
                                
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <a class="btn btn-primary" id="printfamilycalendarbutton" href="javascript:window.print()" role="button"><span class="glyphicon glyphicon-print" aria-hidden="true"></span>Print Family Calendar</a>

        <!----- CLASS CALENDAR ----->
        <div id="familycalendar"></div>
    </div>
</div>



<div id="fullCalModal" class="modal fade">
    <div class="modal-dialog" id="famcalendar_modal">
        
    </div>
</div>








<!------------------      Insert MODAL      ----------->
<div class="modal fade" id="addfamilyeventmodal" tabindex="-1" role="dialog" aria-labelledby="addtasklabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" id="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Create Event</h4>
            </div>
            <form id="addeventfamilyform" name="addevfamform">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <label for="addfamilyevent-title">Event Title</label>
                            <input type="text" class="form-control" id="addfamilyevent_title" name="addfamilyevent_title" placeholder="Enter Title" required><span>You have to enter a title</span>
                        </div>
                        <div class="col-sm-6">
                            <label for="addfamilyevent_location">Location</label>
                            <input type="text" class="form-control" id="addfamilyevent_location" placeholder="Location Name">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5" id="test">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-6">
                            <label for="datepickeraddeventfamily">Event Range</label>
                            <div class="input-daterange input-group" id="datepickeraddeventfamily">
                                <input type="datetime" class="input-sm form-control" placeholder="YYYY-MM-DD HH:MM:SS" name="start" id="datepickeraddeventfamilystart" name="datepickeraddeventfamilystart" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required/><span>Invalid Datetime input</span>
                                <span class="input-group-addon">to</span>
                                <input type="datetime" class="input-sm form-control" placeholder="YYYY-MM-DD HH:MM:SS" name="end" id="datepickeraddeventfamilyend" name="datepickeraddeventfamilyend" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required/><span>Invalid Datetime input</span>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <label for="addfamilyevent_visibility">Visibility</label>
                            <div class="radio" id="addfamilyevent_visibility">
                                <label>
                                    <input type="radio" name="optradio" value="Adults">Adults
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="optradio" value="All">All members
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-6">
                            <label for="addfamilyevent_notification">Notifications before</label>
                            <div class="input-group" role="group" id="addfamilyevent_notification">
                                <input type="number" class="form-control input-sm" id="addfamilyevent_notification_time" placeholder="0" min="0" required>
                                <span class="input-group-btn" style="width:0px;"></span>
                                <select class="form-control input-sm" id="addfamilyevent_notification_period">
                                    <option>Minutes</option>
                                    <option>Hours</option>
                                    <option>Days</option>
                                    <option>Weeks</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <label for="addfamilyparticipating_members">Add participating members</label>
                            <div class="form-group">
                                <select id="addfamilyparticipating_members" onfocus="antegeia3()" multiple="multiple">
                                    <c:forEach items="${fmembers}" var="fmembers_Ass">
                                        <option value="${fmembers_Ass.username}">${fmembers_Ass.firstName} ${fmembers_Ass.lastName}</option>
                                    </c:forEach>
                                    
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-6">
                            <label for="addfamilyevent_categories">Category</label>
                            <select class="form-control" id="addfamilyevent_categories">
                                <option class="red" value="red">Red</option>
                                <option class="green" value="green">Green</option>
                                <option class="blue" value="blue">Blue</option>
                                <option class="purple" value="purple">Purple</option>
                            </select>
                        </div>
                        <div class="col-sm-6">
                            <label for="addfamilyevent_desc">Description</label>
                            <textarea class="form-control" id="addfamilyevent_desc" rows="2" placeholder="Enter description"></textarea>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-sm-6">
                            <div class="checkbox_repeat">
                                <label>
                                    <input type="checkbox" id="addfamilycheckbox_repeat" >Repeat</label>
                            </div>
                        </div>
                    </div>


                    <div class="repeat" id="addfamilyrepeat" style="display:none">
                        <div class="row">
                            <div class="col-sm-6">

                                <div class="control-group">
                                    <label class="control-label">Repeat time</label>
                                    <div class="controls">
                                        <select class="form-control" id="addevent_repeat_time">
                                            <option>Daily</option>
                                            <option>Weekly</option>
                                            <option>Monthly</option>
                                        </select>
                                    </div>
                                </div>

                            </div>
                            <div class="col-sm-6">
                                <label>Starts at:</label>
                                <input type="datetime" class="form-control" id="addfamilyrepeatstart" name="addfamilyrepeatstart" placeholder="YYYY-MM-DD HH:MM:SS" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required/><span>Invalid Datetime input</span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <label>Repeat every:</label>
                                <input type="number" class="form-control input-mir" id="addfamilyrepeatevery" name="addfamilyrepeatevery" placeholder="0" min="1" required><span>Invalid number input</span>
                                <!--- prepei na prosthesw days/ months/ weeks analoga me tin proigoumeni epilogi ---->
                            </div>
                            <div class="col-sm-6">
                                <label>Expiration:</label>
                                <input type="datetime" class="form-control" id="addfamilyexpiresat" name="addfamilyexpiresat" placeholder="YYYY-MM-DD HH:MM:SS" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required/><span>Invalid Datetime input</span>
                            </div>
                        </div>
                    </div>
                    <!---  edw kleinei kai kala to repeat---->
                </div>
                <div class="modal-footer">
                    <div id="suc_todo_mes_valid_F" style=" width:70%"></div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" id="addfameventbut" class="btn btn-primary"data-dismiss="modal" onclick="addEvent()">Add</button>
                </div>
            </form>




        </div>
    </div>






</div>

