function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url = '/api/users';
    var self = this;
    function createUser(user) {
        return fetch('/api/users',{
        	method: 'POST',
        	body: JSON.stringify(user),
        	headers: {'content-type': 'application/json'}
        })
        .then(function(response) {
            return response.json();
        });
    }
    function findAllUsers() {
        return fetch(this.url)
            .then(function(response) {
                return response.json();
            });
    }
    function findUserById(userId) {
        return fetch('/api/user/'+userId).then(function(response) {
            return response.json();
        });
    
    }
    //how to use the callback suggested here
    function updateUser(username,password,firstName,lastName) {
        return fetch('/api/user/update/'+username+'/'+firstName+'/'+ lastName + '/' + password).then(function(response) {
            return response.json();
        });
    	//return fetch('http://localhost:8080/api/user/update/'+username,{
        //	method: 'POST',
        //	body: JSON.stringify(user),
        //	headers: {'content-type': 'application/json'}
       // })
       // .then(function(response) {
         //   return response.json();

    	
    }
    //how to use int or string to fetch
    function deleteUser(userId) {
        return fetch('/api/users/delete/' + userId,{
            method: 'POST',
            body: JSON.stringify(userId),
            headers: {'content-type': 'application/json'}
        }).then(function(response) {
            return response.json();
        });
    
    	
    }
}
