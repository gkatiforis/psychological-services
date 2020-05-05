var selectedDate = "";
var workingHourId = "";
var typeId = "";
var orderId = "";

$(document).ready(function() {
    submitBookingForm();
    submitbuyServiceForm();

    var userId =$('#therapistId').val();
    getTherapistFreeWorkingHourPerDayCountInMonth(userId);

    $('#calendar').fullCalendar({
        // left:   '',
        // center: '',
         right:  'prev,next',
        displayEventTime: false,
        locale: 'el',
        //defaultView: 'month',
        validRange: function(nowDate) {
            return {
                start: nowDate.add(-1, 'days'),
                end: nowDate.clone().add(1, 'months')
            };
        },
        dayClick: function(date, jsEvent, view) {
           // alert('Clicked on: ' + date.format());
           //
           //  $(this).css('background-color', '#f44336');
           //
           //  $('.fc-day').css('background-color', 'white');
            $('#panel1Next').hide();
            //$('#li2').addClass('disabled');
            $('#li3').addClass('disabled');
            var userId =$('#therapistId').val();
            //alert(date.format());
            $('#dateOutput').html(date.format());
            selectedDate = date.format().replace(/\-/g, '');

            getFreeWorkingHours(userId, selectedDate);
        },eventClick: function(event, jsEvent, view) {
            var date=$(this).closest('.'+(view.type=='month'?'fc-row':'fc-time-grid')).find('.fc-bg td:eq('+$(this).closest('td').index()+')').data('date');
            // $(this).css('background-color', '#f44336');
            //
            // $('.fc-day').css('background-color', 'white');
            $('#panel1Next').hide();
            //$('#li2').addClass('disabled');
            $('#li3').addClass('disabled');
            var userId =$('#therapistId').val();
            $('#dateOutput').html(date);
            selectedDate = date.replace(/\-/g, '');
            getFreeWorkingHours(userId, selectedDate);
        }

    });



    $('.conferenceType').on('click',
        function(){
            typeId = $(this).attr('id');
            $('#panel2Next').prop('disabled', false);
            $('#li3').removeClass('disabled');
        });

});


$("#showServicies").on('click', function (e) {
    $(this).hide();
   $('#buyServicePanel').show();
});

$(".orderRadio").on('click', function (e) {

        orderId = $(this).attr('id');
       // $('#panel2Next').prop('disabled', false);
        $('#li2').removeClass('disabled');
    $('#chooseOrderButton').show();
});


function submitBookingForm() {

    var token = $('#csrfToken').val();
    var header = $('#csrfHeader').val();

    /*  Submit form using Ajax */
    $("#panel2Next").on('click', function (e) {

        e.preventDefault();

        var therapistId = $('#therapistId').val();
        var loggedinUserId = $('#loggedinuserId').val();
        var descr = $('#description').val();

        // if($('#saveProfileForm').valid()){
        //
        //     $('input').next("span").remove();

        //alert('/booking/bookConference-' + loggedinUserId + '-' + therapistId + '-' + selectedDate + '-' + workingHourId + '-' + descr + '-' + typeId + '');
        $.post({
            url: '/booking/bookConference-' + loggedinUserId + '-' + therapistId + '-' + selectedDate + '-' + workingHourId + '-' + descr + '-' + typeId + '-' + orderId,
           // data: $('form[name=bookConferenceForm]').serialize(),
            success: function(data, textStatus, xhr) {
                window.location = xhr.getResponseHeader("Location");
            },
            beforeSend: function (xhr) {
                // xhr.setRequestHeader("Accept", "application/json");
                // xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);
            },
        })
        // }
    });
}

function submitbuyServiceForm() {

    var token = $('#csrfToken').val();
    var header = $('#csrfHeader').val();

    /*  Submit form using Ajax */
    $(".buyService").on('click', function (e) {

        e.preventDefault();

        var serviceId = $(this).attr("name");

        // if($('#saveProfileForm').valid()){
        //
        //     $('input').next("span").remove();

        //alert('/booking/bookConference-' + loggedinUserId + '-' + therapistId + '-' + selectedDate + '-' + workingHourId + '-' + descr + '-' + typeId + '');
        $.post({
            url: '/booking/buyService-' + serviceId,
            // data: $('form[name=bookConferenceForm]').serialize(),
            success: function(data, textStatus, xhr) {
                location.reload();
              //  alert(data);
            },
            beforeSend: function (xhr) {
                // xhr.setRequestHeader("Accept", "application/json");
                // xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);
            },
        })
        // }
    });
}




function getTherapistFreeWorkingHourPerDayCountInMonth(userId) {
    $.get('/therapist/getTherapistFreeWorkingHourPerDayCountInMonth-' + userId,
        function (data, status) {


             var date = new Date();

            for(var i = 0; i < data.length; i++){
                if(data[i] > 0){
                    var title = title = data[i]+' διαθεσιμότητες';
                    var myevent = {title: title,start: date};
                    $('#calendar').fullCalendar( 'renderEvent', myevent, true);

                }
                date.setDate(date.getDate() + 1);
            }
        });


}

function getFreeWorkingHours(userId, date) {
    $.get('/therapist/getTherapistFreeWorkingHourInDay-' + userId + '-' + date,
        function (data, status) {
            $('#hoursPanel').hide();

            $('#calendarPanel').removeClass('col-md-offset-2');
            $('#hoursPanel').css('visibility', 'visible');
            //alert(JSON.stringify(data));
            var freeHoursPanel = "";

            if(data.length == 0){
                freeHoursPanel = "<div>Δεν υπάρχουν διαθέσιμα ραντεβού. </div>"
            }
            for(var i = 0; i < data.length; i++){

                var arr = data[i].workingTime.split(':');
                var time =arr[0]+':'+arr[1];
                freeHoursPanel+= "<button id='" + data[i].id + "' type='button' class='btn btn-default hourbutt'> " + time + "</button>";
            }


            $('#freeHoursPanel').html(freeHoursPanel);

//btn-fill
            $('.hourbutt').css('color', 'black');
            $('.hourbutt').css('background-color', '#d9d9d9');


            $('.hourbutt').on('click',
                function(){
                    $('.hourbutt').css('color', 'black');
                    $('.hourbutt').css('background-color', '#d9d9d9');
                    $(this).css('color', 'white');
                    $(this).css('background-color', '#f44336');
                    $('#panel1Next').show();
                    $('#li3').removeClass('disabled');
                    workingHourId = $(this).attr('id');
                });

            $('#hoursPanel').show();
            // var removeSkillsPanel = "";
            //
            // for (var i = 0; i < data.length; i++) {
            //     skillPanel += '<span class="label label-default" id="A_1"> ' + data[i].name + '</span>';
            //     removeSkillsPanel += '<span class="label label-default  removeSkillLabel" name="' + data[i].id + '" id="A_1"> ' + data[i].name + ' ' + ' | X' + '</span>';
            // }


        });


}