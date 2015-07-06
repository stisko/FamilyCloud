<div class="row">
    <div class="col-sm-4">
        <div class="fileinput fileinput-new" data-provides="fileinput">
            <div class="fileinput-new thumbnail" style="width: 150px; height: 150px;">
                <img class="img-rounded"  src="controller_servl?event=GETPIC&username=${json.selected_user.username}&time=<%=System.currentTimeMillis()%>">
            </div>
            <form  role="form" enctype="multipart/form-data" method="post" id="upload_form">
                <input type="file" name="file">
                
                
            </form>
            
        </div>
    </div>
    <div class="col-sm-8">
        <div class="row">
            <div class="col-sm-6">
                <label for="lastnameeditmember">Last Name</label>
                <input type="text" class="form-control"  id="lastnameeditmember" oninput="style_inp('lastnameeditmember')" name="lastName" placeholder="Last Name" value="${json.selected_user.lastName}" required><span>Invalid input</span>
            </div>
            <div class="col-sm-6">
                <label for="firstnameeditmember">First Name</label>
                <input type="text" class="form-control" id="firstnameeditmember" oninput="style_inp('firstnameeditmember')" name="firstName" placeholder="First Name" value="${json.selected_user.firstName}" required><span>Invalid input</span>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6" hidden>
                <label for="emaileditmember">Email</label>
                <input type="text" class="form-control" id="emaileditmember" name="email" placeholder="Email" value="${json.selected_user.email}" pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required><span>Invalid mail input</span>
            </div>
            <div class="col-sm-6">
                <label for="birtheditmember">Birth Date</label>
                <input type="text" class="form-control" id="birtheditmember" oninput="style_inp('birtheditmember')" name="birthdate" placeholder="yyyy-mm-dd" value="${json.selected_user.birthdate}" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" required><span>Invalid date input</span>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <label for="towneditmember">Town</label>
                <input type="text" class="form-control" id="towneditmember"  name="town" placeholder="Town" value="${json.selected_user.town}" >
            </div>
        </div>

        
    </div>
</div>
<div class="modal-footer">
     <div id="suc_todo_mes_valid_FamMem" style="font-size:120%"></div>
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" id="memsaveup" class="btn btn-primary" data-dismiss="modal" value="${json.selected_user.username}" onclick="initFullFormAjaxUploadMemberProfile(this.value)">Save</button>
</div>