/** 24-Hour Time */
public class Time {

	private int hours;  
	private int minutes;

	/** Sets time to 0:00 (12 midnight)*/
	public Time() { hours = 0; minutes = 0;}

	/** Initializes this to the given time */
	public Time(int hours, int minutes) { this.hours = hours; this.minutes = minutes;}

	/** Set this to the given time */           
	public void set(int hours, int minutes) { this.hours = hours; this.minutes = minutes;}

	/** Get the hour */              
	public int getHours() { return hours; }

	/** Get the minutes */               
	public int getMinutes() { return minutes; }

	/** Return a Time object that is minutes ahead of this time */
	public Time advanceMinutes(int minutes) 
	{ 
		return new Time((this.hours + (this.minutes + minutes)/60) % 24, (this.minutes + minutes) % 60);         
	}

	public boolean equals(Time other)
	{
		return (other.hours == hours && other.minutes == minutes);    
	}

	public int compareTo(Time other)
	{
		if (equals(other))
			return 0;
		else {
			if (hours < other.hours || (hours == other.hours && minutes < other.minutes))
				return -1;
			else return 1;    
		}   
	}

	public Time clone()
	{
		return new Time(hours,minutes);
	}                     

	public String toString() { return (hours < 10 ? "0" : "") + hours + ":" + (minutes < 10 ? "0" : "") + minutes;}
}
                           

