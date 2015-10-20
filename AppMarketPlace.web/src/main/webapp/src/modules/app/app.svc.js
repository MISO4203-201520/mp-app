(function (ng) {
    var mod = ng.module('appModule');

    mod.service('appService', ['CrudCreator', 'appContext', '$http', 'Restangular', function (CrudCreator, context, $http, ra) {
            CrudCreator.extendService(this, context);

            this.sendQuestion = function (text, app) {
                return $http.post(
                        'http://localhost:8080/AppMarketPlace.web/webresources/question',
                        {
                            description: text,
                            app: {
                                id: app.id
                            }
                        });
            };
            this.findCheapest = function (text) {
                return this.api.one('cheapest/' + text).get();
            };

            this.getAppsByCategory = function (text) {
                return this.api.getList({category: text});
            };
            this.getAppsByKeyWords = function (text) {
                return this.api.getList({keyword: text});
            };

            this.rateApp = function (app, rate) {
                return app.all('rate').post({rate: rate});
            };

            this.getAppById = function (id) {
                return ra.one(context, id).get();
            };            
        }]);
})(window.angular);
