

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

if(timeSeries === "TIME_SERIES_INTRADAY"){

}

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

//d3
var data = d3CandleFromCSV(parsData);

var yExtent = fc.extentLinear()
    .accessors([
        function(d) { return d.high; },
        function(d) { return d.low; }
    ]);

var xExtent = fc.extentDate()
    .accessors([function(d) { return d.date; }]);

var gridlines = fc.annotationSvgGridline();
var candlestick = fc.seriesSvgCandlestick();
var multi = fc.seriesSvgMulti()
    .series([gridlines, candlestick]);

var mychart = fc.chartCartesian(
    fc.scaleDiscontinuous(d3.scaleTime()),
    d3.scaleLinear()
)
    .yDomain(yExtent(data))
    .xDomain(xExtent(data))
    .svgPlotArea(multi);

d3.select('#chart')
    .datum(data)
    .call(mychart);


//end d3

var dataLength = 390;
var newData = [];

var updateChart = function() {
    var oldData = dps;
    oldData.push.apply(dps, getDataLineFromCSV(parsData));
    newData = onlyUnique(oldData);

    if(timeSeries === "TIME_SERIES_INTRADAY")
    {
        if(chartType === "candle"){
            dataSeries.push();

        }else if (chartType === "line"){
            dps.push(getDataLineFromCSV(newData));
        }

        if(dps.length > dataLength){
            dps.shift();
        }
        chart.render();
    }
};
// setInterval(function(){updateChart()}, 1000);

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


for (var p = 0; p < manySymbolsArr.length; p++) {

    let dataArrs = manySymbolsArr[p].split(":");

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