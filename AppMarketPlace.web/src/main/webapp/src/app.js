(function (ng) {

    var mainApp = ng.module('mainApp', [
        //'ngCrudMock',
        'authModule',
        'appModule',
        'cartItemModule',
        'clientModule',
        'developerModule',
        'ngRoute',
        'ngCrud',
        'xeditable',
        'commentModule',
        'paymentCardsModule',
        'adminModule',
        'forgotModule',
        'transactionModule'
    ]);

    mainApp.config(['$routeProvider', 'CrudTemplateURL', 'CrudCtrlAlias', '$sceDelegateProvider', function ($routeProvider, tplUrl, alias, $sceDelegateProvider) {
            $sceDelegateProvider.resourceUrlWhitelist([
                // Allow same origin resource loads.
                'self',
                // Allow loading from our assets domain.  Notice the difference between * and **.
                'https://s3-us-west-2.amazonaws.com/appoteca/**'
            ]);
            
            $routeProvider.when('/client', {
                templateUrl: tplUrl,
                controller: 'clientCtrl',
                controllerAs: alias
            }).when('/developer', {
                templateUrl: tplUrl,
                controller: 'developerCtrl',
                controllerAs: alias
            }).when('/catalog', {
                templateUrl: tplUrl,
                controller: 'catalogCtrl',
                controllerAs: alias
            }).when('/shoppingCart', {
                templateUrl: 'src/modules/cartItem/shoppingCart.tpl.html',
                controller: 'cartItemCtrl',
                controllerAs: alias
            }).when('/app', {
                templateUrl: tplUrl,
                controller: 'appCtrl',
                controllerAs: alias
            }).when('/app/:appId', {
                templateUrl: 'src/modules/app/app.details.tpl.html',
                controller: 'appDetailsCtrl'
            }).when('/app/:appId/upload', {
                templateUrl: 'src/modules/app/media.tpl.html',
                controller: 'mediaCtrl'
            }).when('/comment', {
                templateUrl: tplUrl,
                controller: 'commentCtrl',
                controllerAs: alias
            }).when('/paymentCard', {
                templateUrl: tplUrl,
                controller: 'paymentCtrl',
                controllerAs: alias
            }).when('/admin/clients', {
                templateUrl: 'src/modules/admin/users.tpl.html',
                controller: 'adminClientCtrl',
                controllerAs: alias
            }).when('/forgot', {
                templateUrl: 'src/modules/forgotPass/forgot.tpl.html',
                controller: 'forgotCtrl',
                controllerAs: alias
            }).when('/verify', {
                templateUrl: 'src/modules/forgotPass/verify.tpl.html',
                controller: 'verifyCtrl',
                controllerAs: alias
            }).when('/change', {
                templateUrl: 'src/modules/forgotPass/change.tpl.html',
                controller: 'forgotCtrl',
                controllerAs: alias
            }).when('/admin/developers', {
                templateUrl: 'src/modules/admin/users.tpl.html',
                controller: 'adminDevCtrl',
                controllerAs: alias
            }).when('/admin/comments', {
                templateUrl: 'src/modules/admin/comments.tpl.html',
                controller: 'adminCommentsCtrl',
                controllerAs: alias
            }).when('/admin/apps', {
                templateUrl: 'src/modules/admin/apps.tpl.html',
                controller: 'adminAppsCtrl',
                controllerAs: alias
            }).when('/history', {
                templateUrl: 'src/modules/transaction/history.tpl.html',
                controller: 'transactionCtrl',
                controllerAs: alias
            }).when('/devprofile', {
                templateUrl: 'src/modules/developer/profile.tpl.html',
                controller: 'devProfileCtrl',
                controllerAs: alias
            }).when('/clientprofile', {
                templateUrl: 'src/modules/client/clientProfile.tpl.html',
                controller: 'clientProfileCtrl',
                controllerAs: alias
            }).otherwise('/catalog');
        }]);

    mainApp.config(['authServiceProvider', function (auth) {
            auth.setValues({
                apiUrl: 'users',
                successPath: '/catalog',
                loginPath: '/login',
                registerPath: '/register',
                logoutRedirect: '/login',
                loginURL: 'login',
                registerURL: 'register',
                logoutURL: 'logout',
                nameCookie: 'userCookie'
            });
            auth.setRoles({'user': 'Client', 'developer': 'Developer'});
        }]);
    mainApp.run(function (editableOptions) {
        editableOptions.theme = 'bs3';
    });
})(window.angular);
