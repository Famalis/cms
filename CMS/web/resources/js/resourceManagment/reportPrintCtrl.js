function ReportPrintCtrl($scope, $http, saveEditDelete) {
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.reports = "";
    $scope.get = saveEditDelete.get($http, '/CMS/reportPrint/reports.htm', $scope);
    var loadDataPromise = $scope.get;

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.reports = $scope.initData.reports;
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

    $scope.create = function() {
        $scope.selected = "";
        $scope.editMode = true;

    };


}