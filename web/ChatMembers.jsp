<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="nav nav-pills nav-stacked">
    
    <c:forEach items="${members}" var="members">
        <li role="presentation"  onclick="show_chatbox('chat${members.username}'); RefreshMessage('${members.username}');">
            <a  id="user1" >
                <img class="img-rounded" alt="Notifications" width="50" height="50" src="controller_servl?event=GETPIC&username=${members.username}" ><span> ${members.lastName} ${members.firstName}</span>
            </a>
        </li>
    </c:forEach>
        
</ul>


