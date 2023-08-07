
    // 체크박스 상태를 감지하고 게시글을 숨기거나 보여주는 함수
    function togglePostVisibility() {
    const checkbox = document.getElementById("hide_y/n");
    const post = document.getElementById("admin-notice-input1"); //

    if (checkbox.checked) {
    // 체크박스가 체크되어 있을 때
    post.style.display = "block"; // 게시글 보이기
} else {
    // 체크박스가 체크되어 있지 않을 때
    post.style.display = "none"; // 게시글 숨기기
}
}


    document.getElementById("hide_y/n").addEventListener("change", togglePostVisibility);
