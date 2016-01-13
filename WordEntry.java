public class WordEntry
{
	MyLinkedList<Position> l;
	String str;

	public WordEntry(String word)
	{
		str=word;
		l = new MyLinkedList<Position>();
	}

	public String returnWord()
	{
		return str;
	}

	public void addPosition(Position position)
	{
		l.Insert(position);
	}

	public void addPositions (MyLinkedList<Position> positions)
	{
		Node<Position> temp = positions.returnTop();
		while (temp!=null)
		{
			l.Insert(temp.data);
			temp=temp.next;
		}
	}

	public MyLinkedList<Position> getAllPositionsForThisWord()
	{
		return l;
	}
}