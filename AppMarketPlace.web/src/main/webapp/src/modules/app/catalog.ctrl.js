(function (ng) {
    var mod = ng.module('appModule');

    mod.controller('catalogCtrl', ['CrudCreator', '$scope', 'appService', 'appModel', 'cartItemService', '$location', 'authService', function (CrudCreator, $scope, svc, model, cartItemSvc, $location, authSvc) {
            CrudCreator.extendController(this, svc, $scope, model, 'catalog', 'Catalog');
            this.asGallery = true;
            this.readOnly = true;

            this.searchByName = function (appName) {
                var search;
                if (appName) {
                    search = '?q=' + appName;
                }
                $location.url('/catalog' + search);
            };

            this.recordActions = [{
                    name: 'addToCart',
                    displayName: 'Add to Cart',
                    icon: 'shopping-cart',
                    class: 'primary',
                    fn: function (app) {
                        return cartItemSvc.addItem({
                            app: app,
                            name: app.name,
                            quantity: 1});
                    },
                    show: function () {
                        return true;
                    }
                }];

            this.fetchRecords();
        }]);
})(window.angular);