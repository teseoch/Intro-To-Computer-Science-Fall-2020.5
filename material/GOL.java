public class GOL {
	public static void init(boolean[][] alive, double alivePerc)
	{
		//assume the grid is squared
		int n = alive.length; //len(alive)

		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				alive[i][j] = Math.random() < alivePerc;
			}
		}
	}

	public static void print(boolean[][] alive)
	{
		char aliveChar = (char) 0x2B1B;
		//assume the grid is squared
		int n = alive.length;

		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				if(alive[i][j])
					System.out.print(aliveChar);
				else
					System.out.print("  ");
			}

			System.out.println("");
		}
	}

	public static boolean isAlive(boolean[][] alive, int i, int j)
	{
		//what do we do with negative indices (or >= n)
		int n = alive.length;
		//i = n -> i -> 0
		//i=n+1->i -> i
		// i=-1 -> i -> n-1

		// + 0 // a + 0 = a
		// * 1
		// a % n = (a+kn) % n

		//a=2, n=3 a%n = 2 4%3 =1

		//assume that i,j >= -n
		int x = (i+n) % n;
		int y = (j+n) % n;

		return alive[x][y];
	}

	public static int countAliveNeighs(boolean[][] alive, int i, int j)
	{
		int nAlive = 0;
		for(int x = i-1; x <= i+1; x++)
		{
			for(int y = j-1; y <= j+1; y++)
			{
				if(x == i && y == j)
					continue;

				if(isAlive(alive, x, y)) //alive[x][y]
					nAlive++;
			}
		}

		return nAlive;
	}

	public static void update(boolean[][] alive)
	{
		//assume the grid is squared
		int n = alive.length; //len(alive)

		boolean[][] newAlive = new boolean[n][n];

		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				int nAlive = countAliveNeighs(alive, i, j);
				if(isAlive(alive, i, j))
				{
					if(nAlive < 2)
						newAlive[i][j] = false;
					else if(nAlive == 2 || nAlive == 3)
						newAlive[i][j] = true;
					else
						newAlive[i][j] = false;
				}
				else
				{
					if(nAlive == 3)
						newAlive[i][j] = true;
					else
						newAlive[i][j] = false;
				}
			}
		}

		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				alive[i][j] = newAlive[i][j];
			}
		}
	}

	public static void main(String[] args) {
		int n = 100;

		boolean[][] alive = new boolean[n][n];
		init(alive, 0.2);
		// alive[0][0] = true;
		// alive[0][1] = true;
		// alive[1][0] = true;
		// alive[1][1] = true;

		while(true)
		{
			print(alive);
			update(alive);

			 try 
			{
			    Thread.sleep(200);
			} 
			catch(InterruptedException e)
			{
			     // this part is executed when an exception (in this example InterruptedException) occurs
			}

					System.out.print("\033[H\033[2J");
			System.out.flush();
		}
	}
}