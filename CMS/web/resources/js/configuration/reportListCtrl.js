function ReportListCtrl($scope, $http, saveEditDelete) {
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.reports = "";
    $scope.mimetypes = new Object();    
    $scope.mimetypes["Excel"] = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";           
    $scope.mimetypes["Word"] = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    $scope.mimetypes["TXT"] = "text/plain";
    $scope.mimetypes["PDF"] = "application/pdf";
    $scope.get = saveEditDelete.get($http, '/CMS/reportList/reports.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/reportList/save/:object.htm', $scope.selected);
    };

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

    $scope.cancel = function() {
        $scope.editMode = false;
    };

    $scope.create = function() {
        $scope.selected = "";
        $scope.editMode = true;

    };


}