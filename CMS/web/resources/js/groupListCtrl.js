function GroupListCtrl($scope, $http) {
    $scope.status = "Ładowanie danych";
    $scope.editMode = false;
    $scope.selected = "";
    $scope.selected.privilegeKeyIds = ""
    $scope.edit = function() {
        $scope.editMode = true;
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
        $http.get('/CMS/groupList/groups.htm').success(function(returnData) {
            $scope.groups = returnData.groups;
            $scope.status = '';
            return "success";
        }).error(function(error) {
            $scope.status = error;
            return "failure";
        });
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

}