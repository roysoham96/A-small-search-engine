import java.io.*;
import java.util.*;
public class SearchEngine
{
	InvertedPageIndex p;
	MyLinkedList<String> l;
	public SearchEngine()
	{
		l = new MyLinkedList<String>();
		p = new InvertedPageIndex();
	}

	public void addPage(String a) throws MyException
	{
		PageEntry page = new PageEntry(a);
		p.addPage(page);
		l.Insert(a);
	}

	public void findPagesWhichContainWord(String a) throws MyException
	{
		if (a.equals("stacks"))
            a="stack";
        else if (a.equals("applications"))
        	a="application";
        else if (a.equals("structures"))
            a="structure";
		p.printPagesWhichContainWord(a);
	}

	public void findPositionsOfWordInAPage(String a, String b) throws MyException
	{
		boolean found=false;
		
		Node<String> temp = l.returnTop();
		while (temp!=null)
		{
			if (temp.data.equals(b))
			{
				found=true;
				break;
			}
			else
				temp=temp.next;
		}
		if (!found)
			throw new MyException("Webpage " + b + " does not exist");
		else
		{
			if (a.equals("stacks"))
	            a="stack";
	        else if (a.equals("applications"))
	        	a="application";
	        else if (a.equals("structures"))
	            a="structure";
			Node<PageEntry> node = p.getPagesWhichContainWord(a).returnTop();
			while (node!=null)
			{
				if (node.data.getPageName().equals(b))
					break;
				else
					node=node.next;
			}
			if (node==null)
				throw new MyException("Webpage " + b + " does not contain the word " + a);
			else
			{
				MyLinkedList<WordEntry> entries = node.data.getPageIndex().getWordEntries();
				Node<WordEntry> entry = entries.returnTop();
				while (entry!=null)
				{
					if (entry.data.str.equals(a))
					{
						MyLinkedList<Position> k = entry.data.getAllPositionsForThisWord();
						int n = k.numElements();
						int pos = 0;
						for (int i=0; i<n-1; i++)
						{
							pos = k.returnElement(i).data.WordIndex();
							System.out.print(pos + ", ");
						}
						pos = k.returnElement(n-1).data.WordIndex();
						System.out.println(pos);
					}
					entry = entry.next;
				}
			}
		}
	}

	public void findPagesWhichContainAllWords(String words[]) throws MyException
	{
		int len = words.length;
		String a = new String();
		ArrayList<SearchResult> arr = new ArrayList<SearchResult>();
		MySet<PageEntry> s = new MySet<PageEntry>();
		MySet<SearchResult> t = new MySet<SearchResult>();

		a = words[0];
		if (a.equals("stacks"))
            a="stack";
        else if (a.equals("applications"))
       		a="application";
       	else if (a.equals("structures"))
       		a="structure";
		s = p.getPagesWhichContainWord(a);

		for (int i=1; i<len; i++)
		{
			a = words[i];
			if (a.equals("stacks"))
            	a="stack";
        	else if (a.equals("applications"))
        		a="application";
        	else if (a.equals("structures"))
        		a="structure";
        	MySet<PageEntry> q = p.getPagesWhichContainWord(a);
        	s=s.intersection(q);
        }

        Node<PageEntry> n = s.returnTop();
        while (n!=null)
        {
        	float k = n.data.getRelevanceOfPage(words, false);
        	SearchResult sr = new SearchResult(n.data, k);
        	t.addElement(sr);
        	n=n.next;
        }

        MySort k = new MySort();
        arr = k.sortThisList(t);

        int v = arr.size();
        if (v==0)
			System.out.println("No webpage contains words all these words");
		else
		{
			for (int i=0; i<v-1; i++)
			{
				System.out.print(arr.get(i).getPageEntry().getPageName() + ", ");
			}
			System.out.println(arr.get(v-1).getPageEntry().getPageName());
		}
	}

	public void findPagesWhichContainAnyOfTheseWords(String words[]) throws MyException
	{
		int len = words.length;
		String a = new String();
		ArrayList<SearchResult> arr = new ArrayList<SearchResult>();
		MySet<PageEntry> s = new MySet<PageEntry>();
		MySet<SearchResult> t = new MySet<SearchResult>();

		a = words[0];
		if (a.equals("stacks"))
            a="stack";
        else if (a.equals("applications"))
       		a="application";
       	else if (a.equals("structures"))
       		a="structure";
		s = p.getPagesWhichContainWord(a);

		for (int i=1; i<len; i++)
		{
			a = words[i];
			if (a.equals("stacks"))
            	a="stack";
        	else if (a.equals("applications"))
        		a="application";
        	else if (a.equals("structures"))
        		a="structure";
        	MySet<PageEntry> q = p.getPagesWhichContainWord(a);
        	s=s.union(q);
        }

        Node<PageEntry> n = s.returnTop();
        while (n!=null)
        {
        	float k = n.data.getRelevanceOfPage(words, false);
        	SearchResult sr = new SearchResult(n.data, k);
        	t.addElement(sr);
        	n=n.next;
        }

        MySort k = new MySort();
        arr = k.sortThisList(t);

        int v = arr.size();
        if (v==0)
			System.out.println("No webpage contains words any of these words");
		else
		{
			for (int i=0; i<v-1; i++)
			{
				System.out.print(arr.get(i).getPageEntry().getPageName() + ", ");
			}
			System.out.println(arr.get(v-1).getPageEntry().getPageName());
		}
	}

