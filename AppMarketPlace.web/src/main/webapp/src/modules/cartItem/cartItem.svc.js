(function (ng) {
    var mod = ng.module('cartItemModule');

    mod.service('cartItemService', ['CrudCreator', 'cartItemContext', function (CrudCreator, context) {
            CrudCreator.extendService(this, context);
            this.addItem = function (record) {
                var self = this;
                this.fetchRecords().then(function (data) {
                    for (var i = 0; i < data.length; i++) {
                        if (record.app.id === data[i].app.id) {
                            record = data[i];
                            record.quantity = data[i].quantity + 1;
                            break;
                        }
                    }
                    self.saveRecord(record);
                });
            };
        }]);
})(window.angular);
