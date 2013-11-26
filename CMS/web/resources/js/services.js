var saveEditDeleteModule = angular.module('cms', []);

saveEditDeleteModule.factory('saveEditDelete', function() {
    return {
        save: function($http, link, object) {
            return $http.post(
                    link,
                    {object: object}).success(function() {

            }).error(function(error) {
                alert(error);
            });
        },
        get: function($http, link, $scope) {
            return $http.get(link).success(function(returnData) {
                $scope.status = null;
                $scope.initData = returnData;
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
                $scope.selected = "";
            }).error(function(error) {

            });
        }
    };
});
