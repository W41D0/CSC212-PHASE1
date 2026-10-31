
//Student Name: Nawaf turki alqusair
//Student Number: 446107045

public class DateTime implements IDateTime {
    private final int year;
    private final int month;
    private final int day;
    private final int hour;
    private final int minute;

    public DateTime(int year, int month, int day, int hour, int minute) {
    	if(year <1)
            throw new IllegalArgumentException("invalid year");
        if (month < 1 || month > 12 )
            throw new IllegalArgumentException("invalid Month");
        if(day<1 || day >31)
            throw new IllegalArgumentException("invalid day");
        if(hour < 0 || hour > 23)
            throw new IllegalArgumentException("invalid hour");
        if (  minute < 0 || minute > 59)
            throw new IllegalArgumentException("invalid minute");
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String format() {
        String s= FormatDigits(month)+"/"+FormatDigits(day)+"/"+yearFormat(year)+
        		" "+FormatDigits(hour)+":"+FormatDigits(minute);
        return s;
    }

    private String FormatDigits(int n) {
        if(n<10)
        	return "0"+n;
        else
        	return ""+n;
    }
    private String yearFormat(int year) {
    	if(year < 10 )
    		return "000"+year;
    	else
    		if(year<100)
    			return "00"+year;
    		else
    			if(year<1000)
    				return "0"+year;
    			else
    				return ""+year;
    	
    }
    
    //o(1) time compilixty
    public static DateTime parse(String text) {

    if (text == null)
        throw new IllegalArgumentException("Invalid date");

    String[] s = text.trim().split(" ");// here trim it remove the extra spaces 
    // split i going to split into to elemnts e0=date and e1=time
    
    if (s.length != 2)
        throw new IllegalArgumentException("Invalid date");

    
    String[] date = s[0].split("/");
    String[] time = s[1].split(":");

    if (date.length != 3 || time.length != 2)
        throw new IllegalArgumentException("Invalid date");

    int month = Integer.parseInt(date[0]);
    int day = Integer.parseInt(date[1]);
    int year = Integer.parseInt(date[2]);

    int hour = Integer.parseInt(time[0]);
    int minute = Integer.parseInt(time[1]);

    return new DateTime(year, month, day, hour, minute);
    }

    public String toString() {
        return format();
    }
}
