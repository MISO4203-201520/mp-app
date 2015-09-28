/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global commentSvc */

(function (ng) {
    var mod = ng.module('forgotModule');

    mod.controller('verifyCtrl', ['$scope', '$location', 'forgotService', function ($scope, $location, svc) {
            svc.verifyToken();
    }]);

})(window.angular);



