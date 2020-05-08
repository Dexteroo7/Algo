import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Binary_Expression_Tree {

    private static final class Node {

        public void leftCentreRight() {

            if (this.left != null)
                this.left.leftCentreRight();
            if (this.right != null)
                this.right.leftCentreRight();
            System.out.print(this.value + " ");
        }

        public Node attach(String toAttach, int index) {

            char current = toAttach.charAt(index);
            if (current == 'L') {
                if (this.left == null)
                    this.left = new Node();
                return this.left.attach(toAttach, index + 1);
            }
            if (current == 'R') {
                if (this.right == null)
                    this.right = new Node();
                return this.right.attach(toAttach, index + 1);
            } else
                this.value = current;
            return this;
        }

        public boolean isRoot() {
            return this.value == '\u0000';
        }

        static Node root() {
            return new Node();
        }

        char value;
        Node left, right;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("input.txt"))));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;

        StringBuilder builder = new StringBuilder();

        Node root = Node.root();
        while ((line = reader.readLine()) != null) {
            root.attach(line, 0);
        }
        root.leftCentreRight();
    }
}
