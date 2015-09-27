(function (ng) {
    var mod = ng.module('adminModule', ['ngCrud']);
    mod.constant('adminContext', 'admin');
    mod.constant('adminClientContext', 'admin/clients');
    mod.constant('adminDevContext', 'admin/developers');

    mod.constant('adminModel', {
       fields: [{
                name: 'name',
                displayName: 'Username',
                type: 'String',
                required: true
            }, {
                name: 'userId',
                displayName: 'UserId',
                type: 'String',
                required: true
            },{
                name: 'email',
                displayName: 'Email',
                type: 'String',
                required: true
            },{
                name: 'fullName',
                displayName: 'Name',
                type: 'String',
                required: true
            },{
                name: 'status',
                displayName: 'Status',
                type: 'String',
                required: true
            },
            
        ]});
})(window.angular);
