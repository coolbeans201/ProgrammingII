import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.*;

public class MicrowaveOven extends JFrame {
	private static final long serialVersionUID = 1L;
	public MicrowaveOven(String s)
	{
	   super(s);
	   
	   JPanel p1 = new JPanel();
	   p1.setLayout(new GridLayout(4,3));
	   for(int i=0; i <= 9; i++)
  	      p1.add(new JButton(i + ""));
	   p1.add(new JButton("Start"));
	   p1.add(new JButton("Stop"));
	   
	   JPanel p2 = new JPanel();
	   p2.setLayout(new BorderLayout());
	   p2.add(new JTextField("Time to be displayed here"), 
			  BorderLayout.NORTH);
	   p2.add(p1, BorderLayout.CENTER);
	   
	   add(p2, BorderLayout.EAST);
	   add(new JButton("Food to be displayed here"), 
		   BorderLayout.CENTER);
	       
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        MicrowaveOven oven = new MicrowaveOven("The Front View of a Microwave"); 
        oven.setSize(800,600);
        oven.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        oven.setVisible(true);
	}
}
