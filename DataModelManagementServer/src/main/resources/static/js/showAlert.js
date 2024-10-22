document.addEventListener("DOMContentLoaded", function () {
    // 알람 컨테이너 요소 가져오기
    const alertContainer = document.getElementById("alertContainer"); // jsp에 이게 있어야댐

    // 알람을 표시하는 함수
    window.showAlert = function showAlert(message, type = "danger") {
        const alertElement = document.createElement("div");
        alertElement.classList.add("alert", `alert-${type}`, "alert-dismissible", "fade", "show");
        alertElement.innerHTML = `
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            ${message}
        `;

        // 알람 컨테이너에 알람 요소 추가
        alertContainer.appendChild(alertElement);

        // 투명도 애니메이션 적용 (나타날 때)
        alertElement.style.transition = "opacity 0.5s";
        alertElement.style.opacity = 0;

        // 강제로 리플로우를 일으켜 애니메이션 적용
        alertElement.offsetHeight;

        alertElement.style.opacity = 1;

        // 3초 후에 알람 삭제 및 투명도 애니메이션 적용 (사라질 때)
        setTimeout(() => {
            alertElement.style.opacity = 0;
            alertElement.addEventListener("transitionend", function () {
                alertElement.remove();
            });
        }, 3000);
    }
});
