<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <title>Family Cloud</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.js"></script>





        <!--------- MULTISELECT IMPORTS ------>
        <script src="js/myjavascript.js"></script>
        <script src="js/Family.js"></script>
        <script src="js/ajaxupload.js"></script>
        <script src="js/ToDo.js"></script>
        <script src="js/refreshmembers.js"></script>
        <script src="js/WallPost.js"></script>
        <script src="js/ajaxCHAT.js"></script>
        <script src="js/RefreshMessage.js"></script>
        <script src="js/FamilyCalendar.js"></script>
        <script src="js/MealsPlan.js"></script>
        <script src="js/Notifications.js"></script>
        <script src="js/PersonalCalendar.js"></script>
        <script src="js/Shop.js"></script>

        <script src="js/moment.js"></script>
        <script src="js/fullcalendar.js"></script>


        <link rel="stylesheet" type="text/css" href="css/mystyle.css">
        <link rel="stylesheet" type="text/css" href="css/fullcalendar.css">
        <script >

            function GetXmlHttpObject()
            {
                //creating xmlhttprequestobject.common method for any ajax application
                var xmlHttp = null;
                try
                {
                    // Firefox, Opera 8.0+, Safari
                    xmlHttp = new XMLHttpRequest();
                }
                catch (e)
                {
                    //Internet Explorer
                    try
                    {
                        xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                    }
                    catch (e)
                    {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                }
                return xmlHttp;
            }
//to get states



        </script>
        <script>
            function show_chatbox(id) {
                //alert(id);
                if (document.getElementById(id).style.display == 'block') {
                    document.getElementById(id).style.display = 'none';
                } else {
                    document.getElementById(id).style.display = 'block';
                }
            }


            window.onload = function () {


                RefreshMembers();
                getUnreadNotificationsCount();
                getUnreadMessageNotificationsCount();

                $('[data-toggle="offcanvas"]').click(function () {
                    $('.row-offcanvas-left').toggleClass('active');
                });

                $('[data-toggle="offcanvasright"]').click(function () {
                    $('.row-offcanvas-right').toggleClass('active');
                });
            }

        </script>
    </head>

    <body>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <!-- 88BA68 9A68BA BA7168 68B1BA -->
        <!-- HEADER/JUMBTRON-->
        <div class="navbar navbar-fixed-top navbar-default" role="navigation">
            <div class="container">

                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar1">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <p class="pull-left visible-xs">
                        <button type="button" class="btn btn-primary" id="menu_button" data-toggle="offcanvas">Menu</button>
                    </p>
                    <p class="pull-right visible-xs">
                        <button type="button" class="btn btn-primary" id="users_button" data-toggle="offcanvasright">Users</button>
                    </p>

                    <a class="navbar-brand" href="#"onclick="getMyFamily()">
                        <img class="img-responsive" alt="Notifications" width="100" height="70" src="img/cloud.png">
                        <h3 class="headerspan">Family Cloud</h3>
                    </a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar1">
                    <ul class="nav navbar-nav navbar-right">


                        <!--                        <li onclick="getMessageNotifications()" class="dropdown dropdown-toggle" data-toggle="dropdown" data-target="dropdown_messages" aria-haspopup="true" aria-expanded="false" role="presentation"><a href="#"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span><span class="badge backg" id="badge_counter_msg">15</span></a></li>
                                                <ul class="takis dropdown-menu col-sm-2" role="menu" id="dropdown_messages">
                                                    <li>malakies</li>
                                                </ul>-->
                        <li class="dropdown">
                            <a href="" onclick="getMessageNotifications()" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span><span class="badge backg" id="badge_counter_msg"></span></a>
                            <ul class="dropdown-menu scroll" id="dropdown_messages">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="" onclick="getNotifications()" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" ><span class="glyphicon glyphicon-bell" aria-hidden="true"></span><span class="badge backg" id="badge_counter"></span></a>
                            <ul class="dropdown-menu scroll" role="menu" id="dropdown_notifications">

                            </ul>
                        </li>






                        <li role="presentation"><a onclick="getMyFamily()">Home</a>
                        </li>
                        <li role="presentation" data-toggle="modal" data-target="#myprofilemodal"><a href="#myprofilemodal">My Profile</a>
                        </li>
                        <li role="presentation" data-toggle="modal" data-target="#logoutmodal"><a href="#logoutmodal">Logout</a>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
        <div class="fullcont">
            <div class="container">
                <!--fixed width no full width container -->
                <div class="row row-offcanvas row-offcanvas-left">
                    <div class="row row-offcanvas row-offcanvas-right">
                        <div class="col-sm-2 col-xs-4 sidebar-offcanvas" id="sidebarleft" role="navigation">
                            <div class="sidebar-nav">
                                <ul class="nav nav-pills nav-stacked">
                                    <li role="presentation"><a onclick="getMyFamily()"><span class="family-icon" aria-hidden="true"><img alt="Notifications" width="60" height="60" src="img/my_family.png"></span>My Family</a>
                                    </li>
                                    <li role="presentation"><a onclick="getWallPost()"><span class="family-icon" aria-hidden="true"><img alt="Notifications" width="60" height="60" src="img/family_wall.png"></span>Family Wall</a>
                                    </li>
                                    <li role="presentation"><a onclick="getFamilyCalendar()"><span class="family-icon" aria-hidden="true"><img alt="Notifications" width="60" height="60" src="img/Calendar.png"></span>Family Calendar</a>
                                    </li>
                                    <li role="presentation"><a onclick="getPersonalCalendar()"><span class="family-icon" aria-hidden="true"><img alt="Notifications" width="60" height="60" src="img/Calendar.png"></span>My Calendar</a>
                                    </li>
                                    <li role="presentation"><a onclick="getMealsPlan()"><span class="family-icon" aria-hidden="true"><img alt="Notifications" width="60" height="60" src="img/Meals.png"></span>Meals Plan</a>
                                    </li>
                                    <li role="presentation"><a onclick="getShoppingList()"><span class="family-icon" aria-hidden="true"><img alt="Notifications" width="60" height="60" src="img/Shopping.png"></span>Shopping List</a>
                                    </li>
                                    <li role="presentation"><a onclick="getToDoList()"><span class="family-icon" aria-hidden="true"><img alt="Notifications" width="60" height="60" src="img/Todo_list.png"></span>To Do List</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div  id="newpage">
                            <%--<%@ include file="../MyFamily.jsp" %>--%>
                            <%--<c:import url="${jsp}" />--%>
                            <jsp:include page="MyFamily.jsp" />
                        </div>
                        <div class="col-sm-2 col-xs-4 sidebar-offcanvas" id="sidebarright" role="navigation">
                            <div class="sidebar-nav" id="chatlist">

                            </div>
                        </div>
                    </div>
                    <!--  row-offcanvas-right -->
                </div>
                <!-- row-offcanvas-left -->
            </div>
        </div>

        <jsp:include page="Messages.jsp" />  
        <!--<script>document.getElementById("chat1").style.display='none';</script>-->

        <div class="modal fade" id="logoutmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">Log Out</h4>
                    </div>
                    <form class="form-horizontal" id="logout_form" action="controller_servl" method="post">
                        <div class="modal-body">

                            Are you sure you want to log out?

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary" name="event" value="LOGOUT" form="logout_form" >Log Out</button>
                        </div>
                    </form>

                </div>

            </div>
        </div>
        <div class="modal fade" id="myprofilemodal" tabindex="-1" role="dialog" aria-labelledby="editfamily" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">My Profile</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <div class="thumbnail" style="width: 150px; height: 150px;">
                                    <img data-src="holder.js/100%x100%" alt="...">
                                </div>
                            </div>
                            <div class="col-sm-8">
                                <div class="row">
                                    <label class="col-sm-4 col-sm-offset-1" for="lnam">Last Name:</label>
                                    <p class="col-sm-7">${json.cur_user.lastName} </p>
                                </div>
                                <div class="row">
                                    <label class="col-sm-4 col-sm-offset-1" for="fnam">First Name:</label>
                                    <p class="col-sm-7">${json.cur_user.firstName}</p>
                                </div>
                                <div class="row">
                                    <label class="col-sm-4 col-sm-offset-1" for="email">Email:</label>
                                    <p class="col-sm-7">${json.cur_user.email}</p>
                                </div>
                                <div class="row">
                                    <label class="col-sm-4 col-sm-offset-1" for="birth">Birth Date:</label>
                                    <p class="col-sm-7">${json.cur_user.birthdate}</p>
                                </div>
                                <div class="row">
                                    <label class="col-sm-4 col-sm-offset-1" for="birth">Town:</label>
                                    <p class="col-sm-7">${json.cur_user.town}</p>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="checkbox_changepassword">
                                    <label>
                                        <input type="checkbox" id="checkbox_changepasswordval" value="0">Change Password</label>
                                </div>
                            </div>
                        </div>
                        <div class="repeat2" id="changepasswordview" style="display:none">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label for="oldpass">Old Password</label>
                                    <input class="form-control" type="text" id="oldpass" placeholder="Old Password"></input>
                                </div>
                                <div class="col-sm-6">
                                    <label for="newpass">New Password</label>
                                    <input class="form-control" type="text" id="newpass" placeholder="New Password"></input>
                                </div>
                                <div class="col-sm-6">
                                    <label for="retypenewpass">Retype Password</label>
                                    <input class="form-control" type="text" id="retypenewpass" placeholder="Retype Password"></input>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button id="savepassmodal" type="button" style="display:none" class="btn btn-primary">Save</button>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>



