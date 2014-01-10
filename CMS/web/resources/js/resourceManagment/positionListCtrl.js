function PositionListCtrl($scope, $http, saveEditDelete, pagination) {
    
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "positions";
    
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'description';
    $scope.columns = {
        'name' : "Nazwa",
        'description' : "Opis"
    };
    $scope.selected = "";
    $scope.positions = "";
    $scope.privileges = "";
    $scope.editMode = false;
    $scope.get = saveEditDelete.get($http, '/CMS/positionList/positions.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        
        if(($scope.selected.name == null) || $scope.selected.description == null) {
            alert("Sprawdź poprowność wprowadzonych danych");
        } else {
            saveEditDelete.save($http, '/CMS/positionList/save/:object.htm', $scope);

        }
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.positions = $scope.initData.positions;
            $scope.privileges = $scope.initData.privileges;
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
        $scope.selected = "";
    };

    $scope.create = function() {
        $scope.selected = new Object();
        $scope.selected.hierarhy = 1;
        $scope.editMode = true;

    };
    
    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/positionList/delete/:object.htm', $scope);
    };
    
    $scope.ble = $scope[$scope.objectsName];
    
    $scope.checkEditPrivileges = function() {
        for (var i = 0; i<$scope.privileges.length; i++) {
            if($scope.privileges[i] == 'all') {
                return true;
            }
            if($scope.privileges[i] == "ManagePositions") {
                return true;
            }
        }
        return false;
    };
}
