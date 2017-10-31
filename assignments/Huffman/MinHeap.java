import java.util.ArrayList;


public class MinHeap {
	ArrayList<TreeNode> list=null;
	
	public MinHeap() {
		list=new ArrayList<TreeNode>();
	}
	

	private void swap(int Index1,int Index2) {
		TreeNode temp=(TreeNode)list.get(Index1);
		list.set(Index1, list.get(Index2));
		list.set(Index2, temp);
	}
	
	public void Insert(TreeNode NewNode) {
		list.add(NewNode);
		int Index=list.size()-1;
		int ParentIndex=(Index-1)/2;
		while(ParentIndex>=0&&Index!=0) {
			if(list.get(ParentIndex).Weight<=list.get(Index).Weight)
			{break;}
			else {
				swap(ParentIndex, Index);
				Index=ParentIndex;
				ParentIndex=(ParentIndex-1)/2;
			}
		}
	}
	
	public TreeNode Delete() {          //这边的delete相当于minHeap里pop，每次出来的都是最小的，因此delete的也是最小的
		TreeNode LastElement=list.get(list.size()-1);
		TreeNode FirstElement=list.get(0);
		list.set(0, LastElement);
		list.remove(list.size()-1);
		int Index=0;
		int LeftChildIndex=2*Index+1;
		int RightChildIndex=2*Index+2;
		while(LeftChildIndex<list.size()) {
				int MinIndex=Index;
				if(list.get(LeftChildIndex).Weight<list.get(MinIndex).Weight){MinIndex=LeftChildIndex;}
				if(RightChildIndex<list.size()){if(list.get(RightChildIndex).Weight<list.get(MinIndex).Weight){MinIndex=RightChildIndex;}}
				if(MinIndex==Index)break;
				else {
					swap(MinIndex,Index);
					Index=MinIndex;
					LeftChildIndex=2*Index+1;
					RightChildIndex=2*Index+2;
				}
		}
		return FirstElement;
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean IsEmpty() {
		return (list.size()==0)?true:false;
	}
	
}

