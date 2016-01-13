import java.io.*;
import java.util.*;

public class PageEntry
{
	PageIndex p;
	String pageName;
	AVLTree t;

	public PageEntry(String pageName)
	{
		t = new AVLTree();
		ArrayList<String> a = new ArrayList<String>();
		a.add("a");
		a.add("an");
		a.add("the");
		a.add("they");
		a.add("these");
		a.add("this");
		a.add("for");
		a.add("is");
		a.add("are");
		a.add("was");
		a.add("of");
		a.add("or");
		a.add("and");
		a.add("does");
		a.add("will");
		a.add("whose");

		int count=0;
		this.pageName = pageName;
		p = new PageIndex();
		FileInputStream fstream;
		Scanner s;
		try
		{
			fstream = new FileInputStream("webpages/" + pageName);
			s = new Scanner (fstream).useDelimiter("[ \\[\\]\n\t{}<>=().,;:\'\"?#!-]");
			while (s.hasNext())
			{
				String str = s.next().toLowerCase();
				str = str.replaceAll("[ \\[\\]\n\t{}<>=().,;:\'\"?#!-]", "");
            	if (!str.equals(""))
            	{
            		count++;
	            	if (str.equals("stacks"))
	            		str="stack";
	            	else if (str.equals("applications"))
	            		str="application";
	            	else if (str.equals("structures"))
	            		str="structure";
	            	if (!a.contains(str))
	            	{
	            		Position temp = new Position(this, count);
	            		p.addPositionForWord(str, temp);
	            		temp.word = str;
	            		t.Insert(temp, count);
	            	}
	            }
        	}
		}
		catch (IOException e)
		{
			System.out.println("No webpage with name " + pageName + " exists in the directory");
		}
	}

	public float getRelevanceOfPage(String str[], boolean doTheseWordsRepresentAPhrase)
	{
		float relevance=0;
		if (doTheseWordsRepresentAPhrase)
		{
			String word = str[0];
			if (word.equals("stacks"))
            	word="stack";
        	else if (word.equals("applications"))
       			word="application";
       		else if (word.equals("structures"))
       			word="structure";
			Node<WordEntry> n = p.getWordEntries().returnTop();
			Node<Position> pos = new Node<Position>();
			while (n!=null)
			{
				if (n.data.returnWord().equals(word))
				{
					pos = n.data.getAllPositionsForThisWord().returnTop();
					break;
				}
				n=n.next;
			}
			while (pos!=null)
			{
				int index = pos.data.WordIndex();
				AVLNode temp = t.inorderSuccessor(index);
				int len = str.length;
				int i;
				for (i=1; i<len; i++)
				{
					String other = str[i];
					if (other.equals("stacks"))
            			other="stack";
        			else if (other.equals("applications"))
       					other="application";
       				else if (other.equals("structures"))
       					other="structure";
					if (temp.data.word.equals(other))
						temp = t.inorderSuccessor(temp.data.WordIndex());
					else
						break;
				}
				if (i==len)
					relevance+= 1.0/(index*index);
				pos=pos.next;
			}
		}
		else
		{
			int len = str.length;
			for (int i=0; i<len; i++)
			{
				String word = str[i];
				if (word.equals("stacks"))
            		word="stack";
        		else if (word.equals("applications"))
       				word="application";
       			else if (word.equals("structures"))
       				word="structure";
				Node<WordEntry> n = p.getWordEntries().returnTop();
				while (n!=null)
				{
					if (n.data.returnWord().equals(word))
					{
						Node<Position> p = n.data.getAllPositionsForThisWord().returnTop();
						while (p!=null)
						{
							int index = p.data.WordIndex();
							relevance+= 1.0/(index*index);
							p=p.next;
						}
					}
					n=n.next;
				}
			}
		}
		return relevance;
	}
	
	public PageIndex getPageIndex()
	{
		return p;
	}

	public String getPageName()
	{
		return pageName;
	}
}