package io.myfunstuff.stocks.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Map.Entry;

public class TimeSeriesData {
	
	private float open;
	private float close;
	private float high;
	private float low;
	private long volume;

	private static Logger log = LoggerFactory.getLogger(TimeSeriesData.class);
	
	public TimeSeriesData(float open, float close, float high, float low, long volume) {
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
		this.volume = volume;
	}
	
	public float getOpen() {
		return open;
	}
	
	public void setOpen(float open) {
		this.open = open;
	}

	public float getClose() {
		return close;
	}

	public void setClose(float close) {
		this.close = close;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}
	
	public static TimeSeriesData convertFromMap(Map<String, String> m) {
		
		if (m == null || m.isEmpty()) {
			log.error("Unable to get Time Series data from empty properties");
			return null;
		}
		
		float open = -1.0F;
		float close = -1.0F;
		float high = -1.0F;
		float low = -1.0F;
		long volume = -1L;
		for (Entry<String, String> e : m.entrySet()) {
			try {
				if (e.getKey().contains("open")) {
					open = Float.parseFloat(e.getValue());
				} else if (e.getKey().contains("close")) {
					close = Float.parseFloat(e.getValue());
				} else if (e.getKey().contains("high")) {
					high = Float.parseFloat(e.getValue());
				} else if (e.getKey().contains("low")) {
					low = Float.parseFloat(e.getValue());
				} else if (e.getKey().contains("volume")) {
					volume = Long.parseLong(e.getValue());
				}
			} catch (Exception ex) {
				log.error("Exception caught to convert Time Series data [ " + m + " ]", ex);
				return null;
			}
		}
		if (open < 0.0F || close < 0.0F || high < 0.0F || low < 0.0F || volume < 0L) {
			log.error("Unable to convert Time Series data [ " + m + " ]");
			return null;
		}
		return new TimeSeriesData(open, close, high, low, volume);
	}
}
