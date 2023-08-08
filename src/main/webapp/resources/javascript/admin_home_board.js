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