import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.ButtonGroup;
import java.awt.event.KeyEvent;
public class Vending extends JFrame {
	private static final long serialVersionUID = 1L;
	protected String price = "$0.00";
	protected String enter = "Enter money here...";
	protected String returnAmount = "Return amount...";
	protected String itemString = "This is where you get your item!"; 
	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JPanel p5;
	JTextField priceField;
	JTextField insert;
	JTextField returnSlot;
	JButton item;
	JButton [] imageButtons;
	ButtonGroup group;
	JButton [] vendingButtons;
	class RequestFocusListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e)
		{
			System.out.println("Requesting focus for the JFrame object"); 
			requestFocusInWindow();
		}
	}
	class VendingKeyListener implements KeyListener {
		
		public void keyPressed(KeyEvent e){
	    	 System.out.println("keycodevalue = " + e.getKeyCode() + " pressed");
		}	 
		public void keyReleased(KeyEvent e){
	    	 System.out.println("keycodevalue = " + e.getKeyCode() + " released");
		}	 
	    public void keyTyped (KeyEvent e) {
			System.out.println("Char value = " + e.getKeyChar() + " pressed");
			if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W')  {
				vendingButtons [0].setSelected(true);
				String enteredPrice = insert.getText ();
				String actualPrice = enteredPrice.substring(enteredPrice.lastIndexOf("$") + 1);
				double actualPrice2 = Double.parseDouble(actualPrice);
				String thePrice = priceField.getText ();
				String thePrice2 = thePrice.substring(thePrice.lastIndexOf("$") + 1);
				double thePrice3 = Double.parseDouble(thePrice2);
				if (actualPrice2 >= thePrice3) {
					double change = actualPrice2 - thePrice3;
					DecimalFormat d = new DecimalFormat("0.00");
					String changed = d.format(change);
					returnSlot.setText("Return amount...$" + changed);
					insert.setText("Enter money here...");
					item.setText("Here is your water!");
				}
				else {
					item.setText("Not enough money.");
				}
			}
			if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
				vendingButtons [1].setSelected(true);
				String enteredPrice = insert.getText ();
				String actualPrice = enteredPrice.substring(enteredPrice.lastIndexOf("$") + 1);
				double actualPrice2 = Double.parseDouble(actualPrice);
				String thePrice = priceField.getText ();
				String thePrice2 = thePrice.substring(thePrice.lastIndexOf("$") + 1);
				double thePrice3 = Double.parseDouble(thePrice2);
				if (actualPrice2 >= thePrice3) {
					double change = actualPrice2 - thePrice3;
					DecimalFormat d = new DecimalFormat("0.00");
					String changed = d.format(change);
					returnSlot.setText("Return amount...$" + changed);
					insert.setText("Enter money here...");
					item.setText("Here is your soda!");
				}
				else {
					item.setText("Not enough money.");
				}
			}
			if (e.getKeyChar() == 'p' || e.getKeyChar()  == 'P') {
				vendingButtons [2].setSelected (true);
				vendingButtons [1].setSelected(true);
				String enteredPrice = insert.getText ();
				String actualPrice = enteredPrice.substring(enteredPrice.lastIndexOf("$") + 1);
				double actualPrice2 = Double.parseDouble(actualPrice);
				String thePrice = priceField.getText ();
				String thePrice2 = thePrice.substring(thePrice.lastIndexOf("$") + 1);
				double thePrice3 = Double.parseDouble(thePrice2);
				if (actualPrice2 >= thePrice3) {
					double change = actualPrice2 - thePrice3;
					DecimalFormat d = new DecimalFormat("0.00");
					String changed = d.format(change);
					returnSlot.setText("Return amount...$" + changed);
					insert.setText("Enter money here...");
					item.setText("Here is your pretzel!");
				}
				else {
					item.setText("Not enough money.");
				}
			}
			if (e.getKeyChar () == 'c' || e.getKeyChar () == 'C') {
				vendingButtons [3].setSelected (true);
				vendingButtons [1].setSelected(true);
				String enteredPrice = insert.getText ();
				String actualPrice = enteredPrice.substring(enteredPrice.lastIndexOf("$") + 1);
				double actualPrice2 = Double.parseDouble(actualPrice);
				String thePrice = priceField.getText ();
				String thePrice2 = thePrice.substring(thePrice.lastIndexOf("$") + 1);
				double thePrice3 = Double.parseDouble(thePrice2);
				if (actualPrice2 >= thePrice3) {
					double change = actualPrice2 - thePrice3;
					DecimalFormat d = new DecimalFormat("0.00");
					String changed = d.format(change);
					returnSlot.setText("Return amount...$" + changed);
					insert.setText("Enter money here...");
					item.setText("Here is your chocolate bar!");
				}
				else {
					item.setText("Not enough money.");
				}
			}
	}
}
class GoodyButtonListener implements ActionListener {
	public void  actionPerformed (ActionEvent e) {
		priceField.setText("$1.25");
	}
}
class CharacterButtonListener implements ActionListener {
	public void actionPerformed (ActionEvent e) {
		String enteredPrice = insert.getText ();
		String actualPrice = enteredPrice.substring(enteredPrice.lastIndexOf("$") + 1);
		double actualPrice2 = Double.parseDouble(actualPrice);
		String thePrice = priceField.getText ();
		String thePrice2 = thePrice.substring(thePrice.lastIndexOf("$") + 1);
		double thePrice3 = Double.parseDouble(thePrice2);
		if (actualPrice2 >= thePrice3) {
			double change = actualPrice2 - thePrice3;
			DecimalFormat d = new DecimalFormat("0.00");
			String changed = d.format(change);
			returnSlot.setText("Return amount...$" + changed);
			insert.setText("Enter money here...");
			String characterString = e.getActionCommand();
			if (characterString.equals("W")) {
				item.setText("Here is your water!");
			}
			else if (characterString.equals("C")) {
				item.setText("Here is your chocolate bar!");
			}
			else if (characterString.equals("S")) {
				item.setText("Here is your soda!");
			}
			else {
				item.setText("Here is your pretzel!");
			}
		}
		else {
			item.setText("Not enough money.");
		}
	}
}
public Vending (String s) {
	super (s);
	double width = 100;
	double height = 100;
	p1 = new JPanel ();
	p1.setLayout(new GridLayout (4, 1, 0, 0));
	p1.setBorder (new TitledBorder ("Goodies"));
	((TitledBorder)p1.getBorder()).setTitleColor(Color.BLACK);
	String [] imageNames = {"waterbottle.jpg","soda.jpg","pretzel.jpg","chocolatebar.jpg"};
	String path = "";
	JButton [] imageButtons = new JButton [imageNames.length];
	for(int i=0; i < imageButtons.length; i++)
	{	   
		ImageIcon image1 = new ImageIcon(path + imageNames[i]);
		ImageIcon image2 = new ImageIcon(image1.getImage().getScaledInstance((int)width, (int)height,  java.awt.Image.SCALE_SMOOTH));
		imageButtons[i] = new JButton(image2);
		imageButtons[i].setContentAreaFilled(false);
		imageButtons[i].setBorderPainted(false);
		GoodyButtonListener listener = new GoodyButtonListener ();
		imageButtons[i].addActionListener(listener);
		p1.add(imageButtons[i]);
	}   
	p2 = new JPanel ();
	p2.setLayout (new GridLayout (4, 1, 0, 0));
	p2.setBorder (new TitledBorder ("Choose one"));
	((TitledBorder)p2.getBorder()).setTitleColor(Color.BLACK);
	p2.setCursor(new Cursor(Cursor.HAND_CURSOR));
	String[] vendingButtonNames = {"W", "S","P","C"};
	String[] buttonTexts = {"Water", "Soda", "Pretzel", "Chocolate"};
	vendingButtons = new JButton[vendingButtonNames.length];
	group = new ButtonGroup ();
	for(int i=0; i < vendingButtons.length; i++) {
		vendingButtons[i] = new JButton(vendingButtonNames[i]);
		vendingButtons[i].setToolTipText(buttonTexts[i]);
		CharacterButtonListener listener1 = new CharacterButtonListener ();
		vendingButtons [i].addActionListener (listener1);
	} 
	vendingButtons [0].setMnemonic(KeyEvent.VK_W);
	vendingButtons [1].setMnemonic(KeyEvent.VK_S);
	vendingButtons [2].setMnemonic(KeyEvent.VK_P);
	vendingButtons [3].setMnemonic(KeyEvent.VK_C);
	for (int i = 0; i < vendingButtons.length; i ++) {
		group.add(vendingButtons [i]);
		p2.add(vendingButtons[i]);
	}
	ImageIcon dollar = new ImageIcon (path + "dollarbill.jpg");
	ImageIcon dollar1 = new ImageIcon(dollar.getImage().getScaledInstance((int)width, (int)height,  java.awt.Image.SCALE_SMOOTH));
	JButton dollar2 = new JButton (dollar1);
	dollar2.setContentAreaFilled(false);
	dollar2.setBorderPainted(false);
	p3 = new JPanel ();
	((FlowLayout)p3.getLayout()).setAlignment(FlowLayout.LEFT);
	JLabel priceLabel = new JLabel ("Price");
	priceField = new JTextField (price);
	p3.add(priceLabel);
	p3.add(priceField);
	p4 = new JPanel ();
	p4.setLayout (new GridLayout (5,1,0,0));
	p4.add(dollar2);
	p4.add (p3);
	insert = new JTextField (enter);
	insert.setPreferredSize(new Dimension (200, 100));
	p4.add (insert);
	p4.add (p2);
	returnSlot = new JTextField (returnAmount);
	returnSlot.setPreferredSize(new Dimension (200, 100));
	p4.add (returnSlot);
	item = new JButton (itemString);
	item.setPreferredSize (new Dimension (250, 100));
	add (item, BorderLayout.SOUTH);
	p5 = new JPanel ();
	p5.setLayout (new BorderLayout ());
	p5.setSize (400, 800);
	p5.add(p1, BorderLayout.CENTER);
	p5.add(p4, BorderLayout.EAST);
	add(p5, BorderLayout.CENTER);
	setFocusable(true);
	addKeyListener(new VendingKeyListener ());
	addMouseListener (new RequestFocusListener ());
	getContentPane().setBackground(Color.GRAY);
	getContentPane().setForeground(Color.WHITE);
}

public static void main (String [] args) {
	// TODO Auto-generated method stub
	Vending machine = new Vending("FRONT VIEW OF A VENDING MACHINE"); 
	machine.setSize(400,800);
	machine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	machine.setVisible(true);
}
}
