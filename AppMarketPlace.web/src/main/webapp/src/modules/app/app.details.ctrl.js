(function (ng) {
    var mod = ng.module('appModule');

    mod.controller('appDetailsCtrl', ['$scope', '$routeParams', 'appService', '$location', 'authService', function ($scope, $routeParams, svc, $location, authService) {
            var appId = $routeParams.appId;
            svc.getAppById(appId).then(function (app) {
                $scope.app = app;
            });
            $scope.actions = {
                upload: {
                    displayName: 'Upload media',
                    icon: 'upload',
                    fn: function () {
                        $location.path('/app/' + appId + '/upload');
                    },
                    show: function () {
                        var user = authService.getCurrentUser();
                        return user && user.role === 'developer';
                    }
                }
            };
        }]);
})(window.angular);
