(function (ng) {
    var mod = ng.module('appModule');

    mod.service('appService', ['CrudCreator', 'appContext', '$http', function (CrudCreator, context, $http) {
            CrudCreator.extendService(this, context);

            this.sendQuestion = function (text, app) {
                $http.post('http://localhost:8080/AppMarketPlace.web/webresources/question', {'description': text, 'app': {id: app.id}}).then(function (response) {
                }, function (response) {
                });
            };
            this.findCheapest = function (text) {
                return this.api.one('cheapest/' + text).get();
            };

            this.getAppsByCategory = function (text) {
                return this.api.customGET("categories/" + text);
            };
            
            this.rateApp = function(app, rate){
                return app.all('rate').post({rate: rate});
            };
        }]);
})(window.angular);
