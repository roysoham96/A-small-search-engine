public class Position
{
	PageEntry p;
	public String word;
	int index;

	public Position(PageEntry page, int WordIndex)
	{
		p = page;
		index = WordIndex;
	}

	public String getWord()
	{
		return word;
	}

	public PageEntry getPageEntry()
	{
		return p;
	}

	public int WordIndex()
	{
		return index;
	}
}