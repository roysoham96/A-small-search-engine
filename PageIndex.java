public class PageIndex
{
	MyLinkedList<WordEntry> w;

	public PageIndex()
	{
		w = new MyLinkedList<WordEntry>();
	}

	public void addPositionForWord(String str, Position p)
	{
		boolean found = false;
		Node<WordEntry> temp = w.returnTop();
		while (temp!=null)
		{
			if (temp.data.returnWord().equals(str))
			{
				found=true;
				temp.data.getAllPositionsForThisWord().Insert(p);
				break;
			}
			else
				temp=temp.next;
		}
		if (!found)
		{
			WordEntry new_word = new WordEntry(str);
			new_word.getAllPositionsForThisWord().Insert(p);
			w.Insert(new_word);
		}
	}

	public MyLinkedList<WordEntry> getWordEntries()
	{
		return w;
	}
}