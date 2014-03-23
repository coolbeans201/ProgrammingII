import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.IOException;

/** Class that represents a bus line */
public class Line
{
  /** Line name */
  protected String name;
  
  /** Departure point */
  protected Location departure;
  /** Destination point */
  protected Location destination;
  /** List of bus stops that this lines stops at */
  protected ArrayList<BusStop> route;
  /** List of durations that show the amount of time it takes the bus 
    * to come from the previous Time Point type of bus stop to this one 
    */
  protected ArrayList<Integer> timePoints;
  /** The start time of this line */
  protected Time first;
  /** No service after this time */
  protected Time last;
  /** Period of buses that serve this line */
  protected int period;
  
  
  public Line() {}
  
  /** Initializes the fields with the parameter values and initializes route and timePoints to empty lists */
  public Line(String name, Location departure, Location destination, Time first, Time last, int period)
  {
     this.name = name;
     this.departure = departure;
     this.destination = destination; 
     this.first = first;
     this.last = last;
     this.period = period;
     route = new ArrayList<BusStop>();
     timePoints = new ArrayList<Integer>();
  }
  
  
  /** Adds bus stop @param bs to the route list and adds @param durationFromPreviousTimePoint 
    * iff bs is a TimePoint object 
    * Also adds this line to lines list of bus stop bs 
    */
  public void addNextBusStop(BusStop bs, int durationFromPreviousTimePoint)
  {
     if (!route.contains(bs))
     {
        route.add(bs);
        bs.addLine(this);
        if (bs instanceof TimePoint)
           timePoints.add(durationFromPreviousTimePoint);     
     }      
  }
  
  /** Prints the schedule of this line to output stream o 
    * by explicitly showing the time point type of bus stops 
    */  
  public void printSchedule(OutputStream o) throws IOException
  {
     o.write(getSchedule().getBytes());
  }
  
  /** Returns the string that represents the formatted schedule of 
    * this line by explicitly showing the time point type of bus stops 
    */  
  public String getSchedule()
  {
     ByteArrayOutputStream outputString = new ByteArrayOutputStream();
     PrintWriter output = new PrintWriter(outputString, true);
     if (timePoints.size() == 0) return "";
  
     BusStop[] tp = new BusStop[timePoints.size()];
     output.println("SCHEDULE FOR LINE " + name);
     for(int i=0, j=0; i < route.size(); i++)
     {
        BusStop bs = route.get(i);
        if (bs instanceof TimePoint)
        {
           tp[j++] = bs;
           output.printf("%20s",bs.getName());
        }   
     }   
     output.println();
     
     Time next = first;
     while (next.compareTo(last) != 1)
     {
        Time nextTimePoint = next.clone();
        for(int i=0; i < tp.length; i++)
        {
           nextTimePoint = nextTimePoint.advanceMinutes(timePoints.get(i));
           output.printf("%20s",nextTimePoint);
           
        }   
        output.println();
        next = next.advanceMinutes(period);
     }
     output.println();
     return outputString.toString(); 
    
  }

  /** 
    * Prints the list of bus stops that this line visits on 
    * its route on output stream o 
    */
  public void printRoute(OutputStream o) throws IOException
  {
     o.write(getRoute().getBytes());     
  }

  /** 
    * Returns a string that contains the list of bus stops 
    * that this line visits on its route 
    */
  public String getRoute()
  {
     ByteArrayOutputStream outputString = new ByteArrayOutputStream();
     PrintWriter output = new PrintWriter(outputString, true);  
     output.println("ROUTE FOR LINE " + name);
     int numStops = route.size();
     for(int i=0; i < numStops; i++)
        output.print(route.get(i).getName() + ((i == numStops -1) ? "\n" : ", "));               
     output.println();   
     return outputString.toString();   
  }

  public String toString()
  {
    return name;
  }  
  
  public boolean onRoute(BusStop bs)
  {
    for(BusStop bsOnRoute: route)
       if (bsOnRoute.equals(bs))
          return true;
    return false;      
  }
}
