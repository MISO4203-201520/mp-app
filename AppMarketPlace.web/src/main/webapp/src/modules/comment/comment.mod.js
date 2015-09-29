(function (ng) {
    var mod = ng.module('commentModule', ['ngCrud']);

    mod.constant('commentContext', 'comments');

    mod.constant('commentModel', {
        fields: [{
                name: 'comment',
                displayName: 'Comment',
                type: 'String',
                required: true
            }, {
                name: 'date',
                displayName: 'date',
                type: 'String',
                required: true
            }
        ]});
})(window.angular);
