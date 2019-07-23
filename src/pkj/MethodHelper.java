package pkj;
import org.apache.commons.csv.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MethodHelper {
	
	public MethodHelper() {
		
	}
	public ArrayList<String> readParser(CSVParser cs){
		ArrayList<String> al = new ArrayList<String>();
		for(CSVRecord cr:cs) {
			for(int i = 0; i<14 ;i++)
			{
				al.add(cr.get(i));
			}
		}
		return al;
	}
	
	
	public int coldestHourInFile(ArrayList<String> al) {
		int rown = leastInFile(al,1);
		return rown;
	}
	
	public int leastInFile(ArrayList<String> al ,int ind){
		int rowN = -1;
		try{
		double min = Double.parseDouble(al.get(ind)); 
		rowN = 0;
		for(int i = ind; i < al.size() ; i = i+14) {						
			if(Double.parseDouble(al.get(ind + rowN*14)) != -9999)
			if(!(al.get(i).equalsIgnoreCase("N/A")))
			if(min > Double.parseDouble(al.get(i)))
			{
				min = Double.parseDouble(al.get(i));
				rowN = i/14;
			}
		}
		return rowN;
		}
		catch(NumberFormatException ex) {
			ex.printStackTrace();
		}
		return rowN;
	}
	
	public void  testColdestHourInFile() throws IOException {
		File f = new File("C:\\Users\\lenovo\\Documents\\Downloads\\Compressed\\Java1\\Week3\\nc_weather\\2014\\weather-2014-04-01.csv");
		FileReader fr = new FileReader(f);
		CSVParser cs = new CSVParser(fr, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().withIgnoreHeaderCase());
		ArrayList<String> al = readParser(cs);
		int rown = coldestHourInFile(al);
		System.out.println("The coldest hour in " + al.get(0 + rown*14) + " is " + al.get(1 + rown*14));
	}
	
	public String fileWithColdestTemperature() throws IOException {
		String s = "";
		double min = 999;
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles()) {
			FileReader fr = new FileReader(f);
			CSVParser cs = new CSVParser(fr, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().withIgnoreHeaderCase());
			ArrayList<String> al = readParser(cs);
			int rown = coldestHourInFile(al);
			if(Double.parseDouble(al.get(1 + rown*14)) != -9999)
			if(min > Double.parseDouble(al.get(1 + rown*14)))
			{
				min = Double.parseDouble(al.get(1 + rown*14));
				s = f.getAbsolutePath();
			}	
		}
		return s;
	}
	
	public void testFileWithColdestTemperature() throws IOException {
		String st =  fileWithColdestTemperature();
		String[] sa = st.split("\\\\");
		String s = sa[sa.length - 1];
		System.out.println("Coldest day was in file: " + s);
		File f = new File(st);
		FileReader fr = new FileReader(f);
		CSVParser cs = new CSVParser(fr, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().withIgnoreHeaderCase());
		ArrayList<String> al = readParser(cs);
		int rown = coldestHourInFile(al);
		System.out.println("Coldest temperature on that day was: " + al.get(1 + rown*14));
		System.out.println("All the Temperatures on the coldest day were:");
		for(int i=0; i<al.size(); i = i+14)
		{
			System.out.println(al.get(13 + i) + ": " + al.get(1 + i));
		}
	}
	
	public int lowestHumidityInFile(ArrayList<String> al) {
		int rown = leastInFile(al, 3);
		return rown;
	}
	
	public void testLowestHumidityInFile() throws IOException {
		File f = new File("C:\\Users\\lenovo\\Documents\\Downloads\\Compressed\\Java1\\Week3\\nc_weather\\2014\\weather-2014-06-29.csv");
		FileReader fr = new FileReader(f);
		CSVParser cs = new CSVParser(fr, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().withIgnoreHeaderCase());
		ArrayList<String> al = readParser(cs);
		int rown = lowestHumidityInFile(al);
		System.out.println("Lowest Humidity was " + al.get(3 + rown*14) + " at " + al.get(13 + rown*14));
	}
	
	public String lowestHumidityInManyFiles() throws IOException {
		String s = "";
		double min = 999;
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles()) {
			FileReader fr = new FileReader(f);
			CSVParser cs = new CSVParser(fr, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().withIgnoreHeaderCase());
			ArrayList<String> al = readParser(cs);
			int rown = lowestHumidityInFile(al);
			if(!(al.get(3 + rown*14).equalsIgnoreCase("N/A")))
			if(min > Double.parseDouble(al.get(3 + rown*14)))
			{
				min = Double.parseDouble(al.get(3 + rown*14));
				s = f.getAbsolutePath();
			}	
		}
		return s;
	}
	
	public void testLowestHumidityInManyFiles() throws IOException {
		String st =  lowestHumidityInManyFiles();
//		String[] sa = st.split("\\\\");
//		String s = sa[sa.length - 1];
		File f = new File(st);
		FileReader fr = new FileReader(f);
		CSVParser cs = new CSVParser(fr, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().withIgnoreHeaderCase());
		ArrayList<String> al = readParser(cs);
		int rown = lowestHumidityInFile(al);
		System.out.println("Lowest Humidity was " + al.get(3 + rown*14) + " at " + al.get(13 + rown*14));
	}
	
	public double averageTemperatureInFile(ArrayList<String> al) {
		double sum = 0;
		int count = 0;
		try{
			for(int i = 1; i < al.size() ; i = i+14) {						
				if(Double.parseDouble(al.get(i)) != -9999)
				if(!(al.get(i).equalsIgnoreCase("N/A")))
				{
					sum = sum + Double.parseDouble(al.get(i));
					count++;
				}
			}
			return (sum/count);
			}
			catch(NumberFormatException ex) {
				ex.printStackTrace();
			}
			return (sum/count);
	}
	
	public void testAverageTemperatureInFile() throws IOException{
		File f = new File("C:\\Users\\lenovo\\Documents\\Downloads\\Compressed\\Java1\\Week3\\nc_weather\\2013\\weather-2013-08-10.csv");
		FileReader fr = new FileReader(f);
		CSVParser cs = new CSVParser(fr, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().withIgnoreHeaderCase());
		ArrayList<String> al = readParser(cs);
		double avgt = averageTemperatureInFile(al);
		System.out.println("Average temperature in file is " + avgt);
	}
	
	public double averageTemperatureWithHighHumidityInFile(ArrayList<String> al,int value) {
		double sum = 0;
		int count = 0;
		try{
			for(int i = 1; i < al.size() ; i = i+14) {		
				if(Double.parseDouble(al.get(i)) != -9999)
				if(!(al.get(i).equalsIgnoreCase("N/A")))
				if(Integer.parseInt(al.get(i + 2)) >= value)
				{
					sum = sum + Double.parseDouble(al.get(i));
					count++;
				}
			}
			if(count == 0)
				return 0;
			return (sum/count);
			}
			catch(NumberFormatException ex) {
				ex.printStackTrace();
			}
			return (sum/count);
	}
	
	public void testAverageTemperatureWithHighHumidityInFile() throws IOException{
		File f = new File("C:\\Users\\lenovo\\Documents\\Downloads\\Compressed\\Java1\\Week3\\nc_weather\\2013\\weather-2013-09-02.csv");
		FileReader fr = new FileReader(f);
		CSVParser cs = new CSVParser(fr, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().withIgnoreHeaderCase());
		ArrayList<String> al = readParser(cs);
		double avgt = averageTemperatureWithHighHumidityInFile(al, 80);
		if(avgt != 0)
		System.out.println("Average temperature in file is " + avgt);
		else
			System.out.println("No temperatures with that humidity");
	}
}
