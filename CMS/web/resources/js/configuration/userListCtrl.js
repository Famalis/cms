function UserListCtrl($scope, $http, saveEditDelete, pagination) {
    
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 9;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "users";
    $scope.attributes = [];
    $scope.attributes[0] = 'surname';
    $scope.attributes[1] = 'name';
    $scope.attributes[2] = 'login';
    $scope.attributes[3] = 'groupName';
    $scope.attributes[4] = 'employeeId';
    $scope.columns = {
      'employeeId' : "Pracownik",
      'groupName' : "Grupa",
      'login' : "Login",      
      'name' : "Imię",
      'surname' : "Nazwisko"      
    };
    $scope.selected = "";
    $scope.editMode = false;
    $scope.get = saveEditDelete.get($http, '/CMS/userList/users.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/userList/save/:object.htm', $scope);
        
        var date = new Date();
        var curDate = null;
        do { curDate = new Date(); }
        while(curDate-date < 1000);
  
        $scope.get = saveEditDelete.get($http, '/CMS/userList/users.htm', $scope);
        loadDataPromise = $scope.get;
        $scope.selected = null;
        $scope.editMode = false;
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.employees = $scope.initData.employees;
            $scope.groups = $scope.initData.groups;
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
    };

    $scope.create = function() {
        $scope.selected = "";
        $scope.editMode = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/userList/delete/:object.htm', $scope);
    };
    
    $scope.getGroupName = function(id) {
      for (var i = 0; i<$scope.groups.length; i++) {
          if($scope.groups[i].id == id){
              return $scope.groups[i].name;
          }
      }  
    };
    
    $scope.generateLogin = function(emp) {
        var login = emp.name.substring(0,1) + emp.surname.substring(0,emp.surname.length);
        var login = login.toLowerCase();
        return login;
    };
    
    $scope.checkEditPrivileges = function() {
        return true;
    };
}