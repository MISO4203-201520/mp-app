/* global commentSvc */

(function (ng) {
    var mod = ng.module('appModule');

    mod.controller('catalogCtrl', ['CrudCreator', '$scope', '$rootScope', '$modal', 'appService', 'appModel', 'cartItemService', '$location', function (CrudCreator, $scope, $rootScope, $modal, svc, model, cartItemSvc, $location) {
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

            var self = this;

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
                }, shareSocial: {
                    name: 'share social',
                    displayName: 'Share',
                    icon: 'share',
                    class: 'primary',
                    fn: function (app) {
                        var modalInstance = $modal.open({
                            animation: true,
                            templateUrl: 'src/modules/socialShare/social.html',
                            controller: 'ModalShare',
                            resolve: {
                                app: function () {
                                    $rootScope.selectedApp = app;
                                    return app;
                                }
                            }
                        });
                        modalInstance.result.then(function (text) {
                            svc.sendQuestion(text, app);
                        }, function () {

                        });
                    },
                    show: function () {
                        return true;
                    }
                },
                doQuestion: {
                    name: 'doQuestion',
                    displayName: 'Do Question',
                    icon: 'question-sign',
                    class: 'primary',
                    fn: function (app) {
                        var modalInstance = $modal.open({
                            animation: true,
                            templateUrl: 'src/modules/app/modalQuestion.tpl.html',
                            controller: 'ModalQuestionCtrl',
                            resolve: {
                                app: function () {
                                    return app;
                                }
                            }
                        });
                        modalInstance.result.then(function (text) {
                            svc.sendQuestion(text, app);
                        }, function () {

                        });
                    },
                    show: function () {
                        return true;
                    }
                }, comment: {
                    name: 'add a comment',
                    displayName: 'Add a comment',
                    icon: 'pencil',
                    class: 'primary',
                    fn: function (app) {
                        $rootScope.modalInstance = $modal.open({
                            animation: true,
                            templateUrl: 'src/modules/comment/comment.html',
                            controller: 'commentCtrl',
                            resolve: {
                                app: function () {
                                    $rootScope.selectedApp = app;
                                    return app;
                                }
                            }
                        });
                        $rootScope.modalInstance.result.then(function (text) {
                            /*TODO create logic to service*/
                            console.log("text");
                        }, function () {

                        });
                    },
                    show: function () {
                        return true;
                    }
                }
            };

            this.recordActions.rate = {
                displayName: 'Rate App',
                icon: 'star',
                class: 'primary',
                fn: function (app) {
                    var modalInstance = $modal.open({
                        animation: true,
                        templateUrl: 'src/modules/app/rateModal.tpl.html',
                        controller: 'rateModalCtrl',
                        resolve: {
                            app: function () {
                                return app;
                            }
                        }
                    });
                    modalInstance.result.then(function (rate) {
                        svc.rateApp(app, rate).then(function () {
                            self.showSuccess('Aplicación calificada');
                        }, function () {
                            self.showError('No es posible calificar la aplicación');
                        });
                    });
                },
                show: function () {
                    return true;
                }};

            this.recordActions.details = {
                displayName: 'Details',
                icon: 'list-alt',
                class: 'info',
                fn: function (app) {
                    $modal.open({
                        animation: true,
                        templateUrl: 'src/modules/app/detailsModal.tpl.html',
                        controller: 'detailsModalCtrl',
                        resolve: {
                            app: function () {
                                return svc.fetchRecord(app);
                            }
                        }
                    });
                },
                show: function () {
                    return true;
                }};

            this.globalActions.getCheapest = {
                displayName: 'Find Cheapest',
                icon: 'search',
                class: 'warning',
                fn: function () {
                    var modalInstance = $modal.open({
                        animation: true,
                        templateUrl: 'src/modules/app/modalFindCheapest.tpl.html',
                        controller: 'FindCheapestCrtl'
                    });
                    modalInstance.result.then(function (text) {
                        svc.findCheapest(text).then(function (data) {
                            console.log(data);
                            $scope.records = [];
                            for (var i = 0; i < data.length; i++) {
                                $scope.records.push(data[i]);
                            }
                        });
                    }, function () {

                    });
                },
                show: function () {
                    return true;
                }
            };

            this.globalActions.getAppsByCategory = {
                displayName: 'Filter by Category',
                icon: 'filter',
                class: 'primary',
                fn: function () {
                    var modalInstance = $modal.open({
                        animation: true,
                        templateUrl: 'src/modules/app/modalFilterCategories.tpl.html',
                        controller: 'ModalFilterCategoriesCtrl'
                    });
                    modalInstance.result.then(function (text) {
                        svc.getAppsByCategory(text).then(function (data) {
                            console.log(data);
                            $scope.records = [];
                            for (var i = 0; i < data.length; i++) {
                                $scope.records.push(data[i]);
                            }
                        });
                    }, function () {

                    });
                },
                show: function () {
                    return true;
                }
            };

            this.fetchRecords();
        }]);
  mod.controller('ModalShare', function ($scope, $modalInstance, app) {
        $scope.itemQuestion = {
            name: app.name,
            text: ""
        };

        $scope.ok = function () {
            $modalInstance.close($scope.itemQuestion.text);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    });
    mod.controller('ModalQuestionCtrl', function ($scope, $modalInstance, app) {
        $scope.itemQuestion = {
            name: app.name,
            text: ""
        };

        $scope.ok = function () {
            $modalInstance.close($scope.itemQuestion.text);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    });

    mod.controller('ModalFilterCategoriesCtrl', function ($scope, $modalInstance) {
        $scope.in = {
            text: ""
        };

        $scope.ok = function () {
            $modalInstance.close($scope.in.text);
        };

        $scope.cancel = function () {
            $scope.in.text = "";
            $modalInstance.dismiss('cancel');
        };
    });

    mod.controller('FindCheapestCrtl', function ($scope, $modalInstance) {
        $scope.dev = {
            text: ""
        };

        $scope.ok = function () {
            $modalInstance.close($scope.dev.text);
        };

        $scope.cancel = function () {
            $scope.dev.text = "";
            $modalInstance.dismiss('cancel');
        };
    });

    mod.controller('rateModalCtrl', ['$scope', '$modalInstance', 'app', function ($scope, $modalInstance, app) {
            $scope.name = app.name;
            $scope.rate = 0;
            $scope.ok = function () {
                $modalInstance.close($scope.rate);
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        }]);
    
    mod.controller('detailsModalCtrl', ['$scope', '$modalInstance', 'app', function ($scope, $modalInstance, app) {
            $scope.app = app;
            $scope.ok = function () {
                $modalInstance.dismiss();
            };
        }]);

})(window.angular);
