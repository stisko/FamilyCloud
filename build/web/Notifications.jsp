<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:forEach items="${notification_list}" var="notifications">

    <div class="media notification_list" onclick="showSelectedNotification('${notifications.idnotifications}');
            redirectTo('${notifications.notification_type}');">
        <li>
            <a style="color: black;">

                <c:choose >
                    <c:when test="${notifications.notification_type eq 'messages'}" >
                        <div class="media-left ">
                            <img class="img-rounded media-object" src="controller_servl?event=GETPIC&username=${notifications.usernameA}" style="margin: 5px 5px 5px 5px;" width="64" height="64" alt="...">
                        </div>
                    </c:when>
                    <c:when test="${fn:contains(notifications.notification_type,'famcalevents')}" >
                         <div class="media-left ">
                             <img class="img-rounded media-object" src="img/Calendar.png" style="margin: 5px 5px 5px 5px;" width="64" height="64" alt="...">
                        </div>
                    </c:when>
                    <c:when test="${fn:contains(notifications.notification_type,'shopping_list')}" >
                         <div class="media-left ">
                             <img class="img-rounded media-object" src="img/Shopping.png" style="margin: 5px 5px 5px 5px;" width="64" height="64" alt="...">
                        </div>
                    </c:when>
                    <c:when test="${fn:contains(notifications.notification_type,'wall_post')}" >
                         <div class="media-left ">
                             <img class="img-rounded media-object" src="img/family_wall.png" style="margin: 5px 5px 5px 5px;" width="64" height="64" alt="...">
                        </div>
                    </c:when>
                    <c:when test="${fn:contains(notifications.notification_type,'todo_list')}" >
                         <div class="media-left ">
                             <img class="img-rounded media-object" src="img/Todo_list.png" style="margin: 5px 5px 5px 5px;" width="64" height="64" alt="...">
                        </div>
                    </c:when>
                    <c:otherwise>
                        
                    </c:otherwise>

                </c:choose>



                <div class="media-body">
                    <h4 class="media-heading">${notifications.notification_type}</h4>
<!--                    <a href="#" id="noti" style="color: black;">${notifications.message}</a>-->
                    ${notifications.message}
                </div>
            </a>
        </li>
    </div>

</c:forEach>


