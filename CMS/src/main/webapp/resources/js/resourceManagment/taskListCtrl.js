function TaskListCtrl($scope, $http, saveEditDelete, pagination) {
    
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "tasks";
    $scope.saveLink = '/CMS/departmentList/save/:object.htm';
    $scope.selected = "";
    $scope.departments = "";
    $scope.privileges = "";
    $scope.employees = "";
    $scope.get = saveEditDelete.get($http, '/CMS/departmentList/deps.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/departmentList/save/:object.htm', $scope);
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.departments = $scope.initData.departmnets;
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
        }
    }

    $scope.edit = function() {
        $scope.editMode = true;
    };

    $scope.create = function() {
        $scope.selected = "";
        $scope.editMode = true;

    };

    $scope.checkEditPrivileges = function() {
        for (var i = 0; i<$scope.privileges.length; i++) {
            if($scope.privileges[i] == 'all') {
                return true;
            }
        }
        return false;
    };
}