(function (ng) {

    var mod = ng.module('transactionModule');

    mod.controller('transactionCtrl', ['CrudCreator', '$scope', 'transactionModel', 'transactionService', function (CrudCreator, $scope, model, svc) {
            CrudCreator.extendController(this, svc, $scope, model, 'transaction', 'Transactions');
            showList(svc, $scope, this, "Transactions");
        }]);

    var showList = function (adminSvc, $scope, self, context) {
        $scope.context = context;
        self.fetchRecords();
        self.actions = {
            comment: {
                displayName: 'comment',
                icon: 'comment',
                class: 'primary',
                fn: function (record) {
                    console.log(record);
                    //
                },
                show: function () {
                    return true;
                }
            }
        };
    };

})(window.angular);