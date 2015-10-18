(function (ng) {
    var mod = ng.module('appModule');

    mod.controller('mediaCtrl', ['$scope', '$routeParams', 'FileUploader', function ($scope, $routeParams, FileUploader) {
            var appId = $routeParams.appId;
            $scope.uploader = new FileUploader({
                url: 'webresources/apps/' + appId + '/media'
            });
        }]);
})(window.angular);
