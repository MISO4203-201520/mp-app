(function(ng){
    var mod = ng.module('developerModule');
    
    mod.service('developerService', ['CrudCreator','developerContext', function(CrudCreator, context){
            CrudCreator.extendService(this, context);
    }]);
})(window.angular);
