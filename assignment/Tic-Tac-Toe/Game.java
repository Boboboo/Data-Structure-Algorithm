//package test0;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.Scanner;
public class Game {
	char[][] board;
	char[][] play;
	int row,col,win;
	int playerNumber;
	int turn=0;
	int empty = 0;
	int steps=0;
	char[] player=new char[]{'X','O','A','B','C','D','E','F','G','H','I','J','K','L','M','N','P','Q','R','S','T','U','V','W','Y','Z'};
	Scanner sc; 
	StringBuffer stringbuffer=new StringBuffer();
	private Game(int playerNo,int row, int col, int win)
	{
		this.row=row;this.col=col;this.win=win;
	}
	
	private Game() {
		sc = new Scanner(System.in); 
	}
	
	private void init(){
	
		board=new char[row+row-1][3*col+col-1];
		play=new char[row][col];
		/*行对应关系:2*row ,列对应关系：4*col+1*/
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				play[i][j]=' ';
			}
		}
		for(int i=1;i<board.length;i+=2)
		{
			for(int j=0;j<board[0].length;j++)
			{
				board[i][j]='-';
			}
		}
		for(int j=3;j<board[0].length;j+=4)
		{
			for(int i=0;i<board.length;i++)
			{
				if(i%2==0)
				board[i][j]='|';
				else
				board[i][j]='+';
				
			}
		}	
		
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				board[2*i][4*j+1]=play[i][j];
			}
		}
	}
	
	private void display()
	{	
		System.out.print("   ");
		String space=" ";
		for(int i=0;i<board[0].length;i+=4)
		{
			String str=Integer.toString(i/4+1);
			if(str.length()==1)
			{stringbuffer.append(space);stringbuffer.append(str);stringbuffer.append(space);stringbuffer.append(space);System.out.print(stringbuffer.toString());}
			if(str.length()==2)
			{stringbuffer.append(str);stringbuffer.append(space);stringbuffer.append(space);System.out.print(stringbuffer.toString());}
			if(str.length()==3)
			{stringbuffer.append(str);stringbuffer.append(space);System.out.print(stringbuffer.toString());}
			stringbuffer.delete(0, stringbuffer.length());
		}
		System.out.println("");
		
		for(int i=0;i<board.length;i++)
		{
			if(i%2==0)
			{
			String str=Integer.toString(i/2+1);
			System.out.print(str);
			for(int k=str.length();k<3;k++)
			{
				System.out.print(" ");
			}
			}
			else
			{
				System.out.print("   ");
			}
			
			for(int j=0;j<board[0].length;j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.println("");
		}
	}
	
	private void status()
	{	
		this.turn++;
		if(turn>=playerNumber)
		turn=0;
	}
	
	public String nextMove(int i,int j)
	{
		
		board[2*(i-1)][4*(j-1)+1]=player[turn];
		//display();
		int winner=winner(i,j,turn+1);
		steps++;
		//System.out.println("winner feedback "+winner);
		if(winner==-1)
		{
		
			if(steps==row*row){
				return "Draw!";
			}
		status();
		return "continue";
		}
		else
		{
			deleteGame();
			return "Player:"+player[winner-1]+" wins";
		}
		
	}
	public  boolean isDraw() {
      for (int row = 0; row < board.length; row++) {
         for (int col = 0; col < board.length; col++) {
            if (board[row][col] == empty) {
               return false;  
            }
         }
      }
      System.out.println("draw");
      return true;
	     
	}
	private int winner(int i,int j,int whichplayer)
	{
		//check row
		int startx=i;
		int starty=j;
		int endx=i;
		int endy=j;
		while(0<=(2*(startx-1))&&(2*(startx-1))<board.length&&0<=(4*(starty-1)+1)&&(4*(starty-1)+1)<board[0].length&&board[2*(startx-1)][4*(starty-1)+1]==board[2*(i-1)][4*(j-1)+1])
		{
				starty=starty-1;
		}
		starty=starty+1;
		while(0<=(2*(endx-1))&&(2*(endx-1))<board.length&&0<=(4*(endy-1)+1)&&(4*(endy-1)+1)<board[0].length&&board[2*(endx-1)][4*(endy-1)+1]==board[2*(i-1)][4*(j-1)+1])
		{
				endy=endy+1;
		}
		endy=endy-1;
		if(Math.abs(endy-starty)+1>=win)
		{
			//System.out.println("check 1");
			return whichplayer;
		}

		//check column
		startx=i;
		starty=j;
		endx=i;
		endy=j;
		while(0<=(2*(startx-1))&&(2*(startx-1))<board.length&&0<=(4*(starty-1)+1)&&(4*(starty-1)+1)<board[0].length&&board[2*(startx-1)][4*(starty-1)+1]==board[2*(i-1)][4*(j-1)+1])
		{
				startx=startx-1;
		}
		startx=startx+1;
		while(0<=(2*(endx-1))&&(2*(endx-1))<board.length&&0<=(4*(endy-1)+1)&&(4*(endy-1)+1)<board[0].length&&board[2*(endx-1)][4*(endy-1)+1]==board[2*(i-1)][4*(j-1)+1])
		{
				endx=endx+1;
		}
		endx=endx-1;
		if(Math.abs(endx-startx)+1>=win)
		{
			//System.out.println("check 2");
			return whichplayer;
		}
		
		//check Northwest to Southeast
		startx=i;
		starty=j;
		endx=i;
		endy=j;
		while(0<=(2*(startx-1))&&(2*(startx-1))<board.length&&0<=(4*(starty-1)+1)&&(4*(starty-1)+1)<board[0].length&&board[2*(startx-1)][4*(starty-1)+1]==board[2*(i-1)][4*(j-1)+1])
		{
			//System.out.print( "("+startx+","+starty+")");
			startx-=1;
			starty-=1;
		}
		startx+=1;
		starty+=1;
		while(0<=(2*(endx-1))&&(2*(endx-1))<board.length&&0<=(4*(endy-1)+1)&&(4*(endy-1)+1)<board[0].length&&board[2*(endx-1)][4*(endy-1)+1]==board[2*(i-1)][4*(j-1)+1])
		{
			//System.out.print( "("+startx+","+starty+")"+ "("+endx+","+endy+")");
			endx+=1;
			endy+=1;
		}
		endx-=1;
		endy-=1;
		if(Math.abs(endy-starty)+1>=win)
		{
			//System.out.print( "("+startx+","+starty+")"+ "("+endx+","+endy+")");
			//System.out.println("check 3");
			return whichplayer;
		}
		
		//check  Northeast to Southwest
		startx=i;
		starty=j;
		endx=i;
		endy=j;
		while(0<=(2*(startx-1))&&(2*(startx-1))<board.length&&0<=(4*(starty-1)+1)&&(4*(starty-1)+1)<board[0].length&&board[2*(startx-1)][4*(starty-1)+1]==board[2*(i-1)][4*(j-1)+1])
		{
			startx-=1;
			starty+=1;
		}
		startx+=1;
		starty-=1;
		while(0<=(2*(endx-1))&&(2*(endx-1))<board.length&&0<=(4*(endy-1)+1)&&(4*(endy-1)+1)<board[0].length&&board[2*(endx-1)][4*(endy-1)+1]==board[2*(i-1)][4*(j-1)+1])
		{
			endx+=1;
			endy-=1;
		}
		endx-=1;
		endy+=1;
		if(Math.abs(endy-starty)+1>=win)
		{
			//System.out.println("check 4");
			return whichplayer;
		}
		//System.out.println("check 5");
		return -1;
	}
	
	public void playing()
	{
		String moveResult="continue";  //Get the return string from nextmove();
		String temp;
		int corX=-1;
		int corY=-1;
		while(moveResult.equals("continue"))
		{
			display();
			
			
					while(true)
					{
						System.out.println(moveResult);
						try
						{
							System.out.println("Player "+player[turn]+" 's turn: "+"example input: '1,2' type 'exit' to exit game");
							temp=sc.nextLine();
							if(temp.equals("exit")){
								System.out.println("Do you want to save game?(Y/N)");
								temp=sc.nextLine();
								if(isUserSelectYes(temp)){
									saveGame();
								}
								System.out.println("Good bye!");
								System.exit(0);
							}
							
							corX=Integer.parseInt(temp.substring(0, temp.indexOf(",")));
							corY=Integer.parseInt(temp.substring(temp.indexOf(",")+1));
							if(corX<1||corX>row||corY<1||corY>col)
							{
							System.out.println("Input error. Try again.");
							continue;
							}
							if(board[2*(corX-1)][4*(corY-1)+1]!=' ')
							{System.out.println("The blank is taken. Try again");continue;}
							break;
						}catch(NumberFormatException e)
						{
							System.out.println("Input Format error. Try again.");
							continue;
						}catch(Exception e)
						{
							System.out.println("Input Format error. Try again.");
							continue;
						}
					}
					moveResult=nextMove(corX,corY);
		}
		display();
		System.out.println(moveResult);
		System.out.println("");
		System.out.println("");
		System.out.println("*******************");
		String contin;
		
		while(true)
		{
			System.out.println("Continue? (Y/N)");
			contin=sc.nextLine();
			if(!contin.equalsIgnoreCase("Y")&&!contin.equalsIgnoreCase("N"))
			{
				System.out.println("I reveive "+contin);
				continue;
			}
			else
			{
				break;
			}
		}

		if(isUserSelectYes(contin))
				{
					Game.main(null);
				}
				else
				{
					System.out.println("Good bye!");
					System.exit(0);
				}
		
	}
	
	public void saveGame(){
		try {
	         // create a new file with an ObjectOutputStream
	         FileOutputStream out = new FileOutputStream("game.txt");
	         ObjectOutputStream oout = new ObjectOutputStream(out);

	         // save game in the file
	         oout.writeObject(board);
	         oout.writeObject(play);
	         oout.writeObject(row);
	         oout.writeObject(win);
	         oout.writeObject(playerNumber);
	         oout.writeObject(turn);
	         oout.writeObject(steps);

	         // close the stream
	         oout.close();
	         System.out.println("save success!");

	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
	}
	
	/**
	 * @return if user has game saved
	 */
	public boolean readGame(){

        try {
        	FileInputStream inputStream = new FileInputStream("game.txt");
			// create an ObjectInputStream for the file we created before
			ObjectInputStream ois =
			        new ObjectInputStream(inputStream);

			// read and print what we wrote before
			board=(char[][]) ois.readObject();
			if(board==null) return false;
			play=(char[][]) ois.readObject();
			row=(int) ois.readObject();
			col=row;
			win=(int) ois.readObject();
			playerNumber=(int) ois.readObject();
			turn=(int) ois.readObject();
			steps=(int) ois.readObject();
			
//			System.out.println("" + board);
//			System.out.println("board size" + row);
//			System.out.println("win condition:" + win);
//			System.out.println("player numbers:" + playerNumber);
//			System.out.println("who's turn" + player[turn]);
			
			return true;
		} catch (Exception e) {
			deleteGame();
			return false;
		}
	}

	private static void deleteGame() {
		try {
			File file=new File("game.txt");
			file.delete();
		} catch (Exception x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
	}
	
//	public void save(){
//		String filepath;
//		FileWriter fw=null;
//		int index=filepath.lastIndexOf("\\");
//		String newfilepath=filepath.substring(0, index+1);
//		File file=new File(newfilepath+"\\game.txt");
//		try {
//			fw=new FileWriter(file);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		try {
//			String result=;
//			fw.write(result);
//			fw.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	public static void main(String[] args)
	{
		Game game =new Game();
		
		if(game.readGame()){
		System.out.println("Would you like to resume a saved game? (Y/N)");
		Scanner sc = new Scanner(System.in);  
		if(isUserSelectYes(sc.nextLine())){
			// continue 
			System.out.println("Game has loaded");
			game.playing();
			return;
		}
		}
		
		deleteGame();
		getPlayers(game);
		getBoardSize(game);
		getWinCondition(game);
		game.init();
		game.playing();
		
		
		
		/*g.display();
		g.nextMove(2, 1);
		g.nextMove(1, 5);
		g.nextMove(1, 2);
		g.nextMove(2, 4);
		g.nextMove(1,1);
		g.nextMove(3, 3);*/
		
		/*System.out.println("请输入：");  
        Scanner sc = new Scanner(System.in);   
        String str=sc.nextLine(); 
        System.out.println(Integer.parseInt(str));*/
		
		
		//g.nextMove(1, 2);
		//System.out.println(g.winner(1, 1, 2)+" player wins");
	}

	private static void getPlayers(Game game) {
		Scanner sc = new Scanner(System.in); 
		System.out.println("How many players?(2-26)");
		String str;
		int result=-1;
		while(result>26 || result<2){
			str=sc.nextLine();
			result= readInputNumber(str);
		    if(result>=2 && result <=26){
		    	game.playerNumber=result;
//		    	getBoardSize(game);
		    	break;
		    } else {
		    	System.out.println("Invalid player numbers, please input again");
		    	System.out.println("How many players(2-26)?");
		    }
		}
	}
	
	private static void getBoardSize(Game game) {
		Scanner sc = new Scanner(System.in); 
		System.out.println("Please input the size of the board(3-999)");
		String str;
		int result=-1;
		while(result>999 || result<3){
			str=sc.nextLine();
			result= readInputNumber(str);
		    if(result>=3 && result <=999){
		    	game.row=result;
		    	game.col=result;
//		    	getWinCondition(game);
		    	break;
		    } else {
		    	System.out.println("Invalid board size, please input again");
		    	System.out.println("Please input the size of the board(3-999)");
		    }
		}
	}
	
	private static void getWinCondition(Game game) {
		Scanner sc = new Scanner(System.in); 
		System.out.println("Please input the win condition (3 to "+game.row +")");
		String str;
		int result=-1;
		while(result>999 || result<3){
			str=sc.nextLine();
			result= readInputNumber(str);
		    if(result>=3 && result <=game.row){
		    	game.win=result;
		    	break;
		    } else {
		    	System.out.println("Invalid board win condition, please input again");
		    	System.out.println("Please input the win condition");
		    }
		}
	}
	
	
	private static boolean isUserSelectYes(String input){
		if(input.equalsIgnoreCase("Y"))
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	private static int readInputNumber(String input){
		try {
			int result= Integer.valueOf(input);
			return result;
		} catch (Exception e) {
			return -1;
		}
	
	}
	

}