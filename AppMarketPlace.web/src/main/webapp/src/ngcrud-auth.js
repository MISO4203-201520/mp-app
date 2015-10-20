(function (ng) {

    var mod = ng.module('authModule', ['restangular', 'ngCookies', 'ngRoute']);
    mod.constant('defaultStatus', {status: false});
    mod.config(['RestangularProvider', function (rp) {
        rp.setBaseUrl('webresources');
    }]);
    mod.config(['$routeProvider', 'authServiceProvider', function ($routeProvider, auth) {
        var authConfig = auth.getValues();
        $routeProvider
            .when(authConfig.loginPath, {
                templateUrl: 'src/templates/login.html',
                controller: 'authController',
                controllerAs: 'authCtrl'
            })
            .when(authConfig.registerPath, {
                templateUrl: 'src/templates/register.html',
                controller: 'authController',
                controllerAs: 'authCtrl'
            })
            .when(authConfig.forbiddenPath, {
                templateUrl: 'src/templates/forbidden.html',
                controller: 'authController',
                controllerAs: 'authCtrl'
            });
    }]);

    mod.run(['Restangular', 'authService', function (restangular, auth) {
        restangular.setErrorInterceptor(function (resp) {
            if (resp.status === 401) {
                auth.goToLogin();
                return false;
            } else if (resp.status === 403) {
                auth.goToForbidden();
                return false;
            }
            return true;
        });
    }]);

})(window.angular);



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module('authModule');

    mod.provider('authService', function () {

        //Default
        var values = {
            apiUrl: 'users',
            successPath: '/product',
            loginPath: '/login',
            registerPath: '/register',
            logoutRedirect: '/login',
            loginURL: 'login',
            registerURL: 'register',
            logoutURL: 'logout',
            nameCookie: 'user',
            forbiddenPath: '/forbidden'
        };

        //Default Roles
        var roles = {
            'user': 'Client',
            'provider': 'Provider'
        };

        this.setValues = function (newValues) {
            values = ng.extend(values, newValues);
        };

        this.getValues = function () {
            return values;
        };

        this.getRoles = function(){
            return roles;
        };

        this.setRoles = function(newRoles){
            roles = newRoles;
        };

        this.$get = ['Restangular', '$cookies', '$location','$rootScope', function (RestAngular, $cookies, $location, $rootScope) {
            var self = this;
            this.api = RestAngular.all(values.apiUrl);
            return {
                getRoles: function(){
                    return roles;
                },
                login: function (user) {
                    return self.api.customPOST(user, values.loginURL).then(function (data) {
                        $cookies.putObject(values.nameCookie, data.plain());
                        $rootScope.$broadcast('logged-in', data.plain());
                        $location.path(values.successPath);
                    });
                },
                getConf: function () {
                    return values;
                },
                logout: function () {
                    return self.api.customGET(values.logoutURL).then(function () {
                        $cookies.remove(values.nameCookie);
                        $rootScope.$broadcast('logged-out');
                        $location.path(values.logoutRedirect);
                    });
                },
                register: function (user) {
                    return self.api.customPOST(user, values.registerURL).then(function () {
                        $location.path(values.loginPath);
                    });
                },
                registration: function () {
                    $location.path(values.registerPath);
                },
                getCurrentUser: function () {
                    return $cookies.getObject(values.nameCookie) || null;
                },
                goToLogin: function () {
                    $location.path(values.loginPath);
                },
                goToBack: function () {
                    $location.path(values.loginPath);
                },
                goToSuccess: function () {
                    $location.path(values.successPath);
                },
                goToForbidden: function(){
                    $location.path(values.forbiddenPath);
                }
            };
        }];
    });
})(window.angular);



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {

    var mod = ng.module('authModule');

    mod.controller('authController', ['$scope', '$cookies', '$location', 'authService', 'defaultStatus', function ($scope, $cookies, $location, authSvc, defaultStatus) {
        this.errorctrl = defaultStatus;
        $scope.roles = authSvc.getRoles();
        $scope.menuitems = [{id:'', label:'', icon:'', url:''}];
        
        $scope.$on('logged-in', function (events, user) {
            if (user.role === 'administrator') {
                $scope.menuitems = [{id:'registeredUsers', label:'Manage users', icon:'list-alt', url:'#/admin/clients'}];
            } else {
                if (user.role === 'developer') {
                    $scope.menuitems = [{id:'devProfile', label:'My profile', icon:'user', url:'#/devprofile'}];
                } else {
                    if (user.role === 'user') {
                        $scope.menuitems = [{id:'clientProfile', label:'My profile', icon:'user', url:'#/clientprofile'}, 
                                            {id:'clientCart', label:'My shopping cart', icon:'shopping-cart', url:'#/shoppingCart'}];
                    }
                }
            }
        });
        
        $scope.isAuthenticated = function(){
            return !!authSvc.getCurrentUser();
        };

        $scope.user = {};
        for(var att in $scope.roles){
            $scope.user.role = att;
            break;
        }

        $scope.currentUser = function(){
            var user = authSvc.getCurrentUser();
            return user && user.userName;
        };
        this.login = function (user) {
            var self = this;
            if (user && user.userName && user.password) {
                authSvc.login(user).then(function () {
                }, function (data) {
                    self.errorctrl = {status: true, type: "danger", msg: ":" + data.data};
                });
            }
        };

        $scope.logout = function () {
            authSvc.logout();
        };

        $scope.log = function(obj){
            console.log(obj);
        };

        this.close = function () {
            this.errorctrl = defaultStatus;
        };

        this.registration = function () {
            authSvc.registration();
        };

        this.register = function (newUser) {
            var self = this;
            if (newUser.password !== newUser.confirmPassword) {
                this.errorctrl = {status: true, type: "warning", msg: ": Passwords must be equals"};
            } else {
                authSvc.register(newUser).then(function (data) {
                    self.errorctrl = {status: true, type: "success", msg: ":" + " User registered successfully"};
                }, function (data) {
                    self.errorctrl = {status: true, type: "danger", msg: ":" + data.data};
                });
            }
        };


        $scope.goToLogin = function () {
            authSvc.goToLogin();
        };

        this.goBack = function () {
            authSvc.goToBack();
        };

        $scope.goToSuccess = function(){
            authSvc.goToSuccess();
        };
    }]);

})(window.angular);


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
    
    var mod = ng.module('authModule');
    
    mod.directive('loginButton',[function(){
        return {
            scope:{},
            restrict: 'E',
            templateUrl: 'src/templates/button.html',
            controller: 'authController'
        };                    
    }]);
})(window.angular);

