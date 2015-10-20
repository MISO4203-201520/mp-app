(function (ng) {
    var mod = ng.module('transactionModule', ['ngCrud']);
    mod.constant('transactionContext', 'transaction');

    mod.constant('transactionModel', {
        fields: [{
                name: 'payer',
                displayName: 'Client',
                type: 'Reference',
                service: 'clientService',
                options: [],
                required: true
            }, {
                name: 'recipient',
                displayName: 'App',
                type: 'Reference',
                service: 'appService',
                options: [],
                required: true
            }, {
                name: 'total',
                displayName: 'Total',
                type: 'Integer',
                required: true
            }, {
                name: 'status',
                displayName: 'Status',
                type: 'String',
                required: true
            }
        ]});
})(window.angular);
