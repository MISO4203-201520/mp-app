(function(ng){
    var mod = ng.module('appModule');
    
    mod.service('appService', ['CrudCreator','appContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
    }]);
})(window.angular);