	public void findPagesWhichContainPhrase(String words[]) throws MyException
	{
		int len = words.length;
		String a = new String();
		ArrayList<SearchResult> arr = new ArrayList<SearchResult>();
		MySet<PageEntry> s = new MySet<PageEntry>();
		MySet<SearchResult> t = new MySet<SearchResult>();

		a = words[0];
		if (a.equals("stacks"))
            a="stack";
        else if (a.equals("applications"))
       		a="application";
       	else if (a.equals("structures"))
       		a="structure";
		s = p.getPagesWhichContainWord(a);

		for (int i=1; i<len; i++)
		{
			a = words[i];
			if (a.equals("stacks"))
            	a="stack";
        	else if (a.equals("applications"))
        		a="application";
        	else if (a.equals("structures"))
        		a="structure";
        	MySet<PageEntry> q = p.getPagesWhichContainWord(a);
        	s=s.intersection(q);
        }

        Node<PageEntry> n = s.returnTop();
        while (n!=null)
        {
        	float k = n.data.getRelevanceOfPage(words, true);
        	if (k>0)
        	{
        		SearchResult sr = new SearchResult(n.data, k);
        		t.addElement(sr);
        	}
        	n=n.next;
        }

        MySort k = new MySort();
        arr = k.sortThisList(t);

        int v = arr.size();
        if (v==0)
			System.out.println("No webpage contains words this phrase");
		else
		{
			for (int i=0; i<v-1; i++)
			{
				System.out.print(arr.get(i).getPageEntry().getPageName() + ", ");
			}
			System.out.println(arr.get(v-1).getPageEntry().getPageName());
		}
	}

	public void validateCommand(String text) throws MyException
	{
		if ((!text.equals("addPage"))&&(!text.equals("queryFindPagesWhichContainWord"))&&(!text.equals("queryFindPositionsOfWordInAPage"))&&(!text.equals("queryFindPagesWhichContainAllWords"))&&(!text.equals("queryFindPagesWhichContainAnyOfTheseWords"))&&(!text.equals("queryFindPagesWhichContainPhrase")))
			throw new MyException();
	}

	public void performAction(String actionMessage)
	{
		String text="";
		String a="";
		String b="";
		int i=0;
		int len = actionMessage.length();
		
		for (int j=0; j<len; j++)
		{	
			if (actionMessage.substring(j, j+1).equals(" "))
				break;
			else
			{
				i++;
				text+=actionMessage.substring(j, j+1);
			}
		}

		try
		{
			validateCommand(text);
			i+=1;
			if (text.equals("addPage"))
			{
				a+=actionMessage.substring(i, len);
				try
				{
					addPage(a);
				}
				catch (MyException p)
				{
					System.out.println();
				}
			}
			else if (text.equals("queryFindPagesWhichContainWord"))
			{
				a+=actionMessage.substring(i, len).toLowerCase().replaceAll("[ \\[\\]\n\t{}<>=().,;:\'\"?#!-]", "");
				try
				{
					System.out.print(actionMessage + ": ");
					findPagesWhichContainWord(a);
				}
				catch (MyException s)
				{
					System.out.println();
				}
			}
			else if (text.equals("queryFindPositionsOfWordInAPage"))
			{
				while (!(actionMessage.substring(i, i+1)).equals(" "))
				{
					a+=actionMessage.substring(i,i+1).toLowerCase().replaceAll("[ \\[\\]\n\t{}<>=().,;:\'\"?#!-]", "");
					i++;
				}
				i+=1;	
				b+=actionMessage.substring(i, len).toLowerCase();
				try
				{
					System.out.print(actionMessage + ": ");
					findPositionsOfWordInAPage(a, b);
				}
				catch (MyException s)
				{
					System.out.println();
				}
			}
			else if (text.equals("queryFindPagesWhichContainAllWords"))
			{
				String[] arr = actionMessage.substring(i, len).split(" ");
				try
				{
					System.out.print(actionMessage + ": ");
					findPagesWhichContainAllWords(arr);
				}
				catch (MyException s)
				{
					System.out.println();
				}
			}
			else if (text.equals("queryFindPagesWhichContainAnyOfTheseWords"))
			{
				String[] arr = actionMessage.substring(i, len).split(" ");
				try
				{
					System.out.print(actionMessage + ": ");
					findPagesWhichContainAnyOfTheseWords(arr);
				}
				catch (MyException s)
				{
					System.out.println();
				}
			}
			else if (text.equals("queryFindPagesWhichContainPhrase"))
			{
				String[] arr = actionMessage.substring(i, len).split(" ");
				try
				{
					System.out.print(actionMessage + ": ");
					findPagesWhichContainPhrase(arr);
				}
				catch (MyException s)
				{
					System.out.println();
				}
			}
		}
		catch (MyException e)
		{
			System.out.println(actionMessage + ": Error - Invalid query/command");
		}
	}
}
