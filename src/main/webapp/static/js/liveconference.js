var isOnlineLinstenerInterval;
var isConnectedToRoom = false;
var activeRoom;

$(document).ready(function() {


    connectToRoomListener();



    // Option 1
    Twilio.Video.createLocalTracks({
        audio:true,
        video: {
            //width: 144
            width: 320,
            height: 200,
            frameRate: 15
            //bandwidth: 500 // 500 Kbps
        }
    }).then(localTracks => {

        $('#local-media-ctr').html('');
        $('#local-media-ctr').append(localTracks[1].attach());
});


});

    function connectToRoomListener(){


        $("#startConference").on('click', function (e) {

            e.preventDefault();
            $("#startConference").hide();
            $("#terminateConference").show();
            $("#welocomeAlert").hide();
            $("#watingAlert").show();

            isOnlineLinstenerInterval =  setInterval(isOnlineLinstener, 5000);
        });

        $("#terminateConference").on('click', function (e) {

            e.preventDefault();
            $("#startConference").show();
            $("#terminateConference").hide();

            clearInterval(isOnlineLinstenerInterval);
       });
    }



    function isOnlineLinstener(){

        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        var loggedinuserId = $('#loggedinuserId').val();
        var conferenceId = $('#conferenceId').val();


        $.post({
            url: '/liveconference/isOnline-' + conferenceId,
            success: function (res) {
                //alert(JSON.stringify(res));

                if(res == 1 && isConnectedToRoom == false){
                    isConnectedToRoom = true;
                    connectToRoom();
                }else if(res == 0){
                    disconnectFromRoom();
                }

            }, beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
        })
    }


function disconnectFromRoom(){
        if(isConnectedToRoom == true){
            // activeRoom.localParticipant.tracks.forEach(function(track) {
            //     track.stop()
            // });
            activeRoom.disconnect();
            isConnectedToRoom = false;
        }
}

    function connectToRoom(){

    // if(isConnectedToRoom == true){
    //     return;
    // }

        var jwt = $('#jwt').val();
        // alert($('#jwt').val());
        // Option 1
        Twilio.Video.createLocalTracks({
            audio:true,

            video: {
                //width: 144
                width: 320,
                height: 200,
                frameRate: 15
                //bandwidth: 500 // 500 Kbps
            }
        }).then(localTracks => {

            $('#local-media-ctr').html('');
        $('#local-media-ctr').append(localTracks[1].attach());


        return Twilio.Video.connect(jwt, {
            // name: 'room',
            tracks: localTracks
        });
    }).then(room => {
            console.log('Connected to Room:', room.name);

        $("#watingAlert").hide();
        $("#conStartAlert").show();

        setTimeout(function(){ $("#conStartAlert").hide(); }, 10000);

        activeRoom = room;
        isConnectedToRoom = true;

// Log any Participants already connected to the Room
        room.participants.forEach(participant => {
            console.log('Participant "%s" is connected to the Room', participant.identity);

       // const div = document.createElement('div');
       // div.id = participant.sid;

        // div.innerText = participant.identity;

        var div  =$('#remote-media-div');

        div.html('');

        participant.on('trackAdded', track => trackAdded(div,  track));
        participant.tracks.forEach(track => trackAdded(div, track));
        participant.on('trackRemoved', trackRemoved);

        // $('#remote-media-div').html('');
        // $('#remote-media-div').append(div);


    });

        room.on('disconnected', room => {
            // Detach the local media elements
            room.localParticipant.tracks.forEach(track => {
            var attachedElements = track.detach();
        attachedElements.forEach(element => element.remove());
    });
    });

        room.on('participantConnected',participantConnected);



    }, function(error) {
            console.error('Unable to connect to Room: ' +  error.message);
        });
    }

    function participantConnected(participant) {
        console.log('Participant "%s" connected', participant.identity);

       // const div = document.createElement('div');
       // div.id = participant.sid;

        var div  =$('#remote-media-div');

        div.html('');

        participant.on('trackAdded', track => trackAdded(div, track));
        participant.tracks.forEach(track => trackAdded(div, track));
        participant.on('trackRemoved', trackRemoved);

        // $('#remote-media-div').html('');
        // $('#remote-media-div').append(div);

    }


    function trackAdded(div, track) {
        div.append(track.attach());
    }

    function trackRemoved(track) {
        track.detach().forEach(element => element.remove());
    }






