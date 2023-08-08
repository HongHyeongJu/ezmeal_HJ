function goToFirstPage() {
    window.location.href = '/notice?page=1';
}

function goToLastPage() {
    // 서버에서 전체 페이지 수를 받아와야 하므로, 이 값을 HTML에 표시해 둘 필요가 있습니다.
    var totalPage = document.getElementById('totalPage').innerText;
    window.location.href = '/notice?page=' + totalPage;
}
