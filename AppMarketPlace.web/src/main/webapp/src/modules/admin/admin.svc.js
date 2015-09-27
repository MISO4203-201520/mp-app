(function (ng) {
    var mod = ng.module('adminModule');

    mod.service('adminClientService', ['CrudCreator', 'adminClientContext', function (CrudCreator, context) {
        CrudCreator.extendService(this, context);
        
        this.disableUser = function(text){
            
        };
    }]);
    mod.service('adminDeveloperService', ['CrudCreator', 'adminDevContext', function (CrudCreator, context) {
        CrudCreator.extendService(this, context);
        
        this.disableUser = function(text){
            
        };
    }]);
})(window.angular);
