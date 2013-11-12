function DepartmentListCtrl($scope, $http, saveEditDelete) {
    $scope.status = "Ładowanie danych";
    $scope.saveLink = '/CMS/departmentList/save/:object.htm';
    $scope.selected = "";
    $scope.departments = "";
    $scope.employees = "";
    $scope.get = saveEditDelete.get($http, '/CMS/departmentList/deps.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/departmentList/save/:object.htm', $scope.selected);
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.departments = $scope.initData.departmnets;
            $scope.employees = $scope.initData.employees;
        } else {
            $scope.status = "Błąd:";
            alert('err');
        }
    });

    $scope.select = function(department) {
        if ($scope.selected == department) {
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