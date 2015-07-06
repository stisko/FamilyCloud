


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <title>Family Cloud</title>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/mystyle.css">
        <script src="js/start.js"></script>

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
            function getDuplicateUsername(username)
            {

                xmlHttp = GetXmlHttpObject();
                if (xmlHttp == null)
                {
                    alert("Browser does not support HTTP Request")
                    return
                }
                else
                {
                    //sending selected country to servlet
                    var url = "controller_servl?event=AJAX&registerusername=" + username;
                    //creating callback method.here countrychanged is callback method
                    xmlHttp.onreadystatechange = usernameReturn

                    xmlHttp.open("GET", url, true)
                    xmlHttp.send(null)
                }

            }
            function usernameReturn()
            {

                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    //getting response from server(Servlet)

                    var json = JSON.parse(xmlHttp.responseText);



                    //displaying response in select box by using that id
                    document.getElementById("apotelesma2").innerHTML = json.message;
                    document.getElementById("signup_btn").setAttribute("class", json.disabled);


                    if (json.flag == "true") {

//                        document.getElementById("registerusername").removeAttribute("style");

                        document.getElementById("registerusername").style.border = "1px solid #FF0000";

                        document.getElementById("registerusername").style.boxShadow = "0 0 5px #FF0000";

                        document.getElementById("registerusername").style.background = "#fff url(./img/invalid.png) 98% center no-repeat";

                        document.getElementById("registerusername").style.backgroundSize = "22px 22px";




                        document.getElementById("apotelesma2").style.color = "red";

                    } else

                    {


                        document.getElementById("registerusername").removeAttribute("style");


                        document.getElementById("apotelesma2").style.color = "green";


                    }



                }

            }
            function getDuplicateEmails(registeremail)
            {

                xmlHttp = GetXmlHttpObject();
                if (xmlHttp == null)
                {
                    alert("Browser does not support HTTP Request")
                    return
                }
                else
                {
                    //sending selected country to servlet
                    var url = "controller_servl?event=AJAX&registeremail=" + registeremail;
                    //creating callback method.here countrychanged is callback method
                    xmlHttp.onreadystatechange = emailReturn

                    xmlHttp.open("GET", url, true)
                    xmlHttp.send(null)
                }

            }
            function emailReturn()
            {

                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    //getting response from server(Servlet)

                    var json = JSON.parse(xmlHttp.responseText);



                    //displaying response in select box by using that id
                    document.getElementById("apotelesma").innerHTML = json.message;
                    document.getElementById("signup_btn").setAttribute("class", json.disabled);



                    if (json.flag == "true") {

//                        document.getElementById("registerusername").removeAttribute("style");

                        document.getElementById("registeremail").style.border = "1px solid #FF0000";

                        document.getElementById("registeremail").style.boxShadow = "0 0 5px #FF0000";

                        document.getElementById("registeremail").style.background = "#fff url(./img/invalid.png) 98% center no-repeat";

                        document.getElementById("registeremail").style.backgroundSize = "22px 22px";




                        document.getElementById("apotelesma").style.color = "red";

                    } else

                    {


                        document.getElementById("registeremail").removeAttribute("style");


                        document.getElementById("apotelesma").style.color = "green";


                    }





                }
            }


            function forgotreveal() {


                if (document.getElementById("forgotpassword").style.display == 'block') {
                    document.getElementById("forgotpassword").style.display = 'none';
                } else {
                    document.getElementById("forgotpassword").style.display = 'block';
                }



            }


            function sendforgot() {

                xmlHttp = GetXmlHttpObject();
                if (xmlHttp == null)
                {
                    alert("Browser does not support HTTP Request")
                    return
                }
                else
                {

                    var mm = document.getElementById("emailforgpass").value;



                    //sending selected country to servlet
                    var url = "controller_servl?event=EMAILSEN&mail=" + mm;
                    //creating callback method.here countrychanged is callback method
                    // xmlHttp.onreadystatechange = sendforgot_return

                    xmlHttp.open("GET", url, true)
                    xmlHttp.send(null)
                }





            }

            function checkretype_signup(retype_passw) {
                var retype_chk = (document.getElementById("registerpassword").value == retype_passw);
                if (retype_chk) {

                    document.getElementById("registerretypepassword").removeAttribute("style");

                    document.getElementById("signup_btn").setAttribute("class", "btn btn-primary");

                    document.getElementById("apotelesma3").innerHTML = "";
                } else {



                    document.getElementById("registerretypepassword").style.border = "1px solid #FF0000";

                    document.getElementById("registerretypepassword").style.boxShadow = "0 0 5px #FF0000";

                    document.getElementById("registerretypepassword").style.background = "#fff url(./img/invalid.png) 98% center no-repeat";

                    document.getElementById("registerretypepassword").style.backgroundSize = "22px 22px";

                    document.getElementById("apotelesma3").innerHTML = "Password Mismatch";

                    document.getElementById("apotelesma3").style.color = "#FF0000";

                    document.getElementById("signup_btn").setAttribute("class", "btn btn-primary disabled");
                }
            }

        </script>



    </head>



    <body>



        <!-- 88BA68 9A68BA BA7168 68B1BA -->
        <!-- HEADER/JUMBTRON-->
        <div class="navbar navbar-fixed-top navbar-default" role="navigation" >
            <div class="container">

                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar2">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>

                    </button>                

                    <a class="navbar-brand" href="#">
                        <img class="img-responsive" alt="Notifications" width="100" height="70" src="img/cloud.png">
                        <h3 class="headerspan">Family Cloud</h3>
                    </a>
                </div>

                <div class="collapse navbar-collapse" id="myNavbar2">
                    <ul class="nav navbar-nav navbar-right">

                        <li role="presentation"><a id="registerbutton" href="#" data-toggle="modal" data-target="#signupmodal" onclick="MyFunctionsignup();
                                return false;">Sign Up</a>
                        </li>
                        <li role="presentation"><a id="loginbutton" href="#" data-toggle="modal" data-target="#loginmodal" onclick="MyFunctionlogin();
                                return false;" >Login</a>
                        </li>

                    </ul>
                </div>
            </div>

        </div>

        <div class="fullcont">

            <div class="container">

                <div class="${alert_class}" role="alert" ><b>${alert_message}</b></div>


                <p class="headerp">Family cloud not that you want ...but that you need</p>
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>

                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">

                        <div class="item active">
                            <img src="img/family1.jpg" alt="Chania">

                        </div>

                        <div class="item">
                            <img src="img/family2.jpg" alt="Chania" width="460" height="345">

                        </div>

                        <div class="item">
                            <img src="img/family3.jpg" alt="Flower" width="460" height="345">

                        </div>



                    </div>

                    <!-- Left and right controls -->
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>



                <div class="row">
                    <div class="col-md-3">
                        <div class="animated1"><h3>Family Organizer</h3>
                            <p>Family Organizer siplifies daily tasks .Create and share shopping lists, to do-lists and meals in real time</p></div>
                    </div>
                    <div class="col-md-3"> 
                        <div class="animated2"><h3>Family Chat</h3>
                            <p>Get Closer Family Members Anytime Anywhere.Access and update from any mobile device or computer</p></div>
                    </div>
                    <div class="col-md-3"> 
                        <div class="animated3"><h3>Family Calendar</h3>
                            <p>Categorize Family Members Tasks.Keep track of everyone's schedules, activities and appointments, all in one place</p></div>
                    </div>

                    <div class="clearfix visible-lg"></div>
                </div>

            </div>
        </div>





        <div class="modal fade" id="signupmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Sign up</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" id="signup_form" action="controller_servl" method="post">
                            <div class="form-group ">
                                <label for="registername" class="col-sm-2 control-label">First name</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="registername" name="firstname_n" placeholder="First Name" required><span>You have to enter first name</span>
                                </div>



                            </div>
                            <div class="form-group">
                                <label for="registerlname" class="col-sm-2 control-label">Last name</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="registerlname" name="lastname_n" placeholder="Last Name" required><span>You have to enter last name</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="registerusername" class="col-sm-2 control-label">Username</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="registerusername" name="username_n"  placeholder="Username" oninput="getDuplicateUsername(this.value)" required>
                                    <div id="apotelesma2"></div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="registerpassword" class="col-sm-2 control-label">Password</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="registerpassword" name="password_n" placeholder="6 digits at least" required pattern="(?=^.{6,}$)((?=.*[A-Z])|(?=.*[a-z])|(?=.*[0-9])).*$"><span>You have to enter valid password</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="registerretypepassword" class="col-sm-2 control-label">Retype Password</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="registerretypepassword" name="passwordrep_n" oninput="checkretype_signup(this.value)" placeholder="6 digits at least" required pattern="(?=^.{6,}$)((?=.*[A-Z])|(?=.*[a-z])|(?=.*[0-9])).*$"><span>You have to reenter valid password</span>
                                    <div id="apotelesma3"></div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="registerdate" class="col-sm-2 control-label">Birth date</label>
                                <div class="col-sm-8">
                                    <input type="date" class="form-control" id="registerdate" name="birthdate_n" placeholder="YYYY-MM-DD" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" required><span>Invalid Date input</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="registeremail" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="registeremail" name="email_n" placeholder="someone@hotmail.com" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" oninput="getDuplicateEmails(this.value)" required><span>You have to enter a valid email address</span>
                                    <div class="" id="apotelesma"></div>

                                </div>


                            </div>

                            <div class="form-group">
                                <label for="registertown" class="col-sm-2 control-label">Town</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="registertown" name="town_n" placeholder="Town">
                                </div>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="submit" id="signup_btn" class="btn btn-primary" name="event" value="SIGNUP" form="signup_form" >Sign Up</button>
                            </div>


                        </form>
                    </div>

                </div>
            </div>
        </div>



        <div class="modal fade" id="loginmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Log In</h4>
                    </div>
                    <div class="modal-body">
                        <form id="login_form" class="form-horizontal" action="controller_servl" method="post">
                            <div class="form-group">
                                <label for="login-username" class="col-sm-2 control-label">Username</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="login-username" placeholder="Username"name="username_n" required>
                                    <span> You have to enter username</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="login-password" class="col-sm-2 control-label">Password</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="login-password" placeholder="Password" name="password_n" required>
                                    <span> You have to enter password</span>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <a id="forgotpassnotif" onclick="forgotreveal()">Forgot your password ?</a>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary" name="event" value="LOGIN" form="login_form" >Log in</button>
                            </div>
                        </form>


                        <div class="forgpass" id="forgotpassword" style="display:none">

                            <form id="login_form_mail" class="form-horizontal" action="controller_servl" method="post"> 

                                <div class="form-group">
                                    <label for="emailforgpass" class="col-sm-2 control-label">Email</label>
                                    <div class="col-sm-8">
                                        <input type="text" id="emailforgpass" class="form-control" placeholder="Email" name="mailret"></input>
                                        <div class="" id="apotelesma"></div>

                                    </div>


                                </div>

                                <div class="row">
                                    <div class="col-sm-2 col-sm-offset-10">
                                        <button type="submit" class="btn btn-primary" name="event" value="EMAILSEN" form="login_form_mail">Send</button>
                                    </div>          
                                </div>





                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>




    </body>

</html>



