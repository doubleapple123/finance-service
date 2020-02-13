package DataParser.DataFormat;

import DataParser.DataFormat.Parser;
import org.apache.wink.json4j.JSONException;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

//class used by other classes to format data into usable data
//methods in here are not necessarily related but could be
public class DataFormatIn{
	private class StockModel{
		private String symbol;

		//low, volume, open, high, close
		private Map<LocalDate, ArrayList<Double>> symbolData; // data of a date and its correlated stockData
		private LocalDate timeDate;
		private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.ENGLISH);

		public StockModel(String symbol){
			this.symbol = symbol;
			symbolData = new TreeMap<>();
		}

		//setters

		public void setSymbol(String symbol){
			this.symbol = symbol;
		}

		public void setTimeDate(String date){
			timeDate = LocalDate.parse(date, format);
		}

		public String[] parseArray(String [] arr){
			String[] newArr = new String[arr.length];
			for(int i = 0; i < arr.length; i++){
				newArr[i] = arr[i].replaceAll("}", "").replaceAll("\\{", "");
				System.out.println(newArr[i]);
			}

			return newArr;
		}

		//functionality

		public void addStockData(Map<String, Object> dataMap){
			for(Map.Entry<String, Object> keys : dataMap.entrySet()){
				setTimeDate(keys.getKey());
				String [] priceData = parseArray(keys.getValue().toString().split(","));
				ArrayList<Double> priceArr = new ArrayList<>();

				String [] finArr;
				for(String pricePoint : priceData){
					finArr = pricePoint.split("=");
					Double value = Double.parseDouble(finArr[1]);
					priceArr.add(value);
				}
				symbolData.put(getTimeDate(), priceArr);
			}
		}

		//getters
		public Map<LocalDate, ArrayList<Double>> getSymbolData(){
			return symbolData;
		}

		public String getSymbol(){
			return symbol;
		}

		public LocalDate getTimeDate(){
			return timeDate;
		}
	}



	//gets data with Parser class
	public StockModel getData(String symbol, String timeseries) throws IOException, JSONException {
		Parser parser = new Parser(symbol, timeseries);
		StockModel model = new StockModel(parser.getSymbol());

		Map<String, Object> stockData = parser.parseData();
		model.addStockData(stockData);

		return model;
	}

	//formats correctly data to import into mysql
	//final step of the import process
	public String updateDatabase(String symbol, String timeseries) throws IOException, JSONException{
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(3);
		StringBuilder stringBuilder = new StringBuilder();

		StockModel stockModel = getData(symbol, timeseries);
		Map<LocalDate, ArrayList<Double>> outMap = stockModel.getSymbolData();
		//example data
		//"'MSFT','2020-03-01', 100, 200, 300, 400, 500";
		for(Map.Entry<LocalDate, ArrayList<Double>> entry : outMap.entrySet()){
			stringBuilder.append("(");
			stringBuilder.append("'" + stockModel.getSymbol() + "'," );
			stringBuilder.append("'" + entry.getKey().toString() + "'");


			for(Double arrayValues : entry.getValue()){
				stringBuilder.append(", " + df.format(arrayValues));
			}
			stringBuilder.append("),\n");

		}

		stringBuilder.deleteCharAt(stringBuilder.toString().length()-1);
		stringBuilder.deleteCharAt(stringBuilder.toString().length()-1);

		return stringBuilder.toString();
	}

}
