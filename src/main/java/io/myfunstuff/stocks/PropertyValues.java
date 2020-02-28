package io.myfunstuff.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Configurable
public class PropertyValues{
	@Value("${apikey}")
	private String apikey;

	@Value("${spring.datasource.username}")
	private String dataUser;

	@Value("${spring.datasource.password}")
	private String dataPass;

	@Value("${spring.datasource.url}")
	private String dataURL;

	@Value("${dataDatabase}")
	private String dataDatabase;

	public PropertyValues(){

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
