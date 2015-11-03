(function (ng) {

    var mod = ng.module('transactionModule');

    mod.controller('transactionCtrl', ['CrudCreator', '$scope', 'transactionModel', 'transactionService', function (CrudCreator, $scope, model, svc) {
            CrudCreator.extendController(this, svc, $scope, model, 'transaction', 'Transactions');
            $scope.context = "Transactions";
            this.fetchRecords();
        }]);
})(window.angular);