package hello;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import db.GemeentenDB;
import db.WeatherDB;
import domain.Example;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ApplicationOpdracht1.class);
	private String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private String URL="";
	private static final String APP_ID = "&APPID=1e8028a880282ab1184a24aea638625f";
	
    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
    	log.info("GATHERING DATA");
    	Example jsonExample = new Example();
    	RestTemplate restTemplate = new RestTemplate();
    	
    	
    	for(String gemeente: GemeentenDB.getInstance().getGemeenten()){
    		log.info("FOR CITY:" + gemeente +".");
    		
    		URL = BASE_URL + gemeente + APP_ID; 
    		
    		try{
				jsonExample = restTemplate.getForObject(URL, Example.class);
				
				log.info("----------------------------------------");
				log.info("| Condition: " + jsonExample.getWeather().get(0).getDescription());
				log.info("| Humidity: " + jsonExample.getMain().getHumidity() + " %");
				log.info("----------------------------------------");
				
				WeatherDB.getInstance().addWeatherObject(jsonExample);

    		}
    		catch (HttpClientErrorException e){
    			log.info("----------------------------------------");
    			log.info("| ERROR = " + e.getMessage());
    			log.info("----------------------------------------");
    		}
    	}
    	

    	
    }
}
