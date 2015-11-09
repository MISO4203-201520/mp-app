(function (ng) {
    var mod = ng.module('clientModule');

    mod.service('clientService', ['CrudCreator', 'clientContext', function (CrudCreator, context) {
            CrudCreator.extendService(this, context);

            this.getClientProfile = function (username) {
                return this.api.customGET("client/" + username, '');
            };
        }]);
})(window.angular);
