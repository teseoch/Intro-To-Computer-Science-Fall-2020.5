public class X {
	private Rect rect1;
	private Rect rect2;

	public X()
	{
		this(0, 0);
	}

	public X(double w, double h)
	{
		rect1 = new Rect(w, h);
		rect2 = new Rect(w, h);
	}

	public static void main(String[] args)
	{
		X x = new X(10, 10)

		x.rect1.setWidth(10);
		x.rect2.area();
		// Rect r1 = new Rect(10.0, 20.0);
		// // area = 200
		// // r1.getWidth()

		// // area = 200
		// r1.setWidth(30.0);
		// // area = 600


		// Rect r2 = new Rect(20.0, 40.0);
	}

}