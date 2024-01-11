import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.List;
/**
 * 
 * @author Eric Chen
 *
 */
public class WordLadders{
	private static Graph<Integer, String> graph = new Graph<>();
	public static String filename;
	
	/**
	 * Public static method that takes a string input showing the path of a .txt file needed to be read
	 * and return a graph construct based on the file read.
	 * @param filename path of the file needed to be read
	 * @return a graph that constructs based on the file read
	 * @throws IOException
	 */
	public static Graph<Integer, String> readWordGraph(String filename) throws IOException{
		//count to distinguish the value read is whether value or name
		int count;
		//check whether node read is in the adjacency list
		boolean isNeighbor;
		//br read from the file
		BufferedReader br = new BufferedReader(new FileReader(filename));
		StringBuffer sb = new StringBuffer();
		String temp;
		int nodeName = Integer.MAX_VALUE;
		String nodeValue = null;
		int firstNode = Integer.MAX_VALUE;
		//still have a line to read
		while((temp = br.readLine()) != null){
			//reset isNeighbor to be false and count to be 0
			isNeighbor = false;
			count = 0;
			for(int i = 0; i < temp.length(); i++){
				//append character when space is not encountered
				if(temp.charAt(i) != ' '){
					sb.append(temp.charAt(i));
				}
				//encountered a space and already read something
				if((temp.charAt(i) == ' ' && sb.length() != 0)
						|| i == temp.length() - 1){
					//read a name
					if(count % 2 == 0){
						nodeName = Integer.parseInt(sb.toString());
						count ++;
					}
					//read a data(finishing reading a name)
					else{
						nodeValue = sb.toString();
						count ++;
						//node not in the adjacency list
						if(!isNeighbor){
							graph.addNode(nodeName, nodeValue);
							firstNode = nodeName;
							isNeighbor = true;
						}
						//node need to add to adjacency list
						else{
							graph.addNode(nodeName, nodeValue);
							graph.addEdge(firstNode, nodeName);
						}
					}
					//reset stringBuilder
					sb.delete(0, sb.length());
					
				}
			}
		}
		return graph;
	}
	
	
	public static void main(String args[]) throws IOException{
		Integer[] nameList = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Type in the path of the file");
		String fileName = scanner.nextLine();
		WordLadders.readWordGraph(fileName);
		graph.printGraphData();
		while(true){
			System.out.println("Please type in the start node"); 
			Integer startNode = scanner.nextInt();
			System.out.println("Please type in the end node");
			Integer endNode = scanner.nextInt();
			System.out.println("Please choose either BFS or DFS");
			String search = scanner.next();
			if(search.equals("BFS")){
				nameList = graph.BFS(startNode, endNode);
			}
			else if(search.equals("DFS")){
				nameList = graph.DFS(startNode, endNode);
			}
			graph.printGraphData();
			System.out.println(nameList);
			String[] dataList = new String[nameList.length];
			for(int i = 0; i < nameList.length; i ++)
				dataList[i] = graph.getNode(nameList[i]).data;
			System.out.println(Arrays.toString(dataList));
		}
	}
}
