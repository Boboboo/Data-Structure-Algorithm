
public class RBTreeTest {

	

	public static void main(String[] args) {
		DictionaryItem[] items=new DictionaryItem[9];
		items[0]=new DictionaryItem("hello","world");
		items[1]=new DictionaryItem("goodbye","everyone");
		items[2]=new DictionaryItem("name","student");
		items[3]=new DictionaryItem("occupation","student");
		items[4]=new DictionaryItem("year","2016");
		items[5]=new DictionaryItem("gpa","4.0");
		items[6]=new DictionaryItem("lab","yes");
		items[7]=new DictionaryItem("assignment","no");
		items[8]=new DictionaryItem("department","cs");
		RBTree<DictionaryItem> tree=new RBTree<DictionaryItem>();

		
		for(int i=0; i<items.length; i++)
		tree.insert(items[i]);

		System.out.printf("\nInorder Traversal: ");
		tree.inOrder();
		System.out.println("*****************************************************");
		System.out.println("Search section");
		System.out.println(tree.search(new DictionaryItem("lab",null)));
		System.out.println(tree.search(new DictionaryItem("hello",null)));
		
		System.out.println("*****************************************************");
		System.out.println("Delete section");
		tree.remove(new DictionaryItem("lab",null));
		System.out.println(tree.search(new DictionaryItem("lab",null)));
	
		
	}
}

