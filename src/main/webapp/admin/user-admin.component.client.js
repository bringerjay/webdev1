(function(){
    var $usernameFld, $passwordFld;
    var $deleteBtn, $editBtn, $createBtn,$searchBtn,$checkBtn;
    var $firstNameFld,$lastNameFld,$dateFld,$roleFld;
    var userService = new AdminUserServiceClient();
    var userRowTemplate
    var body
    var row
    var sanity
    $(main);
    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $dateFld = $("#date");
        $roleFld = $("#roleFld");
        userRowTemplate = $(".wbdv-template");
        body= $(".wbdv-tbody");
        $usernameFld.val("alice");
        userService
            .findAllUsers()
            .then(renderUsers);
        $editBtn=$(".wbdv-update");
        $deleteBtn=$(".wbdv-remove");
        $createBtn=$(".wbdv-create");
        $searchBtn=$(".wbdv-search");
        $checkBtn=$(".wbdv-check");
        $deleteBtn.click(deleteUser);
        $createBtn.click(createUser);
        $editBtn.click(updateUser);
        $searchBtn.click(findUserByUserName);
        $checkBtn.click(selectUser);
    }
    function createUser() {
        const username = $usernameFld.val();
        const password = $passwordFld.val();
        const firstName = $firstNameFld.val();
        const lastName = $lastNameFld.val();
        const role = $roleFld.val();
        console.log(username,password,firstName,lastName,role);
        //why did we have to do this? Why not directly use userRowTemplate?
        if (role != null)
        {
        row = userRowTemplate.clone();
        row.removeClass('wbdv-hidden')
        console.log(row.attr("id"))
            //am I making it too complex here? what are the best practice?
        userService
            .findAllUsers()
            .then(generateUserId).then(updateId).then(function(){
            var userId = row.attr("id")
            console.log("row.attr(\"id\")"+ $(row).attr("id"))
            console.log("id="+ userId)
            console.log(row)
            var user = {
                username: username,
                password: password,
                firstName: firstName,
                lastName: lastName,
                id:    userId,
                role:  role
            }
            return user
        }).then(userService.createUser).then(renderUsers)
        console.log("create" + body)
        const $deletebtnC = row.find(".wbdv-remove")
        $deletebtnC.click(deleteUser)}
        else
        {console.log("no role seleted")}
    }
    function findAllUsers() {
       return userService
            .findAllUsers()
            .then(renderUsers);
    }

    function findUserByUserName() {
        const username = $usernameFld.val();
        console.log(username);
        userService.findUserByUsername(username).then(renderUser).then(function () {
            body.find(".wbdv-password").html("Confidential")
            body.find(".wbdv-dob").html("You guess")
        })
    }
    function deleteUser(event) { 
    	console.log(event);
    	const currentTarget=event.currentTarget;
        const td = currentTarget.parentElement.parentElement
        console.log(td);
        const userid = $(td).attr("id")
        const userId = parseInt(userid)
        console.log(userId);
        userService.deleteUser(userId).then(renderUsers)
    }
    function selectUser(event) {
        console.log(event);
        const currentTarget=event.currentTarget;
        const td = currentTarget.parentElement.parentElement
        console.log(td);
        const userid = $(td).attr("id")
        const userId = parseInt(userid)
        console.log(userId);
        userService.findUserById(userId).then(renderUser)
    }

    function updateUser() {
        const username = $usernameFld.val();
        const password = $passwordFld.val();
        const firstName = $firstNameFld.val();
        const lastName = $lastNameFld.val();
        console.log(username,password,firstName,lastName);
        //var user = {
        //    username: username,
        //    password: password,
        //    firstName: firstName,
        //    lastName: lastName,
        //}
        //console.log(user);
        userService.updateUser(username,password,firstName,lastName).then(findAllUsers)
    }
    function renderUser(user) {
            console.log(user);
            var clone = userRowTemplate.clone()
            clone.removeClass('wbdv-hidden')
            clone.find(".wbdv-username").html(user.username)
            clone.find(".wbdv-password").html(user.password)
            clone.find(".wbdv-first-name").html(user.firstName)
            clone.find(".wbdv-last-name").html(user.lastName)
            clone.find(".wbdv-role").html(user.role)
            clone.find(".wbdv-dob").html(user.dOB)
            clone.attr("id",user.id)
            console.log(clone.attr("id"));
            body.empty()
            console.log(body);
            body.append(clone)
            const $deletebtnA = clone.find(".wbdv-remove")
            const $checkBtn = clone.find(".wbdv-check")
            $deletebtnA.click(deleteUser)
            $checkBtn.click(selectUser);
        }

    function updateId(uId){
        row.attr("id",uId)
        console.log(uId)
        console.log(row)
        console.log($(row).attr("id"))

    }
    function generateUserId(users){
        var uId= users.length+1
        return uId
    }
    //how does js know the users on the server side
    function renderUsers(users) {
        body.empty()
        for(var u=0; u<users.length; u++) {
            console.log(users[u]);
            var clone = userRowTemplate.clone()
            clone.removeClass('wbdv-hidden')
            clone.find(".wbdv-username").html(users[u].username)
            clone.find(".wbdv-password").html("confidential")
            clone.find(".wbdv-first-name").html(users[u].firstName)
            clone.find(".wbdv-last-name").html(users[u].lastName)
            clone.find(".wbdv-role").html(users[u].role)
            clone.find(".wbdv-dob").html("only user can see")
            clone.attr("id",users[u].id)
            console.log(clone.attr("id"));
            body.append(clone)
            const $deletebtnA = clone.find(".wbdv-remove")
            const $checkBtn = clone.find(".wbdv-check")
            $deletebtnA.click(deleteUser)
            $checkBtn.click(selectUser);
        }
    }
})();