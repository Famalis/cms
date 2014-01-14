function ContractListCtrl($scope, $http, saveEditDelete, pagination) {
    
    $scope.indexOnPage = pagination.indexOnPage($scope);
    $scope.pageMin = 0;
    $scope.pageMax = 14;
    $scope.checkMax = pagination.pageMaxSmallerThenSize($scope);
    
    $scope.status = "Ładowanie danych";
    $scope.selected = "";
    $scope.contracts = "";
    $scope.employees = "";
    $scope.customers = "";
    $scope.privileges = "";
    $scope.editMode = false;
    
    $scope.objectsName = "contracts";
    $scope.attributes = [];
    $scope.attributes[0] = 'employeeName';
    $scope.attributes[1] = 'employeeSurname';
    $scope.attributes[2] = 'customerName';
    $scope.attributes[3] = 'customerSurname';
    $scope.attributes[4] = 'date';
    $scope.attributes[5] = 'price';
    $scope.attributes[6] = 'description';
    $scope.columns = {
        'employeeName' : "Pracownik: Imię",
        'employeeSurname' : "Pracownik: Nazwisko",
        'customerName': "Klient: Imię",
        'customerSurname' : "Klient: Nazwisko",
        'date': "Data",
        'price': "Cena",
        'description': "Opis"
    };
   
    $scope.get = saveEditDelete.get($http, '/CMS/contractList/contracts.htm', $scope);
    var loadDataPromise = $scope.get;

    $scope.save = function() {
        
        if($scope.selected.employeeId == null || $scope.selected.employeeId == -1 || $scope.selected.customerId == null || $scope.selected.customerId == -1 || $scope.selected.price == null || $scope.selected.description == null) {
            alert("Sprawdź poprowność wprowadzonych danych");
        } else {
            saveEditDelete.save($http, '/CMS/contractList/save/:object.htm', $scope);
        }
    };

    loadDataPromise.then(function(returnData) {
        if (returnData != null) {
            $scope.privileges = $scope.initData.privileges;
            $scope.contracts = $scope.initData.contracts;
            $scope.employees = $scope.initData.employees;
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
        $scope.selected = "";
    };

    $scope.create = function() {
        $scope.selected = new Object();
        $scope.selected.employeeId = -1;
        $scope.selected.customerId = -1;
        $scope.editMode = true;

    };

    $scope.delete = function() {
        saveEditDelete.remove($http, '/CMS/contractList/delete/:object.htm', $scope);
    };
    
    $scope.getEmployeeName = function(posId) {
        for (var i = 0; i<$scope.employees.length; i++) {
            if($scope.employees[i].id == posId) {
                return $scope.employees[i].name;
            }
        }
    };
    
    $scope.getEmployeeSurname = function(posId) {
        for (var i = 0; i<$scope.employees.length; i++) {
            if($scope.employees[i].id == posId) {
                return $scope.employees[i].surname;
            }
        }
    };
    
    $scope.getCustomerName = function(depId) {
        for (var i = 0; i<$scope.customers.length; i++) {
            if($scope.customers[i].id == depId) {
                return $scope.customers[i].name;
            }
        }
    };
    
    $scope.getCustomerSurname = function(depId) {
        for (var i = 0; i<$scope.customers.length; i++) {
            if($scope.customers[i].id == depId) {
                return $scope.customers[i].surname;
            }
        }
    };
    
    $scope.checkEditPrivileges = function() {
        for (var i = 0; i<$scope.privileges.length; i++) {
            if($scope.privileges[i] == 'all') {
                return true;
            }
            if($scope.privileges[i] == "ManageContracts") {
                return true;
            }
        }
        return false;
    };
    
}