(function (ng) {
    var mod = ng.module('cartItemModule');

    mod.controller('cartItemCtrl', ['CrudCreator', '$scope', 'cartItemService', 'cartItemModel', '$location', function (CrudCreator, $scope, svc, model, $location) {
            CrudCreator.extendController(this, svc, $scope, model, 'cartItem', 'Shopping Cart');
            var self = this;
            $scope.verifyDiscount = function () {

                for (var i = 0; i < $scope.records.length; i++) {
                    var date1 = new Date($scope.records[i].app.startDiscountDate);
                    date1.setDate(date1.getDate() + 1);
                    var date2 = new Date($scope.records[i].app.finishDiscountDate);
                    date2.setDate(date2.getDate() + 1);
                    var actualDate = new Date();
                    var flag = actualDate >= date1 && actualDate <= date2;
                    if (!flag) {
                        $scope.records[i].app.discount = 0;
                    }
                }
            };

            var oldFetch = this.fetchRecords;
            this.fetchRecords = function () {
                return oldFetch.call(this).then(function (data) {
                    $scope.verifyDiscount();
                    self.calcTotal();
                    return data;
                });
            };
            this.fetchRecords();
            this.readOnly = true;
            $scope.lastQuantity = 0;
            $scope.total = 0;

            this.recordActions = {
                delete: {
                    displayName: ' ',
                    icon: 'trash',
                    class: 'primary',
                    fn: function (record) {
                        svc.deleteRecord(record).then(function () {
                            self.fetchRecords();
                        });
                    },
                    show: function () {
                        return true;
                    }
                }};

            this.calcTotal = function () {
                $scope.total = 0;
                for (var i = 0; i < $scope.records.length; i++) {
                    $scope.total += $scope.subtotal($scope.records[i]);
                }
            };

            //guarda la cantidad anterior
            $scope.verify = function (quantity) {
                $scope.lastQuantity = quantity;
            };

            $scope.postVerify = function (record) {
                var patron = /^\d+$/;
                if (patron.test(record.quantity) && record.quantity > 0) {
                    self.calcTotal();
                } else {
                    self.showError("You must enter a valid quantity");
                    record.quantity = $scope.lastQuantity;
                    $scope.currentRecord = record;
                }
            };
            //Realiza la validacion de la nueva cantidad asignada.
            $scope.checkout = function () {
                $location.path('/paymentCard');
            };
            $scope.subtotal = function (record) {
                return (record.app.price - record.app.discount) * record.quantity;


            };

        }]);
})(window.angular);
