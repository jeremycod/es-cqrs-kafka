(function() {
    "use strict";

    var module = angular.module('blackjack-game');

    module.service('commandService', function($http) {
        return {
            createGame: function(success, error) {
                var req = {
                    method: 'POST',
                    url: '/game',
                    headers: {
                        'Content-Type': undefined
                    },
                    data: {test: 'test'}
                }
                $http(req)

                    .then(
                        function onSuccess(response){
                            console.log("Success created");
                        },
                        function onError(response){
                            console.log("Error during game creation");
                        }
                    );

            },
            startGame: function(gameId, playersCount, error) {
                $http.post('/game/' + gameId + '/start?playersCount=' + playersCount)
                    .error(error);
            },
            roll: function(gameId, player, error) {
                $http.post('/game/' + gameId + '/roll/' + player)
                    .error(error);
            }
        };
    });

})();
