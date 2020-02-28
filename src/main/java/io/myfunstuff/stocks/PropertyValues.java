package io.myfunstuff.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PropertyValues{
	private final String apikey;
	private final String dataUser;
	private final String dataPass;
	private final String dataURL;
	private final String dataDatabase;

	public PropertyValues(@Value("${apikey}") String apikey,
						  @Value("${spring.datasource.username}") String dataUser,
						  @Value("${spring.datasource.password}") String dataPass,
						  @Value("${spring.datasource.url}") String dataURL,
						  @Value("${dataDatabase}") String dataDatabase){
		this.apikey = apikey;
		this.dataUser = dataUser;
		this.dataPass = dataPass;
		this.dataURL = dataURL;
		this.dataDatabase = dataDatabase;
	}


	public String getApikey(){
		return apikey;
	}


	public String getDataUser(){
		return dataUser;
	}

	public String getDataPass(){
		return dataPass;
	}

	public String getDataURL(){
		return dataURL;
	}

	public String getDataDatabase(){
		return dataDatabase;
	}
}
