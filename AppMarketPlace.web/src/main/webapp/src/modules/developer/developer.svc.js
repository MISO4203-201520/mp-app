(function (ng) {
    var mod = ng.module('developerModule');

    mod.service('developerService', ['CrudCreator', 'developerContext', function (CrudCreator, context) {
            CrudCreator.extendService(this, context);
            
            // Find a developer by his or her id
            this.findDeveloper = function (id) {
                return this.api.one('' + id).get();
            };
        }]);
})(window.angular);
