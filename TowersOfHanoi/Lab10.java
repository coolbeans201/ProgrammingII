import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Lab10 extends JFrame
{
	private static final long serialVersionUID = 1L;
	JPanel animationPanel;
	JTextField numDisksField;
	JButton nextStartButton;

	private static int frameWidth = 600;
	private static int frameHeight = 700;

	private int preferredPanelWidth = 600;    
	private int preferredPanelHeight = 600;
	private int towerWidth = 20;
	private int towerHeight = 300;

	final static int LEFT_TOWER = 0;    
	final static int MIDDLE_TOWER = 1;
	final static int RIGHT_TOWER = 2;

	private int hGapBwTowers = preferredPanelWidth/4;
	private int vSpace = (preferredPanelHeight - towerHeight)/2;
	private int[] towerX = new int[3];
	private int[] towerY = new int[3];   

	private int baseDiskWidth = (int)(0.75 *hGapBwTowers); 
	private int leftDiskX;
	private int middleDiskX;
	private int rightDiskX;

	private int diskHeight = towerHeight / 10;


	int[][] disksOnTower;
	int[] numDisksOnTower = new int[3];
	int numDisks = 0;
	int i = 0;
	
	ArrayList<MovementInfo> steps = new ArrayList<MovementInfo> ();
	class MovementInfo {
		int disk;
		int fromTower;
		int toTower;

		MovementInfo(int disk, int fromTower, int toTower)
		{
			this.disk = disk;
			this.fromTower = fromTower;
			this.toTower = toTower;   
		} 

	}



	class ButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand().equals("Next")) 
			{
				MovementInfo step = steps.get(i);
				disksOnTower[step.toTower][numDisksOnTower[step.toTower]] = step.disk;
				disksOnTower[step.fromTower][numDisksOnTower[step.fromTower] - 1] = 0;
				numDisksOnTower[step.fromTower]--;
				numDisksOnTower[step.toTower]++;
				i++;
				if (numDisksOnTower[RIGHT_TOWER] == numDisks && numDisksOnTower [LEFT_TOWER] == 0) {
					nextStartButton.setText("Start");
				}
				animationPanel.repaint();
			}
			else if (e.getActionCommand().equals("Start"))
			{
				makeInitialConfiguration();
			}
		}
	}



	public Lab10(String title)
	{
		super(title);
		animationPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g)
			{
				g.setColor(Color.white); 
				g.clearRect(0, 0, getWidth(), getHeight());
				g.setColor(Color.gray);
				g.fillRect(towerX[LEFT_TOWER], towerY[LEFT_TOWER], towerWidth, towerHeight);
				g.fillRect(towerX[MIDDLE_TOWER], towerY[MIDDLE_TOWER], towerWidth, towerHeight);
				g.fillRect(towerX[RIGHT_TOWER], towerY[RIGHT_TOWER], towerWidth, towerHeight);
				g.setColor(Color.darkGray);
				g.fillOval(leftDiskX, towerY[LEFT_TOWER] + towerHeight, baseDiskWidth, 10);  
				g.fillOval(middleDiskX, towerY[LEFT_TOWER] + towerHeight, baseDiskWidth, 10);  
				g.fillOval(rightDiskX, towerY[LEFT_TOWER] + towerHeight, baseDiskWidth, 10);  


				for(int i = 0; i < 3; i++)
				{
					for(int j = 0; j  < numDisksOnTower [i]; j++)
						drawDiskOnTower(g, disksOnTower[i][j], i, j); 
				}

			}

			public Dimension getPreferredSize()
			{
				return new Dimension(preferredPanelWidth, preferredPanelHeight);  
			}
		};

		JPanel actionPanel = new JPanel();
		nextStartButton = new JButton("Start");
		nextStartButton.addActionListener(new ButtonListener());
		numDisksField = new JTextField("   ");
		actionPanel.add(new JLabel("Number of Disks"));
		actionPanel.add(numDisksField);
		actionPanel.add(nextStartButton);

		add(animationPanel, BorderLayout.EAST);
		add(actionPanel, BorderLayout.SOUTH);

		initializeParameters();

	}

	public void initializeParameters()
	{
		towerX[LEFT_TOWER] = hGapBwTowers - towerWidth;   
		towerY[LEFT_TOWER] = vSpace;
		towerX[MIDDLE_TOWER] = towerX[LEFT_TOWER] + hGapBwTowers + towerWidth;
		towerY[MIDDLE_TOWER] = vSpace;
		towerX[RIGHT_TOWER] = towerX[MIDDLE_TOWER] + hGapBwTowers + towerWidth; 
		towerY[RIGHT_TOWER] = vSpace;
		leftDiskX = towerX[LEFT_TOWER] - (baseDiskWidth - towerWidth)/2;
		middleDiskX = towerX[MIDDLE_TOWER] - (baseDiskWidth - towerWidth)/2;
		rightDiskX = towerX[RIGHT_TOWER] - (baseDiskWidth - towerWidth)/2;
	}

	void makeInitialConfiguration()
	{
		numDisks = Integer.parseInt(numDisksField.getText().trim());
		numDisksOnTower[MIDDLE_TOWER] = 0;
		numDisksOnTower[RIGHT_TOWER] = 0;
		numDisksOnTower[LEFT_TOWER] = numDisks; 

		disksOnTower = new int[3][numDisks]; 

		for(int i = 0; i < numDisks; i++)
			disksOnTower[LEFT_TOWER][i] = numDisks - i;

		nextStartButton.setText("Next");  

		towersOfHanoi(numDisks, LEFT_TOWER, RIGHT_TOWER, MIDDLE_TOWER);

		animationPanel.repaint(); 
	}

	void drawDiskOnTower(Graphics g, int disk, int tower, int position)
	{
		System.out.println("Drawing disk = " + disk + " on tower = " + tower + " position = " + position);
		g.setColor(ColorDecoder.getColor(disk));
		g.fillRoundRect(towerX[tower] - ((int)(baseDiskWidth*disk/numDisks) - towerWidth)/2, towerY[tower] + towerHeight - diskHeight * (position + 1), (int)(baseDiskWidth*disk/numDisks), diskHeight, 5, 5);
	}

	public void towersOfHanoi(int numOfDisks, int fromTower, int toTower, int auxTower)
	{
		if (numOfDisks == 1) 
		{
			System.out.println("Move disk 1 from tower " + fromTower + " to tower " + toTower);
			steps.add(new MovementInfo(numOfDisks, fromTower, toTower));
			return; 
		}   
		else {
			towersOfHanoi(numOfDisks-1, fromTower, auxTower, toTower);
			steps.add(new MovementInfo(numOfDisks, fromTower, toTower));
			System.out.println("Move disk " +  numOfDisks + " from tower " + fromTower + " to tower " + toTower);
			towersOfHanoi(numOfDisks-1, auxTower, toTower, fromTower);
		}
	}


	public static void main(String[] args)
	{
		Lab10 animation = new Lab10("Towers of Hanoi");
		animation.setSize(frameWidth, frameHeight);
		animation.setDefaultCloseOperation(EXIT_ON_CLOSE);
		animation.setVisible(true); 
	}
}