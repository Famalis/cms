function TerminalListCtrl($scope, $http, saveEditDelete) {
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "terminals";
    $scope.selected = "";
    $scope.terminals = "";
    $scope.logs = "";
    $scope.editMode = false;
    $scope.get = saveEditDelete.get($http, '/CMS/terminalList/terminals.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/terminalList/save/:object.htm', $scope);
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.terminals = $scope.initData.terminalDtos;
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