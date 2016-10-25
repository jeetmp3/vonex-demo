var makeCall = function(url, data, success, failure) {
    jQuery.ajax({
        url: url,
        data: data,
        success: success,
        error: failure
    });
}