<div class="modal-content" id="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">Edit Event</h4>
    </div>
    <form id="addpersonalcalendarform">
        <div class="modal-body">




            <div class="row">
                <div class="col-sm-6">
                    <label for="editpersonalevent-title">Event Title</label>
                    <input type="text" class="form-control" id="editpersonalevent-title" placeholder="Enter Title" value="${handleevent.title}">
                </div>
                <div class="col-sm-6">
                    <label for="editpersonalevent_location">Location</label>
                    <input type="text" class="form-control" id="editpersonalevent_location" placeholder="Location Name" value="${handleevent.location}">
                </div>
            </div>



            <div class="row">
                <div class="col-sm-6">
                    <label for="datepickeraddeventpersonal">Event Range</label>
                    <div class="input-daterange input-group" id="datepickeraddeventpersonal">
                        <input type="datetime" class="input-sm form-control" name="start" id="datepickerediteventpersonalstart" placeholder="YYYY-MM-DD HH:MM:SS" value="${handleevent.start_date}" />
                        <span class="input-group-addon">to</span>
                        <input type="datetime" class="input-sm form-control" name="end" id="datepickerediteventpersonalend" placeholder="YYYY-MM-DD HH:MM:SS" value="${handleevent.end_date}"/>
                    </div>
                </div>

                <div class="col-sm-6">
                    <label for="addpersonalevent_categories">Category</label>

                    <div>
                        <select id="editpersonalevent_categories" class="form-control input-sm" name="multise" >
                            <option value="1">Category 1</option>
                            <option value="2">Category 2</option>
                            <option value="3">Category 3</option>
                            <option value="4">Category 4</option>
                            <option value="5">Category 5</option>
                        </select>
                    </div>
                </div>

            </div>

            <div class="row">
                <div class="col-sm-6">
                    <label for="addpersonalevent_notification">Notifications before</label>
                    <div class="input-group" role="group" id="addpersonalevent_notification">
                        <input type="number" class="form-control input-sm" id="editpersonalevent_notification_time" placeholder="0" value="${handleevent.notificationTime}">
                        <span class="input-group-btn" style="width:0px;"></span>
                        <select class="form-control input-sm" id="editpersonalevent_notification_period">
                            <option>Minutes</option>
                            <option>Hours</option>
                            <option>Days</option>
                            <option>Weeks</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-6">
                    <label for="addpersonalevent_desc">Description</label>
                    <textarea class="form-control" id="editpersonalevent_desc" rows="2" placeholder="Enter description">${handleevent.description}</textarea>
                </div>

            </div>

            <div class="row">
                <div class="col-sm-6">
                    <div class="checkbox_repeat">
                        <label>
                            <input type="checkbox" id="editpersonalcheckbox_repeat" onchange="toggle_repeat2()">Repeat</label>
                    </div>
                </div>
            </div>


            <div class="repeat" id="editpersonalrepeat" style="display:none">
                <div class="row">
                    <div class="col-sm-6">

                        <div class="control-group">
                            <label class="control-label">Repeat time</label>
                            <div class="controls">
                                <select class="form-control" id="editeventpersonal_repeat_time">
                                    <option>Daily</option>
                                    <option>Weekly</option>
                                    <option>Monthly</option>
                                </select>
                            </div>
                        </div>

                    </div>

                    <div class="col-sm-6">
                        <label>Starts at:</label>
                        <input type="datetime" class="form-control" id="editpersonalrepeatstart" placeholder="YYYY-MM-DD HH:MM:SS" value="${handleevent.startRepeatDate}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <label>Repeat every:</label>
                        <input type="number" class="form-control input-mir" id="editpersonalrpeatevery" placeholder="0" value="${handleevent.repeat_every}">
                        <!--- prepei na prosthesw days/ months/ weeks analoga me tin proigoumeni epilogi ---->
                    </div>

                    <div class="col-sm-6">

                        <label>Expiration:</label>
                        <input type="datetime" class="form-control" id="editpersonalexpiresat" placeholder="YYYY-MM-DD HH:MM:SS" value="${handleevent.endRepeatDate}">
                    </div>
                </div>
            </div>
        </div>
        <!---  edw kleinei kai kala to repeat---->
        <div class="modal-footer">
            <div class="btn-toolbar">
                <div class="btn-group">
                    <button type="button" class="btn btn-default" id="event_view" onclick="viewEventper('${handleevent.idPerCalEvent}')"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"  ></span> View</button>
                    <button type="button" class="btn btn-default" id="event_edit" onclick="editPersonalEvent('${handleevent.idPerCalEvent}')"><span class="glyphicon glyphicon-edit"  aria-hidden="true" ></span> Edit</button>
                    <button type="button" class="btn btn-default" id="event_delete" onclick="deletePersonalEvent('${handleevent.idPerCalEvent}')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>
                </div>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary"data-dismiss="modal" onclick="UpdatePerCalEvent('${handleevent.idPerCalEvent}')">Update</button>
            </div>
        </div>
    </form>


</div>