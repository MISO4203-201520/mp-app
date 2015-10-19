(function (ng) {
    var mod = ng.module('commentModule');

    mod.service('commentService', ['CrudCreator', 'commentContext', function (CrudCreator, context) {
            CrudCreator.extendService(this, context);
            this.deleteComment = function (comment) {
                return this.api.one(comment.toString()).remove();
            };
            this.commentApp = function (app, comment) {
                var date = new Date().toJSON().slice(0, 10);
                console.log(comment);
                var commentMock = {
                    comment: comment,
                    date: date,
                    app: {
                        id: app.id
                    }
                };
                return this.api.post(commentMock);
            };
        }]);
})(window.angular);
