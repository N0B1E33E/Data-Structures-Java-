import java.util.*;
import java.io.*;
/**
 * 
 * @author Eric Chen(zxc595)
 *
 * @param <K> Generic type for name of each node
 * @param <V> Generic type for data of each node
 */
public class Graph<K,V> {
	
	public static class Node<K,V>{
		protected K name;
		protected V data;
		protected List<Node<K,V>> neighbors;
		protected boolean encountered = false;
		public Node<K, V> parent;
		
		public Node(K name, V data){
			this.name = name;
			this.data = data;
			this.neighbors = new ArrayList<>();
			
		}
	}
	
	
	protected List<Node<K,V>> nodes;
	
	
	public Graph(){
		this.nodes = new ArrayList<>();
	}
	
	
	/**
	 * public static class that reads a graph from a txt file with the string input as path
	 * to this file.
	 * @param filename path of the file needed to be read
	 * @return the graph
	 * @throws IOException
	 */
	public static <V> Graph<String, V>read(String filename) throws IOException{
		Graph<String, V> graph = new Graph<>();
		boolean isNeighbor;
		BufferedReader br = new BufferedReader(new FileReader(filename));
		StringBuffer sb = new StringBuffer();
		String temp;
		String nodeName;
		String firstNode = null;
		while((temp = br.readLine()) != null){
			isNeighbor = false;
			for(int i = 0; i < temp.length(); i++){
				if(temp.charAt(i) != ' '){
					sb.append(temp.charAt(i));
				}
				if((temp.charAt(i) == ' ' && sb.length() != 0)
						|| i == temp.length() - 1){
					nodeName = sb.toString();
					sb.delete(0, sb.length());
					if(!isNeighbor){
						graph.addNode(nodeName, null);
						firstNode = nodeName;
						isNeighbor = true;
					}
					else{
						graph.addNode(nodeName, null);
						graph.addEdge(firstNode, nodeName);
					}
				}
			}
		}
		return graph;
	}
	
	/**
	 * Protected method that searches the node in the graph with the string input
	 * @param name Searching key of the node.
	 * @return the Node found with the input String. Null if not found in the graph.
	 */
	protected Node<K,V> getNode(K name){
		for(Node<K,V> node : nodes){
			if(node.name.equals(name))
				return node;
		}
		return null;
	}
	
	
	/**
	 * Public method that add a new node with input name and data to the graph.
	 * @param name Name of the new node needed to be added in the graph.
	 * @param data Data off the new node needed to be added in the graph.
	 * @return true if succeeded in adding this node.
	 * False if the node already exists.
	 */
	public boolean addNode(K name, V data){
		if(this.getNode(name) != null)
			return false;
		else
			nodes.add(new Node<K,V>(name,data));
		return true;
	}
	
	
	/**
	 * Public method that adds more than one node at a time.
	 * @param names Array of names of the nodes added in the graph.
	 * @param data Array of data of the nodes added in the graph.
	 * @return True if all nodes successfully added. False if anyone already exists.
	 */
	public boolean addNodes(K[] names, V[] data){
		boolean allAdded = true;
		if(names.length != data.length)
			throw new IllegalArgumentException();
		else{
			for(int i = 0; i < names.length; i ++){
				if(this.getNode(names[i]) != null)
					allAdded = false;
				else
					this.addNode(names[i], data[i]);
			}
		}
		return allAdded;
	}
	
	
	/**
	 * Public method that adds an edge between two nodes with input names.
	 * @param from One of the twos nodes connected.
	 * @param to The other node connected.
	 * @return True if edge successfully added. False if edge already exists.
	 */
	public boolean addEdge(K from, K to){
		Node<K,V> fromNode = this.getNode(from);
		Node<K,V> toNode = this.getNode(to);
		if(fromNode == null || toNode == null)
			return	false;
		else if(this.getEdge(fromNode, toNode))
			return false;
		else{
			fromNode.neighbors.add(toNode);
			toNode.neighbors.add(fromNode);
			return true;
		}
	}
	
	/**
	 * Public method that takes two input and returns a boolean value,
	 * adds edges of one nodes to its neighbors.
	 * @param from Primary node.
	 * @param toList A list of nodes that are neighbors of the primary node.
	 * @return True if all succeeded in adding edges, false if any edge already exists.+
	 */
	public boolean addEdges(K from, K...toList){
		boolean allAdded = true;
        for (K to : toList) {
            if (!addEdge(from, to)) {
                allAdded = false;
            }
        }
        return allAdded;
	}
	
	/**
	 * Protected method that checks if the graph contains given edge.
	 * @param from One end of the edge.
	 * @param to The other end off the edge.
	 * @return True if found, otherwise false.
	 */
	protected boolean getEdge(Node<K,V> from, Node<K,V> to){
		return from.neighbors.contains(to);
	}
	
