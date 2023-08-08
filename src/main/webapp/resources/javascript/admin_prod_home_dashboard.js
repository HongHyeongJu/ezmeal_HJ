

/*--------------------- [1] 카테고리별 상품 개수 :막대그래프 --------------------------------------------------------*/

google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawBarChart);

function drawBarChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Category');
    data.addColumn('number', '상품');
    data.addColumn({type: 'string', role: 'style'});

    data.addRows([
        ['닭가슴살', 9, '#FFB6C1'],
        ['스테이크', 5, '#FFB6C1'],
        ['소시지', 6, '#FFB6C1'],
        ['도시락', 12, '#FFD700'],
        ['볶음밥', 7, '#FFD700'],
        ['샐러드', 14, '#c1ddb1'],
        ['소스', 9, '#c1ddb1'],
        ['채소/과일', 16, '#ADD8E6'],
        ['달걀', 8, '#ADD8E6'],
        ['분식', 8, '#FFA07A'],
        ['반찬/밀키트', 16, '#FFA07A'],
        ['면', 8, '#FFA07A'],
        ['세트상품', 13, '#e5d3f2']
    ]);

    var options = {
        title: '상품 카테고리별 개수',
        hAxis: {
            title: '개수',
        },
        vAxis: {
            title: '카테고리',
        },
        width: 500,
        height: 300,
        series: {
            0: {color: '#FFD700'}
        }
    };

    var chart = new google.visualization.BarChart(document.getElementById('chart_shape1'));
    chart.draw(data, options);
}

/*--------------------- [2] 카테고리별 판매량 :원형 그래프-----------------------------------------------------*/


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
        document.getElementById("chart_shape2")
    );

    chart.draw(data, options);
}


/*-------------------- [3] 판매 인기상품 : 표 그래프 -------------------------------------------------*/


google.charts.load("current", { packages: ["table"] });
google.charts.setOnLoadCallback(drawTable1);

function drawTable1() {
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
        document.getElementById("chart_shape3")
    );

    table.draw(data, { showRowNumber: true, width: "500px", height: "200px" });
}

/*---------------------- [4] 재고 현황 : 표그래프 ------------------------------------------------------*/

google.charts.load("current", { packages: ["table"] });
google.charts.setOnLoadCallback(drawTable2);

function drawTable2() {
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


/*---------------------- [5] 이벤트 상품 판매 추이 : 꺾은선 그래프 ------------------------------------------*/


google.charts.load("current", { packages: ["line", "corechart"] });
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var chartDiv = document.getElementById("chart_shape5");

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


/*---------------------- [6] 입고 예정 상품 : 표그래프 ------------------------------------------------------*/

google.charts.load("current", { packages: ["table"] });
google.charts.setOnLoadCallback(drawTable3);

function drawTable3() {
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
        document.getElementById("chart_shape6")
    );

    table.draw(data, { showRowNumber: true, width: "500px", height: "300px" });
}


/*---------------------- [7]   [8]   [9]  ------------------------------------------------------*/


google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawBarChart2);

function drawBarChart2() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Category');
    data.addColumn('number', 'Quantity');
    data.addColumn({type: 'string', role: 'style'});

    data.addRows([
        ['닭가슴살', 11, '#FFB6C1'],
        ['스테이크', 5, '#FFB6C1'],
        ['소시지', 6, '#FFB6C1'],
        ['도시락', 12, '#FFD700'],
        ['볶음밥', 7, '#FFD700'],
        ['샐러드', 14, '#c1ddb1'],
        ['소스', 9, '#c1ddb1'],
        ['채소/과일', 16, '#ADD8E6'],
        ['달걀', 8, '#ADD8E6'],
        ['분식', 8, '#FFA07A'],
        ['반찬/밀키트', 16, '#FFA07A'],
        ['면', 8, '#FFA07A'],
        ['세트상품', 13, '#e5d3f2']
    ]);

    var options = {
        title: '상품 카테고리별 개수',
        hAxis: {
            title: '개수',
        },
        vAxis: {
            title: '카테고리',
        },
        width: 500,
        height: 300,
    };

    var chart = new google.visualization.BarChart(document.getElementById('chart_shape7'));
    chart.draw(data, options);
}

//----------------------------------

google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawBarChart3);

function drawBarChart3() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Category');
    data.addColumn('number', 'Quantity');
    data.addColumn({type: 'string', role: 'style'});

    data.addRows([
        ['닭가슴살', 11, '#FFB6C1'],
        ['스테이크', 5, '#FFB6C1'],
        ['소시지', 6, '#FFB6C1'],
        ['도시락', 12, '#FFD700'],
        ['볶음밥', 7, '#FFD700'],
        ['샐러드', 14, '#c1ddb1'],
        ['소스', 9, '#c1ddb1'],
        ['채소/과일', 16, '#ADD8E6'],
        ['달걀', 8, '#ADD8E6'],
        ['분식', 8, '#FFA07A'],
        ['반찬/밀키트', 16, '#FFA07A'],
        ['면', 8, '#FFA07A'],
        ['세트상품', 13, '#e5d3f2']
    ]);

    var options = {
        title: '상품 카테고리별 개수',
        hAxis: {
            title: '개수',
        },
        vAxis: {
            title: '카테고리',
        },
        width: 500,
        height: 300,
    };

    var chart = new google.visualization.BarChart(document.getElementById('chart_shape8'));
    chart.draw(data, options);
}
//--------------------------------
google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawBarChart4);

function drawBarChart4() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Category');
    data.addColumn('number', 'Quantity');
    data.addColumn({type: 'string', role: 'style'});

    data.addRows([
        ['닭가슴살', 11, '#FFB6C1'],
        ['스테이크', 5, '#FFB6C1'],
        ['소시지', 6, '#FFB6C1'],
        ['도시락', 12, '#FFD700'],
        ['볶음밥', 7, '#FFD700'],
        ['샐러드', 14, '#c1ddb1'],
        ['소스', 9, '#c1ddb1'],
        ['채소/과일', 16, '#ADD8E6'],
        ['달걀', 8, '#ADD8E6'],
        ['분식', 8, '#FFA07A'],
        ['반찬/밀키트', 16, '#FFA07A'],
        ['면', 8, '#FFA07A'],
        ['세트상품', 13, '#e5d3f2']
    ]);

    var options = {
        title: '상품 카테고리별 개수',
        hAxis: {
            title: '개수',
        },
        vAxis: {
            title: '카테고리',
        },
        width: 500,
        height: 300,
    };

    var chart = new google.visualization.BarChart(document.getElementById('chart_shape9'));
    chart.draw(data, options);
}

//----------------------