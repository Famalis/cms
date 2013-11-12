function TerminalListCtrl($scope, $http) {
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.terminals = "";
    var loadDataPromise = $http.get('/CMS/terminalList/terminals.htm').success(function(returnData) {
        $scope.terminals = returnData.terminals;
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

    $scope.selectUser = function(id) {
        //alert("test");
        for (var i = 0; i < $scope.users.length; i++) {
            //alert($scope.users[i].name);
            if ($scope.users[i].id === id) {
                $scope.selectedUser = $scope.users[i];

            }
        }
    };
}