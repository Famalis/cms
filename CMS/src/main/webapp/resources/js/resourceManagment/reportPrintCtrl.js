function ReportPrintCtrl($scope, $http, saveEditDelete, pagination) {
    
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.employees = "";
    $scope.contract = "";
    $scope.customers = "";
    $scope.departments = "";
    
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "reports";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'description';
    $scope.columns = {
      'name' : "Nazwa",
      'description' : "Opis"
    };
    $scope.selected = "";
    $scope.reports = "";
    $scope.get = saveEditDelete.get($http, '/CMS/reportPrint/reports.htm', $scope);
    var loadDataPromise = $scope.get;

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.employees = $scope.initData.employees;
            $scope.contract = $scope.initData.contract;
            $scope.customers = $scope.initData.customers;
            $scope.departments = $scope.initData.departments;
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