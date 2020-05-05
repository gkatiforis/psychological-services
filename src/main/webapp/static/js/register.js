$(function() {


       showHideForms();
       validateForm();
       submitRegisterForm();


    function validateForm(){

        $('#registerForm').validate({ // initialize the plugin
            rules: {
                email: {
                    required: true,
                    email: true,
                    maxlength: 30
                },
                ssoId: {
                    required: true,
                    minlength: 3,
                    maxlength: 15
                },
                password: {
                    required: true,
                    minlength: 8,
                    maxlength: 20
                },
                confirmpassword: {
                    required: true,
                    equalTo: "#rpassword"
                }
                ,userProfiles: {
                    required: true
                }
            }
        });

        jQuery.extend(jQuery.validator.messages, {
            required: "Το πεδίο είναι υποχρεωτικό",
            remote: "Please fix this field.",
            email: "Please enter a valid email address.",
            url: "Please enter a valid URL.",
            date: "Please enter a valid date.",
            dateISO: "Please enter a valid date (ISO).",
            number: "Please enter a valid number.",
            digits: "Please enter only digits.",
            creditcard: "Please enter a valid credit card number.",
            equalTo: "Please enter the same value again.",
            accept: "Please enter a value with a valid extension.",
            maxlength: jQuery.validator.format("Please enter no more than {0} characters."),
            minlength: jQuery.validator.format("Please enter at least {0} characters."),
            rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
            range: jQuery.validator.format("Please enter a value between {0} and {1}."),
            max: jQuery.validator.format("Please enter a value less than or equal to {0}."),
            min: jQuery.validator.format("Please enter a value greater than or equal to {0}.")
        });


    }



    function submitRegisterForm(){

        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();
        /*  Submit form using Ajax */
        $("#registerButton").on('click',function(e) {

            e.preventDefault();

            if($('#registerForm').valid()){

                $('input').next("span").remove();

$.post({
                url : 'user/newuser',
                data : $('form[name=registerForm]').serialize(),
                success : function(res) {

                    if(res.validated){

                        //Set response
                        $('#resultContainer pre').text("Επιτυχής εγγραφή!");
                        //    $('#resultContainer pre code').text(JSON.stringify(res.user));
                        $('#resultContainer').show();

                    }else{

                        //Set error messages
                        $.each(res.errorMessages,function(key,value){

                            $('input[name='+key+']').after('<span class="error">'+value+'</span>');
                        });
                    }
                } , beforeSend: function(xhr) {
                    // xhr.setRequestHeader("Accept", "application/json");
                    // xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.setRequestHeader(header, token);
                },
            })
            }
        });
    }


    function showHideForms(){
        var showspeed = 500;
        var hidespeed = 100;

        if($('#command').val() == 'login'){
            $('.login-container').fadeIn(showspeed);
            $('.register-container').fadeOut(hidespeed);
        }else{
            $('.register-container').fadeIn(showspeed);
            $('.login-container').fadeOut(hidespeed);
        }

        $("#registerUrl").on('click',function(e) {
            e.preventDefault();
            $('.register-container').fadeIn(showspeed);
            $('.login-container').fadeOut(hidespeed);
        });
        $("#loginUrl").on('click',function(e) {
            e.preventDefault();
            $('.login-container').fadeIn(showspeed);
            $('.register-container').fadeOut(hidespeed);
        });

    }





});