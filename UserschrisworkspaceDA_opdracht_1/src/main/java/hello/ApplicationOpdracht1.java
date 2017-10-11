package hello;

import java.util.List;
import java.util.Scanner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import db.GemeentenDB;
import db.WeatherDB;

import domain.WeatherInfo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@EnableScheduling
@SpringBootApplication
public class ApplicationOpdracht1 {

	private static final Logger log = LoggerFactory.getLogger(ApplicationOpdracht1.class);
	private String URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static final String APP_ID = "&APPID=3e387d51f2a790ddc177512b8c8e9d24";
	
	private GemeentenDB gemeentenDB;
	private WeatherDB weatherDB;
	
	
	
	public static void main(String args[]) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ApplicationOpdracht1.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
		
		//SpringApplication.run(ApplicationOpdracht1.class);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		
		createGui();
		
		return args -> {
			
			gemeentenDB = GemeentenDB.getInstance();			
			weatherDB = WeatherDB.getInstance();
			
		};
	}
	
	
	public void createGui(){
		JFrame guiFrame = new JFrame();
		
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Weather");
		guiFrame.setSize(300,300);
		
		GridLayout experimentLayout = new GridLayout(0,1);
		guiFrame.setLayout(experimentLayout);
		
		guiFrame.setLocationRelativeTo(null);
		
		final JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0,1));
		JLabel cityLabel = new JLabel("City: ");
		JTextField cityField = new JTextField("",10);
		JButton button = new JButton("Search");
		
		mainPanel.add(cityLabel);
		mainPanel.add(cityField);
		mainPanel.add(button);
		
		final JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(0,1));
		
		final JPanel infoPanelTemperature = new JPanel();
		JLabel infoTemperatureLabel = new JLabel("Temperature:");
		JLabel infoTemperature = new JLabel("");
		infoPanelTemperature.add(infoTemperatureLabel);
		infoPanelTemperature.add(infoTemperature);
		final JPanel infoPanelCondition = new JPanel();
		JLabel infoConditionLabel = new JLabel("Condition:");
		JLabel infoCondition = new JLabel("");
		infoPanelCondition.add(infoConditionLabel);
		infoPanelCondition.add(infoCondition);
		final JPanel infoPanelHumidity = new JPanel();
		JLabel infoHumidityLabel = new JLabel("Humidity:");
		JLabel infoHumidity = new JLabel("");
		infoPanelHumidity.add(infoHumidityLabel);
		infoPanelHumidity.add(infoHumidity);

		
		infoPanel.add(infoPanelTemperature);
		infoPanel.add(infoPanelCondition);
		infoPanel.add(infoPanelHumidity);
		
		final JPanel infoPanelAvg = new JPanel();
		infoPanelAvg.setLayout(new GridLayout(0, 1));
		
		final JPanel infoPanelDays = new JPanel();
		JLabel infoDays = new JLabel("Avg of last number of days:");
		JTextField numberOfDays = new JTextField("",4);
		JButton avgButton = new JButton("Calc");
		infoPanelDays.add(infoDays);
		infoPanelDays.add(numberOfDays);
		infoPanelDays.add(avgButton);
		
		final JPanel infoPanelTempAvg = new JPanel();
		JLabel infoTempAvgLabel = new JLabel("Avg Temperature:");
		JLabel infoTempAvg = new JLabel("");
		infoPanelTempAvg.add(infoTempAvgLabel);
		infoPanelTempAvg.add(infoTempAvg);
		
		final JPanel infoPanelHumidityAvg = new JPanel();
		JLabel infoHumidityAvgLabel = new JLabel("Avg Humidity:");
		JLabel infoHumidityAvg = new JLabel("");
		infoPanelHumidityAvg.add(infoHumidityAvgLabel);
		infoPanelHumidityAvg.add(infoHumidityAvg);
		
		infoPanelAvg.add(infoPanelDays);
		infoPanelAvg.add(infoPanelTempAvg);
		infoPanelAvg.add(infoPanelHumidityAvg);
		
		
		guiFrame.add(mainPanel);
		guiFrame.add(infoPanel);
		guiFrame.add(infoPanelAvg);
		
		infoPanel.setVisible(false);
		infoPanelAvg.setVisible(false);
		mainPanel.setVisible(true);
		guiFrame.setVisible(true);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String city = cityField.getText();
				boolean containsCity = containsCaseInsensitive(city, gemeentenDB.getGemeenten());
				RestTemplate restTemplate = new RestTemplate();
				WeatherInfo weatherInfo = null;
				String url_api="http://localhost:8080/weather?city=";
				if(containsCity){
					
					url_api += city;
					try{
						weatherInfo = restTemplate.getForObject(url_api, WeatherInfo.class);

					}
					catch (Exception exception){
						log.info("----------------------------------------");
						log.info("| ERROR " + exception.getMessage() );
						log.info("----------------------------------------");
					}
					
					infoCondition.setText(weatherInfo.getCondition());
					infoTemperature.setText(weatherInfo.getTemperature().toString() + " °C");
					String humidity = String.valueOf(weatherInfo.getHumidity());
					infoHumidity.setText(humidity + " %");
					infoPanel.setVisible(true);
					infoPanelAvg.setVisible(true);
				}
				
				else{
					JOptionPane.showMessageDialog(guiFrame,
						    "The city cannot be found in the database.",
						    "City not found",
						    JOptionPane.ERROR_MESSAGE);
					infoPanel.setVisible(false);
					infoPanelAvg.setVisible(false);
				}
				
				
			}
		});
		
		avgButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String city = cityField.getText();
				int days = 0;
				boolean containsCity = containsCaseInsensitive(city, gemeentenDB.getGemeenten());
				RestTemplate restTemplate = new RestTemplate();
				WeatherInfo weatherInfo = null;
				String url_api="http://localhost:8080/avg?city=";
				if(containsCity){
					
										
					try{
						days = Integer.parseInt(numberOfDays.getText());
						if(days<0){
							throw new IllegalArgumentException("Days entered to calculate the average values cannot be <0");
						}
						url_api+=city + "&days=" + days;
						weatherInfo = restTemplate.getForObject(url_api, WeatherInfo.class);
						infoTempAvg.setText(weatherInfo.getTemperature().toString() + " °C");
						String humidity = String.valueOf(weatherInfo.getHumidity());
						infoHumidityAvg.setText(humidity + " %");
						infoPanel.setVisible(true);
						infoPanelAvg.setVisible(true);

					}catch(NumberFormatException exception){
						JOptionPane.showMessageDialog(guiFrame,
							    "The number of days entered has to be a valid number format.",
							    "Bad number format exception",
							    JOptionPane.ERROR_MESSAGE);
				    }
					catch(IllegalArgumentException exception){
						JOptionPane.showMessageDialog(guiFrame,
							    exception.getMessage(),
							    "Invalid number of days",
							    JOptionPane.ERROR_MESSAGE);
				    }
					catch (Exception exception){
						log.info("----------------------------------------");
						log.info("| ERROR " + exception.getMessage() );
						log.info("----------------------------------------");
					}
					
					
				}
				
				else{
					JOptionPane.showMessageDialog(guiFrame,
						    "The city cannot be found in the database.",
						    "City not found",
						    JOptionPane.ERROR_MESSAGE);
					infoPanel.setVisible(false);
					infoPanelAvg.setVisible(false);
					
				}
			}
		});
		
	}
	
	public boolean containsCaseInsensitive(String s, List<String> l){
	     for (String string : l){
	        if (string.equalsIgnoreCase(s)){
	            return true;
	         }
	     }
	    return false;
	  }
}