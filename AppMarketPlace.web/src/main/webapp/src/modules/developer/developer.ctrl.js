(function (ng) {
    var mod = ng.module('developerModule');

    mod.controller('developerCtrl', ['CrudCreator', '$scope', 'developerService', 'developerModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'developer', 'Developer');
            this.fetchRecords();
        }]);

    mod.controller('appsCtrl', ['CrudCreator', '$scope', 'appModel', function (CrudCreator, $scope, model) {
            CrudCreator.extendCompChildCtrl(this, $scope, model, 'apps', 'developer');
        }]);

    mod.controller('devProfileCtrl', ['CrudCreator', '$scope', 'developerService', 'developerModel', 'usersService',
        function (CrudCreator, $scope, svc, model, userSvc) {
            CrudCreator.extendController(this, svc, $scope, model, 'developer', 'Developer');

            // Hidding or Showing 'My profile' option
            // Only for developers
            userSvc.getCurrentUser().then(function (user) {
                if (user.role === 'developer') {
                    $("#devprofile").show();

                    // Getting current developer by username returned via userSvc
                    svc.getDeveloperByUsername(user.userName).then(function (developer) {

                        $scope.developers = [];
                        if (developer) {
                            $scope.developers.push(developer);
                        }
                    });
                } else {
                    $("#devprofile").hide();
                }
            });
            $(".dropdown-menu > li > a").click(function () {
                $("#devprofile").hide();
            });
        }]);
})(window.angular);
