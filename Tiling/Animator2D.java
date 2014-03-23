import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Animator2D extends JFrame
{
	private static final long serialVersionUID = 10;
	JButton[][] cells;
	JButton startStop;
	boolean started = false;
	int numX, numY;
	Tiling tiling;
	JTextField settingsFields[];
	JPanel animationPanel;
	static Animator2D animator;
	boolean recursive = true;
	JCheckBox isRecursive;

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			if (started)
			{
				resetAnimator();
				startStop.setText("Start");
				started = false;
			}
			else { 
				startStop.setText("Stop");
				started = true;
				numX = numY = Integer.parseInt(settingsFields[0].getText().trim());
				tiling = new Tiling(numX, Integer.parseInt(settingsFields[1].getText().trim()), Integer.parseInt(settingsFields[2].getText().trim()));
				setBoardSize(numX, numY);
				tiling.setAnimator(animator);
				try { 
					if (isRecursive.isSelected()) 
						tiling.solve();
					else  
						tiling.solveIteratively(1, numX, 1, numY); 
				} 
				catch(Exception exc) {} 
			}

		}
	}

	public Animator2D(String title, String[] parameters)
	{
		super(title);
		System.out.println("Constructor..." +  parameters.length); 

		animationPanel = new JPanel();
		JPanel settingsPanel = new JPanel();
		JLabel[] settingsLabels = new JLabel[parameters.length - 1];
		settingsFields = new JTextField[parameters.length];
		for(int i=0; i < parameters.length - 1; i++)
		{
			settingsLabels[i] = new JLabel(parameters[i]);
			settingsFields[i] = new JTextField("    ");
			settingsPanel.add(settingsLabels[i]);
			settingsPanel.add(settingsFields[i]);
		}        
		isRecursive = new JCheckBox();
		startStop = new JButton("Start");
		startStop.addActionListener(new ButtonListener());
		settingsPanel.add(new JLabel("Recursive?"));
		settingsPanel.add(isRecursive);
		settingsPanel.add(startStop); 

		add(animationPanel, BorderLayout.CENTER);
		add(settingsPanel, BorderLayout.SOUTH);

	}

	public void setBoardSize(int numX, int numY)
	{
		animationPanel.removeAll();
		animationPanel.setLayout(new GridLayout(numX, numY));
		this.numX = numX;
		this.numY = numY;
		cells = new JButton[numX+1][numY+1];
		for(int i=1; i <= numX; i++)
			for(int j=1; j <= numY; j++)
			{
				cells[i][j] = new JButton("");
				animationPanel.add(cells[i][j]);
			}    
		animationPanel.revalidate();
		animationPanel.repaint();
	}

	public void resetAnimator()
	{
		for(int i=1; i <= numX; i++)
			for(int j=1; j <= numY; j++)
				cells[i][j].setText("");
	}

	public void updateAnimator(int x, int y, String label)
	{
		if (started)
		{
			//try{
			cells[x][y].setText(label);  
			animationPanel.repaint();
			//Thread.sleep(1000);
			//}
			//catch(InterruptedException e) { 
				//System.out.println("Thread interrupt not expected");
			//} 
		}
	}

	public void showAnimator()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setVisible(true);
		System.out.println("Showing frame");
	}


	public static void main(String[] args) throws Exception
	{
		String[] paramList = {"size (power of 2)", "defectiveTileX", "defectiveTileY", "recursive?"};
		System.out.println("Creating frame");
		animator = new Animator2D("Defective Board Covering using Triominos", paramList);        
		animator.showAnimator();
	} 
}