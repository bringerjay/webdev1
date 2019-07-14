(function(){
    var $usernameFld, $passwordFld,$userIdFld
    var $deleteBtn, $editBtn, $createBtn,$searchBtn,$checkBtn;
    var $firstNameFld,$lastNameFld,$dateFld,$roleFld;
    var userService = new AdminUserServiceClient();
    var userRowTemplate
    var body
    var table
    var row
    var p
    $(main);
    function main() {
        $("p").click(alert("Click"));
        $("p").click(function(){ alert("Click"); });




        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $roleFld = $("#roleFld");
        $dateFld = $("#dateFld");
        $userIdFld = $("#userIdFld")
        userRowTemplate = $(".wbdv-template");
        body= $(".wbdv-tbody");
        table= $(".wbdv-form");
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
        $searchBtn.click(findUserById);
        $checkBtn.click(selectUser);
    }
    function createUser() {
        const username = $usernameFld.val();
        const password = $passwordFld.val();
        const firstName = $firstNameFld.val();
        const lastName = $lastNameFld.val();
        const dOB = $dateFld.val();
        const role = $roleFld.val();
        console.log(username,password,firstName,lastName,role,dOB)
        //why did we have to do this? Why not directly use userRowTemplate?
        row = userRowTemplate.clone();
        row.removeClass('wbdv-hidden')
        console.log(row.attr("id"))
            //am I making it too complex here? what are the best practice?
        userService
            .findAllUsers()
            //.then(generateUserId)
        .then(function(){
            //console.log("id="+ userId)
            console.log(row)
            var user = {
                username: username,
                password: password,
                firstName: firstName,
                lastName: lastName,
                //id:    userId,
                role:  role,
                dOB:   dOB
            }
            return user
        }).then(userService.createUser).then(renderUsers)
            //.then(function() {
            //$usernameFld.val("111")
       // })
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
        const userId = parseInt($userIdFld.val())
        console.log(userId);
        userService.findUserById(userId).then(renderUser).then(function () {
            body.find(".wbdv-password").html("Confidential")
            body.find(".wbdv-dob").html("You guess")
        })
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
        //const td = currentTarget.parentElement.parentElement
        const idNode = currentTarget.parentElement.parentElement.firstChild.nextSibling
        console.log(idNode);
        const userId =parseInt(idNode.innerHTML)
        userService.deleteUser(userId).then(renderUsers)
    }
    function selectUser(event) {
        console.log(event);
        const currentTarget=event.currentTarget;
        //const td = currentTarget.parentElement.previousElementSibling.previousElementSibling
        const idNode = currentTarget.parentElement.parentElement.firstChild.nextSibling
        console.log(idNode);
        const userId =idNode.innerHTML
        //console.log(td)
        //const userid = td.getElementsByClassName(".wbdv-userId")
        //const userId = parseInt(userid)
        console.log(userId);
        userService.findUserById(userId).then(renderUser).then(function () {
            $usernameFld.val(user.username)
            $firstNameFld.val(user.firstName)
            $lastNameFld.val(user.lastName)
            $roleFld.val(user.role)
            $dateFld.val(user.dOB)
            $("#userIdFld").val(user.id)
        })
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
        userService.updateUser(username,password,firstName,lastName).then(renderUser)
    }
    function renderUser(user) {
            console.log(user);
            var clone = userRowTemplate.clone()
            clone.removeClass('wbdv-hidden')
            clone.find(".wbdv-userId").html(user.id)
            clone.find(".wbdv-username").html(user.username)
            clone.find(".wbdv-password").html(user.password)
            clone.find(".wbdv-first-name").html(user.firstName)
            clone.find(".wbdv-last-name").html(user.lastName)
            clone.find(".wbdv-role").html(user.role)
            clone.find(".wbdv-dob").html(user.dOB)
            body.empty()
            console.log(body);
            body.append(clone)
            const $deletebtnA = clone.find(".wbdv-remove")
            const $checkBtn = clone.find(".wbdv-check")
            $deletebtnA.click(deleteUser)
            $checkBtn.click(selectUser);
        $usernameFld.val(user.username)
        $firstNameFld.val(user.firstName)
        $lastNameFld.val(user.lastName)
        $("#userIdFld").val(user.id)
        $editBtn.click(updateUser);
        $searchBtn.click(findUserById);
        $checkBtn.click(selectUser);
        }
    //function generateUserId(users){
       // const latestId = users.length
       // console.log(latestId);
       // var uId= users[latestId].id+1
       // return uId
   // }
    //how does js know the users on the server side
    function renderUsers(users) {
        body.empty()
        removeInputs()
        //why all these below doesnt work
        //table.find(".usernameFld").html("Username")
        //const tableclone = table.clone()
        //tableclone.find("#usernameFld").html(null)
        //table.empty()
        //table.append(tableclone)
        for(var u=0; u<users.length; u++) {
            console.log(users[u]);
            var clone = userRowTemplate.clone()
            clone.removeClass('wbdv-hidden')
            clone.find(".wbdv-userId").html(users[u].id)
            clone.find(".wbdv-username").html(users[u].username)
            clone.find(".wbdv-password").html("confidential")
            clone.find(".wbdv-first-name").html(users[u].firstName)
            clone.find(".wbdv-last-name").html(users[u].lastName)
            clone.find(".wbdv-role").html(users[u].role)
            clone.find(".wbdv-dob").html("only user can see")
            console.log(users[u].id);
            body.append(clone)
            const $deletebtnA = clone.find(".wbdv-remove")
            const $checkBtn = clone.find(".wbdv-check")
            $deletebtnA.click(deleteUser)
            $checkBtn.click(selectUser);
        }
        $editBtn.click(updateUser);
        $searchBtn.click(findUserById);
        $checkBtn.click(selectUser);
    }
    function removeInputs () {
        $usernameFld.val(null)
        $passwordFld.val(null)
        $firstNameFld.val(null)
        $lastNameFld.val(null)
        $roleFld.val("FACULTY")
        $dateFld.val(null)
        $("#userIdFld").val(null)
    }
})();