import java.util.*;
public class MyHashTable
{
	ArrayList <MyLinkedList<WordEntry>> arr;
	public MyHashTable()
	{
		arr = new ArrayList<MyLinkedList<WordEntry>>();
		for (int i=0; i<100; i++)
			arr.add(new MyLinkedList<WordEntry>());
	}
	private int getHashIndex(String str)
	{		
		int index=0;
		int len = str.length();
		for (int i=0; i<len; i++)
		{
			int n = (int) str.charAt(i);
			int m = (int) (Math.pow(n-97, i)%100);
			if (m<0)
				m=(-1)*m;
			index=(index+((i+1)*m))%100;	//Polynomial hash function
		}
		return index;
	}

	public void addPositionsForWord(WordEntry w)
	{
		String word = w.returnWord();
		int i = getHashIndex(word);
		arr.get(i).Insert(w);
	}

	public int returnKeyFromWord(String str)
	{
		return getHashIndex(str);		
	}
}