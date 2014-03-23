import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.*;
import java.awt.*;

public class MicrowaveOvenUpgrade extends JFrame {

	private static final long serialVersionUID = 1L;

	public MicrowaveOvenUpgrade(String s)
	{
	   super(s);
	   
	   JPanel p1 = new JPanel();
	   p1.setLayout(new GridLayout(4,3));
	   /* New feature: Cursor type */
	   p1.setCursor(new Cursor(Cursor.HAND_CURSOR));
	   String[] basicButtonNames = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "Start", "Stop"};
	   JButton[] basicButtons = new JButton[basicButtonNames.length];
	   for(int i=0; i < basicButtons.length; i++)
	   {
  	      basicButtons[i] = new JButton(basicButtonNames[i]);
  	      /* New feature: Background and foreground colors */
  	      basicButtons[i].setBackground(Color.BLACK);
  	      basicButtons[i].setForeground(Color.WHITE);
  	      p1.add(basicButtons[i]);
	   }   

	   	  
	   /* New feature: This custom modes panel is totally new */
	   JPanel p3 = new JPanel();
	   /* Grid layout has horizontal and vertical gaps of 5 pixels */
	   p3.setLayout(new GridLayout(5,1,5,5));
	   /* Example of a titled border */
	   p3.setBorder(new TitledBorder("Custom Modes"));
	   /* How to change text color of the titled border */
	   ((TitledBorder)p3.getBorder()).setTitleColor(Color.WHITE);
	   p3.setBackground(Color.BLACK);
	   p3.setForeground(Color.WHITE);
	   double width = 100;
	   double height = 100;
	   String[] imageNames = {"popcorn.jpg", "soup.png", "bread.jpg", "meat.png", "vegetables.png"};
	   String[] tipTexts = {"Popcorn", "Soup", "Bread", "Meat", "Vegetables"};
	   String path = "C:\\Users\\Beans\\Pictures";
	   JButton[] customButtons = new JButton[imageNames.length];
	   for(int i=0; i < customButtons.length; i++)
	   {	   
		  /* How to create an image icon by concatenating the path and the file names */ 
   	      ImageIcon image1 = new ImageIcon(path + imageNames[i]);
   	      /* This scales the image to the desired width and height */
	      ImageIcon image2 = new ImageIcon(image1.getImage().getScaledInstance((int)width, (int)height,  java.awt.Image.SCALE_SMOOTH));
	      /* Creating a button using the scaled image */
	      customButtons[i] = new JButton(image2);
	      /* This makes sures that the backgroud does not show up especially useful if my image is smaller than the size of the button */
  	      customButtons[i].setContentAreaFilled(false);
  	      /* Does not paint the rectangle around the button, again useful to avoid problems when image size and button size do not match */
	      customButtons[i].setBorderPainted(false);
	      /* When you ove the mouse to the button it will show you tipText[i] (see tipTexts array above) */
	      customButtons[i].setToolTipText(tipTexts[i]);
	      p3.add(customButtons[i]);
	   }   

	   JPanel p2 = new JPanel();
	   p2.setLayout(new BorderLayout());
	   /* New feature: Example of a line border */
	   p2.setBorder(new LineBorder(Color.WHITE, 2));
	   /* New feature: we specify the width of the textfield as 5 characters */
	   JTextField timeDisplay = new JTextField(" :00",5);
	   /* New feature: Change the color of textfield */
	   timeDisplay.setBackground(Color.BLACK);
	   timeDisplay.setForeground(Color.YELLOW);
	   /* New feature: Add a label to go next to text field */
	   JLabel timeLabel = new JLabel("Time Display");
	   JPanel p4 = new JPanel();
	   /* Flowlayout is the default layout manager for JPanel anyway. 
	    * So instead of creating one in the constructor, we change its attribute using setAlignment method.
	    * Anything we add to this panel will be aligned to left
	    */
	   ((FlowLayout)p4.getLayout()).setAlignment(FlowLayout.LEFT);
	   p4.add(timeLabel);
	   p4.add(timeDisplay);
	   p2.add(p4, BorderLayout.NORTH);
	   p2.add(p1, BorderLayout.CENTER);
       p2.add(p3, BorderLayout.EAST);
	   
       /* We set a horizontal border gap of 30 and hope to imitate 
        * the door handle of the microwave oven */ 
	   setLayout(new BorderLayout(30,10));
	   //p2.setPreferredSize(new Dimension(300,600));
	   add(p2, BorderLayout.EAST);
	   JButton foodButton = new JButton("Food to be displayed here");
	   /* New feature: We set the color and the font of this food button */
	   foodButton.setBackground(Color.BLACK);
	   foodButton.setForeground(Color.WHITE);
	   foodButton.setFont(new Font("TimesRoman", Font.BOLD, 20));
	   add(foodButton, BorderLayout.CENTER);
	   /* New feature: For JFrame setBackground and setForeground does not work.
	    * Instead you have the get the content pane and then change its color.
	    */
	   getContentPane().setBackground(Color.GRAY);
	   getContentPane().setForeground(Color.WHITE);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        MicrowaveOvenUpgrade oven = new MicrowaveOvenUpgrade("The Front View of a Microwave"); 
        oven.setSize(1000,600);
        oven.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        oven.setVisible(true);
	}

}
