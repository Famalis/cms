function EmployeeListCtrl($scope, $http) {
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.employees = "";
    $scope.departments = "";
    $scope.positions = "";
    $scope.editMode = false;
    var loadDataPromise = $http.get('/CMS/employeeList/emps.htm').success(function(returnData) {
        $scope.employees = returnData.employees;
        $scope.departments = returnData.departments;
        $scope.positions = returnData.positions;
        //$scope.status = null;
        return "success";
    }).error(function(error) {
        //$scope.status = "Błąd";
        $scope.error = error;
        return "failure";
    });
    
    $scope.getPositionName = function(positionId) {
      for (var i = 0; i<$scope.positions.length; i++) {
          if($scope.positions[i].id == positionId) {
            return $scope.positions[i].name;
          }
      }  
    };
    
    $scope.getDepartmentName = function(departmentId) {
      for (var i = 0; i<$scope.departments.length; i++) {
          if($scope.departments[i].id == departmentId) {
            return $scope.departments[i].name;
          }
      }  
    };

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
    
    $scope.cancel = function() {
        $scope.editMode = false;
    };
    
    $scope.create = function() {
        $scope.selected = "";
        $scope.editMode = true;

    };
}