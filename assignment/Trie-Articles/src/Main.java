import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {
    
//    Justifying the output.
    public static String justifyData(String data, int columnWidth) {
        while (data.length() < columnWidth) {
                data += " ";
            }
        return data;
    }
    
//    Calculating the column width.
    public static int getColumnWidth(ArrayList<String> array, int degree, int column) {
        int maxWidth = 0;
        for (int i = column; i < array.size(); i += degree) {
            if (array.get(i).length() > maxWidth) {
                maxWidth = array.get(i).length();
            }
        }
        return maxWidth;
    }
    
//    Printing the table.
    public static void printTable(ArrayList<String> array, int degree) {
        String line = "";
        
//        Calculating the width of every column.
        int[] columnWidths = new int[degree];
        for (int i = 0; i < degree; i++) {
            columnWidths[i] = getColumnWidth(array, degree, i);
        }
        
//        Printing the header row of the table, which contains the column headings.
        for (int i = 0; i < degree; i++) {
            if (i == 0) {
                line = justifyData(array.get(i), columnWidths[i]);
            }
            else {
                line += "    " + justifyData(array.get(i), columnWidths[i]);
            }
            if (i == degree - 1) {
                System.out.println(line);
            }
        }
        
//        Printing the table body.
        for (int i = degree; i < array.size(); i++) {
            if (i % degree == 0) {
                line = justifyData(array.get(i), columnWidths[i % degree]);
            }
            else {
                line += "    " + justifyData(array.get(i), columnWidths[i % degree]);
            }
            if (i % degree == degree - 1) {
                System.out.println(line);
            }
        }
    }
    
	public static void main(String[] args)
	{
		//Interact with user. Acquire the file paths for article and vocabulary. Construct FileReaders for both files
		FileReader article_file_reader=null;
		FileReader vocabulary_file_reader=null;
		Scanner scanner=new Scanner(System.in);
		System.out.println("Type in the path of the Company_Names file");
		while(true)
		{
			String path=scanner.nextLine() + "companies.dat";
			try
			{vocabulary_file_reader=new FileReader(new File(path));break;}
			catch(IOException e)
			{System.out.println("Cannot find vocabulary file. Try again");}
		}
		System.out.println("Input the news article line by line\n"
                        + "A line that consists entirely of a period symbol (.) will end inputting");
		
		
		//ScanArticle's constructor would construct the trie and the hashmap from given FileReaders
		ScanArticle scan=new ScanArticle(vocabulary_file_reader);
		
		//ScanArticle.Scan() is the method to scan the article and update HashMap data. Finally it returns the HashMap
		HashMap<String,Integer> map=scan.Scan();

                Integer totalWords = scan.countWords();
                
                Integer totalHitCount = 0;
                
                ArrayList<String> output1 = new ArrayList<>();
                output1.add("Company");
                output1.add("Hit Count");
                output1.add("Relevance");
                
		//Iterate the HashMap
		Iterator<Entry<String,Integer>> iterator=map.entrySet().iterator();
		while(iterator.hasNext())
		{
			Map.Entry entry = (Map.Entry) iterator.next();
                        output1.add(entry.getKey().toString());
                        output1.add(entry.getValue().toString());
                        output1.add(Double.toString((double) Integer.valueOf(entry.getValue().toString()) / totalWords * 100) + "%");
                        totalHitCount += Integer.valueOf(entry.getValue().toString());
		}
                
                output1.add("Total");
                output1.add(Integer.toString(totalHitCount));
                output1.add(Double.toString((double) totalHitCount / totalWords * 100) + "%");
                
                printTable(output1, 3);
                
                ArrayList<String> output2 = new ArrayList<>();
                output2.add("Total Words");
                output2.add(Integer.toString(totalWords));
                
                printTable(output2, 2);
	}
}
