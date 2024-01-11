
public class BinarySearchTreeDemo {
	public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        // Insert some nodes
        tree.insert(14);
        tree.insert(10);
        tree.insert(7);
        tree.insert(11);
        tree.insert(16);
        tree.insert(18);
        tree.insert(30);

        // Print the contents of the tree using the various traversal methods
        System.out.print("Inorder traversal: ");
        tree.inorderRec();
        System.out.println("");
        System.out.print("Preorder traversal: ");
        tree.preorderRec();
        System.out.println("");
        System.out.print("Postorder traversal: ");
        tree.postorderRec();
        System.out.println("");

        // Search for some nodes
        System.out.println("Search for 14: " + tree.search(14));
        System.out.println("Search for 10: " + tree.search(10));
        System.out.println("Search for 7: " + tree.search(7));
        System.out.println("Search for 11: " + tree.search(11));
        System.out.println("Search for 16: " + tree.search(16));
        System.out.println("Search for 18: " + tree.search(18));
        System.out.println("Search for 30: " + tree.search(30));

        // Delete some nodes
        tree.delete(7);
        System.out.print("Inorder traversal after delete 7: ");
        tree.inorderRec();
        System.out.println("");
        tree.delete(14);
        System.out.print("Inorder traversal after delete 7: ");
        tree.inorderRec();
        System.out.println("");
        tree.delete(30);
        System.out.print("Inorder traversal after delete 7: ");
        tree.inorderRec();
        System.out.println("");

        // Find the kth smallest node
        System.out.println("the smallest node: " + tree.kthSmallest(1).key);
        System.out.println("2nd smallest node: " + tree.kthSmallest(2).key);
        System.out.println("3rd smallest node: " + tree.kthSmallest(3).key);
        System.out.println("4th smallest node: " + tree.kthSmallest(4).key);
        
        // Create a new tree with input array
        int[] testArray = {5,7,9,1,3};
        tree.createTree(testArray);
        System.out.print("Create tree with input array: ");
        tree.preorderRec();
    }
}
