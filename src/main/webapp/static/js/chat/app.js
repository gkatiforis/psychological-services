//
// angular.element(document).ready(function () {
//     // Your document is ready, place your code here
// });
//



var app = angular.module('angchat', []);

app.filter('reverse', function() {
    return function(items) {
        return items.slice().reverse();
    };
});

app.directive('ngFocus', function() {
    return function(scope, element, attrs) {
        element.bind('click', function() {
            $('.' + attrs.ngFocus)[0].focus();
        });
    };
});



app.factory('UserService', ['$http', '$q', '$rootScope', function($http, $q, $rootScope){

    var REST_SERVICE_URI = 'http://localhost:8080/getMessages/';

    var factory = {
        fetchAllUsers: fetchAllUsers,
    };



    return factory;

    function fetchAllUsers() {

//alert($rootScope.currentConversationId);
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+$rootScope.currentConversationId)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);

app.factory('socket', function($rootScope) {
    var socket = new SockJS('/portfolio');
    var stompClient = Stomp.over(socket);
    stompClient.connect('', '', function(frame) {
        $rootScope.$broadcast('sockConnected', frame);
    });

    return {
        stompClient: stompClient
    };
});

app.controller('ConversationCtrl', ['$rootScope', '$scope', 'socket', function($rootScope, $scope, socket) {




    $scope.ShowId = function(event)
    {
        $rootScope.currentConversationId =event.currentTarget.id;
        var x = document.getElementsByClassName("selectedConvers");
        x[0].classList.remove("selectedConvers");
        event.currentTarget.className += " selectedConvers";
        //alert('df');
        $rootScope.$emit("fetchAllUsers", {});
    };

}]);


// app.controller('RandomFactCtrl', ['$rootScope', '$scope', 'socket', function($rootScope, $scope, socket) {
//     $scope.stompClient = socket.stompClient;
//     $scope.$on('sockConnected', function(event, frame) {
//
//         userName = frame.headers['user-name'];
//         queueSuffix = frame.headers['queue-suffix'];
//
//         $scope.stompClient.subscribe("/queue/random-fact", function(message) {
//             $scope.fact = JSON.parse(message.body);
//             $scope.$apply();
//         });
//     });
// }]);

app.controller('ErrorCtrl', ['$rootScope', '$scope', 'socket', function($rootScope, $scope, socket) {
    $scope.stompClient = socket.stompClient;
    $scope.$on('sockConnected', function(event, frame) {
        userName = frame.headers['user-name'];
        queueSuffix = frame.headers['queue-suffix'];
        $scope.stompClient.subscribe("/queue/errors", function(message) {
            $scope.error = JSON.parse(message.body);
            $scope.$apply();
        });
    });
}]);