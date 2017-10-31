import java.util.Stack;


public class tree {
	treenode root=null;
	treenode current;
	treenode parent;
	
	void add(int data){
		treenode insertnode=new treenode();
		insertnode.data=data;
		
		if(root==null){root=insertnode;}
		else{
			current=root;
			parent=current;
			while(true){
				if(insertnode.data<current.data){
				parent=current;
				current=current.leftchild;	
				if(current==null){parent.leftchild=insertnode;return;}
				}
				else{
					parent=current;
					current=current.rightchild;
					if(current==null){parent.rightchild=insertnode;return;}
				}
			}	
		}	
	}
	
	void contains(int data){
		current=root;
		parent=current;
		while(data!=current.data){
			if(data<current.data){
				parent=current;
				current=current.leftchild;
				if(current==null){System.out.println("No");return;}
			}
			else{
				parent=current;
				current=current.rightchild;
				if(current==null){System.out.println("No");return;}
			}
		}
		System.out.println("Yes");
	}
	
 
	void displaytree(treenode localroot){
	if(localroot!=null)
	{
		displaytree(localroot.leftchild);
		System.out.print(localroot.data+" ");
		displaytree(localroot.rightchild);
	}
    }
	
	void remove(int key)
	{
		current=root;parent=current;boolean isleftchild=true;
		while(current.data!=key)
		{
			if(key<current.data)
			{
				isleftchild=true;
				parent=current;
				current=current.leftchild;
			}
			else
			{
				isleftchild=false;
				parent=current;
				current=current.rightchild;
			}
			if(current==null)
			{
				System.out.println("cannot find "+key);return;
			}
		}
			
			//删除的节点是叶节点情况
			if(current.leftchild==null && current.rightchild==null)
			{
				if(current==root){root=null;}
				if(isleftchild==true)
					{parent.leftchild=null;}
				else{parent.rightchild=null;}
				return;
			}
			
			
			else if(current.rightchild==null)
			{
				if(current==root){root=current.leftchild;}
				else if(isleftchild==true){parent.leftchild=current.leftchild;}
				    else{parent.rightchild=current.leftchild;}
				return;
			}
			else if(current.leftchild==null)
			{
				if(current==root){root=current.rightchild;}
				else if(isleftchild==true){parent.rightchild=current.rightchild;}
				      else{parent.rightchild=current.rightchild;}
				return;
			}
			
			else
			{
				treenode replacement=getreplacement(current);
				if(current==root){root=replacement;}
				else if(isleftchild){parent.leftchild=replacement;}
				     else{parent.rightchild=replacement;}
				replacement.leftchild=current.leftchild;
				return;
			}
}
	
	public boolean isEmpty()
	{return (root==null)?true:false;}
	
	treenode getreplacement(treenode subroot){
		treenode temp=subroot.rightchild;
		treenode successor=subroot;
		treenode successorparent=subroot;
		while(temp!=null)
		{
			successorparent=successor;
			successor=temp;
			temp=temp.leftchild;
		}
		if(successor!=subroot.rightchild)
		{
			successorparent.leftchild=successor.rightchild;
			successor.rightchild=subroot.rightchild;
			
		}
		return successor;
	}
}
