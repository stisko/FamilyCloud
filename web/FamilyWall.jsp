<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-sm-8 col-xs-12 " id="maincontent">
    <div class="col-sm-12" id="contentheader">
        <div class="media col-sm-4">
            <div class="media-left media-middle">
                <a href="#">
                    <img class="media-object" width="45" height="45" src="img/family_wall.png">
                </a>
            </div>
            <div class="media-body">
                <h4 class="media-heading">Family Wall</h4>
            </div>
        </div>

        <div id="suc_todo_mes" class="${noti_message.classs}">${noti_message.message}</div>
    </div>
    <div class="col-sm-12" id="contentcontent">
        <div class="col-sm-10 col-sm-offset-1 panelpost">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headpost">
                    <div class="panel-title" align="center">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapsepost"><span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span>
                            <font size="3">Click To Post</font>
                        </a>
                    </div>
                </div>
                <div id="collapsepost" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                    <form id="formpost" name="formpost"  enctype="multipart/form-data" >
                        <div class="panel-body">
                            <textarea id="postareatext" name="postareatext" form="formpost" class="form-control blacktextarea" rows="3"></textarea>
                            <!--                            <a class="btn fileUpload" type="file" href="#">-->

                            <input id="file-id" type="file" name="our-file" />

                            <!--</a>-->


                            <div class=""> 
                                <div class="btn-toolbar pull-right">
                                    <div class="btn-group ">
                                        <button type="button" class="btn btn-default" id="closepost">Close</button>
                                    </div>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary" onclick="initFullFormAjaxUpload()" id="postbutton" >Post</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <div class="post_area">
            <ul style="padding:0px; list-style: none;">

                <c:forEach items="${familyPosts}" var="family_post">
                    <li>
                        <div class="col-sm-12" id="post_box">
                            <div class="col-sm-2 col-xs-2 post_user">
                                <img class="img-rounded img-responsive pull-right" alt="Notifications" width="50" height="50" src="controller_servl?event=GETPIC&username=${family_post.created_by}">
                            </div>
                            <div class="col-sm-10 col-xs-10 post_body" id="post_body">
                                <div class="bubble">
                                    <div class="bubble_head">
                                        <div class="btn-toolbar pull-right" role="toolbar" aria-label="true">
                                            <div class="btn-group btn-group-xs" role="group" aria-label="true">
                                                <!--<button type="button" id="post_edit_button" data-toggle="modal" data-target="#post_edit_modal" class="btn btn-default pedit_button">Edit</button>-->
                                                <c:choose>
                                                    <c:when test="${(family_post.created_by eq cur_user.username) || (family_post.director_username eq cur_user.username)}">
                                                        <button type="button" id="post_delete_button" data-toggle="modal" data-target="#post_delete_modal" class="btn btn-default pdelete_button" onclick="deletePost('${family_post.idWallPost}')">Delete</button>
                                                    </c:when>
                                                </c:choose>

                                            </div>
                                        </div>
                                        <div class="post_username pull-left">${family_post.created_by}</div>
                                        <c:set var="now" value="${family_post.creation_time}" />

                                        <div class="post_timestamp pull-left"><fmt:formatDate type="both" dateStyle="MEDIUM" timeStyle="MEDIUM"
                                                        value="${now}" /></div>
                                    </div>
                                    <div class="bubble_content">
                                        <p>${family_post.message}</p>

                                        <c:if test="${family_post.file != null}">
                                            <div class="img-responsive">
                                                <img class="img-responsive" src="controller_servl?event=GETPOSTPIC&postid=${family_post.idWallPost}">
                                            </div>
                                        </c:if>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>

                </c:forEach>
                    
                    <c:choose>
                        <c:when test="${emptya eq '1'}">
                            <div class="col-sm-12">
                            <img class="img-responsive" src="img/emptyWall.png">
                            </div>
                        </c:when>
                    </c:choose>



            </ul>
        </div>
    </div>
</div>




<div class="modal fade" id="post_edit_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Edit Post</h4>
            </div>
            <div class="modal-body">
                <textarea id="postareatext" class="form-control blacktextarea" rows="3" placeholder="Edit post text"></textarea>
                <a class="btn fileUpload" type="file" href="#">
                    <img alt="Notifications" width="24" height="20" src="img/uploadphoto.gif">


                    <input type="file" class="upload" id="photoinput" />


                </a>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Ok</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="post_delete_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" id="deletepost_modal">

        </div>
    </div>
</div>