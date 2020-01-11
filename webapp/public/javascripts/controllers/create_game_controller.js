(function () {
    "use strict";

    var module = angular.module('blackjack-game');

    module.controller('CreateGameController', function($scope, $rootScope, commandService, eventService) {

        $scope.loading = false;

        $scope.createNewGame = function() {
            console.log("Create new game");
            $scope.loading = true;
            commandService.createGame(function(data) {
                $scope.loading = false;
                $rootScope.gameId = data.id;

                eventService.connect($scope.gameId);
            }, function(data) {
                $scope.loading = false;
                alert('Unexpected error occurred, try again later.');
            });
        };

    });

})();
