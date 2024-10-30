package vttp.batch5.sdf.task01;

public class ExtraUtilities {

	public static final String[] WEATHER = { "Clear, Few clouds, Partly cloudy, Partly cloudy", 
    "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist",
    "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds",
    "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog"
	};
	public static final String[] POSITION = { "highest", "second highest", "third highest", "fourth highest", "fifth highest"
	};

    public static String toWeather(int weather) {
		
		switch (weather) {
			case 1:
			case 2:
			case 3:
			case 4:
				return WEATHER[weather - 1];
			default:
				return "unknown weather";
		}
	}

	public static String toPosition(int position) {
		
		switch (position) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				return POSITION[position - 1];
			default:
				return "unknown highest";
		}
	}

	public static String toHoliday(boolean holiday) {
		
		if (holiday){
			return "a holiday.";
		}

		else{
		return "not a holiday.";
		}
	}

}
