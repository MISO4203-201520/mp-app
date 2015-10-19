(function (ng) {

    var mod = ng.module('adminModule');

    mod.controller('adminClientCtrl', ['CrudCreator', '$scope', 'adminModel', 'adminClientService', function (CrudCreator, $scope, model, svc) {
            CrudCreator.extendController(this, svc, $scope, model, 'admin', 'Clients');
            showList(svc, $scope, this, "Clients");
        }]);
    mod.controller('adminDevCtrl', ['CrudCreator', '$scope', 'adminModel', 'adminDeveloperService', function (CrudCreator, $scope, model, svc) {
            CrudCreator.extendController(this, svc, $scope, model, 'admin', 'Developers');
            showList(svc, $scope, this, "Developers");
        }]);
    mod.controller('adminCommentsCtrl', ['CrudCreator', '$scope', 'adminModel', 'commentService', function (CrudCreator, $scope, model, svc) {
            CrudCreator.extendController(this, svc, $scope, model, 'admin', 'Comments');
            showCommentList(svc, $scope, this, "Comments");
        }]);
    mod.controller('adminAppsCtrl', ['CrudCreator', '$scope', 'adminModel', 'appService', function (CrudCreator, $scope, model, svc) {
            CrudCreator.extendController(this, svc, $scope, model, 'admin', 'Apps');
            showAppList(svc, $scope, this, "Apps");
        }]);
    
    var showCommentList = function (adminSvc, $scope, self, context) {
        $scope.context = context;
        self.fetchRecords();
        self.actions = {
            disable: {
                displayName: 'delete',
                icon: 'remove-sign',
                icon2: 'ok-sign',
                class: 'primary',
                fn: function (record) {
                    adminSvc.deleteComment(record.id).then(function () {
                        self.fetchRecords();
                    });
                },
                show: function () {
                    return true;
                }
            }
        };
    };
    
    var showAppList = function (adminSvc, $scope, self, context) {
        $scope.context = context;
        self.fetchRecords();
        self.actions = {
            disable: {
                displayName: 'disable',
                icon: 'remove-sign',
                icon2: 'ok-sign',
                class: 'primary',
                fn: function (record) {
                    adminSvc.disableApp(record.id).then(function () {
                        self.fetchRecords();
                    });
                },
                show: function () {
                    return true;
                }
            }
        };
    };

    var showList = function (adminSvc, $scope, self, context) {
        $scope.context = context;
        self.fetchRecords();
        self.actions = {
            disable: {
                displayName: 'disable',
                icon: 'remove-sign',
                icon2: 'ok-sign',
                class: 'primary',
                fn: function (record) {
                    adminSvc.disableUser(record.id).then(function () {
                        self.fetchRecords();
                    });
                },
                show: function () {
                    return true;
                }
            }
        };
    };

})(window.angular);