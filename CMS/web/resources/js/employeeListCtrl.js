function EmployeeListCtrl($scope, $http) {
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.employees = "";
    $scope.editMode = false;
    var loadDataPromise = $http.get('/CMS/employeeList/emps.htm').success(function(returnData) {
        $scope.employees = returnData;
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
            '/CMS/employeeList/save/:emp.htm',
            {emp:o}).success(function(returnData) {
                
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

    $scope.select = function(employee) {
        if($scope.selected == employee) {
            $scope.selected = "";
        } else {
            $scope.selected = employee;
        }
    }
    
    $scope.edit = function() {
        $scope.editMode = true;
    };
    
    $scope.create = function() {
        $scope.selected = "";
        $scope.editMode = true;

    };
}