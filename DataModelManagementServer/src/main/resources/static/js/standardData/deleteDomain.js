let deleteDomain = new bootstrap.Modal(document.getElementById('deleteDomain'));


function deleteSingleDomain(dicId) {
    sendAjaxRequest(
        "/dms/single-domain/deleteRest", // 삭제 url
        {
            domId: dicId
        },
        function(response) {
            handleDeleteDomain();
            deleteDomain.hide();
        },
        function() {
            handleAjaxError();
        }
    );
}


function handleDeleteDomain() {
    showAlert("도메인 삭제를 완료하였습니다.", "info");
}


