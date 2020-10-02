public class MCPi {
	public static void main(String[] args)
	{
		double xCoord, yCoord;
		double distance;
		boolean isInside;
		long numThrows = 0;
		double pi;
		long insides = 0;

		//p=(x,y)
		//||p|| <= 1, is inside
		// for(numThrows = 0; numThrows < 1000000; ++numThrows)
		while(true)
		{
			xCoord = Math.random()*2-1; //-1,1
			yCoord = Math.random()*2-1; //-1,1

			// System.out.println(xCoord + " " + yCoord);

			distance = Math.sqrt(xCoord*xCoord + yCoord*yCoord);
			isInside = distance <= 1;

			if(isInside)
				insides++;
			// System.out.println(distance + " "+isInside);
			numThrows++;

			if(numThrows % 10000000 == 0){
				pi = (4.0 * insides) / numThrows;
				System.out.println(pi);
			}
		}


	}
}