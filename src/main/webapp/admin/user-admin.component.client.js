(function(){
    var $usernameFld, $passwordFld;
    var $deleteBtn, $editBtn, $createBtn;
    var $firstNameFld,$lastNameFld,$dateFld,$roleFld;
    var userService = new AdminUserServiceClient();
    var userRowTemplate
    var body
    var row
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
        $editBtn=$(".wbdv-update");
        $deleteBtn=$(".wbdv-remove");
        $createBtn=$(".wbdv-create");
        $usernameFld.val("alice");
        userService
            .findAllUsers()
            .then(renderUsers);
        $deleteBtn.click(deleteUser);
        $createBtn.click(createUser);
        $editBtn.click(updateUser);
    }
    function createUser() {
        const username = $usernameFld.val();
        const password = $passwordFld.val();
        const firstName = $firstNameFld.val();
        const lastName = $lastNameFld.val();

        console.log(username,password,firstName,lastName);
        //why did we have to do this? Why not directly use userRowTemplate?
        row = userRowTemplate.clone();
        row.removeClass('wbdv-hidden')
        console.log(row.attr("id"))
        row.find(".wbdv-username").html(username)
        row.find(".wbdv-first-name").html(firstName)
        row.find(".wbdv-last-name").html(lastName)
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
                id:    userId
            }
            return user
        }).then(userService.createUser).then(renderUser)
        body.append(row)
        console.log("create" + body)
        const $deletebtnC = row.find(".wbdv-remove")
        $deletebtnC.click(deleteUser)
    }
    function findAllUsers() {
       return userService
            .findAllUsers()
            .then(renderUsers);

    }
    function findUserById() {

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
    function selectUser() {  }

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
    function renderUser(user) {  }
    function updateId(uId){
        row.attr("id",uId)
        console.log(uId)
        console.log(row)
        console.log($(row).attr("id"))

    }
    function generateUserId(users){
        var uId= users.length +400
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
            clone.find(".wbdv-first-name").html(users[u].firstName)
            clone.find(".wbdv-last-name").html(users[u].lastName)
            clone.attr("id",users[u].id)
            console.log(clone.attr("id"));
            body.append(clone)
            const $deletebtnA = clone.find(".wbdv-remove")
            $deletebtnA.click(deleteUser)
        }
    }
})();