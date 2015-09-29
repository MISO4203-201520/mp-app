(function (ng) {
    var mod = ng.module('paymentCardsModule', ['ngCrud']);

    mod.constant('paymentContext', 'paymentCard');
    mod.constant('paymentMethodContext', 'paymentMethod');
    mod.constant('transactionContext', 'transaction');

    mod.constant('paymentCardsModel', {
        fields: [{
                name: 'fullname',
                displayName: 'Cardholder Name',
                type: 'String',
                required: true
            },
            {
                name: 'cardnumber',
                displayName: 'Card Number',
                type: 'Integer',
                required: true
            },
            {
                name: 'securityCode',
                displayName: 'Security Code',
                type: 'Integer',
                required: true
            },
            {
                name: 'dueDate',
                displayName: 'Due Date',
                type: 'Date',
                required: true
            },
            {
                name: 'paymentType',
                displayName: 'Payment Method',
                type: 'Reference',
                service: 'paymentMethod',
                options: [],
                required: true
            }
        ]});
})(window.angular);
