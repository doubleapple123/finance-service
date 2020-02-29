function getDataLineFromCSV(csv) {
    var dataPoints = csvLines = points = [];
    csvLines = csv.split(/[\r?\n|\r|\n]+/);

    for (var i = 0; i < csvLines.length; i++)
        if (csvLines[i].length > 0) {
            points = csvLines[i].split(",");
            var hereTime = new Date(points[0]).toLocaleString("en-US", {timeZone: "UTC"});
            hereTime = new Date(hereTime);
            dataPoints.push({
                x: hereTime,
                y: parseFloat(points[1])
            });
        }
    return dataPoints;
}


function getDataCandleFromCSV(csv) {
    var dataPoints = csvLines = points = [];
    csvLines = csv.split(/[\r?\n|\r|\n]+/);
    for (var i = 0; i < csvLines.length; i++) {
        if (csvLines[i].length > 0) {
            points = csvLines[i].split(",");

            var hereTime = new Date(points[0]).toLocaleString("en-US", {timeZone: "UTC"});
            hereTime = new Date(hereTime);

            dataPoints.push({
                x: hereTime,
                y: [
                    parseFloat(points[1]),
                    parseFloat(points[2]),
                    parseFloat(points[3]),
                    parseFloat(points[4])
                ]
            });
        }
    }
    return dataPoints;
}

function d3CandleFromCSV(csv) {
    var dataPoints = csvLines = points = [];
    csvLines = csv.split(/[\r?\n|\r|\n]+/);
    for (var i = 0; i < csvLines.length; i++) {
        if (csvLines[i].length > 0) {
            points = csvLines[i].split(",");

            var hereTime = new Date(points[0]).toLocaleString("en-US", {timeZone: "UTC"});
            hereTime = new Date(hereTime);

            dataPoints.push({
                date: hereTime,
                open: parseFloat(points[1]),
                high : parseFloat(points[2]),
                low: parseFloat(points[3]),
                close: parseFloat(points[4]),
            });
        }
    }
    return dataPoints;
}