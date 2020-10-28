public class Grid {
	//properties/fields/varibles
	private boolean[][] alive;
	private int size;

	public Grid(int size)
	{
		this(size, 0);
	}


	//intilize the grid with size size and alive% of cells
	public Grid(int size, double alivePerc)
	{
		//initialize all fields

		//in this scope, there is only 1 alive; the field
		//using or not the this dosent matter
		alive = new boolean[size][size];

		//in this scope there are 2 sizes, one the field
		//one the parameter.
		//if we do size = size, we just "change" the parameter
		//the filed will not be touch

		//instead if we do this
		//this.size refers to the field
		this.size = size;


		// this.size = alive.length;


		// this.init(alivePerc);
		init(alivePerc);
	}

	public void init(double alivePerc)
	{
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				alive[i][j] = Math.random() < alivePerc;
			}
		}
	}


	public void print()
	{
		// This method prints the grid, we use U+2B1B(⬛) to represent a live cell
		// and two spaces for a dead cell.

		char aliveChar = (char) 0x2B1B;

		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				if (alive[i][j])
					System.out.print(aliveChar);
				else
					System.out.print("  ");
			}

			System.out.println("");
		}
	}

	public boolean isAlive(int i, int j) {

		// This method returns the state(alive or dead) of the cell i,j in the grid, it
		// also takes care
		// of the edges

		int n = size;

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

	public int countAliveNeighs(int i, int j) {
		// This counts all the(immediate) neighbours of the cell i,j, and returns the
		// number of all the
		// alive neighbours
		int nAlive = 0;
		for (int x = i - 1; x <= i + 1; x++) {
			for (int y = j - 1; y <= j + 1; y++) {
				if (x == i && y == j)
					continue;

				if (isAlive(x, y))
					nAlive++;
			}
		}

		return nAlive;
	}

	public void update() {
		// This method is responsible to update the grid based on the rules

		// Rules : If an alive cell has :-

		// 1. < 2 alive neighbours : it dies
		// 2. 2 or 3 alive neighbours : it survives
		// 3. > 3 alive neighbours : it dies

		// If a dead cell has :-
		// 1. exactly 3 alive neighbours : it becomes alive

		// assume the grid is squared
		int n = size; // len(alive)

		// We create a new grid called newAlive, we make all the changes in newAlive
		// since, if we read from the same grid that we write to, it would hinder our
		// simulation.
		// Hence, we read from the older grid, and apply the changes to the newer grid
		// and overwrite the older grid with the newer grid

		boolean[][] newAlive = new boolean[n][n];

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				int nAlive = countAliveNeighs(i, j);
				if (isAlive(i, j)) {
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


	public static void main(String[] args)
	{
		//type name;

		//int grid;
		Grid grid;

		//here we have a variable with no value

		//grid = 1;
		grid = new Grid(10);

		//here we have a variable with some value

		//grid += 1
		grid.init(0.2);


		while (true) { // This is our main loop, we update and print the grid each iteration
			grid.print();
			grid.update();

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