angular.module('authModule').run(['$templateCache', function($templateCache) {
  'use strict';

  $templateCache.put('src/templates/button.html',
    "<button id='btnLogin' ng-hide=\"isAuthenticated()\" type=\"button\" class=\"btn btn-default navbar-btn\" ng-click=\"goToLogin()\">\r" +
    "\n" +
    "    <span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span> Login\r" +
    "\n" +
    "</button>\r" +
    "\n" +
    "\r" +
    "\n" +
    "<div ng-show=\"isAuthenticated()\" class=\"btn-group\">\r" +
    "\n" +
    "    <button type=\"button\" class=\"btn btn-default dropdown-toggle navbar-btn\" data-toggle=\"dropdown\" aria-haspopup=\"true\"\r" +
    "\n" +
    "            aria-expanded=\"false\">\r" +
    "\n" +
    "        {{currentUser()}} <span class=\"caret\"></span>\r" +
    "\n" +
    "    </button>\r" +
    "\n" +
    "    <ul class=\"dropdown-menu\">\r" +
    "\n" +
    "        <li id='{{menuitem.id}}' ng-repeat='menuitem in menuitems'><a ng-href='{{menuitem.url}}'><span class='glyphicon glyphicon-{{menuitem.icon}}' aria-hidden='true'></span> {{menuitem.label}}</a></li>\r" +
    "\n" +
    "        <li>\r" +
    "\n" +
    "            <a href ng-click=\"logout()\">\r" +
    "\n" +
    "                <span class=\"glyphicon glyphicon-log-out\" aria-hidden=\"true\"></span> Logout\r" +
    "\n" +
    "            </a>\r" +
    "\n" +
    "        </li>\r" +
    "\n" +
    "    </ul>\r" +
    "\n" +
    "</div>\r" +
    "\n"
  );


  $templateCache.put('src/templates/forbidden.html',
    "<div class=\"jumbotron\">\r" +
    "\n" +
    "    <h1>Oups,Forbidden!</h1>\r" +
    "\n" +
    "    <p> You don't have permissions for this resource!</p>\r" +
    "\n" +
    "    <p><a class=\"btn btn-primary btn-lg\" ng-click=\"goToSuccess()\" role=\"button\">Back</a></p>\r" +
    "\n" +
    "</div>\r" +
    "\n"
  );


  $templateCache.put('src/templates/login.html',
    "<div>\r" +
    "\n" +
    "    <div class=\"col-md-5 col-md-offset-4\">\r" +
    "\n" +
    "        <div class=\"col-md-12\" ng-show=\"authCtrl.errorctrl.status\" ng-message=\"show\">\r" +
    "\n" +
    "            <div class=\"alert alert-{{authCtrl.errorctrl.type}}\" role=\"alert\">\r" +
    "\n" +
    "                <button type=\"button\" class=\"close\" ng-click=\"authCtrl.close()\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\r" +
    "\n" +
    "                <strong>{{authCtrl.errorctrl.type| uppercase}}</strong> {{authCtrl.errorctrl.msg}}\r" +
    "\n" +
    "            </div>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "        <div class=\"col-md-12\">\r" +
    "\n" +
    "            <div class=\"panel panel-default\">\r" +
    "\n" +
    "                <div class=\"panel-heading\">\r" +
    "\n" +
    "                    <h3 class=\"panel-title\">Please Login</h3>\r" +
    "\n" +
    "                </div>\r" +
    "\n" +
    "                <div class=\"panel-body\">\r" +
    "\n" +
    "                    <form name=\"loginform\" accept-charset=\"UTF-8\" role=\"form\">\r" +
    "\n" +
    "                        <div class=\"form-group\">\r" +
    "\n" +
    "                            <input id='txtUsername' class=\"form-control\" required ng-model=\"user.userName\" placeholder=\"Username or Email\" name=\"username\" type=\"text\">\r" +
    "\n" +
    "                        </div>\r" +
    "\n" +
    "                        <div class=\"form-group\">\r" +
    "\n" +
    "                            <input id='txtPassword' class=\"form-control\" required ng-model=\"user.password\" placeholder=\"Password\" name=\"password\" type=\"password\" >\r" +
    "\n" +
    "                        </div>\r" +
    "\n" +
    "                        <div class=\"checkbox\">\r" +
    "\n" +
    "                            <label>\r" +
    "\n" +
    "                                <input  name=\"rememberMe\" type=\"checkbox\" ng-model=\"user.rememberMe\" value=\"false\"> Remember Me\r" +
    "\n" +
    "                            </label>\r" +
    "\n" +
    "                        </div>\r" +
    "\n" +
    "                        <input id='login' class=\"btn btn-lg btn-success btn-block\" ng-click=\"authCtrl.login(user)\" type=\"submit\" value=\"Login\">\r" +
    "\n" +
    "                    </form>\r" +
    "\n" +
    "                    <button class=\"btn btn-lg btn-default btn-block\" ng-click=\"authCtrl.registration()\">Register</button>\r" +
    "                    <a href=\"#/forgot\" class=\"btn btn-lg btn-primary btn-block\"><span class=\"glyphicon glyphicon-lock\" ></span> Forgot your password? </a>\r" +
    "\n" +
    "                </div>\r" +
    "\n" +
    "            </div>\r" +
    "\n" +
    "        </div>\r" +
    "\n" +
    "    </div>\r" +
    "\n" +
    "</div>\r" +
    "\n"
  );


  $templateCache.put('src/templates/register.html',
    "\n" +
    "<div>\n" +
    "    <div class=\"col-md-5 col-md-offset-4\">\n" +
    "        <div class=\"panel panel-default\">\n" +
    "            <div class=\"panel-heading\">\n" +
    "                <h3 class=\"panel-title\">Please Register</h3>\n" +
    "                <div style=\"padding-top: 30px;\" class=\"col-md-12\" ng-show=\"authCtrl.errorctrl.status\" ng-message=\"show\">\n" +
    "                    <div   class=\"alert alert-{{authCtrl.errorctrl.type}}\" role=\"alert\">\n" +
    "                        <button type=\"button\" class=\"close\" ng-click=\"authCtrl.close()\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\n" +
    "                        <strong>{{authCtrl.errorctrl.type| uppercase}}</strong> {{authCtrl.errorctrl.msg}}\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            <div class=\"panel-body\">\n" +
    "                <form name=\"loginform\" accept-charset=\"UTF-8\" role=\"form\">\n" +
    "                    <div class=\"form-group\">\n" +
    "                        <input class=\"form-control\" required ng-model=\"user.userName\" placeholder=\"Username\" name=\"username\" type=\"text\">\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group\">\n" +
    "                        <input class=\"form-control\" required ng-model=\"user.password\" placeholder=\"Password\" name=\"password\" type=\"password\" >\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group\">\n" +
    "                        <input class=\"form-control\" required ng-model=\"user.confirmPassword\" placeholder=\"Confirm Password\" name=\"confirmpassword\" type=\"password\" >\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group\">\n" +
    "                        <input class=\"form-control\" required ng-model=\"user.name\" placeholder=\"Name\" name=\"name\" type=\"text\" >\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group\">\n" +
    "                        <input class=\"form-control\" required ng-model=\"user.lastName\" placeholder=\"LastName\" name=\"lastname\" type=\"text\" >\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group\">\n" +
    "                        <label for=\"SelectRole\"> Please select your role: </label>\n" +
    "                        <select class=\"form-control\" ng-options=\"key as value for (key, value) in roles\" name=\"SelectRole\" ng-model=\"user.role\" required></select>\n" +
    "                    </div>\n" +
    "                    <div class=\"form-group\">\n" +
    "                        <input class=\"form-control\" required ng-model=\"user.email\" placeholder=\"email\" name=\"email\" type=\"email\" >\n" +
    "                    </div>\n" +
    "                    <input class=\"btn btn-lg btn-primary btn-block\" ng-click=\"authCtrl.register(user)\" type=\"submit\" value=\"Register\">\n" +
    "                    <input class=\"btn btn-lg btn-default btn-block\" ng-click=\"authCtrl.goBack()\" type=\"submit\" value=\"Go Back\">\n" +
    "                </form>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "</div>\n"
  );

}]);
