

for (var x = 0; x < volDataArr.length; x++) {
    volData += volDataArr[x] + "\n";
}

for (var i = 0; i < dataArr.length; i++) {
    parsData += dataArr[i] + "\n";
}


var chart = new CanvasJS.Chart("myChart", {
    animationEnabled: true,
    zoomEnabled: true,
    zoomType: "xy",
    theme: "light2",

    title: {
        text: symbolName
    },
    toolTip: {
        shared: true
    },
    axisY: {
        title: "price",
        includeZero: false,
        logarithmic: false,
        labelFontSize: 15
    },
    axisY2: {
        labelFontSize: 15,
        title: "Volume",
        includeZero: false,
        valueFormatString: "#,###,,.##M",
        logarithmic: false
    },
    axisX: {
        labelFontSize: 15,
    },
    data: [
        {}
    ]
});
chart.render();
var dps = [];


if (manySymbolsArr.length === 0) {
    if(chartType === "candle"){
        dps = getDataCandleFromCSV(parsData);
        var dataSeries = {
            type: "candlestick",
            name: "price",
            showInLegend: true,
            dataPoints: dps
        };
        var volSeries = {
            color: "#FF8800",
            type: "column",
            axisYType: "secondary",
            name: "volume",
            showInLegend: true,
            fillOpacity: 0.3,
            dataPoints: getDataLineFromCSV(volData)
        };

    }else{
        dps = getDataLineFromCSV(parsData);
        var dataSeries = {
            color: "#289AFF",
            type: "line",
            name: "price",
            showInLegend: true,
            dataPoints: dps
        };
        var volSeries = {
            color: "#FF8800",
            type: "column",
            axisYType: "secondary",
            name: "volume",
            showInLegend: true,
            fillOpacity: 0.3,
            dataPoints: getDataLineFromCSV(volData)
        };
    }
    chart.options.data.push(volSeries);
    chart.options.data.push(dataSeries);
    chart.render();
}


var dataLength = 390;



//https://stackoverflow.com/questions/11474422/deleting-both-values-from-array-if-duplicate-javascript-jquery
function onlyUnique(arr) {
    var counts = arr.reduce(function(counts, item) {
        counts[item] = (counts[item]||0)+1;
        return counts;
    }, {});
    return Object.keys(counts).reduce(function(arr, item) {
        if(counts[item] === 1) {
            arr.push(item);
        }
        return arr;
    }, []);
}

var symbols = 0;

for (var p = 0; p < manySymbolsArr.length; p++) {

    let dataArrs = manySymbolsArr[p].split("?");

    dataArrs.pop();

    let parsDatas = "";

    for (var l = 0; l < dataArrs.length; l++) {
        parsDatas += dataArrs[l] + "\n";
    }

    var newSeries = {
        type: "line",
        name: symbolsNameArr[symbols],
        showInLegend: true,
        dataPoints: getDataLineFromCSV(parsDatas)

    };
    symbols++;
    chart.options.data.push(newSeries);
    chart.render();
}