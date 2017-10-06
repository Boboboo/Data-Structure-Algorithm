import java.util.ArrayList;
import java.util.Scanner;

public class Heap {
	static ArrayList<Integer> list=new ArrayList<Integer>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Heap heap=new Heap();
		
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<10;i++)
		 {
			 System.out.println("Input the "+i+"th number");
			 try{ list.add(Integer.parseInt(sc.nextLine())) ;}
			 catch(NumberFormatException e){System.out.println("Wrong Input. Try again");i--;continue;} 
			 
			
			 heap.Insert(i);
		 }
		
		/*System.out.println("Your Outputs are:");*/
		for(int i=0;i<10;i++){heap.Delete();}
		System.out.println("After deleting all the nodes. The size of the heap:"+list.size());
	}
	

	public static void swap(int Index1,int Index2)
	{
		int temp=list.get(Index1);
		list.set(Index1, list.get(Index2));
		list.set(Index2, temp);
	}
	
	public void Insert(int Index)
	{
		int ParentIndex=(Index-1)/2;
		while(ParentIndex>=0&&Index!=0)
		{
			if(list.get(ParentIndex)>=list.get(Index))
			{break;}
			else
			{
				swap(ParentIndex, Index);
				Index=ParentIndex;
				ParentIndex=(ParentIndex-1)/2;
			}
		}
	}
	
	public void Delete()
	{
		int LastElement=list.get(list.size()-1);
		System.out.println(list.get(0));
		list.set(0, LastElement);
		list.remove(list.size()-1);
		int Index=0;
		int LeftChildIndex=2*Index+1;
		int RightChildIndex=2*Index+2;
		while(LeftChildIndex<list.size())
		{
			
				int MaxIndex=Index;
				if(list.get(LeftChildIndex)>list.get(MaxIndex)){MaxIndex=LeftChildIndex;}
				if(RightChildIndex<list.size()){if(list.get(RightChildIndex)>list.get(MaxIndex)){MaxIndex=RightChildIndex;}}
				if(MaxIndex==Index)break;
				else
				{
					swap(MaxIndex,Index);
					Index=MaxIndex;
					LeftChildIndex=2*Index+1;
					RightChildIndex=2*Index+2;
				}
		}
	}
	
}
