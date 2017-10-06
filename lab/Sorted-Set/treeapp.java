import java.io.*;
import java.util.*;
import java.util.Scanner;
public class treeapp {

	
	public static void main(String[] args) {
		
		tree tree=new tree();
		ArrayList<Integer> input=readData();
		for(int i=0;i<input.size();i++)
		{
			tree.add(input.get(i));
		}
		//tree.remove(3);
		
		
		tree.displaytree(tree.root);
		
		
		String temp;
		int number=0;
		Scanner sc=new Scanner(System.in);
		boolean Continue=true;
		while(Continue)
		{
			try{
				System.out.println("Input a number to search. Input 'exit' to exit. ");
				temp=sc.nextLine();
					if(temp.equals("exit")){Continue=false;}
					else
					{ 
						number=Integer.parseInt(temp);
					}
				tree.contains(number);
				}
			catch(NumberFormatException e)
						{
							System.out.println("Input Format error. Try again.");
							continue;
						}
			}
	sc.close();
	}
	public static ArrayList<Integer> readData() {
		//String inputPath= System.getProperty("user.dir")+"infile.dat";   //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String inputPath= "infile.dat";
		//System.out.println(inputPath);
		File file = new File(inputPath);
		//System.out.println(file);
		StringBuilder outputStringBuilder=new StringBuilder();
		try {
			BufferedReader input = new BufferedReader(new FileReader(file));

			String line;

			int i = 0;
			while ((line = input.readLine()) != null) {
				/*line=line.replaceAll("[^0-9]","");
				line=line.replaceAll(" ", "");
				line=line.toLowerCase();*/
//				System.out.println(line);
				outputStringBuilder.append(line);
			}
			input.close();
			
		} catch (Exception ex) {
			System.err.println("Error: " + ex.getMessage());
		}
		StringTokenizer st=new StringTokenizer(outputStringBuilder.toString(),",",false);
		ArrayList<Integer> list=new ArrayList<Integer>();
			while ( st.hasMoreElements() )
			{
				list.add(Integer.parseInt(st.nextToken()));
			
			}
		
		return list;
	}
	

	
	
}
