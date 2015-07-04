<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>  <span class="sr-only">close</span>
        </button>
        <h4 id="modalTitle" class="modal-title">${event.title}</h4>
    </div>
    <div id="modalBody" class="modal-body">


        <div class=" row">
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="lnam">Event Title: </label>
                <p class="col-sm-7">${event.title} </p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="fnam">Start Date: </label>
                <p class="col-sm-7">${event.start_date}</p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="email">End Date: </label>
                <p class="col-sm-7">${event.end_date}</p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="birth">Location: </label>
                <p class="col-sm-7">${event.location}</p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="birth">Participating Members: </label>
                <p class="col-sm-7">${event.participating_members}</p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="birth">Description: </label>
                <p class="col-sm-7">${event.description}</p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="birth">Notifications Before:</label>
                <p class="col-sm-7">${event.notificationTime} ${event.notificationDate}</p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="birth">Repeat Every: </label>
                <p class="col-sm-7">${event.repeatTime} ${event.repeat_every}</p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="birth">Repeat Until: </label>
                <p class="col-sm-7">${event.endRepeatDate}</p>
            </div>
        </div>

    </div>
            


    <div class="modal-footer">
        <div class="btn-toolbar">
            <div class="btn-group">

                <button type="button" class="btn btn-default" id="event_view" onclick="viewEvent('${event.idFamCalEvent}')"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"  ></span> View</button>
                <c:choose>
                    <c:when test="${(event.created_by eq cur_user.username) || (event.director_username eq cur_user.username)}">
                        <button type="button" class="btn btn-default" id="event_edit" onclick="editEvent('${event.idFamCalEvent}')"><span class="glyphicon glyphicon-edit"  aria-hidden="true" ></span> Edit</button>
                        <button type="button" class="btn btn-default" id="event_delete" onclick="deleteEvent('${event.idFamCalEvent}')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-default" id="event_edit" onclick="editEvent('${event.idFamCalEvent}')" disabled><span class="glyphicon glyphicon-edit"  aria-hidden="true" ></span> Edit</button>
                        <button type="button" class="btn btn-default" id="event_delete" onclick="deleteEvent('${event.idFamCalEvent}')" disabled><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>
                    </c:otherwise>
                </c:choose>
            </div>
            <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
        </div>
    </div>
</div>