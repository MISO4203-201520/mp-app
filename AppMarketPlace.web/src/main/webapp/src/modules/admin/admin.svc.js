(function (ng) {
    var mod = ng.module('adminModule');

    mod.service('adminClientService', ['CrudCreator', 'adminClientContext', function (CrudCreator, context) {
            CrudCreator.extendService(this, context);

            this.disableUser = function (user) {
                return this.api.one(user + '/disable').post();
            };
        }]);
    mod.service('adminDeveloperService', ['CrudCreator', 'adminDevContext', function (CrudCreator, context) {
            CrudCreator.extendService(this, context);

            this.disableUser = function (user) {
                return this.api.one(user + '/disable').post();
            };
        }]);
    mod.service('usersService', ['CrudCreator', 'usersContext', function (CrudCreator, context) {
            CrudCreator.extendService(this, context);

            this.getCurrentUser = function () {
                return this.api.one('currentUser').get();
            };
        }]);
})(window.angular);
