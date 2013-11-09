function DepartmentListCtrl($scope, $http) {
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.departments = "";
    $scope.employees = "";
    var loadDataPromise = $http.get('/CMS/departmentList/deps.htm').success(function(returnData) {
        $scope.departments = returnData.departmnets;
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
            '/CMS/departmentList/save/:dep.htm',
            {dep:o}).success(function() {
                
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
    
    $scope.select = function(department) {
        if($scope.selected == department) {
            $scope.selected = "";
        } else {
            $scope.selected = department;
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