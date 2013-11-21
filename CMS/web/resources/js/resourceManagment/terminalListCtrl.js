function TerminalListCtrl($scope, $http) {
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.terminals = "";
    $scope.logs = "";
    $scope.editMode = false;
    $scope.logMode = false;
    var loadDataPromise = $http.get('/CMS/terminalList/terminals.htm').success(function(returnData) {
        $scope.terminals = returnData.terminals;
        $scope.logs = returnData.logs;
        //$scope.status = null;
        return "success";
    }).error(function(error) {
        //$scope.status = "Błąd";
        $scope.error = error;
        return "failure";
    });

    $scope.save = function() {        
        var o = $scope.selected;
        $http.post(
            '/CMS/terminalList/save/:terminal.htm',
            {terminal:o}).success(function(returnData) {
                
            }).error(function(error) {
                alert(error);
            });
    };

    loadDataPromise.then(function(data) {
        if (data != null) {
            $scope.status = "";
        } else {
            $scope.status = "Błąd:";
        }
    });

    $scope.select = function(terminal) {
        if($scope.selected == terminal) {
            $scope.selected = "";
            $scope.logMode = false;
        } else {
            $scope.selected = terminal;
            if($scope.editMode) {
                $scope.logMode = false;
            } else {
                $scope.logMode = true;
            }
        }
    }
    
    $scope.edit = function() {
        $scope.editMode = true;
        $scope.logMode = false;
    };
    
    $scope.cancel = function() {
        $scope.editMode = false;
        if($scope.selected == "") {
            $scope.logMode = false;
        } else {
            $scope.logMode = true;
        }
    };
    
    $scope.create = function() {
        $scope.selected = "";
        $scope.editMode = true;

    };
}