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
                name: 'userId',
                displayName: 'UserId',
                type: 'String',
                required: true
            }], 
        childs: [{
                name: 'cartItems',
                displayName: 'CartItems',
                //template: '', //override generic template
                ctrl: 'cartItemsCtrl'            }        ]});
})(window.angular);
