function LoginCtrl($scope, $http, saveEditDelete, pagination) {

    $scope.employee = "";
    $scope.status="";

    $http.get("/CMS/getEmpData.htm").success(function(returnData) {
        $scope.employee = returnData.employee;
        $scope.status = null;
    }).error(function(error) {
        $scope.status = "Błąd";
        //return null;
    });
    
    $scope.save = function() {
        if (($scope.employee.name == null) || $scope.employee.surname == null || $scope.employee.phone == null) {
            alert("Sprawdź poprowność wprowadzonych danych");
        } else {
            $scope.selected = $scope.employee;
            saveEditDelete.save($http, '/CMS/employeeList/save/:object.htm', $scope);
            var date = new Date();
            var curDate = null;
            do {
                curDate = new Date();
            }
            while (curDate - date < 1000);
        }
    };
}