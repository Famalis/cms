function PositionListCtrl($scope, $http) {
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.positions = "";
    $scope.editMode = false;
    var loadDataPromise = $http.get('/CMS/positionList/positions.htm').success(function(returnData) {
        $scope.positions = returnData.positions;
        return "success";
    }).error(function(error) {
        $scope.error = error;
        return "failure";
    });

    $scope.save = function() {        
        var o = $scope.selected;
        $http.post('/CMS/positionList/save/:position.htm',{position:o}).success(function(returnData) {                
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

    $scope.select = function(position) {
        if($scope.selected == position) {
            $scope.selected = "";
        } else {
            $scope.selected = position;
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