public class MyLinkedList<X>
{
	Node<X> top;
	public MyLinkedList()
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

	public boolean isMember(Node<X> element)
	{
		Node<X> temp = top;
		while (temp!=null)
		{
			if (temp==element)
				return true;
			temp=temp.next;
		}
		return false;
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

	public void Insert(X element)
	{
		Node<X> temp = new Node<X>();
		temp.data=element;
		temp.next=top;
		top=temp;
	}

	public void Insert(Node<X> element)
	{
		Node<X> temp = new Node<X>();
		temp.data=element.data;
		temp.next=top;
		top=temp;
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
}