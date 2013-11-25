function GroupListCtrl($scope, $http, saveEditDelete) {
    $scope.status = "Ładowanie danych";
    $scope.editMode = false;
    $scope.selected = "";
    $scope.selected.privilegeKeyIds = "";
    $scope.get = saveEditDelete.get($http, '/CMS/groupList/groups.htm', $scope);
    $scope.aGet = function() {
        $scope.status = "Zapisywanie zmian...";
        for (var i=0; i<1000000; i++) {
            
        }
        saveEditDelete.get($http, '/CMS/groupList/groups.htm', $scope);
        $scope.groups = $scope.initData.groups;
    };

    $scope.edit = function() {
        $scope.editMode = true;
    };
    
    $scope.cancel = function() {
        $scope.editMode = false;
    };
    
     $scope.loadData = function () {
            $http.get('/CMS/groupList/groups.htm').success(function(returnData) {
                $scope.groups = returnData.groups;
                $scope.status = '';
                return "success";
            }).error(function(error) {
                $scope.status = error;
                return "failure";
            });
        };
    

    $scope.create = function() {
        $scope.selected = "";
        $scope.selected.privilegeKeyIds = ""
        $scope.editMode = true;

    };

    $scope.select = function(selectedObject) {
        if ($scope.selected == selectedObject) {
            $scope.selected = "";
            $scope.selected.privilegeKeyIds = "";
        } else {
            $scope.selected = selectedObject;
        }
    
    };

    var loadDataPromise = $http.get('/CMS/groupList/groups.htm').success(function(returnData) {
        $scope.groups = returnData.groups;
        $scope.privilegeKeys = returnData.privilegeKeys;
        $scope.status = null;
        return "success";
    }).error(function(error) {
        $scope.status = "Błąd";
        $scope.error = error;
        return "failure";
    });

    $scope.save = function() {
        var o = $scope.selected;
        $http.post(
                '/CMS/groupList/save/:group.htm',
                {group: o}).success(function() {
            $scope.editMode = false;
        }).error(function(error) {

        });
        $scope.status = "Zapisywanie zmian...";
        /*$scope.loadData = function () {
            $http.get('/CMS/groupList/groups.htm').success(function(returnData) {
                $scope.groups = returnData.groups;
                $scope.status = '';
                return "success";
            }).error(function(error) {
                $scope.status = error;
                return "failure";
            });
        };
        $scope.loadData();*/
        $scope.loadData();
    };

    $scope.delete = function() {
        var o = $scope.selected;
        $http.post(
                '/CMS/groupList/delete/:group.htm',
                {group: o}).success(function() {
            $scope.selected = "";
        }).error(function(error) {

        });
        $scope.status = "Zapisywanie zmian...";
        $http.get('/CMS/groupList/groups.htm').success(function(returnData) {
            $scope.groups = returnData.groups;
            $scope.status = '';
            return "success";
        }).error(function(error) {
            $scope.status = error;
            return "failure";
        });
        $scope.loadData();
    };

    loadDataPromise.then(function(data) {
        if (data != null) {
            $scope.status = "";
        } else {
            $scope.status = "Błąd:";
        }
    });

    $scope.addKey = function() {
        if (!$scope.selectedGroupHasKey($scope.newKeyId)) {
            $scope.selected.privilegeKeyIds.push($scope.newKeyId);
        }
    };
    
    $scope.removeKey = function() {
        if ($scope.selectedGroupHasKey($scope.oldKeyId)) {
            var index = $scope.selected.privilegeKeyIds.indexOf($scope.oldKeyId);
            $scope.selected.privilegeKeyIds.splice(index, 1);
            $scope.oldKeyId = 0;
        }
    }

    $scope.groupHasKey = function(group, privKeyId) {
        for (var i = 0; i < group.privilegeKeyIds.length; i++) {
            if (group.privilegeKeyIds[i] == privKeyId) {
                return true;
            }
        }
        return false;
    }

    $scope.selectedGroupHasKey = function(privKeyId) {
        if ($scope.selected.privilegeKeyIds !== undefined) {
            for (var i = 0; i < $scope.selected.privilegeKeyIds.length; i++) {
                if ($scope.selected.privilegeKeyIds[i] == privKeyId) {
                    return true;
                }
            }
        }
        return false;
    }
    $scope.loadData();
}