	/**
	 * Public method that removes a node with input name in the graph.
	 * @param name Searching key of the node needed to be removed.
	 * @return True if succeeded in removing the node.
	 * False if the node does not exist in the graph.
	 */
	public boolean removeNode(K name) {
        Node<K, V> node = getNode(name);
        if (node == null) {
            return false;
        }
        nodes.remove(node);
        for (Node<K, V> neighbor : node.neighbors) {
            neighbor.neighbors.remove(node);
        }
        return true;
    }

	/**
	 * public method that removes nodes with the given lists of names
	 * @param names list of names of nodes needed to be removed from the graph.
	 * @return true if all successfully removed. False otherwise.
	 */
    public boolean removeNodes(K... names) {
        boolean allRemoved = true;
        for (K name : names) {
            if (removeNode(name) == false) {
                allRemoved = false;
            }
        }
        return allRemoved;
    }
    
    /**
     * print the name of the node in the graph and their neighbors as adjacency lists.
     */
    public void printGraph(){
    	for(Node<K,V> node: nodes){
    		System.out.print(node.name + " ");
    		ArrayList<Node<K,V>> neighbors = new ArrayList<>(node.neighbors);
    		for(Node<K,V> neighbor : neighbors){
    			System.out.print(neighbor.name + " ");
    		}
    		System.out.println();
    	}
    }

    /**
     * Protected method that takes no input and returns all node in the graph.
     * @return
     */
    protected List<Node<K,V>> getNodes(){
    	return this.nodes;
    }
    
    /**
     * Depth-First search that takes two input as the name of two nodes in the graph and return 
     * an array as path.
     * @param from Starting node in the graph.
     * @param to End node in the graph.
     * @return An array of path from the starting node to the end node.
     */
    public K[] DFS(K from, K to) {
        Node<K,V> startNode = getNode(from);
        Node<K,V> endNode = getNode(to);
        if (startNode == null || endNode == null) {
            return null;
        }
        Stack<Node<K,V>> stack = new Stack<>();
        stack.push(startNode);
        startNode.encountered = true;
        boolean found = false;
        while (!stack.isEmpty()) {
            Node<K,V> node = stack.pop();
            if (node == endNode) {
                found = true;
                break;
            }
            for (Node<K,V> neighbor : node.neighbors) {
                if (!neighbor.encountered) {
                    neighbor.encountered = true;
                    neighbor.parent = node;
                    stack.push(neighbor);
                }
            }
        }
        if (!found) {
            return null;
        }
        List<K> path = new ArrayList<>();
        Node<K,V> currNode = endNode;
        while (currNode != startNode) {
            path.add(0, currNode.name);
            currNode = currNode.parent;
        }
        path.add(0, startNode.name);
        K[] result = (K[]) new Object[path.size()];
        result = path.toArray(result);
        for (Node<K,V> node : nodes) {
            node.encountered = false;
            node.parent = null;
        }
        return result;
    }

    /**
     * Breadth-First search that takes two input as the name of two nodes in the graph and return 
     * an array as path.
     * @param from Starting node in the graph.
     * @param to End node in the graph.
     * @return An array of path from the starting node to the end node.
     */
	public K[] BFS(K from, K to) {
        Node<K,V> startNode = getNode(from);
        Node<K,V> endNode = getNode(to);
        if (startNode == null || endNode == null) {
            return null;
        }
        //Create a queue to hold nodes to be visited during BFS
        Queue<Node<K,V>> queue = new LinkedList<>();
        queue.offer(startNode);
        //Add the starting node to the queue and mark it as encountered
        startNode.encountered = true;
        //Initialize a boolean variable to keep track of 
        //whether the ending node has been found
        boolean found = false;
        //While the queue is not empty, dequeue the next node to visit and process its neighbors
        while (!queue.isEmpty()) {
            Node<K,V> node = queue.poll();
            if (node == endNode) {
                found = true;
                break;
            }
            for (Node<K,V> neighbor : node.neighbors) {
                if (!neighbor.encountered) {
                    neighbor.encountered = true;
                    neighbor.parent = node;
                    queue.offer(neighbor);
                }
            }
        }
        if (!found) {
            return null;
        }
        //If the ending node was found, create a list to hold the keys of the nodes along the path
        List<K> path = new ArrayList<>();
        Node<K,V> currNode = endNode;
        while (currNode != startNode) {
            path.add(0, currNode.name);
            currNode = currNode.parent;
        }
        path.add(0, startNode.name);
        @SuppressWarnings("unchecked")
		K[] result = (K[]) new Object[path.size()];
        result = path.toArray(result);
        for (Node<K,V> node : nodes) {
            node.encountered = false;
            node.parent = null;
        }
        return result;
    }

    
    /**
     * Protected method that print out the data instead of the name of the nodes in the graph
     * and the nodes' neighbor as adjacency list.
     */
    protected void printGraphData(){
    	for(Node<K,V> node: nodes){
    		System.out.print(node.data + " ");
    		ArrayList<Node<K,V>> neighbors = new ArrayList<>(node.neighbors);
    		for(Node<K,V> neighbor : neighbors){
    			System.out.print(neighbor.data + " ");
    		}
    		System.out.println();
    	}
    }
}
