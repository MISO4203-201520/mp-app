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
            }]});
})(window.angular);
