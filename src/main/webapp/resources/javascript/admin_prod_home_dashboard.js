

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
    data.addColumn("string", "상품");
    data.addColumn("string", "카테고리");
    data.addColumn("number", "낱개상품 공급가");
    data.addRows([
        ["떡볶이", "분식", 2500],
        ["떡볶이", "분식", 2500],
        ["떡볶이", "분식", 2500],
        ["떡볶이", "분식", 2500],
        ["떡볶이", "분식", 2500],
    ]);

    var table = new google.visualization.Table(
        document.getElementById("chart_shape3")
    );
    var numColumns = data.getNumberOfColumns();
    var tableWidth = numColumns * 150; // Adjust the column width as needed

    table.draw(data, {
        showRowNumber: true,
        width: tableWidth + "px",
        height: "200px",
        cssClassNames: {
            headerCell: 'center-align',
            tableCell: 'center-text' // Custom CSS class for center-aligned text
        }
    });

    table.draw(data, options);

}

/*---------------------- [4] 재고 현황 : 표그래프 ------------------------------------------------------*/

google.charts.load("current", { packages: ["table"] });
google.charts.setOnLoadCallback(drawTable2);

function drawTable2() {
    var data = new google.visualization.DataTable();
    data.addColumn("string", "상품코드"); // Use lowercase 'string'
    data.addColumn("string", "상품명");   // Use lowercase 'string'
    data.addColumn("string", "재고 수량 - 재고 상태");
    data.addRows([
        ["P0031233", "품절상품이름", " 0 - 위험 "],
        ["P0031233", "품절상품이름", " 0 - 위험 "],
        ["P0031233", "품절상품이름", " 27 - 주의 "],
        ["P0031233", "품절상품이름", " 35 - 주의 "],
        ["P0031233", "품절상품이름", " 45 - 주의 "],
        ["P0031233", "품절상품이름", " 48 - 주의 "],
    ]);

    var table = new google.visualization.Table(
        document.getElementById("chart_shape4")
    );

    var options = {
        showRowNumber: true,
        width: "100%", // Change width to 100%
        height: "200px",
        cssClassNames: {
            headerCell: 'center-align',
            tableCell: 'center-text' // Custom CSS class for center-aligned text
        }
    };

    table.draw(data, options);
}



/*---------------------- [5] 이벤트 상품 판매 추이 : 꺾은선 그래프 ------------------------------------------*/

google.charts.load("current", { packages: ["line", "corechart"] });
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var chartDiv = document.getElementById("chart_shape5");

    var data = new google.visualization.DataTable();
    data.addColumn("date", "Date");
    data.addColumn("number", "A세트");
    data.addColumn("number", "B세트");

    data.addRows([
        [new Date(2023, 6, 1), 5, 2],
        [new Date(2023, 6, 10), 16, 13],
        [new Date(2023, 6, 21), 11, 14],
        [new Date(2023, 6, 30), 29, 35],
        [new Date(2023, 7, 10), 31, 26],
        [new Date(2023, 7, 21), 55, 37],
        [new Date(2023, 7, 31), 56, 30],
        [new Date(2023, 8, 9), 48, 50],
        // Add more data points for each day
    ]);

    var options = {
        chart: {
            title: "Event Product Sales",
        },
        width: 500,
        height: 300,
        series: {
            0: { color: "blue" }, // Product A
            1: { color: "red" },  // Product B
        },
        hAxis: {
            format: "M/d", // Format for x-axis labels
        },
        vAxis: {
            title: "Sales",
        },
    };

    var chart = new google.visualization.LineChart(chartDiv);
    chart.draw(data, options);
}



/*---------------------- [6] 입고 예정 상품 : 표그래프 ------------------------------------------------------*/
google.charts.load("current", { packages: ["table"] });
google.charts.setOnLoadCallback(drawTable3);

function drawTable3() {
    var data = new google.visualization.DataTable();
    data.addColumn("string", "상품명");
    data.addColumn("string", "카테고리");
    data.addColumn("string", "거래처 : 거래예정일");
    data.addRows([
        ["천도복숭아", "과일", " 농부의 마음 : 2023-08-27 "],
        ["로제 떡볶이 밀키트", "밀키트", " 대림당 : 2023-08-28 "],
        ["3과일 요거트 소스", "소스", " 요거요트 : 2023-09-03 "],
        ["큐브 아이스 홍시", "과일", " 아이셔스 : 2023-09-07 "],
        ["청양야채볶음밥", "볶음밥", " 담담볶 : 2023-09-13 "],
    ]);

    var table = new google.visualization.Table(
        document.getElementById("chart_shape6")
    );

    var options = {
        showRowNumber: true,
        width: "100%", // Change width to 100%
        height: "200px",
        cssClassNames: {
            headerCell: 'center-align',
            tableCell: 'center-text' // Custom CSS class for center-aligned text
        }
    };

    table.draw(data, options); // This line was missing in your code
}

