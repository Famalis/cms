function UserListCtrl($scope, $http, saveEditDelete) {
    $scope.status = "Ładowanie danych";
    $scope.objectsName = "users";
    $scope.attributes = [];
    $scope.attributes[0] = 'surname';
    $scope.attributes[1] = 'name';
    $scope.attributes[2] = 'login';
    $scope.attributes[3] = 'bgcolor';
    $scope.attributes[4] = 'groupId';
    $scope.attributes[5] = 'employeeId';
    $scope.columns = {
      'employeeId' : "Pracownik",
      'groupId' : "Grupa",
      'bgcolor' : "Kolor tła",
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
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.users = $scope.initData.users;
            $scope.employees = $scope.initData.employees;
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
}