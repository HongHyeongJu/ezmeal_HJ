//start: 총 주문 / 주문 접수 그래프
google.charts.load("current", {packages: ["bar"]}); // 지금 당장 load하는 패키지 *current 현재 버전
google.charts.setOnLoadCallback(drawOrderGraph); // 그래프 함수 수행 -> 1. data 2. option

function drawOrderGraph() {
    var data = google.visualization.arrayToDataTable([
        ['date', '주문', '주문접수'],
        ['8/6', 16, 16],
        ['8/7', 25, 25],
        ['8/8', 18, 18],
        ['8/9', 14, 14],
        ['8/10', 20, 20],
        ['8/11', 20, 15],
        ['8/12', 0, 0]
    ]);

    var options = {
        width: 450,
        height: 300,
        series: {
            0: {
                color: '#FFD700',
                axis: 'distance'
            },  // 주문 - 파스텔톤 색상 예시 (금색)
            1: {color: '#87CEEB'},  // 취소 - 파스텔톤 색상 예시 (하늘색)
            2: {color: '#FFA07A'}   // 반품 - 파스텔톤 색상 예시 (라이트 코랄)
        }
    };
    var chart = new google.visualization.ColumnChart(document.getElementById("chart_shape1"));
    chart.draw(data, options);
}
// 주간 주문 끝

// 주간 배송 관리 시작
google.charts.load("current", {packages: ["bar"]}); // 지금 당장 load하는 패키지 *current 현재 버전
google.charts.setOnLoadCallback(drawDeliveryGraph); // 그래프 함수 수행 -> 1. data 2. option

function drawDeliveryGraph() {
    var data = google.visualization.arrayToDataTable([
        ['date', '배송', '배송중', '배송완료'],
        ['8/6', 16, 0, 16],
        ['8/7', 25, 0, 25],
        ['8/8', 18, 0, 18],
        ['8/9', 14, 1, 13],
        ['8/10', 20, 15, 5],
        ['8/11', 20, 5, 0],
        ['8/12', 0, 0, 0]
    ]);

    var options = {
        width: 450,
        height: 300,
        series: {
            0: {
                color: '#FFD700',
                axis: 'distance'
            },  // 주문 - 파스텔톤 색상 예시 (금색)
            1: {color: '#87CEEB'},  // 취소 - 파스텔톤 색상 예시 (하늘색)
            2: {color: '#FFA07A'}   // 반품 - 파스텔톤 색상 예시 (라이트 코랄)
        }
    };
    var chart = new google.visualization.ColumnChart(document.getElementById("chart_shape2"));
    chart.draw(data, options);
}
// 주간 배송 관리 끝


// 주간 취소 관리 시작
google.charts.load("current", {packages: ["line", "corechart"]});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var chartDiv = document.getElementById("chart_shape3");

    var data = new google.visualization.DataTable();
    data.addColumn("string", "Date");
    data.addColumn("number", "취소");
    data.addColumn("number", "취소중");
    data.addColumn("number", "취소완료");

    data.addRows([
        ['8/6', 10, 0, 10],
        ['8/7', 13, 0, 13],
        ['8/8', 7, 0, 7],
        ['8/9', 9, 0, 9],
        ['8/10', 14, 2, 12],
        ['8/11', 6, 3, 0],
        ['8/12', 0, 0, 0]
    ]);

    var options = {
        width: 450,
        height: 300,
    };

    var chart = new google.visualization.LineChart(chartDiv);
    chart.draw(data, options);
}

// 주문 취소 관리 끝

// 반품 관리 시작
google.charts.load("current", {packages: ["line", "corechart"]});
google.charts.setOnLoadCallback(drawChartReturn);

function drawChartReturn() {
    var chartDiv = document.getElementById("chart_shape4");

    var data = new google.visualization.DataTable();
    data.addColumn("string", "Date");
    data.addColumn("number", "반품");
    data.addColumn("number", "반품중");
    data.addColumn("number", "반품철회");
    data.addColumn("number", "반품완료");

    data.addRows([
        ['8/6', 5, 0, 1, 4],
        ['8/7', 8, 0, 3, 5],
        ['8/8', 7, 0, 0, 7],
        ['8/9', 9, 0, 3, 6],
        ['8/10', 14, 4, 3, 5],
        ['8/11', 3, 2, 0, 0],
        ['8/12', 0, 0, 0, 0]
    ]);

    var options = {
        width: 450,
        height: 300,
    };

    var chart = new google.visualization.LineChart(chartDiv);
    chart.draw(data, options);
}
// 반품 관리 시작
