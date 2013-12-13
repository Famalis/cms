function CustomerListCtrl($scope, $http, saveEditDelete) {
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.customers = "";
    $scope.editMode = false;
    $scope.displayPage = true;
    $scope.displayPageName = "customerPage";
    
    $scope.objectsName = "customers";
    $scope.attributes = [];
    $scope.attributes[0] = 'name';
    $scope.attributes[1] = 'surname';
    $scope.attributes[2] = 'phone';
    $scope.attributes[3] = 'email';
    $scope.attributes[4] = 'companyName';
    $scope.columns = {
        'name' : "Imię",
        'surname': "Nazwisko",
        'phone': "Telefon",
        'email': "Email",
        'companyName': "Firma"
    };
    $scope.columnClasses = {
        'name' : "klient-name",
        'surname': "klient-surname",
        'phone': "klient-phone",
        'email': "klient-email",
        'companyName': "klient-companyName"
    }
        
    $scope.get = saveEditDelete.get($http, '/CMS/customerList/customers.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        saveEditDelete.save($http, '/CMS/customerList/save/:object.htm', $scope);
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.customers = $scope.initData.customers;
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
        saveEditDelete.remove($http, '/CMS/customerList/delete/:object.htm', $scope);
    };
    
    $scope.checkEditPrivileges = function() {
        
    };
}