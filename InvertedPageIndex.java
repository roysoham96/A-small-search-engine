public class InvertedPageIndex
{
	MyHashTable h;
	public InvertedPageIndex()
	{
		h = new MyHashTable();
	}
	public void addPage(PageEntry p)
	{
		PageIndex ind = p.getPageIndex();
		Node<WordEntry> l = ind.getWordEntries().returnTop();
		while (l!=null)
		{
			WordEntry w = l.data;
			h.addPositionsForWord(w);
			l=l.next;
		}
	}
	public MySet<PageEntry> getPagesWhichContainWord(String str)
	{
		MySet<PageEntry> s = new MySet<PageEntry>();
		int i = h.returnKeyFromWord(str);
		MyLinkedList<WordEntry> nodes = h.arr.get(i);
		Node<WordEntry> node = nodes.returnTop();
		while (node!=null)
		{
			if (node.data.str.equals(str))
			{
				Node<Position> pos = node.data.getAllPositionsForThisWord().returnTop();	
				while (pos!=null)
				{
					s.addElement(pos.data.getPageEntry());
					pos=pos.next;
				}
			}
			node=node.next;
		}
		return s;
	}

	public MySet<PageEntry> getPagesWhichContainPhrase(String str[])
	{
		MySet<PageEntry> s = new MySet<PageEntry>();
		return s;
	}

	public void printPagesWhichContainWord(String str)
	{
		MySet<PageEntry> r = getPagesWhichContainWord(str);
		Node<PageEntry> t = r.returnTop();
		int n = r.numElements();
		if (n==0)
			System.out.println("No webpage contains word " + str);	
		else
		{
			for (int i=0; i<n-1; i++)
			{
				System.out.print(r.returnElement(i).data.getPageName() + ", ");
			}
			System.out.println(r.returnElement(n-1).data.getPageName());
		}
	}
}