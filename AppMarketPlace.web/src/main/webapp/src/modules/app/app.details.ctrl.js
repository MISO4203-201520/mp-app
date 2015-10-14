(function (ng) {
    var mod = ng.module('appModule');

    mod.controller('appDetailsCtrl', ['$scope', '$routeParams', 'appService', function ($scope, $routeParams, svc) {
            var appId = $routeParams.appId;
            svc.getAppById(appId).then(function (app) {
                $scope.app = app;
            });
        }]);
})(window.angular);
