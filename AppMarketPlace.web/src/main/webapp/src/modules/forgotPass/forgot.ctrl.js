/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module('forgotModule');

    mod.controller('forgotCtrl', ['$scope', '$location', 'forgotService', '$timeout', function ($scope, $location, svc, $timeout) {
            $scope.messages = [];
            $scope.sendMail = function () {
                $scope.messages = [];
                svc.sendForgotMail($scope.user.email).then(function (result) {
                    $scope.addMessage("Email was sent", "success");
                }, function (error) {
                    $scope.addMessage("Email you provided does not exist", "danger");
                });
            };

            $scope.changePass = function () {
                $scope.messages = [];
                if ($scope.forgot.pass === $scope.forgot.repass) {
                    svc.changePass($scope.forgot.pass).then(function () {
                        $scope.addMessage("Password changed successfully", "success");
                        $timeout(function () {
                            $location.path("/login");
                        }, 2000);
                    }, function () {
                        $scope.addMessage("Change Password Error: Remember that password lenght must be at least 8,  and should contain 1 Uppercase character, 1 lowercase character, 1 number and a symbol", "danger");
                    });
                } else {
                    $scope.addMessage("Passwords must be equal", "danger");
                }
            };

            $scope.user = {};
            $scope.forgot = {};

            $scope.goToLogin = function () {
                svc.goToLogin();
            };

            $scope.addMessage = function (message, type) {
                var newMessage = {
                    "message": message,
                    "type": type
                }
                $scope.messages.push(newMessage);
            };

        }]);

})(window.angular);
