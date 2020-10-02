class GOL {

	public static void init(boolean[][] alive, double percentage)
	{
		//assume that alive is squared!
		int n = alive.length;

		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				alive[i][j] = Math.random() < percentage;
			}
		}
	}

	public static void print(boolean[][] alive)
	{
		//assume that alive is squared!
		int n = alive.length;

		int c = 0x2B1B;
		char code = (char)c;

		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				if(alive[i][j])
				{
					System.out.print(code);
				}
				else
				{
					System.out.print("  ");
				}
			}

			System.out.println();
		}
	}

	public static boolean isAlive(boolean[][] alive, int i, int j)
	{
		//assume that alive is squared!
		//assume that i > -n
		int n = alive.length;
		//i=-1

		//(x + kn) % n = x % n


		int x = (i + n) % n;
		int y = (j + n) % n;

		return alive[x][y];
	}

	public static int aliveNeigh(boolean[][] alive, int i, int j)
	{
		int aliveN = 0;
		for(int x = i - 1; x <= i + 1; ++x)
		{
			for(int y = j - 1; y <= j + 1; ++y)
			{
				if(x == i && y == j)
					continue;

				if(isAlive(alive,x, y))
					aliveN++;
			}
		}

		return aliveN;
	}

	public static void update(boolean[][] alive)
	{
		//assume that alive is squared!
		int n = alive.length;

		boolean[][] next = new boolean[n][n];

		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				int nAlive = aliveNeigh(alive, i, j);

				//current cell is alive
				if(alive[i][j])
				{
					if(nAlive < 2)
						next[i][j] = false;
					else if(nAlive == 2 || nAlive == 3)
						next[i][j] = true;
					else
						next[i][j] = false;
				}
				else//the current cell is dead
				{
					if(nAlive == 3)
						next[i][j] = true;
					else
						next[i][j] = false;
				}
			}
		}

		for(int i = 0; i < n; ++i)
		{
			for(int j = 0; j < n; ++j)
			{
				alive[i][j] = next[i][j];
			}
		}
	}

	public static void main(String[] args)
	{
		int n = 100;


		boolean[][] alive = new boolean[n][n];
		init(alive, 0.3);
		// // alive[0][0] = true;
		// alive[1][1] = true;
		// alive[1][2] = true;
		// alive[2][1] = true;
		// alive[2][2] = true;
		// // alive[0][2] = true;

		while(true)
		{
			print(alive);
			update(alive);

			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException ex)
			{
				Thread.currentThread().interrupt();
			}

			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
	}
}