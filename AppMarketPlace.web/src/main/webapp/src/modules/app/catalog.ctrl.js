(function (ng) {
    var mod = ng.module('appModule');

    mod.controller('catalogCtrl', ['CrudCreator', '$scope', '$modal', 'appService', 'cartItemService', 'commentService', '$location', 'authService',
        function (CrudCreator, $scope, $modal, svc, cartItemSvc, commentSvc, $location, authSvc) {
            var model = {
                fields: [{
                        name: 'name',
                        displayName: 'Name',
                        type: 'String',
                        required: true
                    }, {
                        name: 'picture',
                        displayName: 'Picture',
                        type: 'Image',
                        required: true
                    }
                ]};
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
            $scope.searchByKeyWord = function (keyword) {


                if (keyword) {
                    $location.url('/catalog?keyword=' + keyword);
                } else {
                    $location.url('/catalog');
                }

            };

            var self = this;

            this.recordActions = {
                details: {
                    displayName: 'Details',
                    icon: 'list-alt',
                    class: 'info',
                    fn: function (app) {
                        $location.path('/app/' + app.id);
                    },
                    show: function () {
                        return true;
                    }
                },
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
                shareSocial: {
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
                    displayName: 'Make a question',
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
                },
                comment: {
                    name: 'add a comment',
                    displayName: 'Add a comment',
                    icon: 'pencil',
                    class: 'primary',
                    fn: function (app) {
                        var modalInstance = $modal.open({
                            animation: true,
                            templateUrl: 'src/modules/comment/comment.html',
                            controller: 'commentModalCtrl',
                            resolve: {
                                app: function () {
                                    return app;
                                }
                            }
                        });
                        modalInstance.result.then(function (comment) {
                            commentSvc.commentApp(app, comment).then(function () {
                                self.showSuccess('Comentario agregado');
                            }, function () {
                                self.showError('No es posible agregar el comentario');
                            });
                            /*TODO create logic to service*/
                        }, function () {

                        });
                    },
                    show: function (app) {
                       /*, var user = authSvc.getCurrentUser();
                        if (!!user) {
                            if (user.role == "user") {
                                var num = commentSvc.countByAppClient(app.id);
                                if (num > 0) {
                                    return true;
                                }
                            }
                        }
                        return false;*/
                        return true;
                    }
                },
                rate: {
                    displayName: 'Rate App!',
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
                    }
                }
            };

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
                            $scope.records = data;
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
    mod.controller('commentModalCtrl', ['$scope', '$modalInstance', 'app', function ($scope, $modalInstance, app) {
            $scope.name = app.name;
            $scope.comment = "";
            $scope.ok = function () {
                $modalInstance.close($scope.comment);
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };
        }]);

})(window.angular);
