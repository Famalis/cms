function EmployeeListCtrl($scope, $http, saveEditDelete, pagination) {

    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);

    $scope.status = "Ładowanie danych";
    //$scope.displayPage = true;
    //$scope.displayPageName = "employeePage";

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
        'name': "Imię",
        'surname': "Nazwisko",
        'departmentName': "Wydział",
        'country': "Kraj",
        'city': "Miasto",
        'phone': "Telefon",
        'positionName': "Stanowisko"
    };
    $scope.columnClasses = {
        'name': "pracownik-name",
        'surname': "pracownik-surname",
        'departmentName': "pracownik-wydzial",
        'country': "pracownik-kraj",
        'city': "pracownik-miasto",
        'phone': "pracownik-telefon",
        'positionName': "pracownik-stanowisko"

    };


    $scope.selected = "";
    $scope.employees = "";
    $scope.departments = "";
    $scope.positions = "";
    $scope.privileges = "";
    $scope.employments = "";
    $scope.editMode = false;
    $scope.selectedEmployment = "";
    $scope.selectedAddress = "";

    $scope.get = saveEditDelete.get($http, '/CMS/employeeList/emps.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        if (($scope.selected.name == null) || $scope.selected.surname == null || $scope.selected.phone == null || $scope.selected.departmentId == -1 || $scope.selected.positionId == -1 || $scope.selected.departmentId == null || $scope.selected.positionId == null || $scope.selected.country == null || $scope.selected.city == null) {
            alert("Sprawdź poprowność wprowadzonych danych");
        } else {
            saveEditDelete.save($http, '/CMS/employeeList/save/:object.htm', $scope);
        }
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.departments = $scope.initData.departments;
            $scope.positions = $scope.initData.positions;
            $scope.privileges = $scope.initData.privileges;
            $scope.employments = $scope.initData.employments;
            //$scope.addresses = $scope.initData.addresses;
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
        $scope.selected = "";
    };

    $scope.create = function() {
        $scope.selected = new Object();
        $scope.selected.positionId = -1;
        $scope.selected.departmentId = -1;
        $scope.editMode = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/employeeList/delete/:object.htm', $scope);
    };

    $scope.getPositionName = function(posId) {
        for (var i = 0; i < $scope.positions.length; i++) {
            if ($scope.positions[i].id == posId) {
                return $scope.positions[i].name;
            }
        }
    };

    $scope.getPositionName = function(posId) {
        for (var i = 0; i < $scope.positions.length; i++) {
            if ($scope.positions[i].id == posId) {
                return $scope.positions[i].name;
            }
        }
    };

    $scope.getPositionHierarhy = function(posId) {
        for (var i = 0; i < $scope.positions.length; i++) {
            if ($scope.positions[i].id == posId) {
                return $scope.positions[i].hierarhy;
            }
        }
    };

    $scope.getDepartmentName = function(depId) {
        for (var i = 0; i < $scope.departments.length; i++) {
            if ($scope.departments[i].id == depId) {
                return $scope.departments[i].name;
            }
        }
    };

    $scope.checkEditPrivileges = function() {
        for (var i = 0; i < $scope.privileges.length; i++) {
            if ($scope.privileges[i] == "all") {
                return true;
            }
            if ($scope.privileges[i] == "ManageEmployees") {
                if ($scope.getPositionHierarhy($scope.selected.positionId) == "1") {
                    return true;
                }
            }
            if ($scope.privileges[i] == "ManageManagers") {
                if ($scope.getPositionHierarhy($scope.selected.positionId) == "2") {
                    return true;
                }
            }
            if ($scope.privileges[i] == "ManagePresidents") {
                if ($scope.getPositionHierarhy($scope.selected.positionId) == "3") {
                    return true;
                }
            }
            if (!$scope.selected) {
                if ($scope.privileges[i] == "ManageEmployees") {
                    return true;
                }
            }
        }
        return false;
    };

    $scope.selectEmployment = function(empl) {
        $scope.selectedEmployment = empl;
    };

    $scope.selectAddress = function(addr) {
        $scope.selectedAddress = addr;
    };
    
    $scope.getMainAddress = function() {        
        for (var i = 0; i<$scope.selected.addresses.length; i++) {
            //alert($scope.selected.addresses.length);
            if ($scope.selected.addresses[i].id == $scope.selected.mainAddressId) {
                $scope.selectedAddress = $scope.selected.addresses[i];
            }
        }
    }
}