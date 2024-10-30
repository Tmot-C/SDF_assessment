package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import vttp.batch5.sdf.task01.models.DayDetails;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws IOException {

		String fileTest = "task01\\day.csv";

		// File readers setup
        File file = new File(fileTest);		
		FileReader fr = new FileReader(file); 
        BufferedReader br = new BufferedReader(fr);
		
		//Holding string declaration
		String inLine = "";
		int lineCounter = 0;
		
		//Holding array to store day details, to be passed into the toMethod for conversion.
		String[] dayString;
		//Array list for holding the various day details and for sorting.
		List<DayDetails> dayList = new ArrayList<DayDetails>();  

		//read in line by line
		while ((inLine = br.readLine()) !=null) {

			//Ignore first CSV line
			if (lineCounter != 0) {
				//split the string using commas
				dayString = inLine.split(",");
				//passing in toMethod
				DayDetails day = DayDetails.toDayDetails(dayString);
				//Add to daylist
				dayList.add(day);				
			}
			lineCounter++;
		}

		//Sort using function. sortDays declared below.
		sortDays(dayList);

		//After sorting, print out the first 5 days using template printer
		for (int i = 0; i < 5; i++) {
			printTemplate(dayList.get(i), i+1);
		}

		br.close();
	}

	//Sort daylist using aggregated total field.
    public static void sortDays(List<DayDetails> dayList){
        Comparator<DayDetails> compare = Comparator.comparing(DayDetails::getTotal, Comparator.reverseOrder());
		dayList.sort(compare);
    }

	//template printer, Day fields converted to string, and other string values hardwritten in.
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
