package assignment0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class HuffmanCode {
	public HuffmanCode() {
	}

	public static void main(String[] args) {
		MinHeap mh = new MinHeap();
		String inputSource = readData();
		HashMap<Character, Integer> frequenceMap = getFrequence(inputSource);
		Set<Character> set = frequenceMap.keySet();
		Iterator cIterator = set.iterator();

		while(cIterator.hasNext()) {
			char c = ((Character)cIterator.next()).charValue();
			mh.Insert(new TreeNode(c, ((Integer)frequenceMap.get(Character.valueOf(c))).intValue()));
		}

		TreeNode root = constructHuffmanTree(mh);
		ArrayList<data> list = new ArrayList();
		getBinMap(list, frequenceMap, root, "");
		Collections.sort(list);
		int totalBit = 0;

		for(int i = 0; i < list.size(); ++i) {
			data temp = (data)list.get(i);
			char c = temp.c;
			totalBit += ((Integer)frequenceMap.get(Character.valueOf(c))).intValue() * ((data)list.get(i)).huffmancode.length();
			System.out.println(c + "," + Integer.toString(((Integer)frequenceMap.get(Character.valueOf(c))).intValue()) + ", " + ((data)list.get(i)).huffmancode);
		}

		System.out.println("original totalBits" + inputSource.length() * 8);
		System.out.println("compressed totalBits" + totalBit);
		System.out.println("compress rate " + 1.0F * (float)totalBit / (float)(inputSource.length() * 8));
		writeData(list, frequenceMap, inputSource);
	}

	public static String readData() {    //read data from file and output caracters and digits
		System.out.println("Please input the path of directory which contains infile.dat. Example D:\\ABX\\");
		Scanner scanner = new Scanner(System.in);
		String inputPath = "";
		String filename = "infile.dat";

		while(true) {
			inputPath = scanner.nextLine();
			if(inputPath.charAt(inputPath.length() - 1) != 92) {
				inputPath = inputPath + '\\';
			}

			inputPath = inputPath + filename;
			System.out.println(inputPath);
			File file = new File(inputPath);
			if(file.exists()) {
				System.out.println(file);
				StringBuffer outputStringBuilder = new StringBuffer();

				try {
					BufferedReader input = new BufferedReader(new FileReader(file));
					boolean var7 = false;

					String line;
					while((line = input.readLine()) != null) {
						line = line.replaceAll("[^A-Za-z0-9]+", "");
						outputStringBuilder.append(line);
					}

					input.close();
				} catch (Exception var8) {
					System.err.println("Error: " + var8.getMessage());
				}

				return outputStringBuilder.toString();
			}

			System.out.println("Cannot Fild infile.dat. Please try again.");
		}
	}

	public static HashMap<Character, Integer> getFrequence(String s) {    //get frequence for each character and digit
		HashMap<Character, Integer> map = new HashMap();                  //因为读取到的data有字母和数字，所以HashMap用Character做key可以兼顾两者
                                                                          //因为这样就可以所有的key都是对应的ASCII
		for(int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			Integer val = (Integer)map.get(new Character(c));
			if(val != null) {
				map.put(Character.valueOf(c), new Integer(val.intValue() + 1));
			} else {
				map.put(Character.valueOf(c), Integer.valueOf(1));
			}
		}
		return map;
	}
                                                                         //统计好的frequence放到最小堆里，堆里的每个treeNode的weight就是其frequence
	private static TreeNode constructHuffmanTree(MinHeap minHeap) {      //construct Hoffman tree using minHeap
		TreeNode curRoot = null;

		while(minHeap.size() > 1) {
			TreeNode leftNode = minHeap.Delete();
			TreeNode rightNode = minHeap.Delete();
			TreeNode parentNode = new TreeNode('?', leftNode.Weight + rightNode.Weight); //用？是因为可能是字母也可能是数字
			parentNode.LeftNode = leftNode;
			parentNode.RightNode = rightNode;
			curRoot = parentNode;
			minHeap.Insert(parentNode);                                 //建树的过程不断insert新parentNode，parentNode下面还带着刚才连续删除的两个结点
		}

		return curRoot;
	}

	private static void getBinMap(ArrayList<data> map, HashMap<Character, Integer> frequenceMap, TreeNode root, String binCode) {
		if(root != null) {
			if(root.Charecter != 63) {
				data temp = new data(root.Charecter, ((Integer)frequenceMap.get(Character.valueOf(root.Charecter))).intValue(), binCode);
				map.add(temp);
			} else {
				getBinMap(map, frequenceMap, root.LeftNode, binCode + "0");
				getBinMap(map, frequenceMap, root.RightNode, binCode + "1");
			}
		}
	}

	public static void writeData(ArrayList<data> list, HashMap<Character, Integer> frequenceMap, String inputSource) {
		System.out.println("Please input the path of directory in which you would like to save outfile.dat.");
		Scanner scanner = new Scanner(System.in);
		String filename = "outfile.dat";

		while(true) {
			String outputPath = scanner.nextLine();
			if(outputPath.charAt(outputPath.length() - 1) != 92) {
				outputPath = outputPath + '\\';
			}

			File file = new File(outputPath);
			if(file.exists()) {
				try {
					FileWriter fw = new FileWriter(outputPath + filename);
					NumberFormat nf = NumberFormat.getPercentInstance();
					nf.setMinimumFractionDigits(1);
					int totalBit = 0;
					StringBuffer sb = new StringBuffer();

					for(int i = 0; i < list.size(); ++i) {
						char c = ((data)list.get(i)).c;
						totalBit += ((Integer)frequenceMap.get(Character.valueOf(c))).intValue() * ((data)list.get(i)).huffmancode.length();
						sb.append(c);
						sb.append(", ");
						sb.append(String.format("%-6s", new Object[]{nf.format((double)((float)((Integer)frequenceMap.get(Character.valueOf(c))).intValue() / (float)inputSource.length()))}));
						sb.append(", ");
						sb.append(((data)list.get(i)).huffmancode);
						sb.append('\n');
						fw.write(sb.toString());
						sb.setLength(0);
					}

					sb.append("original totalBits: ");
					sb.append(Integer.toString(inputSource.length() * 8));
					sb.append('\n');
					sb.append("compressed totalBits: ");
					sb.append(Integer.toString(totalBit));
					sb.append('\n');
					sb.append("compress rate: ");
					sb.append(Float.toString(1.0F * (float)totalBit / (float)(inputSource.length() * 8)));
					sb.append('\n');
					fw.write(sb.toString());
					sb.setLength(0);
					fw.close();
				} catch (Exception var12) {
					var12.printStackTrace();
				}

				return;
			}

			System.out.println("Cannot find the directory. Please try again.");
		}
	}
}
