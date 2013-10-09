function UserListCtrl($scope, $http) {
    $scope.status = "Ładowanie danych";
    var loadDataPromise = $http.get('/CMS/userList/users.htm').success(function(returnData) {
        $scope.users = returnData;
        //$scope.status = null;
        return "success";
    }).error(function(error) {
        //$scope.status = "Błąd";
        $scope.error = error;
        return "failure";
    });

    $scope.save = function() {        
        var o = $scope.selectedUser;
        $http.post(
            '/CMS/userList/save/:user.htm',
            {user:o}).success(function(returnData) {
                
            }).error(function(error) {
                alert(error);
            });
    };

    loadDataPromise.then(function(data) {
        if (data != null) {
            $scope.status = "";
        } else {
            $scope.status = "Błąd:";
        }
    });

    $scope.selectUser = function(id) {
        //alert("test");
        for (var i = 0; i < $scope.users.length; i++) {
            //alert($scope.users[i].name);
            if ($scope.users[i].id === id) {
                $scope.selectedUser = $scope.users[i];

            }
        }
    };
}