<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>  <span class="sr-only">close</span>
        </button>
        <h4 id="modalTitle" class="modal-title">${event.title}</h4>
    </div>
    <div id="modalBody" class="modal-body">

        ${event.description}
        ${event.start_date}
    </div>
    <div class="modal-footer">
        <div class="btn-toolbar">
            <div class="btn-group">
                <button type="button" class="btn btn-default" id="event_view" onclick="viewMealsEvent('${event.idMealsPlanEvent}')"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"  ></span> View</button>
                <c:choose>
                    <c:when test="${(event.created_by eq cur_user.username) || (event.director_username eq cur_user.username)}">                
                        <button type="button" class="btn btn-default" id="event_edit" onclick="editMealsEvent('${event.idMealsPlanEvent}')"><span class="glyphicon glyphicon-edit"  aria-hidden="true" ></span> Edit</button>
                        <button type="button" class="btn btn-default" id="event_delete" onclick="deleteMealsEvent('${event.idMealsPlanEvent}')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-default" id="event_edit" onclick="editMealsEvent('${event.idMealsPlanEvent}')" disabled><span class="glyphicon glyphicon-edit"  aria-hidden="true" ></span> Edit</button>
                        <button type="button" class="btn btn-default" id="event_delete" onclick="deleteMealsEvent('${event.idMealsPlanEvent}')" disabled><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>    
                    </c:otherwise>
                </c:choose>
            </div>
            <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
        </div>
    </div>
</div>