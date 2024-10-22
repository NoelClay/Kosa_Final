let deleteModal;

function handleDeleteButtonClick(button) {
    row = button.closest('tr');
    deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
    deleteModal.show();
}

// 삭제 확인 버튼 클릭 시 Ajax 요청
document.getElementById("deleteConfirmButton").addEventListener("click", function () {
    showLoadingSpinner();
    const standardAreaName = row.querySelector('td:nth-child(1)').textContent;
    sendAjaxRequest(
        "/dms/standardArea/delete",
        {stdAreaNm: standardAreaName},
        function (response) {
            if (response.deleteResult) {
                showAlert("삭제가 완료되었습니다!", "success");
                row.remove();
                // 이미 렌더링된 select 엘리먼트를 찾아옴
                const selectElement = document.getElementById("selectStandardArea");
                // 기존의 옵션을 찾음
                const existingOption = selectElement.querySelector(`option[value="${standardAreaName}"]`);
                existingOption.remove();
                // 모달 닫기
                deleteModal.hide();
                hideLoadingSpinner();
            } else {
                showAlert("삭제 도중 오류가 발생하였습니다!");
                hideLoadingSpinner();
            }
        },
        function () {
            handleAjaxError();
            hideLoadingSpinner();
        }
    );
});
