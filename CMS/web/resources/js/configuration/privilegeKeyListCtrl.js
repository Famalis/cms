function PrivilegeKeyListCtrl($scope, $http) {
    $scope.status = "Ładowanie danych";
    $scope.editMode = false;
    $scope.editValue = "Edytuj";
    $scope.selected = null;
    var loadDataPromise = $http.get('/CMS/privilegeKeyList/privKeys.htm').success(function(returnData) {
        $scope.privilegeKeys = returnData;
        return "success";
    }).error(function(error) {
        $scope.error = error;
        return "failure";
    });

    $scope.save = function() {
        var o = $scope.selected;
        $http.post(
                '/CMS/privilegeKeyList/save/:privKey.htm',
                {privKey: o}).success(function(returnData) {
                    
        }).error(function(error) {
            
        });
        $scope.editMode = false;
        $http.get('/CMS/privilegeKeyList/privKeys.htm').success(function(returnData) {
            $scope.privilegeKeys = returnData;
            return "success";
        }).error(function(error) {
            $scope.error = error;
            return "failure";
        });
    };

    loadDataPromise.then(function(data) {
        if (data != null) {
            $scope.status = "";
        } else {
            $scope.status = "Błąd:";
        }
    });

    $scope.delete = function() {
        var o = $scope.selected;
        $http.post(
                '/CMS/privilegeKeyList/delete/:privKey.htm',
                {privKey: o}).success(function(returnData) {
            $scope.selected = "";
        }).error(function(error) {
            
        });
        $http.get('/CMS/privilegeKeyList/privKeys.htm').success(function(returnData) {
            $scope.privilegeKeys = returnData;
            return "success";
        }).error(function(error) {
            $scope.error = error;
            return "failure";
        });
    };

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

    $scope.select = function(selectedObject) {
        if($scope.selected == selectedObject) {
            $scope.selected = "";
        } else {
            $scope.selected = selectedObject;
        }
        
    };
}