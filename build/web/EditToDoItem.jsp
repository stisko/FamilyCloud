<div class="row">									
    <div class="col-sm-6">				
        <label for="titleedittask">Task Title</label>
        <input type="text" class="form-control" id="titleedittask" placeholder="Enter Title" value="${json.item.title}" required>										
    </div>									

    <div class="col-sm-6">
        <label for="duedateedittask">Due Date</label>
        <input type="date" class="form-control" id="duedateedittask" placeholder="MM-DD-YYYY" value="${json.item.dueDate}">
    </div>
</div>
<div class="row">

    <div class="col-sm-6">				
        <label for="checkaddpending">Status</label>
        <div class="radio" id="checkaddpending">
            <label>
                <input name="statusradio" type="radio" value="Pending" ${json.pending}>
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
<label>Assigned To</label>		
<select class="form-control" id="assignedTo_edit">
    <option>Anna</option>
    <option>Giorgos</option>
    <option>Kostas</option>
    <option>Mixalhs</option>
    <option>Marios</option>
</select>
 
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-primary" data-dismiss="modal" value="${json.item.itemID}" onclick="Save_changes_todo(this.value)">Save</button>
</div>