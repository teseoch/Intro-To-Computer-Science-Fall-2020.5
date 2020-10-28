class Circle {
	private double radiusSquare = 1;
	private double x =0,y=0;
	private String color = "";

	static int numberOfCircles = 0;

	Circle()
	{
		this(1.0);
		//new Circle(1)
	}

	Circle(double newR)
	{
		this(newR, "black");
		//new Circle(newR, "black")
	}

	Circle(String color)
	{
		this(1.0, color);
		//new Circle(newR, "black")
	}

	// Circle(double newR, String color)
	// {
	// 	numberOfCircles++;
	// 	this.radiusSquare = newR*newR;
	// 	x = 0;
	// 	y = 0;
	// 	this.color = color;
	// }

	Circle(double newR, String newColor)
	{
		numberOfCircles++;
		this.radiusSquare = newR*newR;
		this.x = 0;
		this.y = 0;
		this.color = newColor;
	}



	double getArea()
	{
		return this.radiusSquare*3.14;
	}

	double getPerimeter()
	{
		return Math.sqrt(this.radiusSquare)*2*3.14;
	}

	double getRadius()
	{
		return Math.sqrt(this.radiusSquare);
	}

	Circle setRadius(double newR)
	{
		radiusSquare = newR*newR;

		return this;
	}

	String getName(){
		return "Circle";
	}




	public static void main(String[] args) {
		Circle c1;
		Circle c2;

		System.out.println(Circle.numberOfCircles);


		c1 = new Circle(3.4);
		System.out.println(Circle.numberOfCircles);
		c2 = new Circle();
		System.out.println(Circle.numberOfCircles);

		System.out.println(c1.getRadius());
		// System.out.println(c2.radius);
		// System.out.println(c2.color);

		// c2.radiusSquare = 10;
		c2.setRadius(10);
		c2.setRadius(40);

		c2.setRadius(10).getName();

		//c2.setRadius(10) == c2

		// System.out.println(c2.getRadius());

		double a1 = c1.getArea();
		double a2 = c2.getArea();
		// System.out.println(a1);
		// System.out.println(a2);

		// System.out.println(c2.getPerimeter());
	}
}