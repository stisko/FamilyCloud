<div class="row">
    <div class="col-sm-3">
        <div class="thumbnail" style="width: 150px; height: 150px;">
            <img class="img-rounded" src="controller_servl?event=GETPIC&username=${json.selected_user.username}">
        </div>
    </div>
    <div class="col-sm-8">
        <div class="row">
            <label class="col-sm-4 col-sm-offset-1" for="lnam">Last Name:</label>
            <p class="col-sm-7">${json.selected_user.lastName}</p>
        </div>
        <div class="row">
            <label class="col-sm-4 col-sm-offset-1" for="fnam">First Name:</label>
            <p class="col-sm-7">${json.selected_user.firstName}</p>
        </div>

        <div class="row">
            <label class="col-sm-4 col-sm-offset-1" for="birth">Birth Date:</label>
            <p class="col-sm-7">${json.selected_user.birthdate}</p>
        </div>
        <div class="row">
            <label class="col-sm-4 col-sm-offset-1" for="town">Town:</label>
            <p class="col-sm-7">${json.selected_user.town}</p>
        </div>
    </div>
</div>