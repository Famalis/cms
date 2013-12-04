function DepartmentListCtrl($scope, $http, saveEditDelete) {
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "departments";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'managerSurname';
    $scope.columns = {
        'name' : "Nazwa",
        'managerSurname' : "Zarządca"
    };
    $scope.selected = "";
    $scope.departments = "";
    $scope.employees = "";
    $scope.get = saveEditDelete.get($http, '/CMS/departmentList/deps.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/departmentList/save/:object.htm', $scope);
        
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.employees = $scope.initData.employees;
        } else {
            alert('err');
        }
    });

    $scope.select = function(department) {
        if ($scope.selected == department) {
            $scope.selected = "";
        } else {
            $scope.selected = department;
            $scope.selectedEmp = $scope.selectEmp(department.managerId);
            
        }
    }

    $scope.selectEmp = function(id) {
        for (var i = 0; i<$scope.employees.length; i++) {
            if($scope.employees[i].id == id) {
                return $scope.employees[i];
            }
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
    
    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/departmentList/delete/:object.htm', $scope);
    };


}
