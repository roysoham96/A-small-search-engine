public class SearchResult implements Comparable<SearchResult>
{
	PageEntry page;
	float rel;
	public SearchResult(PageEntry p, float r)
	{
		page = p;
		rel = r;
	}

	public PageEntry getPageEntry()
	{
		return page;
	}

	public float getRelevance()
	{
		return rel;
	}

	@Override
	public int compareTo(SearchResult otherObject)
	{
		if (rel<otherObject.getRelevance())
			return 1;
		else if (rel==otherObject.getRelevance())
			return 0;
		else
			return -1;
	}
}