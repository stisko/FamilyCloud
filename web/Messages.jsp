<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="chat_box_area visible-sm visible-md visible-lg " id="chat_box_area">

    <c:forEach items="${members}" var="members">

        <div class="collapse chat_box" id="chat${members.username}" style="display: none" >
            <div class="chat_head" data-toggle="collapse"  data-target="#chat_wrap${members.username}">${members.lastName} ${members.firstName}
                <div class="chat_close" onclick="show_chatbox('chat${members.username}')"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </div>
            </div>
            <div class="chat_wrap collapse panel-collapse" id="chat_wrap${members.username}">
                <div class="chat_body" id="chat_body${members.username}">



                </div>
                <div class="chat_footer">
                    <form id="formmessage${members.username}" name="formmessage${members.username}" enctype="multipart/form-data" >   
                        <div>
                            <textarea id="postareatext" name="postareatext" rows="2" form="formmessage${members.username}" class="form-control msg_input" onkeyup=""  onkeypress="if (event.keyCode === 13 && !event.ctrlKey) {
                                    initFullFormAjaxUploadChat('${members.username}');
                                    this.value = '';
                                }"></textarea>

                            <div class="btn btn-primary fileUpload uploadpos"><span class="glyphicon glyphicon-paperclip"></span>
                                <input id="file-id" class="upload" type="file" name="our-file" style="background-color: white"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </c:forEach>




</div>