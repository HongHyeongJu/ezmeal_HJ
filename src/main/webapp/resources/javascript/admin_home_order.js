//start: 주문 그래프
google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawPieChart);

function drawPieChart() {
    const data = new google.visualization.DataTable();
    data.addColumn("timeofday", "Time of Day");
    data.addColumn("number", "Motivation Level");

    data.addRows([
        [{ v: [8, 0, 0], f: "8 am" }, 1],
        [{ v: [9, 0, 0], f: "9 am" }, 2],
        [{ v: [10, 0, 0], f: "10 am" }, 3],
        [{ v: [11, 0, 0], f: "11 am" }, 4],
        [{ v: [12, 0, 0], f: "12 pm" }, 5],
        [{ v: [13, 0, 0], f: "1 pm" }, 6],
        [{ v: [14, 0, 0], f: "2 pm" }, 7],
        [{ v: [15, 0, 0], f: "3 pm" }, 8],
        [{ v: [16, 0, 0], f: "4 pm" }, 9],
        [{ v: [17, 0, 0], f: "5 pm" }, 10],
    ]);

    const options = {
        title: "금일 주문 현황",
        hAxis: {
            title: "Time of Day",
            format: "h:mm a",
            viewWindow: {
                min: [7, 30, 0],
                max: [17, 30, 0],
            },
        },
        vAxis: {
            title: "Rating (scale of 1-10)",
        },

        width: 500, // 원하는 차트의 너비
        height: 300, // 원하는 차트의 높이
    };
    const chart = new google.visualization.ColumnChart(
        document.getElementById("chart_shape1")
    );

    chart.draw(data, options);
}