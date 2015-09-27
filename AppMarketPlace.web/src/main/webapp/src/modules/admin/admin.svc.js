(function (ng) {
    var mod = ng.module('adminModule');

    mod.service('adminService', ['CrudCreator', 'adminContext', function (CrudCreator, context) {
        CrudCreator.extendService(this, context);

        this.fullUser = function () {
            return this.api.one('../users/currentUser').get();
        };  
        
        this.disableUser = function(text){
           // return this.api
        }
    }]);
})(window.angular);
