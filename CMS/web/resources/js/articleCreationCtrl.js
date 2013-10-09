function ArticleCreationCtrl($scope, $http) {
    
    $scope.save = function() {
        $http.post(
            '/CMS/article/save/:article.htm',
            {article:$scope.article}).success(function(returnData) {
                
            }).error(function(error) {
                alert(error);
            });
    };
}