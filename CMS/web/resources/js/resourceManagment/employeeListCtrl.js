function EmployeeListCtrl($scope, $http, saveEditDelete) {
    $scope.status = "Ładowanie danych";
    $scope.displayPage = true;
    $scope.displayPageName = "employeePage";
    
    $scope.objectsName = "employees";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'surname';
    $scope.attributes[2] = 'positionName';
    $scope.attributes[3] = 'departmentName';
    $scope.attributes[4] = 'salary';
    $scope.attributes[5] = 'country';
    $scope.attributes[6] = 'city';
    $scope.attributes[7] = 'pesel';
    $scope.columns = {
        'name' : "Imię",
        'surname': "Nazwisko",
        'departmentName': "Wydział",
        'salary': "Wypłata",
        'country': "Kraj",
        'city': "Miasto",
        'positionName' : "Stanowisko",
        'pesel' : "PESEL"
    };
    $scope.columnClasses = {
        'name' : "nazwaklasy",
        'surname': "nazwaklasy",
        'departmentName': "nazwaklasy",
        'salary': "nazwaklasy",
        'country': "nazwaklasy",
        'city': "nazwaklasy",
        'positionName' : "nazwaklasy",
        'pesel' : "nazwaklasy"
    };
    
    $scope.selected = "";
    $scope.employees = "";
    $scope.departments = "";
    $scope.positions = "";
    $scope.privileges = "";
    $scope.editMode = false;

    $scope.get = saveEditDelete.get($http, '/CMS/employeeList/emps.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/employeeList/save/:object.htm', $scope);
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.departments = $scope.initData.departments;
            $scope.positions = $scope.initData.positions;
            $scope.privileges = $scope.initData.privileges;
        } else {
            alert('err');
        }
    });

    $scope.select = function(object) {
        if ($scope.selected == object) {
            $scope.selected = "";
        } else {
            $scope.selected = object;
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
        saveEditDelete.remove($http, '/CMS/employeeList/delete/:object.htm', $scope);
    };
    
    $scope.getPositionName = function(posId) {
        for (var i = 0; i<$scope.positions.length; i++) {
            if($scope.positions[i].id == posId) {
                return $scope.positions[i].name;
            }
        }
    };
    
    $scope.getDepartmentName = function(depId) {
        for (var i = 0; i<$scope.departments.length; i++) {
            if($scope.departments[i].id == depId) {
                return $scope.departments[i].name;
            }
        }
    };
    
    $scope.checkEditPrivileges = function() {
        for (var i = 0; i<$scope.privileges.length; i++) {
            if($scope.privileges[i] == "all") {
                return true;
            }
            if($scope.privileges[i] == "ManageEmployees") {
                if($scope.selected.privilegeGroupId == "3") {
                    return true;
                }
            }
            if($scope.privileges[i] == "ManageManagers") {
                if($scope.selected.privilegeGroupId == "4") {
                    return true;
                }
            }
        }
        return false;
    };
}