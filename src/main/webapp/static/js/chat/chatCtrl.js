app.controller('ChatCtrl', ['$rootScope', '$scope', 'socket', 'UserService', function($rootScope, $scope, socket, UserService) {

    $scope.chats = [];
    $scope.stompClient = socket.stompClient;

    var selectedConvId = angular.element( document.querySelector('#selectedConv') );

    var x = document.getElementById(selectedConvId.val());
    x.className += " selectedConvers";
    $rootScope.currentConversationId = selectedConvId.val();

     fetchAllUsers();


        $rootScope.$on("fetchAllUsers", function () {
         //   alert('df');
            fetchAllUsers();
        });


      function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
                function(d) {
                    //alert("ok");
                       $scope.chats= [];
                    angular.forEach(d, function(value, key){
                        //console.log(value);
                       $scope.processIncomingMessage(value, false);
                  });



                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }



    $scope.$on('sockConnected', function(event, frame) {

        $scope.userName = frame.headers['user-name'];
        queueSuffix = frame.headers['queue-suffix'];

        // $scope.stompClient.subscribe("/queue/chats" + queueSuffix, function(message) {
        //     $scope.processIncomingMessage(message, true);
        // });
        //
        // $scope.stompClient.subscribe("/queue/chats", function(message) {
        //     $scope.processIncomingMessage(message, true);
        // });
        $scope.stompClient.subscribe("/user/queue/chats", function(message) {
            $scope.processIncomingMessage(message, true);
        });


    });

    $scope.$on('sendingChat', function(event, sentChat) {
        chat = angular.copy(sentChat);
        chat.from = 'Me';
        chat.direction = 'outgoing';
        $scope.addChat(chat);
    });

    $scope.processIncomingMessage = function(message, islive) {



        if(islive) {
            message = JSON.parse(message.body);
        }
        message.direction = 'incoming';
        message.pull = 'pull-left';
        message.broadcast = false;
      //  message.sendDate = (new Date(message.sendDate),'yyyy-MM-dd');
        if(message.from != $scope.userName) {
            message.pull = 'pull-right';

        }
        $scope.addChat(message);
        $scope.$apply(); // since inside subscribe closure
         var scroller = document.getElementById("autoscroll");
        scroller.scrollTop = scroller.scrollHeight;
    };


    $scope.addChat = function(chat) {
        $scope.chats.push(chat);
    };



}]);