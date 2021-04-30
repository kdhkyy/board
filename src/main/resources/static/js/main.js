(function ($) {
    "use strict";


    /*==================================================================
    [ Focus Contact2 ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })

    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
            hideValidate(this);
        });
    });
  
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                check=false;
            }
        }

        return check;
    });

    //ID Check
    //1.대소문자 영문과 숫자
    function validate (input) {
        if($(input).attr('type') == 'id' || $(input).attr('name') == 'id') {
            if($(input).val().trim().match("^[a-zA-Z0-9]+$") == null) {
                showValidateId(input);
                return false;
            }
        } else if($(input).attr('type') == 'password' || $(input).attr('name') == 'pass') {
            if($(input).val().trim().match("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$") == null) {
                showValidatePassword(input);
                return false;
            }
        }else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidateId(input) {
        var thisAlert = $(input).parent();
        $(input).setCustomValidity("아이디는 영대소문자, 숫자로 입력해주세요");
        // $(thisAlert).addClass('alert-validate');
    }

    function showValidatePassword(input) {
        var thisAlert = $(input).parent();
        $(input).setCustomValidity("아이디는 영대소문자, 숫자로 입력해주세요");
        // $(thisAlert).addClass('alert-validate');

    }


    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }

})(jQuery);