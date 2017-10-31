import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BFS {
    private ArrayList vertexList;//存储点的链表
    private int[][] edges;//邻接矩阵，用来存储边
    private int numOfEdges;//边的数目
    private boolean isVisited[];
    public BFS(int n) {
        //初始化矩阵，一维数组，和边的数目
        edges=new int[n][n];
        vertexList=new ArrayList(n);
        numOfEdges=0;
        this.isVisited=new boolean[n];
    }

    //得到结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }


    public int getNumOfEdges() {
        return numOfEdges;
    }


    public Object getValueByIndex(int i) {
        return vertexList.get(i);
    }


    public int getWeight(int v1,int v2) {
        return edges[v1][v2];
    }

    public void insertVertex(Object vertex) {
        vertexList.add(vertexList.size(),vertex);
    }


    public void insertEdge(int v1,int v2,int weight) {
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }


    public void deleteEdge(int v1,int v2) {
        edges[v1][v2]=0;
        numOfEdges--;
    }

    
    public void BFS()
    {
    	int count=1;
    	for(int i=0;i<isVisited.length;i++){isVisited[i]=false;}
    	Queue queue=new LinkedList<Integer>();
    	queue.add(0);
    	System.out.println(vertexList.get(0)+", "+count++);
    	isVisited[0]=true;
    	int v2;
    	while(queue.size()>0)
    	{
    		int v1=(int) queue.poll();
    		while((v2=getAdjUnvisitedVertex(v1))!=-1)
    		{
    			isVisited[v2]=true;
    			System.out.println(vertexList.get(v2)+", "+count++);
    			queue.add(v2);
    		}
    	}
    	
    	for(int i=0;i<isVisited.length;i++)
    	{
    		isVisited[i]=false;
    	}
    }
    
    public int getAdjUnvisitedVertex(int v)
    {
    	for(int j=0;j<vertexList.size();j++)
    	{
    		if(edges[v][j]==1&&isVisited[j]==false)
    		return j;
    	}
    	return -1;
    }

    public static void main(String args[]) {
    	Scanner scanner=new Scanner(System.in);
    	FileReader fr=null;
    	System.out.println("Input the path of infile.dat");
    	while(true)
		{
			String path=scanner.nextLine();
			try
			{fr=new FileReader(new File(path));break;}
			catch(IOException e)
			{System.out.println("Cannot find infile.dat. Try again");}
		}
    	BufferedReader buffered_reader=new BufferedReader(fr);

		
		 BFS graph =null;
		 String way;
		try
		{
			int numberofnodes=Integer.parseInt(buffered_reader.readLine());
			graph=new BFS(numberofnodes);
			StringTokenizer st=new StringTokenizer(buffered_reader.readLine(),",");
			 while(st.hasMoreTokens()) { 
                 graph.insertVertex(st.nextToken());
             }
			while((way=buffered_reader.readLine())!=null)
			{
				st=new StringTokenizer(way,",");
				int par1=Integer.parseInt(st.nextToken());
				int par2=Integer.parseInt(st.nextToken());
				int par3=Integer.parseInt(st.nextToken());
				graph.insertEdge(par1, par2,par3);
			}
		}
		catch(IOException e)
		{
			System.out.println("Error when loading article");
		}	


        

        graph.BFS();

    }

}
