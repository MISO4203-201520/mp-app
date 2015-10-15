(function (ng) {
    var mod = ng.module('developerModule');

    mod.controller('developerCtrl', ['CrudCreator', '$scope', 'developerService', 'developerModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'developer', 'Developer');
            this.fetchRecords();
        }]);

    mod.controller('appsCtrl', ['CrudCreator', '$scope', 'appModel', function (CrudCreator, $scope, model) {
            CrudCreator.extendCompChildCtrl(this, $scope, model, 'apps', 'developer');
        }]);

    mod.controller('devProfileCtrl', ['CrudCreator', '$scope', '$location', 'developerService', 'developerModel', 'usersService',
        function (CrudCreator, $scope, $location, svc, model, userSvc) {
            CrudCreator.extendController(this, svc, $scope, model, 'developer', 'Developer');

            // Getting current developer by username returned via userSvc
            userSvc.getCurrentUser().then(function (user) {
                if (user) {
                    svc.getDeveloperByUsername(user.userName).then(function (developer) {
                        $scope.developers = [];
                        if (developer) {
                            $scope.developers.push(developer);
                        }
                    });
                } else {
                    $location.path("#/catalog");
                }
            });
        }]);
})(window.angular);
