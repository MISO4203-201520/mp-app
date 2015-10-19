(function (ng) {
    var mod = ng.module('developerModule');

    mod.service('developerService', ['CrudCreator', 'developerContext', 'Restangular', function (CrudCreator, context, rest) {
            CrudCreator.extendService(this, context);
            
            // Find a developer by username
            this.getDeveloperByUsername = function (username) {
                return rest.one(context, username).get();
            };
        }]);
})(window.angular);
