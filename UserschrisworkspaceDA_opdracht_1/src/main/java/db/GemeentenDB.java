package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GemeentenDB {

	private static final String FILENAME = "C:/Users/chris/OneDrive/Documenten/UCLL 2017-2018/Distributed Applications/Opdracht 1/lijst_gemeenten.txt";
	private ArrayList<String> gemeenten;
	private static GemeentenDB singleton;
	
	private GemeentenDB(){
		gemeenten=new ArrayList<String>();
		readFromFile();
	}
	
	public static synchronized GemeentenDB getInstance(){
		if (singleton == null)
	          singleton=new GemeentenDB();
	      return singleton;
	}

	private void readFromFile() {
		// TODO Auto-generated method stub
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				gemeenten.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getTotal(){
		return gemeenten.size();
	}
	
	public ArrayList<String> getGemeenten(){
		return this.gemeenten;
	}
	
}
