<div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>  <span class="sr-only">close</span>
        </button>
        <h4 id="modalTitle" class="modal-title">${eventPersonal.title}</h4>
    </div>
    <div id="modalBody" class="modal-body">
        <div class=" row">
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="lnam">Event Title: </label>
                <p class="col-sm-7">${eventPersonal.title} </p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="fnam">Start Date: </label>
                <p class="col-sm-7">${eventPersonal.start_date}</p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="email">End Date: </label>
                <p class="col-sm-7">${eventPersonal.end_date}</p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="birth">Location: </label>
                <p class="col-sm-7">${eventPersonal.location}</p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="birth">Description: </label>
                <p class="col-sm-7">${eventPersonal.description}</p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="birth">Notifications Before:</label>
                <p class="col-sm-7">${eventPersonal.notificationTime} ${event.notificationDate}</p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="birth">Repeat Every: </label>
                <p class="col-sm-7">${eventPersonal.repeatTime} ${event.repeat_every}</p>
            </div>
            <div class="row">
                <label class="col-sm-4 col-sm-offset-1" for="birth">Repeat Until: </label>
                <p class="col-sm-7">${eventPersonal.endRepeatDate}</p>
            </div>
        </div>
        
        
        
        
    </div>
    <div class="modal-footer">
        <div class="btn-toolbar">
            <div class="btn-group">
                <button type="button" class="btn btn-default" id="event_view" onclick="viewEventper('${eventPersonal.idPerCalEvent}')"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"  ></span> View</button>
                <button type="button" class="btn btn-default" id="event_edit" onclick="editPersonalEvent('${eventPersonal.idPerCalEvent}')"><span class="glyphicon glyphicon-edit"  aria-hidden="true" ></span> Edit</button>
                <button type="button" class="btn btn-default" id="event_delete" onclick="deletePersonalEvent('${eventPersonal.idPerCalEvent}')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>
            </div>
            <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
        </div>
    </div>
</div>