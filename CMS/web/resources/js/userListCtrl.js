function UserListCtrl($scope, $http) {
    $scope.testVar = "to jest test";
    $scope.osoba;
    $scope.testVar2;    
    $http.get('/CMS/userList/users.htm').success(function(data) {
        $scope.users = data;
    }).error(function(error) {
        $scope.testVar = error;
    });
}