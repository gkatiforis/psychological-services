$(document).ready(function() {

    var confereceToCompleteId = null;
    var confereceToCancelId = null;
    var orderToCancelId = null;

    var userId = $('#loggedinUserId').val();
    getTherapistFreeWorkingHourPerDayCountInMonth(userId);
   searchActiveConference();
  //  searchHistoryConference();

    $('#calendar').fullCalendar({
        // left:   '',
        // center: '',
        // right:  'today prev,next',
        locale: 'el',
        displayEventTime: false,
        //defaultView: 'month',
        validRange: function(nowDate) {
            return {
                start: nowDate.add(-1, 'days'),
                end: nowDate.clone().add(1, 'months')
            };
        },
        dayClick: function(date, jsEvent, view) {
            // alert('Clicked on: ' + date.format());
            // $('.fc-day').css('background-color', 'white');
            // $(this).css('background-color', '#f44336');
            $('#panel1Next').hide();

            $('#li2').addClass('disabled');
            $('#li3').addClass('disabled');
            var userId =$('#therapistId').val();
            //alert(date.format());
            selectedDate = date.format().replace(/\-/g, '');
            getFreeWorkingHours(userId, selectedDate);
        }

    });



    function searchHistoryConference() {



        $.get('conference/searchConferenceByCriteria-' + userId + '-' + 2, function (data, status) {

            showConference(data);
           // alert(JSON.stringify(data));
        });
    }

    function searchActiveConference() {



        $.get('conference/searchConferenceByCriteria-' + userId + '-' + 1, function (data, status) {
            //alert(JSON.stringify(data));
            showConference(data);

        });
    }


    function showConference(data){
      // alert( JSON.stringify(data));
        $('.conferencesList').html('');

        $('.ordersList').html('');

        if(data.length == 0){
            $('.conferencesList').append("<div class='text-center'> Δεν έχετε συνεδρίες </div>");
        }


        for(var i = 0; i < data.length; i++) {
            var conference = data[i];
            appendConference(conference);

        }


        var modalConfirm = function(callback){

            $(".completeButton").on("click", function(){
                $("#confirmCompleteModal").modal('show');
                confereceToCompleteId = $(this).attr('name');

            });

            $(".cancelButton").on("click", function(){
                $("#confirmCancelModal").modal('show');
                confereceToCancelId = $(this).attr('name');

            });
            $(".cancelOrderButton").on("click", function(){
                $("#confirmCancelOrderModal").modal('show');
                orderToCancelId = $(this).attr('name');

            });

            $(".modal-btn-si").on("click", function(){
                callback(true);

            });

            $(".modal-btn-no").on("click", function(){
                callback(false);
                $("#confirmCancelModal").modal('hide');
                $("#confirmCompleteModal").modal('hide');
                $("#confirmCancelOrderModal").modal('hide');

            });
        };
        modalConfirm(function(confirm){
            if(confirm){


                if(confereceToCompleteId != null){

                    setConnferenceCompleted(confereceToCompleteId);
                }else if(confereceToCancelId!=null){
                    setConnferenceCanceled(confereceToCancelId);
                }else if(orderToCancelId !=null){
                    setOrderCanceled(orderToCancelId);
                }

            }else{  }
        });

        $('[data-toggle="tooltip"]').tooltip();


    }


    function getTherapistFreeWorkingHourPerDayCountInMonth() {
        $.get('/conference/getConferenceCountPerDayInMounth',
            function (data, status) {


                var date = new Date();

                for(var i = 0; i < data.length; i++){
                    if(data[i] > 0){
                        var title = title = data[i]+' ραντεβού';
                        var myevent = {title: title,start: date};
                        $('#calendar').fullCalendar( 'renderEvent', myevent, true);

                    }
                    date.setDate(date.getDate() + 1);
                }
            });


    }

    $("#confActiveButton").on("click", function(){
        $("#confHistoryButton").removeClass('active');
        $(this).addClass('active');
      searchActiveConference();


    });
    $("#confHistoryButton").on("click", function(){
        $("#confActiveButton").removeClass('active');
        $(this).addClass('active');
        searchHistoryConference();
    });



    function setOrderCanceled(orderId){
        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        $.post({
            url: '/conference/setOrderCanceled-' + orderId,
            success: function (res) {
                //alert(JSON.stringify(res));

                //alert(res);
                if(res == 1){
                    //$('#'+orderId).hide();
                    $("#confirmCancelOrderModal").modal('hide');

                    location.reload();
                }else if(res == 0){}

            }, beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
        })
    }

    function setConnferenceCompleted(conferenceId){
        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        $.post({
            url: '/conference/setConnferenceCompleted-' + conferenceId,
            success: function (res) {
                //alert(JSON.stringify(res));

                if(res == 1){

                    $("#confirmCompleteModal").modal('hide');
                    location.reload();

                }else if(res == 0){}

            }, beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
        })
    }

    function setConnferenceCanceled(conferenceId){
        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        $.post({
            url: '/conference/setConnferenceCanceled-' + conferenceId,
            success: function (res) {
                //alert(JSON.stringify(res));

                if(res == 1){
                    $('#'+conferenceId).hide();
                    $("#confirmCancelModal").modal('hide');
                    location.reload();
                }else if(res == 0){}

            }, beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
        })
    }


    function appendConference(order){




        //order details
        var id = order.id;
        var orderStatus = order.orderStatus.id;
        var sum = order.service.conferenceCount;
        var num = order.conferenceCountUsed;

        var personId =order.advisedPerson.id;
        var therpistId = order.therapist.id;
        var therapistName = order.therapist.last_name + ' ' + order.therapist.first_name;
        var personName = order.advisedPerson.last_name + ' ' + order.advisedPerson.first_name;

        var userName = "";
        var orderTitle = sum + " " +order.service.serviceType.title;

        var closeAnotherConferenceDisplay = "";
        if(orderStatus != 1 || therpistId == userId){
            closeAnotherConferenceDisplay = "display: none;"
        }

        var closeCancelOrderDisplay = "";
        if(therpistId == userId){
            closeCancelOrderDisplay = "display: none;"
            userName =personName;
        }else{
            userName = therapistName;
        }


        var orderForm = "<div class=\"list-group-item\" id=\"order" + id + "\">" +
            " <a class='cancelOrderButton pull-right' name='" + id +"' style='" + closeCancelOrderDisplay +"'>Ακύρωση υπηρεσίας</a>" +

            "<h3 class=\"list-group-item-heading\">\n" +
            userName +" | "+orderTitle+
            "                                                </h3>" +
            "<div style='margin-top: 5px;' class=\"progress\">\n" +
            "  <div class=\"progress-bar progress-bar-success\" role=\"progressbar\" aria-valuenow=\"\"\n" +
            "     aria-valuemin=\"1\" aria-valuemax=\"\" style=\"width: " + num / sum * 100 +"%\">\n" +
            "        Συνεδρία " + num + "η από " + sum +
            "  </div>\n" +
            "</div>" +
            "<div style='" + closeAnotherConferenceDisplay +"'>" +
                 "<a href=\"/book-"+ therpistId + "\" class=\"btn btn-next\">\n" +
            "Κλείσε το επόμενο ραντεβού</a>" +
            "</div>" +

            "<ul class=\"event-list\">\n";


        for(var i = 0; i < order.conferences.length; i++){
            var color= "";
            var displayButton="";
            var displayCompleteButton="";
            var cancelButton="";

            var conference =order.conferences[i];
            var conferencesId= conference.id;
            var conferencesStatus = conference.conferencestatus.id;

            var expertJoinedDate = conference.expertJoinedDate;
            var personJoinedDate = conference.personJoinedDate;
            var date = conference.dateWhen;
            var timeFrom = conference.time;
            var arr = timeFrom.split(':');
            timeFrom =arr[0]+':'+arr[1];
            var date = conference.dateWhen;
            var conferencesType = conference.conferencesType.id;

            var type ="";
            if(conferencesType == 1){
                type="Βίντεο κλήση";
            }else{
                type="Φωνητική κλήση"
            }

            var userDesc = conference.userDesc;
            if(userDesc == null || userDesc ==""){
                userDesc="Ο ασθενής δεν εχει δώσει καμία σημείωση";
            }
            var personLabel ="";
            if(therpistId == userId){
                personLabel = "<a href='/profile-" + personId + "' class=''>" + personName +"</a>";
                if((expertJoinedDate == null || personJoinedDate == null) || conferencesStatus == 2){
                    displayCompleteButton = "display:none;";
                }
                cancelButton= "display:none;";
            }
            else{
                personLabel = " <a href='/profile-" + therpistId+ "' class=''>"+ therapistName +"</a>";
                if((expertJoinedDate != null && personJoinedDate != null) || conferencesStatus == 2){
                    cancelButton = "display:none;";
                }
                displayCompleteButton = "display:none;";

            }

            if(date == 'Σήμερα'){
                color = "today";
                if(conferencesStatus == 2){
                    displayButton ="display:none;";
                }

            }
            else if(date == 'Αύριο'){
                color = "tomorrow";
                displayButton ="display:none;";
            }
            else{
                color='future';
                displayButton ="display:none;";
            }

            var tr = "<li id='" + id +"'>\n" +
                "                                                            <time  id='"+ color +"' datetime='2014-07-31 1600'>\n" +
                "                                                                <span style='margin-top: 15px;' class='month' style='margin-top: 10px;'>"+date+" </span>\n" +
                "                                            <span  style='margin-top: 10px;' class='timee'>στις</span>\n" +
                "                                                                <span  style='margin-top: 10px;' class='timee'>" + timeFrom + "</span>\n" +
                "                                                             </time>\n" +
                "                                                            <div class='info'>\n" +
                "                                                               \n" +
                "                                                                  "+ type+"<br>\n" +
                "                                                               \n" +
                "                                                                <p class='desc'>"+ userDesc +" </p>\n" +
                "                                                                <ul>\n" +
                // "                                                                    <li style='width:100%;'>Περισσότερα</li>\n" +
                "                                                                </ul>\n" +
                "                                                            </div>\n" +
                "                                                            <div class='social'>\n" +
                "                                                                <ul>" +
                "                                                                    <li class='startConference' name='" + conferencesId + "' style=' " + displayButton +"'><a  href='/liveconference-" + conferencesId +"' target='_blank' class=\"btn btn-default\" data-toggle='tooltip' data-placement='right' title='Έναρξη συνεδρίας'><img src='https://cdn2.iconfinder.com/data/icons/ios-7-icons/50/video_camera-256.png' width='20' />Έναρξη </a></li>\n" +
                "                                                                    <li class='cancelButton' name='" + conferencesId + "' style=' " + cancelButton +"'><a  href='#' class=\"btn btn-default\" data-toggle='tooltip' data-placement='right' title='Αναβολή συνεδρίας'><img src='https://d30y9cdsu7xlg0.cloudfront.net/png/7197-200.png' width='20' />Αναβολή </a></li>\n" +
                "                                                                    <li class='completeButton' name='" + conferencesId + "' style=' " + displayCompleteButton +"'><a href='#' class=\"btn btn-default\" data-toggle='tooltip' data-placement='right' title='Ολοκλήρωση συνεδρίας'><img src='https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Checkmark_green.svg/1180px-Checkmark_green.svg.png' width='20' />Ολοκλήρωση </a></li>\n" +
                "                                                                </ul>\n" +
                "                                                            </div>\n" +
                "                                                        </li>";
            if(conferencesStatus != 2 && conferencesStatus != 3){
                orderForm+=tr;
            }

        }


        orderForm+="           </ul> </div>";

        $('.ordersList').append(orderForm);
    }


});
