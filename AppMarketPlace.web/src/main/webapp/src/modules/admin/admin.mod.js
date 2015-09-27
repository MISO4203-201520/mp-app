(function (ng) {
    var mod = ng.module('adminModule', ['ngCrud']);

    mod.constant('adminContext', 'users');

    mod.constant('adminModel', {
        fields: [{
                name: 'name',
                displayName: 'Name',
                type: 'String',
                required: true
            }, {
                name: 'id',
                displayName: 'UserID',
                type: 'String',
                required: true
            }
        ]});
})(window.angular);
