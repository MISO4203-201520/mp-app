(function (ng) {
    var mod = ng.module('forgotModule');

    mod.controller('verifyCtrl', ['forgotService', function (svc) {
            svc.verifyToken();
        }]);
})(window.angular);
