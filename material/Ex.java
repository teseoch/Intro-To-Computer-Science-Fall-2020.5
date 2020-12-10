import java.io.IOException;


public class Ex {
	public static void main(String[] args) {
		Ex ex = new Ex();
		try {
			ex.method(5,2);
			ex.method(2);
			ex.method(5);
			ex.method(3);
		}
		catch(IOException e)
		{
			System.out.println("Something went wrong");
			System.out.println(e);
		}

		
	}

	public void method(int x, int y) throws IOException
	{
		method(x);
	}




	public void method(int x) throws IOException
	{
		if(x == 5) //this bad
		{
			IOException ex = new IOException();
			throw ex;
		}

		System.out.println("all good" + x);
	}
}