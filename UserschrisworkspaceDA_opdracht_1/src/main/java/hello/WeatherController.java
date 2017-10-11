package hello;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import db.WeatherDB;
import domain.Example;
import domain.WeatherInfo;

@RestController
public class WeatherController {

	
	@RequestMapping("/weather")
    public WeatherInfo weather(@RequestParam(value="city", defaultValue="leuven") String city) {
		
		Example result = WeatherDB.getInstance().getLast(city);
		
        return new WeatherInfo((double) Math.round(result.getMain().getTemp()-273.15) , result.getWeather().get(0).getDescription() , result.getMain().getHumidity());
    }
	
	@RequestMapping("/avg")
    public WeatherInfo avg(@RequestParam(value="city", defaultValue="leuven") String city,@RequestParam(value="days", defaultValue="7") int days) throws IllegalArgumentException {
		
		ArrayList<Example> lijst = new ArrayList<Example>();
		
		if(days<0){
			throw new IllegalArgumentException("Days entered to calculate the average values cannot be <0");
		}
		else if(days==0){
			lijst=(ArrayList<Example>) WeatherDB.getInstance().getAll(city);
		}
		else{
			lijst = (ArrayList<Example>) WeatherDB.getInstance().getAll(city, days);
		}
		
		double totalTemp=0.0;
		int totalHumidity=0;
		double avgTemp=0.0;
		int avgHumidity=0;
		
		for(Example x:lijst){
			totalTemp+=(x.getMain().getTemp()-273.15);
			totalHumidity+=x.getMain().getHumidity();
		}
		
		if(lijst.size()!=0){
			avgTemp=(double)Math.round(totalTemp/lijst.size());
			avgHumidity=(int)Math.round(totalHumidity/lijst.size());
		}
						
        return new WeatherInfo(avgTemp,"average values",avgHumidity);
    }
	

}
