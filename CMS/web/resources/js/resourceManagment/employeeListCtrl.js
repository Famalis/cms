function EmployeeListCtrl($scope, $http, saveEditDelete) {
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "employees";
    $scope.columns = {
        'surname': "Nazwisko",
        'departmentName': "Wydział",
        'salary': "Wypłata",
        'country': "Kraj",
        'city': "Miasto",
        'positionName' : "Stanowisko"
    };
    $scope.selected = "";
    $scope.employees = "";
    $scope.departments = "";
    $scope.positions = "";
    $scope.editMode = false;

    $scope.get = saveEditDelete.get($http, '/CMS/employeeList/emps.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/employeeList/save/:object.htm', $scope);
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.departments = $scope.initData.departments;
            $scope.employees = $scope.initData.employees;
            $scope.positions = $scope.initData.positions;
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
}