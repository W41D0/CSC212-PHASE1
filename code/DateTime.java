
//Student Name: Nawaf turki alqusair
//Student Number: 446107045

public class DateTime implements IDateTime {
    private final int year;
    private final int month;
    private final int day;
    private final int hour;
    private final int minute;
    // o(1) time complixity
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

    
    // o(1) time complixity
    public int getYear() {
        return year;
    }
    // o(1) time complixity
    public int getMonth() {
        return month;
    }

        // o(1) time complixity
public int getDay() {
        return day;
    }

       // o(1) time complixity
 public int getHour() {
        return hour;
    }
    // o(1) time complixity
    public int getMinute() {
        return minute;
    }

        // o(1) time complixity
public String format() {
        String s= FormatDigits(month)+"/"+FormatDigits(day)+"/"+yearFormat(year)+
        		" "+FormatDigits(hour)+":"+FormatDigits(minute);
        return s;
    }

       // o(1) time complixity
 private String FormatDigits(int n) {
        if(n<10)
        	return "0"+n;
        else
        	return ""+n;
    }
       // o(1) time complixity
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

       // o(1) time complixity
 public int compareTo(IDateTime other) {
    	if(year!=other.getYear())
    		return year-other.getYear();
    	if(month!=other.getMonth())
    		return month-other.getMonth();
    	if(day!=other.getDay())
        		return day-other.getDay();
    	if(hour!=other.getHour())
    		return hour - other.getHour();
    	return minute-other.getMinute();
    }

        // o(1) time complixity
public String toString() {
        return format();
    }
}
