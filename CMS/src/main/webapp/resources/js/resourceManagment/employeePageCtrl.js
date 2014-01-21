function EmployeePageCtrl($scope, $http, saveEditDelete) {
    
    $scope.getInfoLink = "";
    $scope.get = saveEditDelete.get($http, $scope.getInfoLink, $scope);
    var loadDataPromise = $scope.get;
    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.employee = $scope.initData.employee;
            $scope.employments = $scope.initData.employments;
        } else {
            alert('err');
        }
    });
}