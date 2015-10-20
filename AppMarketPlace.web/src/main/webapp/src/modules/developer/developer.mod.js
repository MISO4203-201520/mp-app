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
                name: 'photo',
                displayName: 'Profile photo',
                type: 'Image',
                required: true
            }, {
                name: 'bannerProfile',
                displayName: 'Profile banner',
                type: 'Image',
                required: true
            }, {
                name: 'commentProfile',
                displayName: 'Profile comment',
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
