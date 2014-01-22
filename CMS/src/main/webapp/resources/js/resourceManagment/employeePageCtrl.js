function EmployeePageCtrl($scope, $http, saveEditDelete) {
    
    $scope.selectedEmployment = "";
    $scope.empId = "";
    $scope.get = $http.post(
            '/CMS/employeePage/loadData/:id.htm',
            {id:35}).success(function(data) {
                $scope.employee = data.employee;                
                $scope.employments = data.employments;
            }).error(function(error) {
               alert("error\n"+error) ;
            });
    var loadDataPromise = $scope.get;
    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            
        } else {
            alert('err');
        }
    });
    
    $scope.selectEmployment = function(id) {
      for (var i = 0; $scope.employments.length; i++) {
          if($scope.employments[i].id == id) {
              $scope.selectedEmployment = $scope.employments[i];
              return $scope.employments[i];
          }
      }  
    };
}