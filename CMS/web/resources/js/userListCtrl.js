function UserListCtrl($scope, $http) {
    $scope.status = "<h1>Ładowanie danych</h1>";
    $http.get('/CMS/userList/users.htm').success(function(data) {
        $scope.users = data;
        $scope.status = null;
    }).error(function(error) {
        $scope.status = "Błąd";
        $scope.error = error;
    });
}