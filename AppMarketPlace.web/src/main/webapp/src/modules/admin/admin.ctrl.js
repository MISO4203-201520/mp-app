(function (ng) {
    
    var mod = ng.module('adminModule');

    mod.controller('adminClientCtrl', ['CrudCreator', '$scope', 'clientService', 'clientModel', 'authService', 'adminService','$location', function (CrudCreator, $scope, svc, model, adminSvc) {
        CrudCreator.extendController(this, svc, $scope, model, 'admin', 'Clients');
        showList(adminSvc,$scope,this,true);        
    }]);
    mod.controller('adminDevCtrl', ['CrudCreator', '$scope', 'developerService', 'developerModel', 'authService', 'adminService','$location', function (CrudCreator, $scope, svc, model, adminSvc) {
        CrudCreator.extendController(this, svc, $scope, model, 'admin', 'Developers');
        showList(adminSvc,$scope,this,false); 
    }]);
    
    //var showList =function(authSvc,adminSvc,$location,$scope,self,first){
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
                class: 'primary',
                fn: function (record) {
                   adminSvc.disableUser().then(function(){
                        self.fetchRecords(record.id); 
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