function TerminalListCtrl($scope, $http, saveEditDelete, pagination) {
    
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 9;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "terminals";
    $scope.objects = [];
    $scope.attributes = [];
    $scope.attributes[0] = 'description';
    $scope.columns = {
        'description': "Opis"
    };
    $scope.selected = "";
    $scope.terminals = "";
    $scope.logs = "";
    $scope.editMode = false;
    $scope.get = saveEditDelete.get($http, '/CMS/terminalList/terminals.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/terminalList/save/:object.htm', $scope);
        
        var date = new Date();
        var curDate = null;
        do { curDate = new Date(); }
        while(curDate-date < 500);
        
        $scope.get = saveEditDelete.get($http, '/CMS/terminalList/terminals.htm', $scope);
        loadDataPromise = $scope.get;
        $scope.selected = null;
        $scope.editMode = false;
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
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
        saveEditDelete.remove($http, '/CMS/terminalList/delete/:object.htm', $scope);
    };

    $scope.size = function(map) {
        var size = 0;
        for (val in map) {
            size++;
        }
        return size;
    };
}