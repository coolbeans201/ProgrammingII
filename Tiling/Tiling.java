import java.util.Stack;

public class Tiling {

	private int size;
	private int defectiveTileX;    
	private int defectiveTileY;
	private int[][] board;
	private int label = 1;
	static final int NORMAL = 0;    
	static final int ROTATED_RIGHT = 1;    
	static final int ROTATED_DOWN = 2;    
	static final int ROTATED_RIGHT_DOWN = 3;
	Animator2D animator;   

	class Instance {
		int lowX;
		int highX;
		int lowY;
		int highY;
		public Instance (int lX, int hX, int lY, int hY) {
			lowX = lX;
			highX = hX;
			lowY = lY;
			highY = hY;
		}
	}

	public Tiling(int size, int defectiveTileX, int defectiveTileY)
	{ 
		this.size = size;
		this.defectiveTileX = defectiveTileX;        
		this.defectiveTileY = defectiveTileY;
		board = new int[size+1][size+1]; 
		for(int i=0; i < size+1; i++)
			for(int j=0; j < size+1; j++)
				board[i][j] = 0;
		board[defectiveTileX][defectiveTileY] = -1;
	}

	public void setAnimator(Animator2D animator)
	{
		this.animator = animator;
		animator.updateAnimator(defectiveTileX, defectiveTileY, "*"); 
	}

	public void solve() throws Exception
	{
		solve(1,size,1,size); 
	}

	public void solveIteratively (int lowX, int highX, int lowY, int highY) throws Exception {
		Stack<Instance> subProblems = new Stack<Instance> ();
		subProblems.push(new Instance (lowX, highX, lowY, highY));
		while (!subProblems.empty ()) {
			Instance subProblem = subProblems.pop();
			if (subProblem.highX == subProblem.lowX + 1) 
			{
				for(int i = subProblem.lowX; i <= subProblem.highX; i++)
					for(int j = subProblem.lowY; j <= subProblem.highY; j++)
						if (board[i][j] == 0)
							setBoard(i, j, label);
				label++;             
			} 
			else {
				int topLlowX = subProblem.lowX;
				int topLhighX = (subProblem.lowX + subProblem.highX)/2;
				int topLlowY = subProblem.lowY;
				int topLhighY = (subProblem.lowY + subProblem.highY)/2;
				boolean topLeftHas = hasDefective(topLlowX, topLhighX, topLlowY, topLhighY);

				int topRlowX = subProblem.lowX;
				int topRhighX = (subProblem.lowX + subProblem.highX)/2;
				int topRlowY = (subProblem.lowY + subProblem.highY)/2 + 1;
				int topRhighY = subProblem.highY;
				boolean topRightHas = (topLeftHas? false  : 
					hasDefective(topRlowX, topRhighX, topRlowY, topRhighY));

				int bottomLlowX = (subProblem.lowX + subProblem.highX)/2 + 1;
				int bottomLhighX = subProblem.highX;
				int bottomLlowY = subProblem.lowY;
				int bottomLhighY = (subProblem.lowY + subProblem.highY)/2;
				boolean bottomLeftHas = ((topLeftHas || topRightHas) ? false : 
					hasDefective(bottomLlowX, bottomLhighX, bottomLlowY, bottomLhighY));

				int bottomRlowX = (subProblem.lowX + subProblem.highX)/2 + 1;
				int bottomRhighX = subProblem.highX;
				int bottomRlowY = (subProblem.lowY + subProblem.highY)/2 + 1;
				int bottomRhighY = subProblem.highY;
				boolean bottomRightHas = ((topLeftHas || topRightHas || bottomLeftHas) ? false : 
					hasDefective(bottomRlowX, bottomRhighX, bottomRlowY, bottomRhighY));


				if (topLeftHas)
					placeTriomino(bottomRlowX, bottomRlowY, ROTATED_RIGHT);
				else if (topRightHas)
					placeTriomino(bottomLlowX, topLhighY, NORMAL);
				else if (bottomLeftHas)
					placeTriomino(topLhighX, topRlowY, ROTATED_RIGHT_DOWN);
				else if (bottomRightHas)
					placeTriomino(topLhighX, topLhighY, ROTATED_DOWN);    
				else throw new Exception("Unexpected case: no one has defective"); 

				subProblems.push(new Instance (bottomRlowX, bottomRhighX,bottomRlowY, bottomRhighY));
				subProblems.push(new Instance (bottomLlowX, bottomLhighX, bottomLlowY, bottomLhighY));
				subProblems.push(new Instance (topRlowX, topRhighX, topRlowY, topRhighY));
				subProblems.push(new Instance (topLlowX, topLhighX, topLlowY, topLhighY));                    
			}
		}
	}

