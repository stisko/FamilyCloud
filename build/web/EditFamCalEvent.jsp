<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-content" id="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">Edit Event</h4>
    </div>
    <form id="editeventfamilyform" name="editevfamform">
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-6">
                    <label for="editfamilyevent-title">Event Title</label>
                    <input type="text" class="form-control" id="editfamilyevent_title"  name="editfamilyevent_title" placeholder="Enter Title" value="${event.title}" required><span>You have to enter a title</span>
                </div>
                <div class="col-sm-6">
                    <label for="editfamilyevent_location">Location</label>
                    <input type="text" class="form-control" id="editfamilyevent_location" placeholder="Location Name" value="${event.location}">
                </div>
            </div>
            <div class="row">
                <div class="col-md-5" id="test">
                </div>
            </div>

            <div class="row">
                <div class="col-sm-6">
                    <label for="datepickerediteventfamily">Event Range</label>
                    <div class="input-daterange input-group" id="datepickerediteventfamily">
                        <input type="datetime" class="input-sm form-control" placeholder="YYYY-MM-DD HH:MM:SS" name="start" id="datepickerediteventfamilystart" name="datepickerediteventfamilystart" value="${event.start_date}" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required/><span>Invalid Datetime input</span>
                        <span class="input-group-editon">to</span>
                        <input type="datetime" class="input-sm form-control" placeholder="YYYY-MM-DD HH:MM:SS" name="end" id="datepickerediteventfamilyend" name="datepickerediteventfamilyend" value="${event.end_date}" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required/><span>Invalid Datetime input</span>
                    </div>
                </div>
                <div class="col-sm-6">
                    <label for="editfamilyevent_visibility">Visibility</label>
                    <div class="radio" id="editfamilyevent_visibility">
                        <label>
                            <input type="radio" name="optradio" value="Adults" ${visibility.adults} required>Adults
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="optradio" value="All" ${visibility.all}>All members
                        </label>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-6">
                    <label for="editfamilyevent_notification">Notifications before</label>
                    <div class="input-group" role="group" id="editfamilyevent_notification">
                        <input type="number" class="form-control input-sm" id="editfamilyevent_notification_time"  name="editfamilyevent_notification_time" placeholder="0" value="${event.notificationTime}" min="0" required>
                        <span class="input-group-btn" style="width:0px;"></span>
                        <select class="form-control input-sm" id="editfamilyevent_notification_period">
                            <option>Minutes</option>
                            <option>Hours</option>
                            <option>Days</option>
                            <option>Weeks</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-6">
                    <label for="editfamilyparticipating_members">Add participating members</label>
                    <div class="form-group">
                        <select id="editfamilyparticipating_members" onfocus="antegeia3()" multiple >


                            <c:forEach items="${allusers}" var="all_U">
                                <c:set var="flag" value="false"/>


                                <c:forEach items="${part_users}" var="pu">


                                    <c:if test="${pu eq all_U.username}">

                                        <c:set var="flag" value="true"/>
                                    </c:if>

                                </c:forEach>


                                <c:if test="${flag}">

                                    <option>${all_U.lastName} ${all_U.firstName}</option>
                                </c:if>


                                <c:if test="${!flag}">

                                    <option>${all_U.lastName} ${all_U.firstName}</option>
                                </c:if>

                            </c:forEach>


                        </select>
                    </div>
                </div>
            </div>

            <div class="row">
                
                <div class="col-sm-6">
                    <label for="editfamilyevent_categories">Category</label>
                    <select class="form-control" id="editfamilyevent_categories">
                        <option class="red" value="red">Red</option>
                        <option class="green" value="green">Green</option>
                        <option class="blue" value="blue">Blue</option>
                        <option class="purple" value="purple">Purple</option>
                    </select>
                </div>
                <div class="col-sm-6">
                    <label for="editfamilyevent_desc">Description</label>
                    <textarea class="form-control" id="editfamilyevent_desc" rows="2" placeholder="Enter description">${event.description}</textarea>
                </div>
            </div>


            <div class="row">
                <div class="col-sm-6">
                    <div class="checkbox_repeat">
                        <label>
                            <input type="checkbox" id="editfamilycheckbox_repeat" onchange="toggle_repeat()">Repeat
                        </label>
                    </div>
                </div>
            </div>


            <div class="repeat" id="editfamilyrepeat" style="display:none">
                <div class="row">
                    <div class="col-sm-6">

                        <div class="control-group">
                            <label class="control-label">Repeat time</label>
                            <div class="controls">
                                <select class="form-control" id="editevent_repeat_time" >
                                    <option>Daily</option>
                                    <option>Weekly</option>
                                    <option>Monthly</option>
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="col-sm-6">
                        <label>Starts at:</label>
                        <input type="datetime" class="form-control" id="editfamilyrepeatstart" name="editfamilyrepeatstart" placeholder="YYYY-MM-DD HH:MM:SS" value="${event.startRepeatDate}" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required/><span>Invalid Datetime input</span>
                        <span class="input-group-editon">to</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <label>Repeat every:</label>
                        <input type="number" class="form-control input-mir" id="editfamilyrepeatevery" name="editfamilyrepeatevery" placeholder="0" value="${event.repeat_every}" min="1" required><span>Invalid number input</span>
                        <!--- prepei na prosthesw days/ months/ weeks analoga me tin proigoumeni epilogi ---->
                    </div>
                    <div class="col-sm-6">
                        <label>Expiration:</label>
                        <input type="datetime" class="form-control" id="editfamilyexpiresat" name="editfamilyexpiresat" placeholder="YYYY-MM-DD HH:MM:SS" value="${event.endRepeatDate}" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required/><span>Invalid Datetime input</span>
                        <span class="input-group-editon">to</span>
                    </div>
                </div>
            </div>
            <!---  edw kleinei kai kala to repeat---->
        </div>




        <div class="modal-footer">
            <div id="suc_todo_mes_valid_Fed" style=" width:100%"></div>
            <div class="btn-toolbar">
                <div class="btn-group">
                    <button type="button" class="btn btn-default" id="event_view" onclick="viewEvent('${event.idFamCalEvent}')"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"  ></span> View</button>
                    <button type="button" class="btn btn-default" id="event_edit" onclick="editEvent('${event.idFamCalEvent}')"><span class="glyphicon glyphicon-edit"  aria-hidden="true" ></span> Edit</button>
                    <button type="button" class="btn btn-default" id="event_delete" onclick="deleteEvent('${event.idFamCalEvent}')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>
                </div>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" id="editsaveFameve" class="btn btn-primary"data-dismiss="modal" onclick="UpdateFamCalEvent('${event.idFamCalEvent}')">Update</button>
            </div>
        </div>

    </form>




</div>