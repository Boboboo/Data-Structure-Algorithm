import java.util.*;
import java.io.*;
public class Cipher {
	static Scanner sc=new Scanner(System.in);;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cipher cipher=new Cipher();
		FileReader fr=null;
		FileWriter fw=null;
		StringBuffer sb=new StringBuffer();
		BufferedReader br ;
		String filepath;
		File f;
		while(true)
		{
		System.out.println("Input file path");
		filepath=sc.nextLine();
		try{
			f=new File(filepath);
			try {
				fr=new FileReader(f);
				br=new BufferedReader(fr);
				break;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Cannot find file. Try again.");
				continue;
			}
			
		}
		catch(NullPointerException e)
		{
			System.out.println("Cannot find file. Try again.");
			continue;
		}
		}
		String temp;
		try {
			while((temp = br.readLine()) != null) { 
				sb.append(temp);
				}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String code=sb.toString();
		System.out.println(code);
		
		int index=filepath.lastIndexOf("\\");
		String newfilepath=filepath.substring(0, index+1);
		File file=new File(newfilepath+"\\solution.txt");
		try {
			fw=new FileWriter(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String result=cipher.dicipher("Htsnyhcdjwlevbah! Pfl zxo afsb dwusb srnsyz!");
			System.out.println(result);
			fw.write(result);
			//fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String dicipher(String code)
	{
		
		StringBuffer sb=new StringBuffer();
		int key=-5;
		int temp=0;
		for(int i=0;i<code.length();i++)
		{
			if(i>0&&i%3==0)
			{key-=2;}
				
				temp = (int)(code.charAt(i));
				
			if(temp>=65&&temp<=90)
			{
				temp=temp+key%26;
				if(temp>90)
				{temp=temp-26;}
				if(temp<65)
				{temp=temp+26;}
			}
			else
			{
				if(temp>=97&&temp<=122)
				{temp=temp+key%26;
				if(temp>122)
				{
					temp=temp-26;
				}
				if(temp<97)
				{temp=temp+26;}
				}
			}
			
			sb.append(Character.toString((char)temp));
			
		}
		
		return sb.toString();
		
		
	}

}
