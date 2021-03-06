<div class="col-sm-8 col-xs-12 " id="maincontent">
    <div class="col-sm-12" id="contentheader">
        <div class="media col-sm-4">
            <div class="media-left media-middle">
                <a href="#">
                    <img class="media-object" width="45" height="45" src="img/Meals.png">
                </a>
            </div>
            <div class="media-body">
                <h4 class="media-heading">Meals Plan</h4>
            </div>
        </div>
        <div id="suc_todo_mes" class="${noti_message.classs}">${noti_message.message}</div>
    </div>
    <div class="col-sm-12" id="contentcontent">	
        <div class="btn-group" role="group">
            <a class="btn btn-primary" id="addmealbutton" href="#" role="button"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>Add Meal</a>
        </div>
        <div class="btn-group" role="group">
            <a class="btn btn-primary" id="mealcustom-prev" href="#" role="button"><span class="glyphicon glyphicon-circle-arrow-left" aria-hidden="true"></span> Prev</a>
            <a class="btn btn-primary" id="mealcustom-next" href="#" role="button"><span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span> Next</a>
        </div>
        <div class="btn-group" role="group">
            <a class="btn btn-primary" id="mealscustom-current" href="#" role="button"><span class="glyphicon glyphicon-time" aria-hidden="true"></span> Current</a>
        </div>
        <div class="btn-group">
            <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" id="mealviewbycalendar">View by<span class="caret"></span></a>
            <ul class="dropdown-menu multi-level">
                <!-- dropdown menu links -->
                <li><a href="#" id="mealsmonthlyView">Month</a></li>
                <li><a href="#" id="mealsweeklyView">Week</a></li>
                <li><a href="#" id="mealsdailyView">Day</a></li>
                <li class="divider"></li>
                <li><a onclick="getMealsPlan()">All</a></li>
                <li><a onclick="viewMealsPlanByType('Lunch')">Lunch</a></li>
                <li><a onclick="viewMealsPlanByType('Dinner')">Dinner</a></li>
            </ul>
        </div>
        <a class="btn btn-primary" id="mealsprint" href="javascript:window.print()" role="button"><span class="glyphicon glyphicon-print" aria-hidden="true"></span>Print Week Meal</a>
        <div class="col-sm-12">
            <div class="meals"id='mealscalendar'></div>
        </div>
    </div>
</div>

<div id="viewmealmodal" class="modal fade">
    <div class="modal-dialog" id="meal_modal">
        
    </div>
</div>				

<div class="modal fade" id="deletemodal" tabindex="-1" role="dialog" aria-labelledby="deletelabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Delete</h4>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Delete</button>
            </div>
        </div>
    </div>
</div>				

<div class="modal fade" id="addmealmodal" tabindex="-1" role="dialog" aria-labelledby="addmeallab" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Add Meal</h4>
            </div>
            
            <form id="addmealform" name="addmealform">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <label for="addmealtitle">Meal Title</label>
                            <input type="text" class="form-control" name="titleaddmeal" oninput="style_inp('addmealtitle')" id="addmealtitle" placeholder="Enter Title" required><span>You have to enter a title</span>
                        </div>
                        <div class="col-sm-6">
                            <label for="addmeallocation">Location</label>
                            <input type="text" class="form-control" id="addmeallocation" placeholder="Location Name">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <label for="addmealdatefrom">Meal Date</label>
                            <input type="date" class="form-control" id="addmealdatefrom" oninput="style_inp('addmealdatefrom')" name="dateaddmeal" placeholder="yyyy-MM-dd" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" required><span>Invalid Date Input</span>
                        </div>
                        <div class="col-sm-6">
                            <label for="addmealtype">Meal Type</label>
                            <div class="radio" id="addmealtype">
                                <label>
                                    <input type="radio" value="Lunch" name="optradio" >Lunch
                                </label>
                                
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" value="Dinner" name="optradio">Dinner
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <label for="addmealnotification">Notifications before</label>
                            <div class="input-group" role="group" id="addmealnotification">
                                <input type="number" class="form-control input-sm" name="notificationnumberaddmeal" id="addmealnotification_time" oninput="style_inp('addmealnotification_time')" placeholder="Enter a number" required min="1"><span>Invalid Number input</span>
                                <span class="input-group-btn" style="width:0px;"></span>
                                <select class="form-control input-sm" id="addmealnotification_period">
                                    <option>Minutes</option>
                                    <option>Hours</option>
                                    <option>Days</option>
                                    <option>Weeks</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <label for="addmealdesc">Description</label>
                            <textarea class="form-control" rows="2" placeholder="Enter description" id="addmealdesc"></textarea>
                        </div>
                        <div class="col-sm-6">
                            <div class="checkbox_repeat">
                                <label>
                                    <input type="checkbox" id="addmealcheckbox_repeat" value="0">Repeat</label>
                            </div>
                        </div>
                    </div>
                    <div class="repeat" id="addmealrepeat" style="display:none">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="control-group">
                                    <label class="control-label">Repeat time</label>
                                    <div class="controls">
                                        <select class="form-control" id="addmeal_repeat_time">
                                            <option>Daily</option>
                                            <option>Weekly</option>
                                            <option>Monthly</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <label>Starts at:</label>
                                <input type="date" class="form-control" id="addmealstartsat" name="startdateaddmeal" oninput="style_inp('addmealstartsat')" placeholder="yyyy-MM-dd" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" required><span>Invalid Date Input</span>
                            </div>

                            <div class="col-sm-6">
                                <label>Repeat every:</label>
                                <input type="number" class="form-control input-mir" name="repeatnumberaddmeal" id="addmealrepeatevery" oninput="style_inp('addmealrepeatevery')" placeholder="0" required><span>Invalid number input</span>
                                <!--- prepei na prosthesw days/ months/ weeks analoga me tin proigoumeni epilogi ---->
                            </div>

                            <div class="col-sm-6">
                                <label>Expires on:</label>
                                <input type="date" class="form-control" id="addmealexpiresat" name="enddateaddmeal" oninput="style_inp('addmealexpiresat')" placeholder="yyyy-MM-dd" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" required><span>Invalid Date Input</span>
                            </div>
                        </div>
                    </div>
                    <!---  edw kleinei kai kala to repeat---->
                </div>
                <div class="modal-footer">
                    <div id="suc_todo_mes_valid_M" style="font-size:120%"></div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="insertmealbutton" data-dismiss="modal" onclick="addMeal()">Add</button>
                </div>
            </form>
            
        </div>
    </div>

</div>



</div>


