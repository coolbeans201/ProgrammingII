import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Scanner;
public class Lab12
{
	static QueryResult q;
	static class DistanceCompare implements Comparable<DistanceCompare>
	{
		Location l;
		BusStop bs;
		int distance;

		public DistanceCompare(Location l, BusStop bs)
		{
			this.l = l;
			this.bs = bs;
			distance = l.distanceFrom(bs);
		}

		public int compareTo(DistanceCompare dc)
		{
			if (this.distance < dc.distance) {
				return -1;
			}
			else if (this.distance == dc.distance) {
				return 0;
			}
			else {
				return 1;
			}
		}
	}
	public static class QueryResult
	{
		Line line;
		Location departure;
		Location destination;

		public QueryResult(Line line, Location departure, Location destination)
		{
			this.line = line;
			this.departure = departure;
			this.destination = destination;
		}

		public String toString()
		{
			return "[QUERY RESULT:]\n" + line + " moving from " + departure + " heading to " + destination;
		}
	}

	public static QueryResult search(Collection<BusStop> locations, Location departure, Location destination)
	{
		PriorityQueue<DistanceCompare> sortedList1 = new PriorityQueue<DistanceCompare>(locations.size ());
		for(BusStop b2: locations)
			sortedList1.offer(new DistanceCompare(departure, b2));
		PriorityQueue<DistanceCompare> sortedList2 = new PriorityQueue<DistanceCompare> (locations.size ());
		for (BusStop b3: locations) 
			sortedList2.offer(new DistanceCompare(destination, b3));
		BusStop closestDestination = sortedList2.poll().bs;
		for (int i = 0; i < IOUtil.lines.size (); i ++) {
			Line l = IOUtil.lines.get(i);
			for (int j = 0; j < l.route.size (); j ++) {
				if (l.route.get(j).getName ().equals(closestDestination.getName ())) {
					while (sortedList1.peek() != null) {
						BusStop closestDeparture = sortedList1.poll().bs;
						for (int k = 0; k < l.route.size (); k ++) {
							if (l.route.get(k).getName().equals(closestDeparture.getName ())) {
								q = new QueryResult(l, closestDeparture, closestDestination);
								return q;
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static ArrayList<BusStop> filterBusStops(ArrayList<Object> list)
	{
		ArrayList<BusStop> bsList = new ArrayList<BusStop>();
		for(Object o: list)
			if (o instanceof BusStop)
				bsList.add((BusStop)o);
		return bsList; 
	}

	public static void main(String[] args) throws Exception
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter input file name:");
		ArrayList<Object> rtsItems = IOUtil.readBusstopAndLineInfo(input.next());
		input.nextLine();
		ArrayList<BusStop> busStops = filterBusStops(rtsItems);
		System.out.println("Please enter the query to find the best line");
		System.out.println("that stops at the closest bus stop from the");
		System.out.print("departure location (enter as x,y):");
		input.useDelimiter("[,\\s]+");         
		Location departure = new Location("departure", input.nextInt(), input.nextInt());  
		System.out.println("and stops at a bus stop closest to");
		System.out.print("destination location (enter as x,y):");
		Location destination =  new Location("destination", input.nextInt(), input.nextInt());  
		System.out.println("favoring the one that is closer to the destination point.");
		QueryResult result = search(busStops, departure, destination);
		System.out.println(result); 
		input.close ();
	}
}

