<div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">Edit Meal</h4>
    </div>

    <form id="editmealform">
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-6">
                    <label for="editmealtitle">Meal Title</label>
                    <input type="text" class="form-control" id="editmealtitle" placeholder="Enter Title" value="${mealsEvent.title}">
                </div>
                <div class="col-sm-6">
                    <label for="editmeallocation">Location</label>
                    <input type="text" class="form-control" id="editmeallocation" placeholder="Location Name" value="${mealsEvent.location}">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <label for="editmealdatefrom">Meal Date</label>
                    <input type="datetime" class="form-control" id="editmealdatefrom" placeholder="YYYY-MM-DD HH:MM:SS" value="${mealsEvent.start_date}">
                </div>
                <div class="col-sm-6">
                    <label for="editmealtype">Meal Type</label>
                    <div class="radio" id="editmealtype">
                        <label>
                            <input type="radio" value="Lunch" name="optradio" ${type.lunch}>Lunch
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" value="Dinner" name="optradio" ${type.dinner}>Dinner
                        </label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <label for="editmealnotification">Notifications before</label>
                    <div class="input-group" role="group" id="editmealnotification">
                        <input type="number" class="form-control input-sm" id="editmealnotification_time" placeholder="0" value="${mealsEvent.notificationTime}">
                        <span class="input-group-btn" style="width:0px;"></span>
                        <select class="form-control input-sm" id="editmealnotification_period">
                            <option>Minutes</option>
                            <option>Hours</option>
                            <option>Days</option>
                            <option>Weeks</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-6">
                    <label for="editmealdesc">Description</label>
                    <textarea class="form-control" rows="2" placeholder="Enter description" id="editmealdesc">${mealsEvent.description}</textarea>
                </div>
                <div class="col-sm-6">
                    <div class="checkbox_repeat">
                        <label>
                            <input type="checkbox" id="editmealcheckbox_repeat" value="0">Repeat</label>
                    </div>
                </div>
            </div>
            <div class="repeat" id="editmealrepeat" style="display:none">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="control-group">
                            <label class="control-label">Repeat time</label>
                            <div class="controls">
                                <select class="form-control" id="editmeal_repeat_time" >
                                    <option>Daily</option>
                                    <option>Weekly</option>
                                    <option>Monthly</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <label>Starts at:</label>
                        <input type="datetime" class="form-control" id="editmealstartsat" placeholder="YYYY-MM-DD HH:MM:SS" value="${mealsEvent.startRepeatDate}">
                    </div>

                    <div class="col-sm-6">
                        <label>Repeat every:</label>
                        <input type="number" class="form-control input-mir" id="editmealrepeatevery" placeholder="0" value="${mealsEvent.repeat_every}">
                        <!--- prepei na prosthesw days/ months/ weeks analoga me tin proigoumeni epilogi ---->
                    </div>

                    <div class="col-sm-6">
                        <label>Expires on:</label>
                        <input type="datetime" class="form-control" id="editmealexpiresat" placeholder="YYYY-MM-DD HH:MM:SS" value="${mealsEvent.endRepeatDate}">
                    </div>
                </div>
            </div>
            <!---  edw kleinei kai kala to repeat---->
        </div>
        <div class="modal-footer">
            <div class="btn-toolbar">
                <div class="btn-group">
                    <button type="button" class="btn btn-default" id="event_view" onclick="viewMealsEvent('${mealsEvent.idMealsPlanEvent}')"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"  ></span> View</button>
                    <button type="button" class="btn btn-default" id="event_edit" onclick="editMealsEvent('${mealsEvent.idMealsPlanEvent}')"><span class="glyphicon glyphicon-edit"  aria-hidden="true" ></span> Edit</button>
                    <button type="button" class="btn btn-default" id="event_delete" onclick="deleteMealsEvent('${mealsEvent.idMealsPlanEvent}')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>
                </div>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="UpdateMealsPlanEvent('${mealsEvent.idMealsPlanEvent}')">Update</button>
            </div>
        </div>
    </form>

</div>