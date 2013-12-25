
/* Controllers */

/*var gameListController = function($scope, $http) {
    $http.get('http://localhost:9000/game/list').success(function(data) {
        $scope.games = data;
    }

}*/


angular.module('rouletteApp', ['ngResource']);

var gameListController = function($scope, $resource) {
//    var items = $resource(game/list);
    var items = $resource('https://graph.facebook.com/22092443056/albums');
    $scope.games = items.get();
};

/*rouletteApp.controller('gameListController', function($scope, $http) {
  $http.get('http://localhost:9000/game/list').success(function(data) {
    $scope.games = data;
  });

});*/

/*var mainCtrl = function($scope) {
    $scope.users = [
        {"name":"sato", "score":"10"},
        {"name":"tanaka", "score":"20"},
        {"name":"suzuki", "score":"30"},
    ];
} */