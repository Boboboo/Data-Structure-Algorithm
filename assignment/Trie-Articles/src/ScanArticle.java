import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class ScanArticle 
{

	FileReader vocabulary_file_reader=null;
	HashMap<String, Integer> map=null;
	Trie trie=null;
        Integer totalWords = 0;
        int subtracted = 0;//        To be subtracted from totalWords,
                        //        since company names containing more than one word should be count as one.
                        //        E.g, having a name "iPhone 7 Plus",
                        //        and having an article "The iPhone 7 Plus features additional capabilities.",
                        //        should result in a relevance of 1/5, or 20%.
	
	public ScanArticle(FileReader vocabulary_file_reader)
	{
		this.vocabulary_file_reader=vocabulary_file_reader;
		this.trie=Trie.CreateTrie();
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		//Read the Company names and synonyms from vocabulary_file_reader. And then construct the trie
		try {
			BufferedReader input = new BufferedReader(this.vocabulary_file_reader);

			String line;

			while ((line = input.readLine()) != null) {
                                line = line.replaceAll("[^A-Za-z0-9 \t]", "");
                                if(line.contains("\t")) {
                                    String[] words = line.split("\t");
                                    for (int i = 0; i < words.length; i++) {
                                        words[i] = words[i].replaceAll("[^A-Za-z0-9 ]", "");
                                    }
                                    this.trie.Add(words[0]);
                                    for (int i = 1; i < words.length; i++) {
                                        this.trie.Add(words[i]);
                                        this.trie.SetSynonym(words[0], words[i]);
                                    }
                                }
                                else {
                                    this.trie.Add(line);
                                }
			}
			input.close();
		} catch (Exception ex) {
			System.err.println("Error: " + ex.getMessage());
		}
		Trie.TrieIterator iter=trie.GetTrieIterator();
		this.map=new HashMap<String,Integer>();
		while(iter.hasNext())
		{
			Trie.Node temp=iter.next();
			if(temp.isWord)// make sure only display the node path which the node is the end of a word
			{
				if(temp.synonym==null)  //if synonym cannot be found, display this node path
				map.put(temp.path, 0);
				else					//Otherwise, display the synonym
				map.put(temp.synonym,0);
			}
		}
	}
	
//	Reading the news article from console.
        public HashMap<String,Integer> Scan()
	{
		
                BufferedReader buffered_reader = new BufferedReader(new InputStreamReader(System.in));
		String ArticleLine=null;
		try
		{
			while((ArticleLine=buffered_reader.readLine())!=null)
			{
                            if (ArticleLine.equals(".")) {
                                return this.map;
                            }
                            else {
				this.subtracted += trie.SearchALine(ArticleLine, this.map);
                                String[] words = ArticleLine.replaceAll("[^A-Za-z0-9 ]", "").split(" ");
                                for (int i = 0; i < words.length; i++) {
                                        if (!words[i].matches("a|an|the|and|or|but")) {
                                            this.totalWords++;
                                        }
                                    }
                            }
			}
		}
		catch(IOException e)
		{
			System.out.println("Error when loading article");
		}	
		
		return this.map;
	}
        
        public Integer countWords()
	{
		return this.totalWords - this.subtracted;
	}
}


