package io.myfunstuff.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PropertyValues{
	public final String apikey;
	public final String dataUser;
	public final String dataPass;
	public final String dataURL;
	public final String dataDatabase;

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
		System.out.printf("Property apikey: %s\n", apikey);
		return apikey;
	}

	public String getDataUser(){
		System.out.printf("Property dataUser  : %s\n", dataUser);
		return dataUser;
	}

	public String getDataPass(){
		System.out.printf("Property dataPass: %s\n", dataPass);
		return dataPass;
	}

	public String getDataURL(){
		System.out.printf("Property dataURL  : %s\n", dataURL  );
		return dataURL;
	}

	public String getDataDatabase(){
		System.out.printf("Property dataDatabase  : %s\n", dataDatabase);
		return dataDatabase;
	}
//
//	public String getApikey(){
//		return apikey;
//	}
//
//	public String getDataUser(){
//		return dataUser;
//	}
//
//	public String getDataPass(){
//		return dataPass;
//	}
//
//	public String getDataURL(){
//		return dataURL;
//	}
//
//	public String getDataDatabase(){
//		return dataDatabase;
//	}
}