	private void solve(int lowX, int highX, int lowY, int highY) throws Exception
	{

		//System.out.println("lx=" + lowX + " hx="  + highX + " ly=" + lowY + " hy=" + highY);

		/** Base case */
		if (highX == lowX + 1) 
		{
			for(int i=lowX; i <= highX; i++)
				for(int j=lowY; j <= highY; j++)
					if (board[i][j] == 0)
						setBoard(i, j, label);
			label++;             
			return;
		} 

		/** top-left */
		int topLlowX = lowX;
		int topLhighX = (lowX + highX)/2;
		int topLlowY = lowY;
		int topLhighY = (lowY + highY)/2;
		boolean topLeftHas = hasDefective(topLlowX, topLhighX, topLlowY, topLhighY);

		int topRlowX = lowX;
		int topRhighX = (lowX + highX)/2;
		int topRlowY = (lowY + highY)/2 + 1;
		int topRhighY = highY;
		boolean topRightHas = (topLeftHas? false  : 
			hasDefective(topRlowX, topRhighX, topRlowY, topRhighY));

		int bottomLlowX = (lowX + highX)/2 + 1;
		int bottomLhighX = highX;
		int bottomLlowY = lowY;
		int bottomLhighY = (lowY + highY)/2;
		boolean bottomLeftHas = ((topLeftHas || topRightHas) ? false : 
			hasDefective(bottomLlowX, bottomLhighX, bottomLlowY, bottomLhighY));

		int bottomRlowX = (lowX + highX)/2 + 1;
		int bottomRhighX = highX;
		int bottomRlowY = (lowY + highY)/2 + 1;
		int bottomRhighY = highY;
		boolean bottomRightHas = ((topLeftHas || topRightHas || bottomLeftHas) ? false : 
			hasDefective(bottomRlowX, bottomRhighX, bottomRlowY, bottomRhighY));


		if (topLeftHas)
			placeTriomino(bottomRlowX, bottomRlowY, ROTATED_RIGHT);
		else if (topRightHas)
			placeTriomino(bottomLlowX, topLhighY, NORMAL);
		else if (bottomLeftHas)
			placeTriomino(topLhighX, topRlowY, ROTATED_RIGHT_DOWN);
		else if (bottomRightHas)
			placeTriomino(topLhighX, topLhighY, ROTATED_DOWN);    
		else throw new Exception("Unexpected case: no one has defective"); 

		solve(topLlowX, topLhighX, topLlowY, topLhighY);            
		solve(topRlowX, topRhighX, topRlowY, topRhighY);            
		solve(bottomLlowX, bottomLhighX, bottomLlowY, bottomLhighY);
		solve(bottomRlowX, bottomRhighX,bottomRlowY, bottomRhighY ); 
	}


	private void setBoard(int x, int y, int value)
	{
		board[x][y] = value;
		animator.updateAnimator(x, y, new Integer(value).toString());  
	}


	/** x and y shows middle tile in the triomino */
	private void placeTriomino(int x, int y, int position)
	{
		setBoard(x, y, label);
		switch (position) {
		case NORMAL: 
			setBoard(x-1, y, label);              
			setBoard(x, y+1, label); 
			break;
		case ROTATED_RIGHT:
			setBoard(x, y-1, label);
			setBoard(x-1, y, label);
			break;
		case ROTATED_DOWN:
			setBoard(x, y+1, label);
			setBoard(x+1, y, label);
			break;
		case ROTATED_RIGHT_DOWN:
			setBoard(x, y-1, label);
			setBoard(x+1, y, label); 
			break;
		}      
		label++;
	}

	private boolean hasDefective(int lowX, int highX, int lowY, int highY)
	{
		for(int i= lowX; i <= highX; i++)
			for(int j=lowY; j <= highY; j++)
				if (board[i][j] != 0)
					return true;
		return false;
	}

	public String toString()
	{
		String result = "";
		for(int i=1; i <= size; i++)
		{
			for(int j=1; j <= size; j++)
				result += (board[i][j] == -1 ? " *" : (board[i][j] > 9 ? board[i][j] : " " + board[i][j])) + " ";
			result += "\n";             
		}
		return result;
	}

}
