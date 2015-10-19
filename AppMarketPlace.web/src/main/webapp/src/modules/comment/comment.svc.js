(function (ng) {
    var mod = ng.module('commentModule');

    mod.service('commentService', ['CrudCreator', 'commentContext', function (CrudCreator, context) {
            CrudCreator.extendService(this, context);
            this.deleteComment = function (comment) {
                console.log(this.api);
                return this.api.one(comment.toString()).remove();
            };
        }]);
})(window.angular);
