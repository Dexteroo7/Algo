		private static final class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
		}

		public Node() {
		}
	}
  
  public static void main(String [] args) {

		Node root = new Node(10);
		Node left = new Node(5);
		Node right = new Node(1);
		root.left = left;
		root.right = right;
		postOrder(root);
	}

	int inOrder(Node root) {

		if (root == null)
			return -1;

		//not left subtree, print and move to right
		if (root.left == null) {
//			print(root);
			return inOrder(root.right);
		}

		//find the inorder predecessor, also detect and avoid cycle
		Node pred = root.left;
		while (pred.right != null && pred.right != root)
			pred = pred.right;

		//make a thread to the inorder successor, and move left from current
		if (pred.right != root) {
			pred.right = root;
			return inOrder(root.left);
		}
		//we have found a cycle
		else {
			pred.right = null;
			print(pred);
			//move to the right
			return inOrder(root.right);
		}
	}

	int preOrder(Node root) {

		if (root == null)
			return -1;

		//not left subtree, print and move to right
		if (root.left == null) {
			print(root);
			return preOrder(root.right);
		}

		Node pred = root.left;
		while (pred.right != null && pred.right != root)
			pred = pred.right;

		if (pred.right != root) {
			pred.right = root;
			print(root);
			return preOrder(root.left);
		}
		//we have found a cycle
		else {
			pred.right = null;
//			print(root);
			//move to the right
			return preOrder(root.right);
		}
	}

	static void postOrder(Node root) {

		Node dummy = new Node();
		dummy.left = root;
		postOrderActual(dummy);
	}

	static int postOrderActual(Node root) {

		if (root == null)
			return -1;

		if (root.left == null) {
			return postOrderActual(root.right);
		}

		Node succ = root.left;
		while (succ.right != null && succ.right != root)
			succ = succ.right;

		//make a thread to the inorder successor, and move left from current
		if (succ.right != root) {
			succ.right = root;
			return postOrderActual(root.left);
		}
		//we have found a cycle
		else {


			// predeccessor found second time
			// reverse the right references in chain from pred to p
			Node first = root;
			Node middle = root.left;
			while(middle!=root){
				Node last = middle.right;
				middle.right = first;
				first = middle;
				middle = last;
			}

			// visit the nodes from pred to p
			// again reverse the right references from pred to p
			first = root;
			middle = succ;
			while(middle!=root){
				print(middle);
				Node last = middle.right;
				middle.right = first;
				first = middle;
				middle = last;
			}

			// remove the pred to node reference to restore the tree structure
			succ.right = null;
			return postOrderActual(root.right);
		}
	}

	static void print(Node data) {
		System.out.print(data.data + " ");
	}
