public class Vector{
	
	int length=0;
	Node first=null;
	Node last=null;
	Node temp;
	public void insert(Object obj)
	{
		if(length==0)
		{
			Node newnode=new Node(obj);
			first=newnode;
			last=newnode;
			length++;
		}
		else
		{
			Node newnode=new Node(obj);
			last.setNext(newnode);
			last=last.next;
			length++;
		}	
	}
	
	public void push(Object obj)
	{
		if(length==0)
		{
			Node newnode=new Node(obj);
			first=newnode;
			last=newnode;
			length++;
		}
		else
		{
			Node newnode=new Node(obj);
			newnode.next=first;
			first=newnode;
			length++;
		}
	}
	
	public Object pop()
	{
		if(length==0)
			return null;
		else
		{
			Object result=first.object;
			first=first.next;
			length--;
			return result;
		}
	}
	
	public Object get(int index)
	{
		int i=1;
		Node temp=first;
		if(index>length)
			return null;
		while(i<index)
		{
			temp=temp.next;
			i++;
		}
		return temp.object;
	}
	
	public boolean set(int index, Object obj)
	{
		if(index>length)
		return false;
		else
		{
			int i=1;
			Node temp=first;
			while(i<index)
			{
			i++;
			temp=temp.next;
			}
			temp.object=obj;
			return true;
		}
		
	}
	public void display()
		{
			Node temp=first;
			while(temp!=null)
			{
				System.out.println(temp.object);
				temp=temp.next;
			}
		}	
		public Iterator getIterator()
		{
			return new Iterator();
		}
		
		class Iterator
		{
			Node temp;
			Iterator()
			{
				this.temp=first;
			}
			public boolean hasNext()
			{
				return temp!=null;
			}
			public Object next()
			{
				Object obj=temp.object;
				temp=temp.next;
				return obj;
			}
		}
	
	

	
	public static void main(String[] args)
	{
		Vector v=new Vector();
		v.insert(1);
		v.insert(2);
		v.insert(3);
		//v.set(2, 9);
		
		Iterator i=v.getIterator();
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
		//v.display();
	}

	
}


class Node
	{
		Object object;
		Node next;
		public Node(Object obj) {
			this.object=obj;
			
		}
		public void setNext(Node nextnode)
		{
			this.next=nextnode;
		}
		
		
	}