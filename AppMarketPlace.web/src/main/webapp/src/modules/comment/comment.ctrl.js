(function (ng, $) {
    var mod = ng.module('commentModule');
    mod.controller('commentCtrl', ['$scope', '$http', function ($scope, $http) {
            $scope.model = {};
            submitForm = function () {
                $("#commentsubmit").button('loading');
                var date = new Date().toJSON().slice(0, 10);
                $http.post('http://localhost:8080/AppMarketPlace.web/webresources/comments', {
                    comment: $scope.model.comment,
                    date: date,
                    app: {
                        id: $scope.selectedApp.id
                    }
                }).success(function () {
                    $("#commentsubmit").button('reset');
                    $scope.modalInstance.dismiss('cancel');
                }).error(function () {
                    $("#commentsubmit").button('reset');
                    $scope.modalInstance.dismiss('cancel');
                });
            };
        }]);
})(window.angular, window.$);
