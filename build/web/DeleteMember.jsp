Are you sure you want to delete ${json.selected_user.lastName} ? 
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-primary" data-dismiss="modal" value="${json.selected_user.username}" onclick="delete_member(this.value)">Delete</button>
</div>