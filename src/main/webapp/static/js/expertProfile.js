$(function() {



    var skills = [];

    setRate();

    $('#showHoursBtn').click(function (e) {

        document.getElementById('hoursBody').style.overflow = 'visible';
        document.getElementById('hoursBody').style.height = document.getElementById('hoursBody').scrollHeight;

    });

    $('#amount').keyup(function (e) {

        var percentage = 10;
        var sumCost = $(this).val();
        var plusCost = sumCost*percentage/100;

        $('#plusCost').html(plusCost);
        $('#profit').html(sumCost - plusCost);


    });

    $("input[name='numPanel']").click(function(){
        if($(this).val()==1){
            $('#addServicePanel').show();
            $('#numConfPanel').hide();
            $('#singleConfPanel').show();

        }else{
            $('#addServicePanel').show();
            $('#numConfPanel').show();
            $('#singleConfPanel').hide();
        }

    });


    $('#addService').click(function (e) {
        $("#addServiceModal").modal('show');


    });


    $('#addSpec').click(function (e) {
        $("#editSpecModal").modal('show');
        getSpecNotHave();

    });

    $('#removeSpec').click(function (e) {
        $("#removeSpecModal").modal('show');
    });

    $('#addSkills').click(function (e) {
        $("#editSkillsModal").modal('show');
        getSkillsNotHave();

    });

    $('#removeSkills').click(function (e) {
        $("#removeSkillsModal").modal('show');
    });

    $('.editedWorkingHousButton').click(function (e) {
        $("#workingHoursModal").modal('show');
       // getSkillsNotHave();

    });

    $('.editButton').click(function (e) {




        var arr = $('#user_name').html().split(' ');

        $('#first_name').val(arr[0]);
        $('#last_name').val(arr[1]);
        $('#description').val($('#user_description').html());
        $('#specialisation').val($('#user_spec').html());
        $('#bio').val($('#user_bio').html());
        $('#exp').val($('#user_exp').html());

        $("#editProfileModal").modal('show');
    });

    //getData();
    getSkills();
    getSpec();

    submitEditProfileForm();
    submitSeriviceForm();
    submitRemoveServiceForm();
    submitSaveWorkingHoursForm();
    submitSaveSkillsForm();
    submitRemoveSkillsForm();
    submitRemoveSpecForm();
    submitSaveSpecForm();



    function getSkills() {
        $.get('/user/getDisorders-' + $('#id').val() + '-1', function (data, status) {

            var skillPanel = "";
            var removeSkillsPanel = "";

            for (var i = 0; i < data.length; i++) {
                skillPanel += '<span class="label label-default" id="A_1"> ' + data[i].name + '</span>';
                removeSkillsPanel += '<span class="label label-default  removeSkillLabel" name="' + data[i].id + '" id="A_1"> ' + data[i].name + ' ' + ' | X' + '</span>';
            }

            if (removeSkillsPanel != "") {
                $('#removeskillsPanel').html(removeSkillsPanel);
                $('#skillsPanel').html(skillPanel);
            } else {
                $('#removeskillsPanel').html("<p class='text-center'> Δεν έχετε προσθέσει καμία πάθηση.</p>");
                $('#skillsPanel').html("<p class='text-center'> Δεν έχετε προσθέσει καμία πάθηση.</p>");
            }


            $('.removeSkillLabel').on('click', function () {


                var id = $(this).attr('name');

                if (!(skillsIdstoRemove.indexOf(id) > -1)) {
                    skillsIdstoRemove.push(id);
                    $(this).css('background-color', '#ffcccc');
                } else {
                    skillsIdstoRemove.splice(skillsIdstoRemove.indexOf(id), 1);
                    $(this).css('background-color', 'white');
                }

            });

        });
    }
    function getSkillsNotHave() {
        $.get('/user/getDisorders-' + $('#id').val() + '-0', function (data, status) {


            var str = JSON.stringify(data);
            str = str.replace(/name/g, 'text'); // need to be text not name
            var array = JSON.parse(str)

            //alert(JSON.stringify(data));
            //   alert(str);

            $("#id_label_multiple_skills").select2({
                data: array

            })


            // $("#skillsList").typeahead({
            //     source: data,
            //     autoSelect: true,
            //     hint: true,
            //     highlight: true,
            //     minLength: 0,
            //     limit: 10,
            // });


        });
    }

    function getSpec() {
        $.get('/therapist/getSpec-' + $('#id').val() + '-1', function (data, status) {

            var specPanel = "";
            var removeSpecPanel = "";

            for (var i = 0; i < data.length; i++) {
                specPanel += '<span class="label label-default" id="A_1"> ' + data[i].name + '</span>';
                removeSpecPanel += '<span class="label label-default  removeSpecLabel" name="' + data[i].id + '" id="A_1"> ' + data[i].name + ' ' + ' | X' + '</span>';
            }

            if (removeSpecPanel != "") {
                $('#removeSpecPanel').html(removeSpecPanel);
                $('#specPanel').html(specPanel);
            } else {
                $('#removeSpecPanel').html("<p class='text-center' > Δεν έχετε προσθέσει καμία εξειδίκευση.</p>");
                $('#specPanel').html("<p class='text-center'> Δεν έχετε προσθέσει καμία εξειδίκευση.</p>");
            }


            $('.removeSpecLabel').on('click', function () {


                var id = $(this).attr('name');

                if (!(specIdstoRemove.indexOf(id) > -1)) {
                    specIdstoRemove.push(id);
                    $(this).css('background-color', '#ffcccc');
                } else {
                    specIdstoRemove.splice(specIdstoRemove.indexOf(id), 1);
                    $(this).css('background-color', 'white');
                }

            });

        });
    }
    function getSpecNotHave() {
        $.get('/therapist/getSpec-' + $('#id').val() + '-0', function (data, status) {


            var str = JSON.stringify(data);
            str = str.replace(/name/g, 'text'); // need to be text not name
            var array = JSON.parse(str)

            //alert(JSON.stringify(data));
            //   alert(str);

            $("#id_label_multiple_spec").select2({
                data: array

            })


            // $("#skillsList").typeahead({
            //     source: data,
            //     autoSelect: true,
            //     hint: true,
            //     highlight: true,
            //     minLength: 0,
            //     limit: 10,
            // });


        });
    }

    // function getData() {
    //     $.get('/user/getExpertProfileData-' + $('#id').val(), function (data, status) {
    //
    //         $('#user_name').html(data.last_name + ' ' + data.first_name);
    //         $('#user_spec').html(data.specialisation);
    //         $('#user_description').html(data.description);
    //         $('#user_bio').html(data.bio);
    //         // $('#user_exp').html(data.exp);
    //         //  $('.loadingPanel').hide();
    //         // $('#subbody').fadeIn(500);
    //
    //
    //         getSkills();
    //     });
    // }


    function submitEditProfileForm() {

        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        /*  Submit form using Ajax */
        $("#saveExpertProfileButton").on('click', function (e) {

            e.preventDefault();

            // if($('#saveProfileForm').valid()){
            //
            //     $('input').next("span").remove();

            $.post({
                url: '/therapist/saveExpertProfile',
                data: $('form[name=saveExpertProfileForm]').serialize(),
                success: function (res) {
                    $("#editProfileModal").modal('hide');
                    location.reload();
                    // alert(JSON.stringify(res));


                }, beforeSend: function (xhr) {
                    // xhr.setRequestHeader("Accept", "application/json");
                    // xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader(header, token);
                },
            })
            // }
        });
    }

    function submitSeriviceForm() {

        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        /*  Submit form using Ajax */
        $("#addServiceButton").on('click', function (e) {

            e.preventDefault();

            // if($('#saveProfileForm').valid()){
            //
            //     $('input').next("span").remove();

      //      alert(JSON.stringify($('form[name=addServiceForm]').serialize()));
            $.post({
                url: '/therapist/saveService',
                data: $('form[name=addServiceForm]').serialize(),
                success: function (res) {
                    $("#addServiceModal").modal('hide');
                    location.reload();
                    // alert(JSON.stringify(res));


                }, beforeSend: function (xhr) {
                    // xhr.setRequestHeader("Accept", "application/json");
                    // xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader(header, token);
                },
            })
            // }
        });
    }
    function submitRemoveServiceForm() {

        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        /*  Submit form using Ajax */
        $(".removeService").on('click', function (e) {

            e.preventDefault();

            var serviceId =  $(this).attr("name");

            $.get('/therapist/removeService-' + serviceId,
                function (data, status) {

                    if (data == "ok") {
                        $("#service"+ serviceId).hide();
                    //  location.reload();
                    }

                });


        });
    }
    function submitSaveWorkingHoursForm() {

        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        /*  Submit form using Ajax */
        $("#saveworkingHoursButton").on('click', function (e) {

            e.preventDefault();

            var str = $('form[name=workingHoursForm]').serialize();
            str = str.replace("wh=", '');
            str = str.replace(/wh=/g, ',');
            str = str.replace(/&/g, '');

            $.get('/therapist/setWorkingHours-' + $('#id').val() + '-' + str,
                function (data, status) {

                    if (data == "ok") {
                        $("#workingHoursModal").modal('hide');
                        location.reload();
                    }

                });


        });
    }




    var skillsIdstoRemove = [];
    function submitRemoveSkillsForm() {

        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        /*  Submit form using Ajax */
        $("#removeSkillsButton").on('click', function (e) {

            e.preventDefault();


            $.get('/user/removeDisorders-' + $('#id').val() + '-' + skillsIdstoRemove,
                function (data, status) {

                    if (data == "ok") {
                        $("#removeSkillsModal").modal('hide');
                        location.reload();
                    }

                });


        });
    }
    function submitSaveSkillsForm() {

        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        /*  Submit form using Ajax */
        $("#saveSkillsButton").on('click', function (e) {

            e.preventDefault();

            var str = $('form[name=saveSkillsForm]').serialize();
            str = str.replace("disorder=", '');
            str = str.replace(/disorder=/g, ',');
            str = str.replace(/&/g, '');

            $.get('/user/addDisorders-' + $('#id').val() + '-' + str,
                function (data, status) {

                    if (data == "ok") {
                        $("#editSkillsModal").modal('hide');
                        location.reload();
                    }

                });


        });
    }


    var specIdstoRemove = [];
    function submitRemoveSpecForm() {

        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        /*  Submit form using Ajax */
        $("#removeSpecButton").on('click', function (e) {

            e.preventDefault();


            $.get('/therapist/removeSpec-' + $('#id').val() + '-' + specIdstoRemove,
                function (data, status) {

                    if (data == "ok") {
                        $("#removeSpecModal").modal('hide');
                        location.reload();
                    }

                });


        });
    }
    function submitSaveSpecForm() {

        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        /*  Submit form using Ajax */
        $("#saveSpecButton").on('click', function (e) {

            e.preventDefault();

            var str = $('form[name=saveSpecForm]').serialize();
            str = str.replace("specialite=", '');
            str = str.replace(/specialite=/g, ',');
            str = str.replace(/&/g, '');

            $.get('/therapist/addSpec-' + $('#id').val() + '-' + str,
                function (data, status) {

                    if (data == "ok") {
                        $("#editSpecModal").modal('hide');
                        location.reload();
                    }

                });


        });
    }



    function setRate(){
        $("#rateYo_therapist").rateYo({
            rating: 0,

        });

        var starWidth = $("#rateYo_therapist").rateYo("option", "starWidth"); //returns 40px

        $("#rateYo_therapist").rateYo("option", "starWidth", "20px"); //returns a jQuery Element

    }

});












