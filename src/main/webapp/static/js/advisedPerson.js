$(function() {
    getSkills();
    submitEditProfileForm();
    submitSaveSkillsForm();

});




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
$("#personal").on('click', function (e) {
    $("#editProfileModal").modal('show');
});
    function submitEditProfileForm() {

    var token = $('#csrfToken').val();
    var header = $('#csrfHeader').val();

    /*  Submit form using Ajax */
    $("#saveProfile").on('click', function (e) {

        e.preventDefault();

        // if($('#saveProfileForm').valid()){
        //
        //     $('input').next("span").remove();

        $.post({
            url: '/advisedPerson/saveAdvisedPersonProfile',
            data: $('form[name=saveAdvisedPersonForm]').serialize(),
            success: function (res) {

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


$('#addSkills').click(function (e) {
    $("#editSkillsModal").modal('show');
    getSkillsNotHave();

});

// $('#removeSkills').click(function (e) {
//     $("#removeSkillsModal").modal('show');
// });


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


