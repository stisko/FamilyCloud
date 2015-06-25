<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${messages}" var="messages">

    <c:if test="${messages.sender == json.cur_user.username}">
        <div class="msg_b">${messages.message}</div>

            <c:if test="${messages.file != null}">
                <div class="img-responsive msg_b">
                    <img class="img-responsive" src="controller_servl?event=GETMESSAGEPIC&messageid=${messages.idMessage}">
                </div>
            </c:if>
        

    </c:if>

    <c:if test="${messages.sender != json.cur_user.username}">
        <div class="msg_a">${messages.message}</div>
            <c:if test="${messages.file != null}">
                <div class="img-responsive msg_a">
                    <img class="img-responsive" src="controller_servl?event=GETMESSAGEPIC&messageid=${messages.idMessage}">
                </div>
            </c:if>
        
    </c:if>



</c:forEach>
<div class="msg_push"></div>