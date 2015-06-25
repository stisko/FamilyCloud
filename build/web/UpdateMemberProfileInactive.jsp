<div class="row">
    <div class="col-sm-4">
        <div class="fileinput fileinput-new" data-provides="fileinput">
            <div class="fileinput-new thumbnail" style="width: 150px; height: 150px;">
                <img class="img-rounded" src="controller_servl?event=GETPIC&username=${json.selected_user.username}">
            </div>
            <form action="upload_servl?username=${json.selected_user.username}" role="form"enctype="multipart/form-data" method="post"id="upload_form">
                <input type="file" name="file">
                <button type="submit" form="upload_form" class="btn btn-success">Upload</button>
            </form>
        </div>
    </div>
    <div class="col-sm-8">
        <div class="row">
            <div class="col-sm-6">
                <label for="lastnameeditmember">Last Name</label>
                <input type="text" class="form-control" id="lastnameeditmember" name="lastName" placeholder="Last Name" value="${json.selected_user.lastName}" required>
            </div>
            <div class="col-sm-6">
                <label for="firstnameeditmember">First Name</label>
                <input type="text" class="form-control" id="firstnameeditmember" name="firstName" placeholder="First Name" value="${json.selected_user.firstName}" required>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6" hidden>
                <label for="emaileditmember">Email</label>
                <input type="text" class="form-control" id="emaileditmember" name="email" placeholder="Email" value="${json.selected_user.email}" required>
            </div>
            <div class="col-sm-6">
                <label for="birtheditmember">Birth Date</label>
                <input type="text" class="form-control" id="birtheditmember" name="birthdate" placeholder="MM-DD-YYYY" value="${json.selected_user.birthdate}" required>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <label for="towneditmember">Town</label>
                <input type="text" class="form-control" id="towneditmember"  name="town" placeholder="Town" value="${json.selected_user.town}" >
            </div>
        </div>

        <div class="col-sm-6">
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="">Adult
                </label>
            </div>
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="">Kid *(Under 18)
                </label>
            </div>
        </div>
    </div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-primary" data-dismiss="modal" value="${json.selected_user.username}" onclick="UpdateMemberProfile(this.value)">Save</button>
</div>