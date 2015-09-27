(function (ng) {
    
    var mod = ng.module('adminModule');

    mod.controller('adminClientCtrl', ['CrudCreator', '$scope', 'adminModel', 'adminClientService', function (CrudCreator, $scope, model, svc) {
        CrudCreator.extendController(this, svc, $scope, model, 'admin', 'Clients');
        showList(svc,$scope,this,true);        
    }]);
    mod.controller('adminDevCtrl', ['CrudCreator', '$scope', 'adminModel', 'adminDeveloperService', function (CrudCreator, $scope, model, svc) {
        CrudCreator.extendController(this, svc, $scope, model, 'admin', 'Developers');
        showList(svc,$scope,this,false); 
    }]);
    
     var showList =function(adminSvc,$scope,self,first){
        $scope.listUsers = {
            clients:first,
            devs:!first
        }; 
        self.fetchRecords();    
        self.actions = {    
            disable: {
                displayName: 'disable',
                icon: 'remove-sign',
                icon2: 'ok-sign',
                class: 'primary',
                fn: function (record) {
                   adminSvc.disableUser(record.id).then(function(){
                        self.fetchRecords(); 
                   });
                },
                show: function () {
                    return true;
                }
            }
        };
//        if(authSvc.getCurrentUser()){
//            adminSvc.fullUser().then(function(data){ 
//                if(data.role==='admin'){
//                    $scope.listUsers = {
//                        clients:first,
//                        devs:!first
//                    }; 
//                    self.fetchRecords();       
//                }else{
//                    $location.path('/catalog');
//                }
//            });           
//        }else{
//            $location.path('/catalog');
//        }
    };
   
})(window.angular);