Are you sure you want to delete this item <b>${json.item.title}?</b>
            
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" value="${json.item.itemID}" onclick="deleteToDoItem_save(this.value)">Delete</button>
            </div>