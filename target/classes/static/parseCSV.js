function getDataPointsFromCSV(csv) {
    var dataPoints = csvLines = points = [];
    csvLines = csv.split(/[\r?\n|\r|\n]+/);

    for (var i = 0; i < csvLines.length; i++)
        if (csvLines[i].length > 0) {
            points = csvLines[i].split(",");
            var hereTime = new Date(points[0]).toLocaleString("en-US", {timeZone: "Australia/Brisbane"});
            hereTime = new Date(hereTime);
            dataPoints.push({
                x: hereTime,
                y: parseFloat(points[1])
            });
        }
    return dataPoints;
}

