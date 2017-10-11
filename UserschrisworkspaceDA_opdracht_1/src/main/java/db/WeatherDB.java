package db;

import java.util.ArrayList;
import java.util.List;


import domain.Example;
import domain.Main;
import domain.Weather;

public class WeatherDB {

	private ArrayList<Example> weatherObjects;
	private static WeatherDB singleton;


	private WeatherDB() {
		weatherObjects = new ArrayList<Example>();
		addSomeData();
	}

	//Adding a weather data object of "Rotselaar" with temp=27°C , humidity=50%, description="lekker zonnig" and date=6 days ago
	private void addSomeData() {
		
		Example n = new Example();
		n.setName("rotselaar");
		Main nMain = new Main();
		nMain.setHumidity(50);
		nMain.setTemp(300.00);
		Weather nWeather = new Weather();
		nWeather.setDescription("lekker zonnig");
		n.setMain(nMain);
		int time = (int) ((System.currentTimeMillis()-(6*86400*1000))/1000);
		n.setDt(time);
		List<Weather> nWeatherList = new ArrayList<Weather>();
		nWeatherList.add(nWeather);
		n.setWeather(nWeatherList);
		weatherObjects.add(n);
	}

	public static synchronized WeatherDB getInstance() {
		if (singleton == null)
			singleton = new WeatherDB();
		return singleton;
	}

	public void addWeatherObject(Example wObject) {
		weatherObjects.add(wObject);
	}

	public int getTotal() {
		return weatherObjects.size();
	}

	public List<Example> getList() {
		return weatherObjects;
	}

	public Example getLast(String city) {
		Example result = null;
		List<Example> listCity = new ArrayList<Example>();

		boolean contains = containsCaseInsensitive(city, GemeentenDB.getInstance().getGemeenten());

		if (contains) {
			for (Example ex : getList()) {
				if (ex.getName().equalsIgnoreCase(city)) {
					listCity.add(ex);
				}
			}
			result = listCity.get(listCity.size() - 1);
		}

		return result;
	}

	public List<Example> getAll(String city) {

		List<Example> lijst = new ArrayList<Example>();

		boolean contains = containsCaseInsensitive(city, GemeentenDB.getInstance().getGemeenten());

		if (contains) {
			for (Example ex : getList()) {
				if (ex.getName().equalsIgnoreCase(city)) {
					lijst.add(ex);
				}
			}
		}
		
		return lijst;

	}
	
	public List<Example> getAll(String city,int days) {


		long startTime = (System.currentTimeMillis()-(days*86400*1000))/1000L;
		long endTime = System.currentTimeMillis()/1000L;
		
		List<Example> lijst = new ArrayList<Example>();

		boolean contains = containsCaseInsensitive(city, GemeentenDB.getInstance().getGemeenten());

		if (contains) {
			for (Example ex : getList()) {
				if (ex.getName().equalsIgnoreCase(city)) {
					if(ex.getDt()>startTime && ex.getDt()<endTime){
						lijst.add(ex);
					}
				}
			}
		}
		
		return lijst;

	}

	public boolean containsCaseInsensitive(String s, List<String> l) {
		for (String string : l) {
			if (string.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}
}
