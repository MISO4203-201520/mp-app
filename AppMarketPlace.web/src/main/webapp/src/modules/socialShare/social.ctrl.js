
(function (ng) {
    var mod = ng.module('commentModule');
    mod.controller('socialCtrl', ['CrudCreator', '$scope', 'commentService', 'commentModel','$http','$modal', function (CrudCreator, $scope, svc, model,$http,$modal) {
            $scope.model = {};
            submitForm = function(){
                $("#commentsubmit").button('loading')
                var date = new Date().toJSON().slice(0,10);
                $http.post('http://localhost:8080/AppMarketPlace.web/webresources/comments',{ 
                    comment: $scope.model.comment,
                    date: date,
                    app:{
                        id : $scope.selectedApp.id
                    } 
                }).success(function(data){
                    $("#commentsubmit").button('reset')
                    $scope.modalInstance.dismiss('cancel');                    
                }).error(function(data){
                     $("#commentsubmit").button('reset')
                    $scope.modalInstance.dismiss('cancel');                                        
                });
            };
        }]);
})(window.angular);