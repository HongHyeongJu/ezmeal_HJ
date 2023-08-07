google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawPieChart);

function drawPieChart() {
    const data = new google.visualization.DataTable();
    data.addColumn("string", "월");
    data.addColumn("number", "주문량");

    data.addRows([
        ["9", 120],
        ["10", 400],
        ["11", 660],
        ["12", 1000],
        ["1", 1200],
        ["2", 2000],
        ["3", 1300],
        ["4", 1600],
        ["5", 2000],
        ["6", 5000],
        ["7", 3000],
        ["8", 2600]
    ]);

    var options = {
        title: "월별 주문량",
        hAxis: {
            title: "2022년 9월 ~ 2023년 8월",
            titleTextStyle: {
                color: "#333",
                fontSize: 16,
            },
        },
        vAxis: {
            title: "주문 량",
            titleTextStyle: {
                color: "#333",
                fontSize: 14,
            },
            minValue: 0,
            gridlines: {
                color: "transparent",
            },
        },
        colors: ["rgb(0, 199, 40)"],
        width: 550,
        height: 300,
        legend: {
            position: "none", // 범례 숨김
        },
        chartArea: {
            left: 50,
            top: 40,
            width: "85%",
            height: "70%",
        },
    };

    var chart = new google.visualization.ColumnChart(
        document.getElementById("chart_shape1")
    );

    chart.draw(data, options);
}
