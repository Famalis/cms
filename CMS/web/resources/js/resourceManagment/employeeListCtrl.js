function EmployeeListCtrl($scope, $http, saveEditDelete, pagination) {
    
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.displayPage = true;
    $scope.displayPageName = "employeePage";
    
    $scope.objectsName = "employees";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'surname';
    $scope.attributes[2] = 'phone';
    $scope.attributes[3] = 'positionName';
    $scope.attributes[4] = 'departmentName';
    $scope.attributes[5] = 'country';
    $scope.attributes[6] = 'city';
    
    $scope.columns = {
        'name' : "Imię",
        'surname': "Nazwisko",
        'departmentName': "Wydział",
        'country': "Kraj",
        'city': "Miasto",
        'phone': "Telefon",
        'positionName' : "Stanowisko"
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
        var date = new Date();
        var curDate = null;
        do { curDate = new Date(); }
        while(curDate-date < 1000);
  
        $scope.get = saveEditDelete.get($http, '/CMS/employeeList/emps.htm', $scope);
        loadDataPromise = $scope.get;
        $scope.selected = null;
        $scope.editMode = false;
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
    
    $scope.getPositionName = function(posId) {
        for (var i = 0; i<$scope.positions.length; i++) {
            if($scope.positions[i].id == posId) {
                return $scope.positions[i].name;
            }
        }
    };
    
    $scope.getPositionHierarhy = function(posId) {
        for (var i = 0; i<$scope.positions.length; i++) {
            if($scope.positions[i].id == posId) {
                return $scope.positions[i].hierarhy;
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
                if($scope.getPositionHierarhy($scope.selected.positionId) == "1") {
                    return true;
                }
            }
            if($scope.privileges[i] == "ManageManagers") {
                if($scope.getPositionHierarhy($scope.selected.positionId) == "2") {
                    return true;
                }
            }
            if($scope.privileges[i] == "ManagePresidents") {
                if($scope.getPositionHierarhy($scope.selected.positionId) == "3") {
                    return true;
                }
            }
            if(!$scope.selected){
                if($scope.privileges[i] == "ManageEmployees"){
                    return true;
                }
            }
        }
        return false;
    };
}