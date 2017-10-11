package domain;

public class WeatherInfo {
	
	private Double temperature;
	private String condition;
	private int humidity;

	public WeatherInfo(){
		
	}
	
	public WeatherInfo(Double temperature, String condition, int humidity) {
        this.temperature=temperature;
        this.condition=condition;
        this.humidity=humidity;
    }

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	
}
