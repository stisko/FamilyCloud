<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-sm-8 col-xs-12 " id="maincontent">

    <div class="col-sm-12" id="contentheader">
        <div class="media">
            <div class="media-left media-middle">
                <a href="#">
                    <img class="media-object" width="45" height="45" src="img/my_family.png">
                </a>
            </div>
            <div class="media-body">
                <h4 class="media-heading">My Family</h4>
            </div>
        </div>
    </div>

    <div class="col-sm-12" id="contentcontent">


        <div class="row">


            <h4>Family Director</h4>



            <div class="col-sm-4 col-md-3 col-xs-4">
                <div class="thumbnail family_director">
                    <img class="img-rounded" src="controller_servl?event=GETPIC&username=${json.director.username}">
                    <div class="caption">
                        <h4>${json.director.firstName} ${json.director.lastName}</h4>

                        <button style="${json.hidden}" class="btn btn-default btn-sm" role="button" data-toggle="modal" data-target="#editfamilydirector"><span class="glyphicon glyphicon-pencil" > Edit</span>
                        </button>
                        <button class="btn btn-default btn-sm" role="button" data-toggle="modal" data-target="#viewfamilydirector"><span class="glyphicon glyphicon-eye-open"> View</span>
                        </button>

                    </div>
                </div>
            </div>


        </div>
        <div class="row">
            <div class="col-sm-12">
                <button class="btn btn-primary pull-right" id="addfamilymemberbutton" href="#" role="button" data-toggle="modal" data-target="#addfamilymembermodal">
                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>Add Family Member</button>
            </div>

        </div>
        <hr class="style-eight">
        <h4>Family Members</h4>
        <ul class="nav" id="membersnavigation">

            <c:forEach items="${family}" var="family_list">
                <li>
                    <div class="col-sm-4 col-md-3 col-xs-4">
                        <div class="thumbnail family_members">
                            <img class="img-rounded" src="controller_servl?event=GETPIC&username=${family_list.username}">
                            <div class="caption">
                                <h4>${family_list.lastName} ${family_list.firstName}</h4>

                                <c:choose>
                                    <c:when test="${family_list.username eq json.cur_user.username}">
                                        <button class="btn btn-default btn-sm" role="button" data-toggle="modal" data-target="#editfamilymember" value="${family_list.username}" onclick="editMemberProfile(this.value)"><span class="glyphicon glyphicon-pencil" > Edit</span></button>
                                    </c:when>
                                    <c:otherwise>
                                        <button style="${json.hidden}" class="btn btn-default btn-sm" role="button" data-toggle="modal" data-target="#editfamilymember" value="${family_list.username}" onclick="editMemberProfile(this.value)"><span class="glyphicon glyphicon-pencil" > Edit</span></button>
                                    </c:otherwise>
                                </c:choose>
                                
                                <button class="btn btn-default btn-sm" type="button" data-toggle="modal" data-target="#viewfamilymembermodal" value="${family_list.username}" onclick="viewMemberProfile(this.value)"><span class="glyphicon glyphicon-eye-open"> View</span></button>
                                <button style="${json.hidden}" class="btn btn-default btn-sm" role="button" data-toggle="modal" data-target="#deletemodal" value="${family_list.username}" onclick="load_member(this.value)"><span class="glyphicon glyphicon-trash" > Delete</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </li>

            </c:forEach>

        </ul>
    </div>
</div>

