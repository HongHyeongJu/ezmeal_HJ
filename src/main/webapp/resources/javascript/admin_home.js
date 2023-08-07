//start: 주문 그래프
google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawPieChart);

function drawPieChart() {
    var data = new google.visualization.DataTable();
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

    var options = {
        title: "Motivation Level Throughout the Day",
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
    var chart = new google.visualization.ColumnChart(
        document.getElementById("chart_shape1")
    );

    chart.draw(data, options);
}

//start: 상품 그래프
google.charts.load("current", { packages: ["line", "corechart"] });
google.charts.setOnLoadCallback(drawLineChart);

function drawLineChart() {
    var data = google.visualization.arrayToDataTable([
        ["Task", "Hours per Day"],
        ["Work", 11],
        ["Eat", 2],
        ["Commute", 2],
        ["Watch TV", 2],
        ["Sleep", 7],
    ]);

    var options = {
        title: "My Daily Activities",
        width: 500, // 원하는 차트의 너비
        height: 300, // 원하는 차트의 높이
    };

    var chart = new google.visualization.PieChart(
        document.getElementById("chart_shape2")
    );

    chart.draw(data, options);
}

//start: 회원 그래프
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

//start: 게시판 그래프
google.charts.load("current", { packages: ["table"] });
google.charts.setOnLoadCallback(drawTable);

function drawTable() {
    var data = new google.visualization.DataTable();
    data.addColumn("string", "Name");
    data.addColumn("number", "Salary");
    data.addColumn("boolean", "Full Time Employee");
    data.addRows([
        ["Mike", { v: 10000, f: "$10,000" }, true],
        ["Jim", { v: 8000, f: "$8,000" }, false],
        ["Alice", { v: 12500, f: "$12,500" }, true],
        ["Bob", { v: 7000, f: "$7,000" }, true],
    ]);

    var table = new google.visualization.Table(
        document.getElementById("chart_shape4")
    );

    table.draw(data, { showRowNumber: true, width: "500px", height: "300px" });
}
