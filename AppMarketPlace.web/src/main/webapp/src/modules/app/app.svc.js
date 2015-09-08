(function (ng) {
    var mod = ng.module('appModule');
    
    mod.service('appService', ['CrudCreator','appContext','$http', function(CrudCreator, context,$http){
            CrudCreator.extendService(this, context);
            
            this.sendQuestion = function(text,app){
                console.log(app.id);
                $http.post('http://localhost:8080/AppMarketPlace.web/webresources/question',{'description':text, 'app':{id:app.id}}).then(function(response){
                        },function(response){});
            }
            this.findCheapest = function () {
                return this.api.one('cheapest').get();
            };
        }]);
})(window.angular);
