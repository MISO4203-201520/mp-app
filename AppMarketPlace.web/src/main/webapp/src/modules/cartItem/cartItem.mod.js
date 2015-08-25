(function (ng) {
    var mod = ng.module('cartItemModule', ['ngCrud']);

    mod.constant('cartItemContext', 'cartItems');

    mod.constant('cartItemModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }, {
                name: 'quantity',
                displayName: 'Quantity',
                type: 'Integer',
                required: true
            }, {
                name: 'app',
                displayName: 'App',
                type: 'Reference',
                service: 'appService',
                options: [],
                required: true
            }, {
                name: 'price',
                displayName: 'Price',
                type: 'Computed',
                fn: function (record) {
                    return record.app.price;
                },
                required: true,
                editable: false,
                currency: true
            }, {
                name: 'SubTotal',
                displayName: 'SubTotal',
                type: 'Computed',
                editable: false,
                fn: function (record) {
                    return record.app.price * record.quantity;
                }
            }]});
})(window.angular);
