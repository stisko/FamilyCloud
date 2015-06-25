<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${SortedfamToDo}" var="sftd" varStatus="loopStatus">
    <tr>

        <td>${loopStatus.index+1}</td>
        <td>${sftd.title}</td>                                    
        <td>${sftd.assignedTo}</td> 
        <td>${sftd.createdBy}</td> 
        <td>${sftd.dueDate}</td> 
        <td>${sftd.completedDate}</td>
        <td>${sftd.status}</td>

        <td><button class="btn btn-default disabled " href="#" data-toggle="modal" data-target="#edittask" value="${sftd.itemID}" onclick="editToDoItem(this.value)"><span class="glyphicon glyphicon-pencil"> Edit</span></button></td>
        <td><button class="btn btn-default disabled " href="#" data-toggle="modal" data-target="#deletemodal" value="${sftd.itemID}" onclick="deleteToDoItem(this.value)"><span class="glyphicon glyphicon-remove"> Delete</span></button></td>


    </tr>
</c:forEach>