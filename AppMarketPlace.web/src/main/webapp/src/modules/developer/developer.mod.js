(function (ng) {
    var mod = ng.module('developerModule', ['ngCrud']);

    mod.constant('developerContext', 'developers');

    mod.constant('developerModel', {
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
                name: 'apps',
                displayName: 'Apps',
                //template: '', //override generic template
                ctrl: 'appsCtrl'
            }]
    });
})(window.angular);
