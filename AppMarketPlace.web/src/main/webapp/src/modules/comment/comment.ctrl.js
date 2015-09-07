
(function (ng) {
    var mod = ng.module('commentModule');
    mod.controller('commentCtrl', ['CrudCreator', '$scope', 'commentService', 'commentModel','$http', function (CrudCreator, $scope, svc, model,$http) {
            CrudCreator.extendController(this, svc, $scope, model, 'comment', 'Comment');
            //this.fetchRecords();
            $scope.commentProduct = function() {
                $http.post('http://localhost:8080/AppMarketPlace.web/webresources/comments',{ 'comment':$scope.comment,'date':'1990-12-12' }
                    
                        ).then(function(response){
                        },function(response){
                        });
            };
            
            submitForm = function(){
                
                var comment = document.getElementById("comment").value;
                var date = new Date().toJSON().slice(0,10);
                $http.post('http://localhost:8080/AppMarketPlace.web/webresources/comments',{ 'comment':comment,'date':date }
                    
                        ).then(function(response){
                        },function(response){
                        });
                location.reload();
            };
        }]);

})(window.angular);