<div class="modal fade" id="addfamilymembermodal" tabindex="-1" role="dialog" aria-labelledby="editfamily" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Add Family Member</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-8">
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="lastnameaddmember">Last Name</label>
                                <input type="text" class="form-control" id="lastnameaddmember" placeholder="Last Name">
                            </div>
                            <div class="col-sm-6">
                                <label for="firstnameaddmember">First Name</label>
                                <input type="text" class="form-control" id="firstnameaddmember" placeholder="First Name">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="emailaddmember">Email</label>
                                <input type="text" class="form-control" id="emailaddmember" placeholder="Email">
                            </div>
                            <div class="col-sm-6">
                                <label for="birthaddmember">Birth Date</label>
                                <input type="date" class="form-control" id="birthaddmember" placeholder="MM-DD-YYYY">
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="">Adult
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="">Kid *(Under 18)
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal"  onclick="AddMember()">Add</button>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="viewfamilydirector" tabindex="-1" role="dialog" aria-labelledby="editfamily" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">View Family Director</h4>
            </div>
            <div class="modal-body">

                <div class="row">


                    <div class="col-sm-3">


                        <div class="thumbnail" style="width: 150px; height: 150px;">
                            <img class="img-rounded" src="controller_servl?event=GETPIC&username=${json.director.username}">
                        </div>




                    </div>


                    <div class="col-sm-8">


                        <div class="row">
                            <label class="col-sm-4 col-sm-offset-1" for="lnamdirect">Last Name:</label>
                            <p class="col-sm-7">${json.director.lastName}</p>
                        </div>

                        <div class="row">
                            <label class="col-sm-4 col-sm-offset-1" for="fnamdirect">First Name:</label>
                            <p class="col-sm-7">${json.director.firstName}</p>
                        </div>

                        <div class="row">
                            <label class="col-sm-4 col-sm-offset-1" for="emaildirect">Email:</label>
                            <p class="col-sm-7">${json.director.email}</p>
                        </div>

                        <div class="row">
                            <label class="col-sm-4 col-sm-offset-1" for="birthdirect">Birth Date:</label>
                            <p class="col-sm-7">${json.director.birthdate}</p>
                        </div>

                        <div class="row">
                            <label class="col-sm-4 col-sm-offset-1" for="towndirect">Town:</label>
                            <p class="col-sm-7">${json.director.town}</p>
                        </div>







                    </div>


                </div>




            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="deletemodal" tabindex="-1" role="dialog" aria-labelledby="deletelabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Delete</h4>
            </div>
            <div class="modal-body" id="modal_body_delete">

            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="editfamilydirector" tabindex="-1" role="dialog" aria-labelledby="editfamily" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Edit Family Director</h4>
            </div>
            <div class="modal-body">
<div class="row">
                <div class="col-sm-4">
                    <div class="fileinput fileinput-new" data-provides="fileinput">
                        <div class="fileinput-new thumbnail" style="width: 150px; height: 150px;">
                            <img class="img-rounded" src="controller_servl?event=GETPIC&username=${json.director.username}">
                        </div>
                        <form action="upload_servl?username=${json.selected_user.username}" role="form"enctype="multipart/form-data" method="post"id="upload_form2">
                            <input type="file" name="file">
                            <button type="submit" form="upload_form2" class="btn btn-success">Upload</button>
                        </form>
                    </div>

                </div>


                



                    <div class="col-sm-8">
                        <form class="editfamilydirector_form" id="editfamilydirector_form" >
                            <div class="row">
                                <div class="col-sm-6">
                                    <label for="lastnameeditdirector">Last Name</label>
                                    <input type="text" class="form-control" id="lastnameeditdirector" name="lastName" placeholder="Last Name" value="${json.director.lastName}"  required>
                                </div>
                                <div class="col-sm-6">
                                    <label for="firstnameeditdirector">First Name</label>
                                    <input type="text" class="form-control" id="firstnameeditdirector" name="firstName" placeholder="First Name" value="${json.director.firstName}"  required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <label for="emaileditdirector">Email</label>
                                    <input type="text" class="form-control" id="emaileditdirector" name="email" placeholder="Email" value="${json.director.email}"  required>
                                </div>
                                <div class="col-sm-6">
                                    <label for="birtheditdirector">Birth Date</label>
                                    <input type="date" class="form-control" id="birtheditdirector" name="birthdate" placeholder="MM-DD-YYYY" value="${json.director.birthdate}"  required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <label for="lastnameeditdirector">Town</label>
                                    <input type="text" class="form-control" id="towndirector" name="town" placeholder="Town" value="${json.director.town}">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary"   data-dismiss="modal" form="editfamilydirector_form" onclick="Update_profile()">Save</button>
                </div>



            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="editfamilymember" tabindex="-1" role="dialog" aria-labelledby="editfamily" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Edit Family Member</h4>
            </div>
            <div class="modal-body" id="edit_modal_body">


            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="viewfamilymembermodal" tabindex="-1" role="dialog" aria-labelledby="editfamily" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">View Family Member</h4>
            </div>
            <div class="modal-body" id="modal_body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>