package vttp.batch5.sdf.task01.models;
//
//Class renamed to be clearer
public class DayDetails {

	private int season;
	private int month;
	private boolean holiday;
	private int weekday;
	private int weather;
	private float temperature;
	private float humidity;
	private float windspeed;
	private int casual;
	private int registered;
    private int total;

	public void setSeason(int season) { this.season = season; }
	public int getSeason() { return this.season; }

	public void setMonth(int month) { this.month = month; }
	public int getMonth() { return this.month; }

	public void setHoliday(boolean holiday) { this.holiday = holiday; }
	public boolean isHoliday() { return this.holiday; }

	public void setWeekday(int weekday) { this.weekday = weekday; }
	public int getWeekday() { return this.weekday; }

	public void setWeather(int weather) { this.weather = weather; }
	public int getWeather() { return this.weather; }

	public void setTemperature(float temperature) { this.temperature = temperature; }
	public float getTemperature() { return this.temperature; }

	public void setHumidity(float humidity) { this.humidity = humidity; }
	public float getHumidity() { return this.humidity; }

	public void setWindspeed(float windspeed) { this.windspeed = windspeed; }
	public float getWindspeed() { return this.windspeed; }

	public void setCasual(int casual) { this.casual = casual; }
	public int getCasual() { return this.casual; }

	public void setRegistered(int registered) { this.registered = registered; }
	public int getRegistered() { return this.registered; }

    //extra total field added(not modified), set by summming up the values of casual and registered.
    public void setTotal() { this.total = this.casual + this.registered; }
	public int getTotal() { return this.total; }

    //toClass updated with extra field
	public static DayDetails toDayDetails(String[] cols) {
		DayDetails entry = new DayDetails();
		entry.setSeason(toInt(cols[0]));
		entry.setMonth(toInt(cols[1]));
		entry.setHoliday(toBoolean(cols[2]));
		entry.setWeekday(toInt(cols[3]));
		entry.setWeather(toInt(cols[4]));
		entry.setTemperature(toFloat(cols[5]));
		entry.setHumidity(toFloat(cols[6]));
		entry.setWindspeed(toFloat(cols[7]));
		entry.setCasual(toInt(cols[8]));
		entry.setRegistered(toInt(cols[9]));
        entry.setTotal();
		return entry;
	}

	private static int toInt(String col) {
		return Integer.parseInt(col);
	}
	private static float toFloat(String col) {
		return Float.parseFloat(col);
	}
	private static boolean toBoolean(String col) {
		return toInt(col) == 1;
	}
}
