<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
    </button>
    <h4 class="modal-title" id="myModalLabel">Delete Post</h4>
</div>

<div class="modal-body">
    Are you sure you want to delete this post?
    Message: ${post.message}
</div>

<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="deletePost2('${post.idWallPost}')">Ok</button>
</div>