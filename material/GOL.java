public class GOL {
	public static void init(boolean[][] alive, double alivePerc) {
		// Initialize the grid : we start with a random seed
		// no. of alive cells is proportional to alivePerc
		// assume the grid is squared(n*n)
		int n = alive.length; // the dimensions of the grid

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				alive[i][j] = Math.random() < alivePerc;
			}
		}
	}

	public static void print(boolean[][] alive) {

		// This method prints the grid, we use U+2B1B(⬛) to represent a live cell
		// and two spaces for a dead cell.

		char aliveChar = (char) 0x2B1B;
		// assume the grid is squared
		int n = alive.length;

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (alive[i][j])
					System.out.print(aliveChar);
				else
					System.out.print("  ");
			}

			System.out.println("");
		}
	}

	public static boolean isAlive(boolean[][] alive, int i, int j) {

		// This method returns the state(alive or dead) of the cell i,j in the grid, it
		// also takes care
		// of the edges

		int n = alive.length;

		// assume that i,j >= -n
		// If we get an index >= n, then we need
		// If we get a negative index, then the smallest it can be is
		// -1, in which case we need to transform it to n-1
		// On the other hand, if we get an index greater than n-1, then we need to roll
		// it back to the 0
		// for example n becomes 0
		// if the index was initially negative, then adding n to it would clearly make
		// it positive
		// also, note that if the index was positive, then adding n wouldn't change the
		// modulo operation
		// a % n = (a+kn) % n
		int x = (i + n) % n;
		int y = (j + n) % n;

		return alive[x][y];
	}

	public static int countAliveNeighs(boolean[][] alive, int i, int j) {
		// This counts all the(immediate) neighbours of the cell i,j, and returns the
		// number of all the
		// alive neighbours
		int nAlive = 0;
		for (int x = i - 1; x <= i + 1; x++) {
			for (int y = j - 1; y <= j + 1; y++) {
				if (x == i && y == j)
					continue;

				if (isAlive(alive, x, y))
					nAlive++;
			}
		}

		return nAlive;
	}

	public static void update(boolean[][] alive) {
		// This method is responsible to update the grid based on the rules

		// Rules : If an alive cell has :-

		// 1. < 2 alive neighbours : it dies
		// 2. 2 or 3 alive neighbours : it survives
		// 3. > 3 alive neighbours : it dies

		// If a dead cell has :-
		// 1. exactly 3 alive neighbours : it becomes alive

		// assume the grid is squared
		int n = alive.length; // len(alive)

		// We create a new grid called newAlive, we make all the changes in newAlive
		// since, if we read from the same grid that we write to, it would hinder our
		// simulation.
		// Hence, we read from the older grid, and apply the changes to the newer grid
		// and overwrite the older grid with the newer grid

		boolean[][] newAlive = new boolean[n][n];

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				int nAlive = countAliveNeighs(alive, i, j);
				if (isAlive(alive, i, j)) {
					if (nAlive < 2)
						newAlive[i][j] = false;
					else if (nAlive == 2 || nAlive == 3)
						newAlive[i][j] = true;
					else
						newAlive[i][j] = false;
				} else { // The cell is dead
					if (nAlive == 3)
						newAlive[i][j] = true;
					else
						newAlive[i][j] = false;
				}
			}
		}

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				alive[i][j] = newAlive[i][j]; // overwrite the older grid with the newer grid
			}
		}
	}

	public static void main(String[] args) {
		int n = 100; // the size of our grid, we create a a grid of n rows and n columns

		boolean[][] alive = new boolean[n][n];
		init(alive, 0.2); // we start with 20% alive cells

		while (true) { // This is our main loop, we update and print the grid each iteration
			print(alive);
			update(alive);

			// Wait 200 ms between each iteration to slow down the animation
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// this part is executed when an exception (in this example
				// InterruptedException) occurs
			}

			// Clear out the screen each iteration and flush the terminal,
			// This prints the grid on the same place every time, hence allowing the
			// animation
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
	}
}