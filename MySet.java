public class MySet<X>
{
	Node<X> top;
	public MySet()
	{
		top=null;
	}

	public int numElements()
	{
		int count=0;
		Node<X> temp = top;
		while (temp!=null)
		{
			temp=temp.next;
			count++;
		}
		return count;
	}

	public Node<X> returnElement(int i)
	{
		Node<X> temp = top;
		for (int x=0; x<i; x++)
		{
			temp=temp.next;
		}
		return temp;
	}

	public Node<X> returnTop()
	{
		return top;
	}

	public boolean isEmpty()
	{
		return (top==null);
	}

	public boolean isMember(X element)
	{
		Node<X> temp = top;
		while (temp!=null)
		{
			if (temp.data==element)
				return true;
			temp=temp.next;
		}
		return false;
	}

	public void addElement(X element)
	{
		if (!isMember(element))
		{
			Node<X> temp = new Node<X>();
			temp.data=element;
			temp.next=top;
			top=temp;
		}
	}

	public void Delete(X element)
	{
		if (top.next==null && top.data==element)	//Only 1 element and matching
			top=null;
		else
		{
			Node<X> temp = top;
			while (temp.next!=null)
			{
				if (temp.next.data==element)
				{
					temp.next=temp.next.next;
					break;
				}
				else
					temp=temp.next;
			}
		}
	}

	public MySet<X> union(MySet<X> otherSet)
	{
		MySet<X> b = new MySet<X>();
		b.top = otherSet.returnTop();
		Node<X> temp = top;
		while (temp!=null)
		{
			if (!b.isMember(temp.data))
				b.addElement(temp.data);
			temp = temp.next;
		}
		return b;
	}

	public MySet<X> intersection(MySet<X> otherSet)
	{
		MySet<X> b = new MySet<X>();
		Node<X> temp = top;
		while (temp!=null)
		{
			if (otherSet.isMember(temp.data))
				b.addElement(temp.data);
			temp = temp.next;
		}
		return b;	
	}
}