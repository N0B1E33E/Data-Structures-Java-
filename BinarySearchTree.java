/**
 * This is a Binary Search Tree Class. This class implements methods including
 * a protected inner class Node,
 * insert(), search(), createTree(), delete(),inorderRec(), preorderRec(), postorderRec()
 * and kthsmallest().
 * @author Eric Chen
 *
 */
public class BinarySearchTree {

	
	/**
	 * this is a private class that initiate Node.
	 * This private class can get access to the value(key) of the node,
	 * the left and right sub-node.
	 * This class also provide functions to change(reassign) the value(key) of the node,
	 * the left and right sub-node.
	 * @author Eric Chen
	 *
	 */
	protected class Node{
		public int key;
		private Node left;
		private Node right;
		/**
		 * constructor that initialize the node with a given integer input
		 * @param key represent both the value and the key of the node
		 */
		private Node(int key){
			this.key = key;
			left = null;
			right = null;
		}
		/**
		 * private method that takes no input and returns a type Node value.
		 * Returns the left sub-node of this node.
		 * @return The left child of this node in the type of node.
		 */
		private Node getLeft(){
			return this.left;
		}
		
		/**
		 * This private method takes no input and returns a type Node value.
		 * Returns the right child of this node.
		 * @return The right child of this node in the type of Node.
		 */
		private Node getRight(){
			return this.right;
		}
		
		/**
		 * Private method that takes one Node input,
		 * and set the input node to be the right child of this node.
		 * @param right input node that will be the right child of this node.
		 */
		private void setRight(Node right){
			this.right = right;
		}
		
		/**
		 * Private method that takes one Node input,
		 * and set the input node to be the left child of this node
		 * by changing the field "left".
		 * @param left input node that will be the left child of this node.
		 */
		private void setLeft(Node left){
			this.left = left;
		}
		
		/**
		 * private method that takes no input and return the key(value) of the node.
		 * @return the value of the node.
		 */
		private int getKey(){
			return this.key;
		}
	}
	
	
	private Node root;
	private int count;
	
	/**
	 * this method takes an integer input as the key and at the same time the value of the node
	 * added to this tree, if the binary search tree does not contain any node, set the root
	 * to be the inserted node with the input value, this method use a while loop to 
	 * to find the location of the insertion place and then insert the node.
	 * This method does not have any return value.
	 * @param key input integer value that represent both the key and the value of the node.
	 */
	public void insert(int key){
		Node parent = null;
		Node trav = root;
		//find the parent of the new node
		while(trav != null){
			parent = trav;
			if(key < trav.getKey())
				trav = trav.getLeft();
			else
				trav = trav.getRight();
		}
		
		//insert the node
		//the tree is empty
		if(parent == null)
			root = new Node(key);
		//input value smaller than the parent value
		else if(key < parent.getKey())
			parent.setLeft(new Node(key));
		//input value larger than parent value
		else
			parent.setRight(new Node(key));
	}
	
	/**
	 * This method takes an integer array input,
	 * rewrite the array to a binary search tree.
	 * Overwrite any existing tree in this class.
	 * @param value integer array that needed to be rewrote as a binary tree.
	 */
	public void createTree(int[] value){
		root = null;
		for(int i = 0; i < value.length; i ++){
			this.insert(value[i]);
		}
	}
	
	/**
	 * This method takes a single integer input as the key(value) of the node,
	 * and find the node with the input key(value) in the tree.
	 * @param key Integer input that represents the key(value) of the node.
	 * @return True if the a node with input key(value) is found.
	 * False if such a node with given key(value) is not in the tree.
	 */
	public boolean search(int key){
		// Find the node
		Node trav = root;
		while (trav != null && trav.key != key) {
			if (key < trav.key)
				trav = trav.left;
			else
				trav = trav.right;
			}
		if (trav == null) // no such key
			return false;
		else {
			return true;
		}
	}
	
	/**
	 * This method delete the node with the input key value by using a private helper method.
	 * @param key An integer input that represents the key(value) of the node deleted.
	 * @return The Node if found and deleted. 
	 * Null if the Node with input key does not exist.
	 */
	public Node delete(int key) {
		// Find the node and its parent.
		Node parent = null;
		Node trav = root;
		while (trav != null && trav.key != key) {
			parent = trav;
			if (key < trav.key)
				trav = trav.left;
			else
				trav = trav.right;
			}
			// Delete the node (if any) and return the removed item.
			if (trav == null) // no such key
				return null;
			else {
				deleteNode(trav, parent);
				return trav;
			}
		}
	
