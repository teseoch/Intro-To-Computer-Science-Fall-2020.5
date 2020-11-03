public class Rect {
	private double width;
	private double height;
	private double area;

	public Rect(double width, double height)
	{
		this.width = width;
		this.height = height;

		computeArea();
	}

	public Rect(double squareSide)
	{
		this(squareSide, squareSide);
	}

	public Rect()
	{
		// Rect(0, 0);
		//new Rect(0, 0);
		this(0, 0);
		/*this(0, 0) ->
		this.width = width;
		this.height = height;
		*/
	}

	public double area()
	{
		return area;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
		computeArea();
	}

	private void computeArea()
	{
		area = width * height;
	}


	public static void main(String[] args)
	{
		Rect r1 = new Rect(10.0, 20.0);
		// area = 200
		// r1.getWidth()

		r1.width = 30.0;
		r1.computeArea();
		// area = 200
		r2.setWidth(30.0);
		// area = 600


		Rect r2 = new Rect(20.0, 40.0);
		r2.width
	}

}