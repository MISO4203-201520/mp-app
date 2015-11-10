(function (ng) {
    var mod = ng.module('clientModule', ['ngCrud']);

    mod.constant('clientContext', 'clients');

    mod.constant('clientModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }, {
                name: 'firstName',
                displayName: 'First name',
                type: 'String',
                required: true
            }, {
                name: 'lastName',
                displayName: 'Last name',
                type: 'String',
                required: true
            }, {
                name: 'email',
                displayName: 'Email',
                type: 'String',
                required: true
            }, {
                name: 'userId',
                displayName: 'UserId',
                type: 'String',
                required: true
            }],
        childs: [{
                name: 'cartItems',
                displayName: 'CartItems',
                //template: '', //override generic template
                ctrl: 'cartItemsCtrl'
            }, {
                name: 'apps',
                displayName: 'Apps',
                //template: '', //override generic template
                ctrl: 'appsCtrl'
            }, {
                name: 'transaction',
                displayName: 'Transaction',
                //template: '', //override generic template
                ctrl: 'transactionCtrl'
            }]});

    mod.constant('transactionModel', {
        fields: [{
                name: 'id',
                displayName: 'Id',
                type: 'String',
                required: true
            }, {
                name: 'payer',
                displayName: 'Payer',
                type: 'String',
                required: true
            }, {
                name: 'recipient',
                displayName: 'Recipient',
                type: 'String',
                required: true
            }, {
                name: 'total',
                displayName: 'Total',
                type: 'String',
                required: true
            }, {
                name: 'status',
                displayName: 'Status',
                type: 'String',
                required: true
            }, {
                name: 'payment_card',
                displayName: 'Payment Card',
                type: 'String',
                required: true
            }, {
                name: 'date',
                displayName: 'Transaction Date',
                type: 'String',
                required: true
            }]});
})(window.angular);
