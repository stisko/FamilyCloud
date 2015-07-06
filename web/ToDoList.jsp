

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-sm-8 col-xs-12 " id="maincontent">
    <div class="col-sm-12" id="contentheader">
        <div class="media col-sm-4">
            <div class="media-left media-middle ">
                <a href="#">
                    <img class="media-object" width="45" height="45" src="img/Todo_list.png">
                </a>
            </div>
            <div class="media-body " >
                <h4 class="media-heading">To-Do List</h4>

            </div>



        </div>

        <div id="suc_todo_mes" class="${noti_message.classs}">${noti_message.message}</div>

    </div>
    <div class="col-sm-12" id="contentcontent">

        <div class="col-sm-12 ">			

            <div class="btn-toolbar" role="toolbar" id="todolistbuttonbar"> 

                <div class="btn-group" role="group">

                    <button class="btn btn-primary" id="addtaskbutton" data-toggle="modal" data-target="#addtask" >
                        <span class="glyphicon glyphicon-plus-sign" aria-hidden="true">
                        </span>Add Task</button>
                </div>
                <div class="btn-group" role="group">
                    <a class="btn btn-primary" id="custom-prev-list" href="#" role="button"><span class="glyphicon glyphicon-circle-arrow-left" aria-hidden="true"></span> Prev</a>
                    <a class="btn btn-primary" id="custom-next-list" href="#" role="button">Next <span class="glyphicon glyphicon-circle-arrow-right" aria-hidden="true"></span></a>
                </div>
                <a class="btn btn-primary" id="printtaskbutton" href="javascript:window.print()" role="button"><span class="glyphicon glyphicon-print" aria-hidden="true"></span>Print To-Do List</a>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12 mavroperivlima" >
                <div class="perivlimatable2">
                    <div class="table-responsive ">
                        <table class="table" id="todolisttable">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th><input class="stat_sort" type="button" value="Title" onclick="sorttableshop('title')"></th>
                                    <th><input class="stat_sort" type="button" value="Assigned To" onclick="sorttableshop('assigned_to')"></th>
                                    <th><input class="stat_sort" type="button" value="Created By" onclick="sorttableshop('created_by')"></th>
                                    <th><input class="stat_sort" type="button" value="Due Date" onclick="sorttableshop('due_date')"></th>
                                    <th><input class="stat_sort" type="button" value="Date Completed" onclick="sorttableshop('completed_date')"></th>
                                    <th><input class="stat_sort" type="button" value="status" onclick="sorttableshop(this.value)"></th>
                                    <th></th>
                                    <th></th>							
                                </tr>
                            </thead>
                            <tbody id="changedTable">
                                <c:choose>
                                    <c:when test="${emptya eq '1'}">
                                    <div class="col-sm-12" style="color:  white"><b>You have no events</b></div>
                                </c:when>
                                </c:choose>

                                <c:forEach items="${famToDo}" var="famToDo" varStatus="loopStatus">
                                    <tr>
                                        <td>${loopStatus.index+1}</td>
                                        <td>${famToDo.title}</td>                                    
                                        <td>${famToDo.assignedTo}</td> 
                                        <td>${famToDo.createdBy}</td> 
                                        <td>${famToDo.dueDate}</td> 
                                        <td>${famToDo.completedDate}</td>
                                        <td>${famToDo.status}</td>

                                        <c:choose>
                                            <c:when test="${(famToDo.createdBy eq cur.username)||(cur.director eq 'Y')}">
                                                <td><button class="btn btn-default"href="#" data-toggle="modal" data-target="#edittask" value="${famToDo.itemID}" onclick="editToDoItem(this.value)"><span class="glyphicon glyphicon-pencil"> Edit</span></button></td>
                                                <td><button class="btn btn-default" href="#" data-toggle="modal" data-target="#deletemodal" value="${famToDo.itemID}" onclick="deleteToDoItem(this.value)"><span class="glyphicon glyphicon-remove"> Delete</span></button></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><button class="btn btn-default disabled " href="#" data-toggle="modal" data-target="#edittask" value="${famToDo.itemID}" onclick="editToDoItem(this.value)"><span class="glyphicon glyphicon-pencil"> Edit</span></button></td>
                                                <td><button class="btn btn-default disabled " href="#" data-toggle="modal" data-target="#deletemodal" value="${famToDo.itemID}" onclick="deleteToDoItem(this.value)"><span class="glyphicon glyphicon-remove"> Delete</span></button></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>





<div class="modal fade" id="deletemodal" tabindex="-1" role="dialog" aria-labelledby="deletelabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Delete</h4>
            </div>
            <div class="modal-body" id="delete_to_do_modalBody">

            </div>
        </div>
    </div>
</div>



<div class="modal fade" id="edittask" tabindex="-1" role="dialog" aria-labelledby="edittasklabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Edit Task</h4>
            </div>
            <div id="editToDo_modal_body" >

            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="addtask" tabindex="-1" role="dialog" aria-labelledby="addtasklabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Add Task</h4>
            </div>


            <form id="addtaskform" name="formaddToDo" action="controller_servl" method="post">
                <div class="modal-body">

                    <div class="row">									
                        <div class="col-sm-6">				
                            <label for="titleaddtask">Task Title</label>
                            <input type="text" name="addtodotitle" class="form-control" id="titleaddtask" oninput="style_inp('titleaddtask')" placeholder="Enter Title" required><span>You have to enter a title</span>
                        </div>									
                        <div class="col-sm-6">				
                            <label for="duedateaddtask">Due Date</label>
                            <input type="date" class="form-control" name="addtododuedatetask" id="duedateaddtask" oninput="style_inp('duedateaddtask')" placeholder="yyyy-MM-dd" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" required><span>Invalid Date Input</span>
                        </div>								
                    </div>

                    <div class="row">																
                        <div class="col-sm-6">
                            <label>Assigned To</label>		
                            <select class="form-control" id="assignedToDo" onfocus="antegeia2()" multiple="multiple">
                                <c:forEach items="${userlistTODO}" var="userlistTODO">

                                    <option>${userlistTODO.username}</option>

                                </c:forEach>

                            </select>
                        </div>
                        <div class="col-sm-6" id="addtodoradio">				
                            <label for="checkaddpending">Status</label>
                            <div class="radio" id="checkaddpending">
                                <label>
                                    <input name="statusradio" type="radio" value="Pending">
                                    Pending
                                </label>
                            </div>
                            <div class="radio" id="checkaddcomplete">
                                <label>
                                    <input name="statusradio" type="radio" value="Completed" >
                                    Completed
                                </label>
                            </div>

                        </div>
                    </div>







                </div>

                <div class="modal-footer">

                    <div id="suc_todo_mes_valid"style="font-size:120%" ></div>                        
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" id="addtodobutton" form="addtaskform" onclick="insertToDoItem()">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>	

