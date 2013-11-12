var saveEditDeleteModule = angular.module('cms', []);

saveEditDeleteModule.factory('serviceFunction', function() {
    return function(input) {
        return input;
    };
});