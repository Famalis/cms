var cmsModule = angular.module('cms', []);

cmsModule.factory('saveEditDelete', function() {
    return {
        save: function($http, link, $scope) {
            return $http.post(
                    link,
                    {object: $scope.selected}).success(function() {
                if (!$scope.selected.id) {
                    $scope[$scope.objectsName].push($scope.selected);
                }
            }).error(function(error) {
                alert(error);
            });
        },
        get: function($http, link, $scope) {
            return $http.get(link).success(function(returnData) {
                $scope.status = null;
                $scope.initData = returnData;
                $scope[$scope.objectsName] = returnData[$scope.objectsName];
                $scope.objects = returnData[$scope.objectsName];
                //alert(returnData.departmnets);
                return "Success";
            }).error(function(error) {
                $scope.status = "Błąd";
                return null;
            });
        },
        remove: function($http, link, $scope) {
            return $http.post(
                    link,
                    {object: $scope.selected}).success(function() {
                var index;
                for (var i = 0; i < $scope[$scope.objectsName].length; i++) {
                    if ($scope[$scope.objectsName][i].id == $scope.selected.id) {
                        index = i;
                    }
                }
                $scope[$scope.objectsName].splice(index, 1);
                $scope.selected = "";
            }).error(function(error) {

            });
        }
    };
});

cmsModule.factory('pagination', function() {
    return {
        indexOnPage: function($scope) {
            return function(index)
            {
                if (index <= $scope.pageMax && index >= $scope.pageMin) {
                    return true;
                } else {
                    return false;
                }
            };
        },
        
        pageMaxSmallerThenSize: function($scope) {
          return function() {
              if($scope.pageMax < $scope[$scope.objectsName].length) {
                  return true;
              } else {
                  return false;
              }
          };  
        }
    };
});
