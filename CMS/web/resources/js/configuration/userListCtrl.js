function UserListCtrl($scope, $http) {
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    var loadDataPromise = $http.get('/CMS/userList/users.htm').success(function(returnData) {
        $scope.users = returnData.users;
        $scope.employees = returnData.employees;
        //$scope.status = null;
        return "success";
    }).error(function(error) {
        //$scope.status = "Błąd";
        $scope.error = error;
        return "failure";
    });

    $scope.save = function() {        
        var o = $scope.selected;
        $http.post(
            '/CMS/userList/save/:user.htm',
            {user:o}).success(function() {
                
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

    $scope.select = function(user) {
        if($scope.selected == user) {
            $scope.selected = "";
        } else {
            $scope.selected = user;
        }
    };
}