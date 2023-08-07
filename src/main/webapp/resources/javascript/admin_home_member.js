//start: 회원 그래프

google.charts.load("current", { packages: ["line", "corechart"] });
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var chartDiv = document.getElementById("chart_shape3");

    var data = new google.visualization.DataTable();
    data.addColumn("date", "Month");
    data.addColumn("number", "Average Temperature");
    data.addColumn("number", "Average Hours of Daylight");

    data.addRows([
        [new Date(2014, 0), -0.5, 5.7],
        [new Date(2014, 1), 0.4, 8.7],
        [new Date(2014, 2), 0.5, 12],
        [new Date(2014, 3), 2.9, 15.3],
        [new Date(2014, 4), 6.3, 18.6],
        [new Date(2014, 5), 9, 20.9],
        [new Date(2014, 6), 10.6, 19.8],
        [new Date(2014, 7), 10.3, 16.6],
        [new Date(2014, 8), 7.4, 13.3],
        [new Date(2014, 9), 4.4, 9.9],
        [new Date(2014, 10), 1.1, 6.6],
        [new Date(2014, 11), -0.2, 4.5],
    ]);

    var options = {
        chart: {
            title: "Average Temperatures and Daylight in Iceland Throughout the Year",
        },
        width: 500, // 원하는 차트의 너비
        height: 300, // 원하는 차트의 높이
    };

    var chart = new google.visualization.LineChart(chartDiv);
    chart.draw(data, options);
}