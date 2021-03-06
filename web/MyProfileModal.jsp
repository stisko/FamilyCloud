<div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">My Profile</h4>
    </div>
    <div class="modal-body">
        <div class="row">
            <div class="col-sm-3">
                <div class="thumbnail" style="width: 150px; height: 150px;">
                    <img class="img-rounded" src="controller_servl?event=GETPIC&username=${json.selected_user.username}">
                </div>
            </div>
            <div class="col-sm-8">
                <div class="row">
                    <label class="col-sm-4 col-sm-offset-1" for="lnam">Last Name:</label>
                    <p class="col-sm-7">${json.selected_user.lastName} </p>
                </div>
                <div class="row">
                    <label class="col-sm-4 col-sm-offset-1" for="fnam">First Name:</label>
                    <p class="col-sm-7">${json.selected_user.firstName}</p>
                </div>
                <div class="row">
                    <label class="col-sm-4 col-sm-offset-1" for="email">Email:</label>
                    <p class="col-sm-7">${json.selected_user.email}</p>
                </div>
                <div class="row">
                    <label class="col-sm-4 col-sm-offset-1" for="birth">Birth Date:</label>
                    <p class="col-sm-7">${json.selected_user.birthdate}</p>
                </div>
                <div class="row">
                    <label class="col-sm-4 col-sm-offset-1" for="birth">Town:</label>
                    <p class="col-sm-7">${json.selected_user.town}</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="checkbox_changepassword">
                    <label><a type="button" id="checkbox_changepasswordval" onclick="show_changepassword('changepasswordview')">Change Password</a></label>
                </div>
            </div>
        </div>
        <div class="repeat2" id="changepasswordview" style="display:none">
            <form id="myprofile_form">
                <div class="row">
                    <div class="col-sm-6">
                        <label for="oldpass">Old Password</label><div id="checkpasswords" style="display: none"></div>
                        <input class="form-control" type="text" id="oldpass" oninput="checkPassword(this.value)" placeholder="Old Password" pattern="(?=^.{6,}$)((?=.*[A-Z])|(?=.*[a-z])|(?=.*[0-9])).*$" required/><span id="passspan">Invalid input</span>
                    </div>
                    
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <label for="newpass">New Password</label>
                        <input class="form-control" type="text" id="newpass" oninput="style_inp('newpass')" placeholder="New Password" pattern="(?=^.{6,}$)((?=.*[A-Z])|(?=.*[a-z])|(?=.*[0-9])).*$" required/><span>Invalid input</span>
                    </div>
                    <div class="col-sm-6">
                        <label for="retypenewpass">Retype Password</label>
                        <input class="form-control" type="text" id="retypenewpass" oninput="style_inp('retypenewpass')" placeholder="Retype Password" pattern="(?=^.{6,}$)((?=.*[A-Z])|(?=.*[a-z])|(?=.*[0-9])).*$" required/><span>Invalid input</span>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="modal-footer">
        <div id="suc_todo_mes_valid_MYPROF" style="font-size:120%"></div>
        <div class="btn-group">

            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button id="savepassmodal_prof"  type="button" style="display:none"  data-dismiss="modal" onclick="ChangePassword()" class="btn btn-primary" >Save</button>
        </div>
    </div>
</div>