(function (ng) {
    var mod = ng.module('paymentCardsModule');

    mod.controller('paymentCtrl', ['CrudCreator', '$scope', 'paymentCard', 'transaction', 'paymentCardsModel', '$location', '$timeout', '$filter', function (CrudCreator, $scope, svc, svcPay, model, $location, $timeout, $filter) {
            CrudCreator.extendController(this, svc, $scope, model, 'payment', 'Payment');

            this.loadRefOptions();
            this.fetchRecords();

            var self = this;
            this.readOnly = true;

            self.recordActions.pay = {
                displayName: 'Buy',
                icon: 'usd',
                class: 'primary',
                fn: function (record) {
                    
                    svcPay.placePayment(record).then(function () {
                        self.showSuccess("Pago realizado exitosamente ... redirigiendo a catalogo");
                    }).then($timeout(payCompleted, 4000));
                },
                show: function () {
                    return true;
                }
            };

            function payCompleted() {
                $location.path('/catalog');
            }

        }]);
})(window.angular);
