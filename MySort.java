import java.io.*;
import java.util.*;
public class MySort
{
	public ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfSortableEntries)
	{
		ArrayList<SearchResult> arr = new ArrayList<SearchResult>();
		Node<SearchResult> n = listOfSortableEntries.returnTop();
		while (n!=null)
		{
			arr.add(n.data);
			n=n.next;
		}
		Collections.sort(arr);
		return arr;
	}
}