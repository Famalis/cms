function LoginCtrl($scope, $http, saveEditDelete, pagination) {

    $scope.employee = "";

    $http.get("/CMS/getEmpData.htm").success(function(returnData) {
        $scope.employee = returnData.employee;
    }).error(function(error) {
        $scope.status = "Błąd";
        //return null;
    });
}