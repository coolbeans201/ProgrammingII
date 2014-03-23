import java.awt.Color;


public class ColorDecoder {
	
	private static boolean equalsIgnoreCase(String s1, String s2)
	{
		return (s1.compareToIgnoreCase(s2) == 0);
	}
	
	public static Color getColor(String colorName)
	{
		if (equalsIgnoreCase(colorName, "black"))
		   return Color.black;	
		else if (equalsIgnoreCase(colorName, "blue"))
			   return Color.blue;
		else if (equalsIgnoreCase(colorName, "cyan"))
			   return Color.cyan;
		else if (equalsIgnoreCase(colorName, "darkGray"))
			   return Color.darkGray;
		else if (equalsIgnoreCase(colorName, "gray"))
			   return Color.gray;
		else if (equalsIgnoreCase(colorName, "green"))
			   return Color.green;
		else if (equalsIgnoreCase(colorName, "lightGray"))
			   return Color.lightGray;
		else if (equalsIgnoreCase(colorName, "magenta"))
			   return Color.magenta;
		else if (equalsIgnoreCase(colorName, "orange"))
			   return Color.orange;
		else if (equalsIgnoreCase(colorName, "pink"))
			   return Color.pink;
		else if (equalsIgnoreCase(colorName, "red"))
			   return Color.red;
		else if (equalsIgnoreCase(colorName, "white"))
			   return Color.white;
		else if (equalsIgnoreCase(colorName, "yellow"))
			   return Color.yellow;
		return null;
	}

	public static Color getColor(int index)
	{
		if (index == 0)
		   return Color.black;	
		else if (index == 1)
		   return Color.yellow;
		else if (index == 2)
		   return Color.blue;
		else if (index == 3)
		   return Color.magenta;
		else if (index == 4)
		   return Color.green;
		else if (index == 5)
		   return Color.orange;
		else if (index == 6)
		   return Color.darkGray;
		else if (index == 7)
		   return Color.lightGray;
		else if (index == 8)
		   return Color.cyan;
		else if (index == 9)
		   return Color.gray;
		else if (index == 10)
		   return Color.pink;
		else if (index == 11)
		   return Color.red;
		else if (index == 12)
		   return Color.white;

		return null;
	}

}
