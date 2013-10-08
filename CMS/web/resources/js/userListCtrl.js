function UserListCtrl($scope, $http) {
    $scope.status = "Ładowanie danych";
    var promise = $http.get('/CMS/userList/users.htm').success(function(data) {
        $scope.users = data;
        //$scope.status = null;
        return "success";
    }).error(function(error) {
        //$scope.status = "Błąd";
        $scope.error = error;
        return "failure";
    });

    promise.then(function(data) {
        if (data != null) {
            $scope.status = "";
        } else {
            $scope.status = "Błąd:";
        }
    });
}