	private void deleteNode(Node toDelete, Node parent) {
		if (toDelete.left == null || toDelete.right == null) {
		// Cases 1 and 2
		Node toDeleteChild = null;
		if (toDelete.left != null)
		toDeleteChild = toDelete.left;
		else
		toDeleteChild = toDelete.right;
		// both Cases are included. In case 1 toDeleteChild==null
		if (toDelete == root)
			root = toDeleteChild;
		else if (toDelete.key < parent.key)
			parent.left = toDeleteChild;
		else
			parent.right = toDeleteChild;
		}
		else{
			// Get the smallest item in the right subtree.
			Node replacementParent = toDelete;
			Node replacement = toDelete.right;
			while (replacement.left != null) {
				replacementParent = replacement;
				replacement = replacement.left;
			}
			// Replace toDelete's key and data
			toDelete.key = replacement.key;
			// Recursively delete the replacement item's old node.
			deleteNode(replacement, replacementParent);
		}
	}
	
	/**
	 * This method takes no input and utilize a private helper method
	 * to iterate the whole tree in an in-order list and print the key(value) out.
	 */
	public void inorderRec(){
		if (root != null)
			myInorderPrint(root);
	}
	
	/**
	 * private method that takes a Node input which represents the pointer of the node.
	 * First traverse to the left-most node which is the smallest one and then to the right.
	 * Print out the node it traversed.
	 * @param root A type Node input representing a pointer.
	 */
	private void myInorderPrint(Node root){
		if (root.left != null)
			myInorderPrint(root.left);
		System.out.print(root.key + " ");
		if (root.right != null)
			myInorderPrint(root.right);
	}
	
	/**
	 * This method takes no input and utilize a private helper method
	 * to iterate the whole tree in an pre-order list and print the key(value) out.
	 */
	public void preorderRec(){
		if(root != null)
			myPreorderPrint(root);
	}
	
	/**
	 * private method that takes a Node input which represents the pointer of the node.
	 * First traverse to the root node,
	 * then the left subtree of the root and then to the right subtree.
	 * Print out the node it traversed.
	 * @param root A type Node input representing a pointer.
	 */
	private void myPreorderPrint(Node root) {
		System.out.print(root.key + " ");
		if (root.left != null)
			myPreorderPrint(root.left);
		if (root.right != null)
			myPreorderPrint(root.right);
	}
	
	/**
	 * This method takes no input and utilize a private helper method
	 * to iterate the whole tree in an post-order list and print the key(value) out.
	 */
	public void postorderRec(){
		if(root != null)
			myPostorderPrint(root);
	}
	
	/**
	 * private method that takes a Node input which represents the pointer of the node.
	 * First traverse the bottom of the left subtree of the root and 
	 * then the bottom of the right subtree,
	 * and print the root at last.
	 * Print out the node it traversed.
	 * @param root A type Node input representing a pointer.
	 */
	private void myPostorderPrint(Node root) {
		if (root.left != null)
			myPostorderPrint(root.left);
		if (root.right != null)
			myPostorderPrint(root.right);
		System.out.print(root.key + " ");
	}
	
	/**
	 * This is a method that takes a integer input k representing the kth smallest node 
	 * we need to find and return the node utilizing a private helper method.
	 * @param k An Integer Input that represent the kth smallest node needed to be found.
	 * @return kth smallest node found in the tree.
	 */
	public Node kthSmallest(int k){
		count = 0;
		Node kthSmallestNode = kthSmallest(root,k);
		return kthSmallestNode;
	}
	private Node kthSmallest(Node root, int k)
    {
        // base case
        if (root == null)
            return null;
 
        // search in left subtree
        Node left = kthSmallest(root.left, k);
 
        // if k'th smallest is found in left subtree, return it
        if (left != null)
            return left;
 
        // if current element is k'th smallest, return it
        count++;
        if (count == k)
            return root;
 
        // else search in right subtree
        return kthSmallest(root.right, k);
    }
}

