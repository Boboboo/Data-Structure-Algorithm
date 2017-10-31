// A Java program to print topological sorting of a DAG
import java.io.*;
import java.util.*;

 
// This class represents a directed graph using adjacency
// list representation
class Graph
{
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; // Adjacency List
 
    //Constructor
    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
 
    // Function to add an edge into the graph
    void addEdge(int v,int w) { adj[v].add(w); }
 
    // A recursive function used by topologicalSort
    void topologicalSortUtil(int v, boolean visited[],
                             Stack stack)
    {
        // Mark the current node as visited.
        visited[v] = true;
        Integer i;
 
        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext())
        {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }
 
        // Push current vertex to stack which stores result
        stack.push(new Integer(v));
    }
 
    // The function to do Topological Sort. It uses
    // recursive topologicalSortUtil()
    void topologicalSort()
    {
        Stack stack = new Stack();
 
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;
 
        // Call the recursive helper function to store
        // Topological Sort starting from all vertices
        // one by one
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);
 
        // Print contents of stack
        while (stack.empty()==false)
            System.out.print(stack.pop() + " ");
    }
    
    void topologicalSort2()
    {
        Stack stack = new Stack();
 
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;
 
        // Call the recursive helper function to store
        // Topological Sort starting from all vertices
        // one by one
        for (int i = V-1; i >=0; i--)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);
 
        // Print contents of stack
        while (stack.empty()==false)
            System.out.print(stack.pop() + " ");
    }
 
    // Driver method
    public static void main(String args[])
    {
        // Create a graph given in the above diagram
    	Scanner scanner=new Scanner(System.in);
    	FileReader fr=null;
    	System.out.println("Input the path of infile.dat");
    	while(true)
		{
			String path=scanner.nextLine();
			try
			{fr=new FileReader(new File(path));break;}
			catch(IOException e)
			{System.out.println("Cannot find vocabulary file. Try again");}
		}
    	BufferedReader buffered_reader=new BufferedReader(fr);

		
		 Graph g =null;
		 String way;
		try
		{
			int numberofnodes=Integer.parseInt(buffered_reader.readLine());
			g=new Graph(numberofnodes);
			while((way=buffered_reader.readLine())!=null)
			{
				StringTokenizer st=new StringTokenizer(way,",");
				int par1=Integer.parseInt(st.nextToken());
				int par2=Integer.parseInt(st.nextToken());
				g.addEdge(par1, par2);
			}
		}
		catch(IOException e)
		{
			System.out.println("Error when loading article");
		}	
//        Graph g = new Graph(6);
//        g.addEdge(5, 2);
//        g.addEdge(5, 0);
//        g.addEdge(4, 0);
//        g.addEdge(4, 1);
//        g.addEdge(2, 3);
//        g.addEdge(3, 1);
 
        System.out.println("Following is a Topological " +
                           "sort of the given graph");
        g.topologicalSort();
        System.out.println();
        g.topologicalSort2();
    }
}
