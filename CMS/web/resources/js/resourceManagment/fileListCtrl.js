function FileListCtrl($scope, $http, saveEditDelete) {
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.files = "";
    $scope.get = saveEditDelete.get($http, '/CMS/fileList/files.htm', $scope);
    var loadDataPromise = $scope.get;

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.files = $scope.initData.files;
        } else {
            $scope.status = "Błąd:";
            alert('err');
        }
    });

    $scope.select = function(report) {
        if ($scope.selected == report) {
            $scope.selected = "";
        } else {
            $scope.selected = report;
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


}