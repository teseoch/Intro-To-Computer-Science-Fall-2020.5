public class StackOfInts {
	private int[] elements; //bucket
	private int size; //"amout of liquid"


	public StackOfInts()
	{
		this(10);
	}

	public StackOfInts(int capacity)
	{
		size = 0;
		elements = new int[capacity];
	}

	public boolean empty()
	{
		return size == 0;
	}

	public int peek()
	{
		//if size = 1, the top is at index 0
		return elements[size-1];
	}

	public void push(int value)
	{
		// //if stack is empty, size = 0
		// elements[0] = value;

		// //if stack has 1 element, size = 1
		// elements[1] = value;

		//if full means elements.length <= size, TODO
		//if full means size >= elements.length, TODO
		//is the bucket full?
		if(full means size >= elements.length)
		{
			//creates a new bucket
			int[] largerElements = new int[elements.length*2];

			//copy the current data to the new bucket
			for(int i = 0; i < elements.length; ++i)
			{
				largerElements[i] = elements[i];
			}

			elements = largerElements;
		}

		elements[size] = value;
		size++;
	}

	public int pop()
	{
		int topElement = peek();
		elements[size-1] = 0;
		--size;

		return topElement;
	}

	public int size()
	{
		return size;
	}
}