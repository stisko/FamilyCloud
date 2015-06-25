<div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">Delete Event</h4>
    </div>
    <div class="modal-body">
        Are you sure you want to delete this event with title=<b>${handleevent.title}</b>?
    </div>
    
    <div class="modal-footer">
            <div class="btn-toolbar">
                <div class="btn-group">
                <button type="button" class="btn btn-default" id="event_view" onclick="viewEventper('${handleevent.idPerCalEvent}')"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"  ></span> View</button>
                <button type="button" class="btn btn-default" id="event_edit" onclick="editPersonalEvent('${handleevent.idPerCalEvent}')"><span class="glyphicon glyphicon-edit"  aria-hidden="true" ></span> Edit</button>
                <button type="button" class="btn btn-default" id="event_delete" onclick="deletePersonalEvent('${handleevent.idPerCalEvent}')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>
            </div>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary"data-dismiss="modal" onclick="deletedeletePersonalEvent('${handleevent.idPerCalEvent}')">Delete</button>
            </div>
        </div>
