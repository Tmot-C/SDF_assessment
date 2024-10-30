package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vttp.batch5.sdf.task01.models.DayDetails;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws IOException {

		String fileName = "day.csv";
        String currDir = System.getProperty("user.dir");
        String filePath = currDir + File.separator + "day01" + File.separator + fileName;
		String fileTest = "task01\\day.csv";

        File file = new File(fileTest);
        Map<String, Map<String, Float>> categoryInfo = new HashMap<>();
		

		FileReader fr = new FileReader(file); 
        BufferedReader br = new BufferedReader(fr);
		
		String inLine = "";
		int lineCounter = 0;
		String[] dayString;
		List<DayDetails> dayList = new ArrayList<DayDetails>();  

		while ((inLine = br.readLine()) !=null) {

			//store first CSV line
			if (lineCounter == 0) {
				String[] rowEntry = inLine.split(",");
			}

			else{
				dayString = inLine.split(",");
				DayDetails day = DayDetails.toDayDetails(dayString);
				dayList.add(day);				
			}
			lineCounter++;
		}

		sortDays(dayList);

		for (int i = 0; i < 5; i++) {
			printTemplate(dayList.get(i), i+1);
		}
	}


    public static void sortDays(List<DayDetails> dayList){
        Comparator<DayDetails> compare = Comparator.comparing(DayDetails::getTotal, Comparator.reverseOrder());
		dayList.sort(compare);
    }

	
	public static void printTemplate(DayDetails day, int iteration){
		String position = ExtraUtilities.toPosition(iteration);
		String season = Utilities.toSeason(day.getSeason());
		String month = Utilities.toMonth(day.getMonth());
		String holiday = ExtraUtilities.toHoliday(day.isHoliday());
		String weekday = Utilities.toWeekday(day.getWeekday());
		String weather = ExtraUtilities.toWeather(day.getWeather());
		String total = Integer.toString(day.getTotal());

		System.out.printf("The %s recorded number of cyclists was in %s, on a %s,"+
		" in the month of %s. There were a total of %s cyclists. The weather was %s. %s was a %s", 
		position, season, weekday, month, total, weather, weekday, holiday);

		System.out.println("\n");
	}
	

}
