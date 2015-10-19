(function (ng) {
    var mod = ng.module('transactionModule');

    mod.service('transactionService', ['CrudCreator', 'transactionContext', function (CrudCreator, context) {
            CrudCreator.extendService(this, context);
        }]);
})(window.angular);
