package com.example.wikipedia.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

public class LoadPropertiesFile {
	private static final Logger logger = LogManager.getLogger(LoadPropertiesFile.class);

	private Properties propertiesConfig;
	
	public LoadPropertiesFile(){
		
		propertiesConfig = new Properties();
	
		try{
			try(InputStream fileConfig = LoadPropertiesFile.class.getClassLoader().getResourceAsStream("application.properties")){
				propertiesConfig.load(fileConfig);
			}	
		}catch (Exception e){
			logger.info("Error in Loading PropertiesFile With Error Messages : "+e);
		}
		
	}
	
	public String getConfig(String property) {
		return propertiesConfig.getProperty(property);
	}
	
}