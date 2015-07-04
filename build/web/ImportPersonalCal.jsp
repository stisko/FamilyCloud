<div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal alert" aria-label="Close"><span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">Add Events from Family Calendar</h4>
    </div>
    <form id="import_events" name="imp_form">
        <div class="modal-body">


            <!--                       ---------------- edw grafoume sto modal ------------->


            <div class="well" id="well_eventsImp">Pick events by clicking on them</div>

            <div id='calendar_import'></div>

            <div class="row">
                <div class="col-sm-12">
                    <div id="calendar_imp"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                </div>
            </div>

        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" id="importcheck" class="btn btn-primary disabled " data-dismiss="modal" onclick="sendchecked()">Import</button>
        </div>
    </form>

</div>