(function (ng) {
    var mod = ng.module('appModule');

    mod.controller('appCtrl', ['CrudCreator', '$scope', 'appService', 'appModel', function (CrudCreator, $scope, svc, model) {
            CrudCreator.extendController(this, svc, $scope, model, 'app', 'Apps');
            this.asGallery = true;
            this.fetchRecords();
        }]);
})(window.angular);
