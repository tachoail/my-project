$(document).ajaxSend(function(e, xhr, opt){
    var token = localStorage.getItem("user_token");
    xhr.setRequestHeader("user_token_header", token);
});
