(function (ng) {
    var mod = ng.module('clientModule');

    mod.controller('clientCtrl', ['CrudCreator', '$scope', 'clientService', 'clientModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'client', 'Client');
            this.fetchRecords();
        }]);

    mod.controller('cartItemsCtrl', ['CrudCreator', '$scope', 'cartItemModel', function (CrudCreator, $scope, model) {
            CrudCreator.extendCompChildCtrl(this, $scope, model, 'cartItems', 'client');
            this.loadRefOptions();
        }]);

    mod.controller('clientProfileCtrl', ['CrudCreator', '$scope', '$cookies', '$location', 'clientService', 'clientModel', 'transaction', function (CrudCreator, $scope, $cookies, $location, svc, model, trnSvc) {
            CrudCreator.extendController(this, svc, $scope, model, 'client', 'Client');

            svc.getClientProfile($cookies.getObject('userCookie').userName).then(function (client) {
                if (client) {
                    $scope.client = client;
                    trnSvc.getTxByPayer($scope.client.id).then(function (transaction) {
                        $scope.transaction = [];
                        if (transaction) {
                            $scope.transaction.push(transaction);
                        }
                    });
                } else {
                    $location.path("#/catalog");
                }
            });

        }]);

    mod.controller('appsCtrl', ['CrudCreator', '$scope', 'appModel', function (CrudCreator, $scope, model) {
            CrudCreator.extendCompChildCtrl(this, $scope, model, 'apps', 'client');
        }]);

    mod.controller('transactionCtrl', ['CrudCreator', '$scope', 'transactionModel', function (CrudCreator, $scope, model) {
            CrudCreator.extendCompChildCtrl(this, $scope, model, 'transaction', 'client');
        }]);
})(window.angular);
