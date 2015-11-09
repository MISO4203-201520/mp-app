(function (ng) {
    var mod = ng.module('appModule');

    mod.controller('sourceCtrl', ['$scope', '$routeParams', 'FileUploader', function ($scope, $routeParams, FileUploader) {
            $scope.appId = $routeParams.appId;
            $scope.uploader = new FileUploader({
                url: 'webresources/apps/' + $scope.appId + '/source'
            });
        }]);
})(window.angular);
