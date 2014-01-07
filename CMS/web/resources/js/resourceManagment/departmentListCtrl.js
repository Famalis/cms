function DepartmentListCtrl($scope, $http, saveEditDelete, pagination) {
    
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
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
    $scope.privileges = "";
    $scope.get = saveEditDelete.get($http, '/CMS/departmentList/deps.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        
        if(($scope.selected.name == null) || $scope.selected.managerSurname == null) {
            alert("Sprawdź poprowność wprowadzonych danych");
        } else {
        saveEditDelete.save($http, '/CMS/departmentList/save/:object.htm', $scope);
        
        var date = new Date();
        var curDate = null;
        do { curDate = new Date(); }
        while(curDate-date < 1000);
  
        $scope.get = saveEditDelete.get($http, '/CMS/departmentList/deps.htm', $scope);
        loadDataPromise = $scope.get;
        $scope.selected = null;
        $scope.editMode = false;
    }
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.employees = $scope.initData.employees;
            $scope.privileges = $scope.initData.privileges;
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
        $scope.selected = "";
    };

    $scope.create = function() {
        $scope.selected = new Object();
        $scope.selected.managerId = -1;
        $scope.editMode = true;

    };
    
    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/departmentList/delete/:object.htm', $scope);
    };

    $scope.checkEditPrivileges = function() {
        for (var i = 0; i<$scope.privileges.length; i++) {
            if($scope.privileges[i] == 'all') {
                return true;
            }
            if($scope.privileges[i] == "ManageDepartments") {
                return true;
            }
        }
        return false;
    };
}
