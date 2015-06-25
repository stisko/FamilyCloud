<div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>  <span class="sr-only">close</span>
        </button>
        <h4 id="modalTitle" class="modal-title">${eventPersonal.title}</h4>
    </div>
    <div id="modalBody" class="modal-body">
        ${eventPersonal.description}
        ${eventPersonal.start_date}
        ${eventPersonal.end_date}
        
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