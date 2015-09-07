/* global commentSvc */

(function (ng) {
    var mod = ng.module('appModule');

    mod.controller('catalogCtrl', ['CrudCreator', '$scope', '$modal', 'appService', 'appModel', 'cartItemService', '$location', 'authService','CrudTemplateURL' , function (CrudCreator, $scope,$modal, svc, model, cartItemSvc, $location, authSvc,tplUrl) {
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

            this.recordActions = {
                addToCart: {
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
                },
                comment: {
                    displayName: 'Add a comment',
                    icon: 'pencil',
                    class: 'primary',
                    fn: function (app) {
                        $modal.open({
                            animation: $scope.animationsEnabled,
                            templateUrl: 'src/modules/comment/comment.html',
                            controller: 'commentCtrl',
                            size: size,
                            resolve: {
                                product: function () {
                                    return app.id;
                                }
                            }
                        });
                    },
                    show: function () {
                        return true;
                    }
                }
            };
            this.fetchRecords();
        }]);
})(window.angular);