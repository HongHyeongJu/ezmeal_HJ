
// 공지사항 그래프
google.charts.load("current", { packages: ["table"] });
google.charts.setOnLoadCallback(drawTable1);

function drawTable1() {
    var data = new google.visualization.DataTable();
    data.addColumn("string", "작성일");
    data.addColumn("string", "제목");
    data.addColumn("boolean", "글 숨김 여부");
    data.addRows([
        ["2023-08-08","배송 휴무 일정 안내" , true],
        ["2023-08-09", "네이버페이 장애로 인한 네이버 페이 결제 불가 안내", false],
        ["2023-08-10","LGU+ 휴대폰결제 서비스 점검 안내(2023.08.10~08.11)", true],
        ["2023-08-11","낮배송(택배)주문시간 확장 안내" , true]
    ]);

    var table = new google.visualization.Table(
        document.getElementById("chart_board1")
    );

    table.draw(data, { showRowNumber: true, width: "500px", height: "300px" });
}

// 1:1 문의 그래프
google.charts.load("current", { packages: ["table"] });
google.charts.setOnLoadCallback(drawTable2);

function drawTable2() {
    var data = new google.visualization.DataTable();
    data.addColumn("string", "작성일");
    data.addColumn("string", "제목");
    data.addColumn("boolean", "답변 여부");
    data.addRows([
        ["2023-08-08","[배송] 토요일까지 받을 수 있나요?" , false],
        ["2023-08-08", "[환불] 상품이 별로인데 환불 해주세요", false],
        ["2023-08-08","[상품]재입고 언제 되나요?", false],
        ["2023-08-08","[상품]상품이 파손되어 왔어요" , true]
    ]);

    var table = new google.visualization.Table(
        document.getElementById("chart_board2")
    );

    table.draw(data, { showRowNumber: true, width: "500px", height: "300px" });
}

// 상품후기 그래프
google.charts.load("current", { packages: ["table"] });
google.charts.setOnLoadCallback(drawTable3);

function drawTable3() {
    var data = new google.visualization.DataTable();
    data.addColumn("string", "id");
    data.addColumn("string", "주문번호");
    data.addColumn("boolean", "베스트리뷰 선정");
    data.addRows([
        ["dlwldmsgo","1335875123" , true],
        ["hhj0720", "2342454244", true],
        ["ktw0625","243546456", true],
        ["bhw","2344546632" , true]
    ]);

    var table = new google.visualization.Table(
        document.getElementById("chart_board3")
    );

    table.draw(data, { showRowNumber: true, width: "500px", height: "300px" });
}
