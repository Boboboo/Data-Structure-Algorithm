import java.util.*;
import java.util.Map.Entry;

public class Trie {
	class Node
	{	
			Node[] NextNodes=new Node[128];
			Boolean isWord=false;
			//Suppose 'APP' is synonym of "Apple". We can update the record with the key 'Apple' in the hashmap
			String synonym = null;                 
			String path = null;
	}
	
	Node root=null;
	
	private Trie()
	{
		root=new Node();
	}
	
	public static Trie CreateTrie()
	{
		
		return new Trie();
	}
	
	public boolean Add(String s)
	{
		Node current = root;
		for(int i = 0; i < s.length(); i++) 
		{
			char temp = s.charAt(i);
			if(current.NextNodes[temp] == null)
			{
				current.NextNodes[temp] = new Node();	
			}
			current = current.NextNodes[temp];	
			if(current.path==null)current.path=new String(s.substring(0,i)+temp);
		}
		current.isWord = true;
		return true;
	}
	
	public int SearchWord(String s)
	{
		Node current=root;
		int count=0;
		for(int i=0;i<s.length();i++)
		{
			char temp=s.charAt(i);
			if(current.NextNodes[temp]==null){return -1;}
			current=current.NextNodes[temp];
			count++;
		}
		return (current.isWord)?count:-1;
	}
        
        public boolean SetSynonym(String target, String synonym)
	{
		if(SearchWord(target)==-1)return false;
		
		Node current=root;
		for(int i=0;i<synonym.length();i++)
		{
			char temp=synonym.charAt(i);
			if(current.NextNodes[temp]==null){return false;}
			current=current.NextNodes[temp];
		}
		if(current.isWord){current.synonym=target;return true;}
		else return false;
	}
	
	public TrieIterator GetTrieIterator()
	{
		return new TrieIterator();
	}
	
	public class TrieIterator implements Iterator<Node>
	{
		Node IteratorRoot=null;
		Queue<Node> queue=null;
		Node temp=null;
		public TrieIterator()
		{
			this.IteratorRoot=root;
			queue=new LinkedList<Node>();
			queue.add(IteratorRoot);
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(queue.size()>0)return true;
			else              return false;
		}

		@Override
		public Node next() {
			// TODO Auto-generated method stub
			temp=queue.poll();
			for(int i=0;i<temp.NextNodes.length;i++)
			{
				if(temp.NextNodes[i]!=null)queue.add(temp.NextNodes[i]);
			}
			return temp;
		}
	}
	
//        Searching a line.
//        Matching the longest word in the trie first.
//        E.g, having names "iPhone 7" and "iPhone 7 Plus",
//        and having an article "The iPhone 7 Plus features additional capabilities.",
//        SearchALine will correctly match "iPhone 7 Plus",
//        rather than wrongly match "iPhone 7".
        public int SearchALine(String s,HashMap<String,Integer> map)
        {
            int subtracted = 0;//        To be subtracted from total word count,
                            //        since company names containing more than one word should be count as one.
                            //        E.g, having a name "iPhone 7 Plus",
                            //        and having an article "The iPhone 7 Plus features additional capabilities.",
                            //        should result in a relevance of 1/5, or 20%.
            
            s = s.replaceAll("[^A-Za-z0-9 ]", "");
            String[] words = s.split(" ");
            for (int i = 0; i < words.length; i++)
            {
                words[i] = words[i].replaceAll("[^A-Za-z0-9]", "");
            }
            for (int i = 0; i < words.length; i++)
            {
                for (int j = words.length; j > i; j--)
                {
                    String word = words[i];
                    for (int k = i + 1; k < j; k++)
                    {
                        word += " " + words[k];
                    }
                    Node current=root;
                    String result = "";
                    for (int k = 0; k < word.length(); k++)
                    {
                        char temp = word.charAt(k);
                        if(current.NextNodes[temp] != null)
                        {
                            result += temp;
                            current = current.NextNodes[temp];
                        }
                    }
                    if (result.equals(word))
                    {
                        if (current.isWord)
                        {
                            String key = current.synonym == null ? result : current.synonym;
                            int frequency=map.get(key);
                            frequency++;
                            map.put(key, frequency);
                            String[] count = result.split(" ");
                            subtracted += count.length - 1;
                            i = j - 1;
                        }
                    }
                }
            }
            return subtracted;
        }

}
