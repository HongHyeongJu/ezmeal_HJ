//start: 게시판 그래프
google.charts.load("current", { packages: ["table"] });
google.charts.setOnLoadCallback(drawTable);

function drawTable() {
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
        document.getElementById("chart_shape4")
    );

    table.draw(data, { showRowNumber: true, width: "500px", height: "300px" });
}



