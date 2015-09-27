(function (ng) {
    
    var mod = ng.module('adminModule');

    mod.controller('adminClientCtrl', ['CrudCreator', '$scope', 'clientService', 'clientModel', 'authService', 'adminService','$location', function (CrudCreator, $scope, svc, model, authSvc, adminSvc, $location) {
        CrudCreator.extendController(this, svc, $scope, model, 'admin', 'Clients');
        showList(authSvc,adminSvc,$location,$scope,this,true);        
    }]);
    mod.controller('adminDevCtrl', ['CrudCreator', '$scope', 'developerService', 'developerModel', 'authService', 'adminService','$location', function (CrudCreator, $scope, svc, model, authSvc, adminSvc, $location) {
        CrudCreator.extendController(this, svc, $scope, model, 'admin', 'Developers');
        showList(authSvc,adminSvc,$location,$scope,this,false); 
    }]);
    
    var showList =function(authSvc,adminSvc,$location,$scope,self,first){
        if(authSvc.getCurrentUser()){
            adminSvc.fullUser().then(function(data){ 
                if(data.role==='admin'){
                    $scope.listUsers = {
                        clients:first,
                        devs:!first
                    }; 
                    self.fetchRecords();       
                }else{
                    $location.path('/catalog');
                }
            });           
        }else{
            $location.path('/catalog');
        }
    };
   
})(window.angular);