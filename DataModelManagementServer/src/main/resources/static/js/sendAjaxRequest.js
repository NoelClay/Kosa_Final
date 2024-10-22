function sendAjaxRequest(url, data, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),  // 데이터를 JSON 문자열로 변환
        contentType: "application/json",  // Content-Type을 application/json으로 설정
        success: successCallback,
        error: errorCallback,
    });
}