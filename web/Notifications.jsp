<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${notification_list}" var="notifications">

    <div class="media notification_list" onclick="showSelectedNotification('${notifications.idnotifications}');
            redirectTo('${notifications.notification_type}')">
        <li>
            <a style="color: black;">
                <div class="media-left ">

                    <img class="img-rounded media-object" src="controller_servl?event=GETPIC&username=${notifications.usernameA}" style="margin: 5px 5px 5px 5px;" width="64" height="64" alt="...">

                </div>
                <div class="media-body">
                    <h4 class="media-heading">${notifications.notification_type}</h4>
<!--                    <a href="#" id="noti" style="color: black;">${notifications.message}</a>-->
                    ${notifications.message}
                </div>
            </a>
        </li>

    </div>



</c:forEach>


