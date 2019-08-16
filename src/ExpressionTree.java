import java.util.Deque;
import java.util.LinkedList;

// tree or expression tree or binary tree
class ExpressionTree {
    protected Node<Character> root;

    public ExpressionTree() {
        root = null;
    };

    public Node<Character> getTree() {
        // return deque;
        return root;
    }

    // return value string
    public String findValueOfNodeWithPoint(int x, int y) {
        Deque<Node<Character>> q = new LinkedList<>();
        q.add(root);
        Node<Character> curr = null;
        while (!q.isEmpty()) {
            curr = q.pollFirst();
            if ((curr.getX() < x && (curr.getX() + 35) > x) && (curr.getY() < y && (curr.getY() + 35) > y)) {
                break;
            }
            // System.out.print(curr.getElement() + " ");
            if (curr.getLeft() != null) {
                q.add(curr.getLeft());
            }
            if (curr.getRight() != null) {
                q.add(curr.getRight());
            }
        }
        if (curr != null) { // do post evaluation on curr
            return String.valueOf(eval(curr));
        } else {
            // not a node point
            return null;
        }
    }

    // below link is the source to eval algorithm
    // https://www.geeksforgeeks.org/evaluation-of-expression-tree/
    public int eval(Node<Character> root) {
        // empty tree
        if (root == null)
            return 0;
        // leaf node
        if (root.getLeft() == null && root.getRight() == null) {
            return Character.getNumericValue(root.getElement());
        }
        // Evaluate subtrees
        int leftValue = eval(root.getLeft());
        int rightValue = eval(root.getRight());

        // System.out.println(leftValue + " " + rightValue);
        // Check which operator to apply
        if (root.getElement() == '+')
            return leftValue + rightValue;
        if (root.getElement() == '-')
            return leftValue - rightValue;
        if (root.getElement() == '*')
            return leftValue * rightValue;
        return leftValue / rightValue;
    }

    private void postOrderPrint(Node<Character> root) {
        if (root.getLeft() != null) {
            postOrderPrint(root.getLeft());
        }
        if (root.getRight() != null) {
            postOrderPrint(root.getRight());
        }
        System.out.print(root.getElement() + " ");
    }

    public void breadthFirstPrint() {
        Deque<Node<Character>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node<Character> curr = q.pollFirst();
            System.out.print(curr.getElement() + " ");
            if (curr.getLeft() != null) {
                q.add(curr.getLeft());
            }
            if (curr.getRight() != null) {
                q.add(curr.getRight());
            }
        }
    }

    public void postOrderPrint() {
        postOrderPrint(root);
    }

    public void createTree(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) {
            return;
        }
        int rootAt = chars.length - 1;
        root = new Node<>(chars[rootAt]);
        Node<Character> current = root;
        for (int i = rootAt - 1; i >= 0; i--) {
            Node<Character> node = new Node<Character>(chars[i]);
            if (current.canHaveChildren()) {
                node.setParent(current);
                // try inserting at right first or at left
                if (current.getRight() == null) {
                    current.setRight(node);
                } else {
                    current.setLeft(node);
                }
                current = node;
            } else {
                // find a parent or grandparent that can have this node as a child
                // can return a null
                try {
                    current = current.findValidParentToInsert(current);
                    node.setParent(current);
                    // try inserting at right first or at left
                    if (current.getRight() == null) {
                        current.setRight(node);
                    } else {
                        current.setLeft(node);
                    }
                    current = node;
                } catch (Exception e) {
                    // System.out.println(e.getMessage());
                    root = null; // can't create a tree, set root to null
                    return; // get out
                }

            }
        }
    }
}