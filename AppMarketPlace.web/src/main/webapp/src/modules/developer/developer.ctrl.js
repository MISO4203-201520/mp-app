(function (ng) {
    var mod = ng.module('developerModule');

    mod.controller('developerCtrl', ['CrudCreator', '$scope', 'developerService', 'developerModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'developer', 'Developer');
            this.fetchRecords();
        }]);

    mod.controller('appsCtrl', ['CrudCreator', '$scope', 'appModel', function (CrudCreator, $scope, model) {
            CrudCreator.extendCompChildCtrl(this, $scope, model, 'apps', 'developer');
        }]);
})(window.angular);
