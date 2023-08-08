//start: 회원 그래프

google.charts.load("current", { packages: ["line", "corechart"] });
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var chartDiv = document.getElementById("chart_shape3");

    var data = new google.visualization.DataTable();
    data.addColumn("date", "Month");
    data.addColumn("number", "회원가입");
    data.addColumn("number", "접속자");

    data.addRows([
        [new Date(2023, 0), 530, 700],
        [new Date(2023, 1), 340, 1300],
        [new Date(2023, 2), 320, 1130],
        [new Date(2023, 3), 410, 1350],
        [new Date(2023, 4), 370, 1570],
        [new Date(2023, 5), 564, 1790],
        [new Date(2023, 6), 450, 1456],
        [new Date(2023, 7), 645, 1653],
        // [new Date(2023, 8), 7.4, 13.3],
        // [new Date(2023, 9), 4.4, 9.9],
        // [new Date(2023, 10), 1.1, 6.6],
        // [new Date(2023, 11), -0.2, 4.5],
    ]);

    var formatter = new google.visualization.DateFormat({
        pattern: 'M월',
        timeZone: 'UTC',  // 필요에 따라 타임존을 조정하세요
    });

    formatter.format(data,0);
    formatter.format(data,1);
    formatter.format(data,2);



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