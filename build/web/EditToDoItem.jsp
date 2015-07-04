<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <form id="editaskform" name="edittodoform">
                <div class="modal-body" id="editToDo_modal_body">

              
<div class="row">									
    <div class="col-sm-6">				
        <label for="titleedittask">Task Title</label>
        <input type="text" class="form-control" id="titleedittask" name="titleedittodo" placeholder="Enter Title" value="${json.item.title}" required><span>You have to enter a title</span>
    </div>									

    <div class="col-sm-6">
        <label for="duedateedittask">Due Date</label>
        <input type="date" class="form-control" id="duedateedittask" name="duedateedittodo" placeholder="yyyy-MM-dd" value="${json.item.dueDate}" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" required><span>Invalid Date Input</span>
    </div>
</div>
<div class="row">

    <div class="col-sm-6">				

        <label>Assigned To</label>		
        <select class="form-control" id="assignedTo_edittodo" onfocus="antegeia()" multiple>

            <c:forEach items="${userslist}" var="insta" >
                <c:set var="errors" value="false"/>

                <c:forEach items="${seluserlist}" var="se">

                    <c:if test="${se eq insta.username}">
                        <c:set var="errors" value="true"/>

                    </c:if>

                </c:forEach>

                <c:if test="${errors}">
                    <option selected><c:out value="${insta.username}"></c:out> </option>

                </c:if>

                <c:if test="${!errors}">
                    <option ><c:out value="${insta.username}"></c:out> </option>
                </c:if>



            </c:forEach>

        </select>

    </div>
    
    <div class="col-sm-6">				
        <label for="checkaddpending">Status</label>
        <div class="radio" id="checkaddpending">
            <label>
                <input name="statusradio" type="radio" value="Pending" ${json.pending} required>
                Pending
            </label>
        </div>
        <div class="radio" id="checkaddcomplete">
            <label>
                <input name="statusradio" type="radio" value="Completed" ${json.completed}>
                Completed
            </label>
        </div>
    </div>

    
</div>
</div>

<div class="modal-footer">
    <div id="suc_todo_mes_valid_ed" style=" width:70%"></div> 
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-primary" data-dismiss="modal" value="${json.item.itemID}" id="edittodobutton" onclick="Save_changes_todo(this.value)">Save</button>
</div>

  
            </form>