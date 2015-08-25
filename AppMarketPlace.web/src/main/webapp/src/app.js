(function (ng) {

    var mainApp = ng.module('mainApp', [
        //'ngCrudMock',
        'authModule',
        'appModule',
        'cartItemModule',
        'clientModule',
        'developerModule',
        'ngRoute',
        'ngCrud'
    ]);

    mainApp.config(['$routeProvider', 'CrudTemplateURL', 'CrudCtrlAlias', function ($routeProvider, tplUrl, alias) {
            $routeProvider
                .when('/client', {
                    templateUrl: tplUrl,
                    controller: 'clientCtrl',
                    controllerAs: alias
                })
                .when('/developer', {
                    templateUrl: tplUrl,
                    controller: 'developerCtrl',
                    controllerAs: alias
                })
                .otherwise('/');
        }]);
})(window.angular);
