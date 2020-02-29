package DataParser.DataFormat;

import io.myfunstuff.stocks.PropertyValues;
import org.apache.wink.json4j.JSONException;

import java.io.IOException;
import java.text.DecimalFormat;

//class used by other classes to format data into usable data

public class DataFormatIn{


	//formats correctly data to import into mysql
	//final step of the import process

	public String updateDatabase(String symbol, String timeseries, String outputsize, PropertyValues propertyValues) throws IOException, JSONException{
		Parser parser = new Parser(symbol,timeseries, propertyValues);
		if(outputsize.equals("compact")){
			parser.setOutputsize(outputsize);
		}
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(3);
		parser.parseData();
		StringBuilder stringBuilder = parser.getFinalParsed();

		//example data
		//"'MSFT','2020-03-01', 100, 200, 300, 400, 500";
		stringBuilder.deleteCharAt(stringBuilder.length()-2);

		return stringBuilder.toString().trim();
	}

}
