google.charts.load("current", {packages: ["bar"]}); // 지금 당장 load하는 패키지 *current 현재 버전
google.charts.setOnLoadCallback(drawDualYChart); // 그래프 함수 수행 -> 1. data 2. option

function drawDualYChart() {
    var data = google.visualization.arrayToDataTable([
        ['월', '주문', '취소', '반품'],
        ['1월', 661, 213, 102],
        ['2월', 815, 221, 123],
        ['3월', 723, 165, 89],
        ['4월', 914, 213, 97],
        ['5월', 1030, 247, 132],
        ['6월', 1140, 301, 150],
        ['7월', 980, 250, 98],
        ['8월', 1030, 231, 101]
    ]);

    var options = {
        chart: {
            title: 'Company Performance',
            subtitle: 'Sales, Expenses, and Profit: 2014-2017',
        },

        width: 550,
        height: 300,
        // chartArea: {
        //     left: 50,
        //     top: 40,
        //     width: "70%",
        //     height: "70%",
        // },
        series: {
            0: {color: '#FFD700',
                axis: 'distance'},  // 주문 - 파스텔톤 색상 예시 (금색)
            1: {color: '#87CEEB'},  // 취소 - 파스텔톤 색상 예시 (하늘색)
            2: {color: '#FFA07A'}   // 반품 - 파스텔톤 색상 예시 (라이트 코랄)
        }
    };

    var chart = new google.visualization.ColumnChart(document.getElementById("chart_shape1"));
    chart.draw(data, options);
}
