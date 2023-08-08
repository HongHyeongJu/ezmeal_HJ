//start: 상품 그래프
google.charts.load("current", { packages: ["line", "corechart"] });
google.charts.setOnLoadCallback(drawLineChart);

function drawLineChart() {
    var data = google.visualization.arrayToDataTable([
        ["카테고리", "CATEGORY"],
        ["닭가슴살", 396],
        ["도시락|볶음밥", 121],
        ["샐러드", 542],
        ["신선식품", 260],
        ["즉석간편식", 698],
        ["세트상품", 463],
    ]);

    var options = {
        title: "7월 ",
        width: 500, // 원하는 차트의 너비
        height: 300, // 원하는 차트의 높이
        titleTextStyle: {
        fontSize: 14, // 원하는 글자 크기 (예: 18px)
         },
        colors: ["#FFB6C1", "#FFD700", "#c1ddb1", "#ADD8E6", "#FFA07A", "#e5d3f2"], // 파스텔 톤 색상 배열

    };

    var chart = new google.visualization.PieChart(
        document.getElementById("chart_shape2")  //이걸로div자리 정함
    );

    chart.draw(data, options);
}


