(function (ng) {
    var mod = ng.module('paymentCardsModule');

    mod.service('paymentCard', ['CrudCreator', 'paymentContext', function (CrudCreator, context) {
            CrudCreator.extendService(this, context);
        }]);

    mod.service('paymentMethod', ['CrudCreator', 'paymentMethodContext', function (CrudCreator, context) {
            CrudCreator.extendService(this, context);
        }]);

    mod.service('transaction', ['CrudCreator', 'transactionContext', function (CrudCreator, context) {
            CrudCreator.extendService(this, context);

            this.placePayment = function (record) {
                return this.api.customPOST(record, '', {}, {});
            };
        }]);

})(window.angular);