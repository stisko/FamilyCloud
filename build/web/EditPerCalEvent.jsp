<div class="modal-content" id="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">Edit Event</h4>
    </div>
    <form id="editpersonalcalendarform" name="editpersonalcalendarform" >
        <div class="modal-body">




            <div class="row">
                <div class="col-sm-6">
                    <label for="editpersonalevent-title">Event Title</label>
                    <input type="text" class="form-control" id="editpersonalevent-title" oninput="style_inp('editpersonalevent-title')" name="editpersonalevent_title" placeholder="Enter Title" value="${handleevent.title}" required><span>You have to enter a title</span>
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
                        <input type="datetime" class="input-sm form-control" name="start" id="datepickerediteventpersonalstart" oninput="style_inp('datepickerediteventpersonalstart')" name="datepickerediteventpersonalstart" placeholder="YYYY-MM-DD HH:MM:SS" value="${handleevent.start_date}" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required/><span>Invalid datetime input</span>
                        <span class="input-group-addon">to</span>
                        <input type="datetime" class="input-sm form-control" name="end" id="datepickerediteventpersonalend" oninput="style_inp('datepickerediteventpersonalend')" name="datepickerediteventpersonalend" placeholder="YYYY-MM-DD HH:MM:SS" value="${handleevent.end_date}" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required/><span>Invalid datetime input</span>
                    </div>
                </div>


                <div class="col-sm-6">
                    <label for="editpersonalevent_categories">Category</label>
                    <select class="form-control" id="editpersonalevent_categories">
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
                        <input type="number" class="form-control input-sm" id="editpersonalevent_notification_time" oninput="style_inp('editpersonalevent_notification_time')" placeholder="Enter a number" name="editpersonalevent_notification_time" value="${handleevent.notificationTime}"required min="1"><span>Invalid Number input</span>
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
                        <input type="datetime" class="form-control" id="editpersonalrepeatstart" oninput="style_inp('editpersonalrepeatstart')" name="editpersonalrepeatstart" placeholder="YYYY-MM-DD HH:MM:SS" value="${handleevent.startRepeatDate}" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required/><span>Invalid datetime input</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <label>Repeat every:</label>
                        <input type="number" class="form-control input-mir" id="editpersonalrpeatevery" oninput="style_inp('editpersonalrpeatevery')" placeholder="0" name="editpersonalrpeatevery" value="${handleevent.repeat_every}" min="1" required><span>Invalid number input</span>
                        <!--- prepei na prosthesw days/ months/ weeks analoga me tin proigoumeni epilogi ---->
                    </div>

                    <div class="col-sm-6">

                        <label>Expiration:</label>
                        <input type="datetime" class="form-control" id="editpersonalexpiresat" oninput="style_inp('editpersonalexpiresat')" name="editpersonalexpiresat" placeholder="YYYY-MM-DD HH:MM:SS" value="${handleevent.endRepeatDate}" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}" required/><span>Invalid datetime input</span>
                    </div>
                </div>
            </div>
        </div>
        <!---  edw kleinei kai kala to repeat---->
        <div class="modal-footer">
            <div id="suc_todo_mes_valid_Ped" style="font-size:120% ; width:100%;text-align:left"></div>
            <div class="btn-toolbar">
                <div class="btn-group">
                    <button type="button" class="btn btn-default" id="event_view" onclick="viewEventper('${handleevent.idPerCalEvent}')"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"  ></span> View</button>
                    <button type="button" class="btn btn-default" id="event_edit" onclick="editPersonalEvent('${handleevent.idPerCalEvent}')"><span class="glyphicon glyphicon-edit"  aria-hidden="true" ></span> Edit</button>
                    <button type="button" class="btn btn-default" id="event_delete" onclick="deletePersonalEvent('${handleevent.idPerCalEvent}')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>
                </div>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" id="editsaveupdatebut" class="btn btn-primary"data-dismiss="modal" onclick="UpdatePerCalEvent('${handleevent.idPerCalEvent}')">Update</button>
            </div>
        </div>
    </form>


</div>