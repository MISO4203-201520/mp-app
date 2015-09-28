/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module('forgotModule');

    mod.service('forgotService', ['CrudCreator', 'usersContext', '$location', '$routeParams', function (CrudCreator, context, location, params) {
            CrudCreator.extendService(this, context);

            this.sendForgotMail = function (email) {
                var userMock = {
                    "email": email
                }
                return this.api.all("forgot").post(userMock)
            };
            this.goToLogin = function () {
                location.path("/login");
            };

            this.verifyToken = function() {
                var url = location.absUrl();
                url = url.substring(url.indexOf("=")+1, url.indexOf("#"));
                this.api.customGET("verify",{"sptoken": url}).then(function (result) {
                    location.path("/change");
                }, function (error) {
                    location.path("/login");
                });
            };
            
            this.changePass = function(pass){
                var url = location.absUrl();
                var token = url.substring(url.indexOf("=")+1, url.indexOf("#"));
                var forgotMock = {
                    "newPassword": pass,
                    "token": token
                }
                return this.api.all("change").post(forgotMock);
            };
        }]);
})(window.angular);



