// node of expression tree
class Node<E> {
    private E element;
    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;
    private String operators = "/*%+-^";

    private int x = 0;
    private int y = 0;

    public Node(E e) {
        element = e;
        left = right = parent = null;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public E getElement() {
        return element;
    };

    public Node<E> getParent() {
        return parent;
    }

    public Node<E> getLeft() {
        return left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setElement(E e) {
        element = e;
    }

    public void setParent(Node<E> parentNode) {
        parent = parentNode;
    }

    public void setLeft(Node<E> leftChild) {
        left = leftChild;
    }

    public void setRight(Node<E> rightChild) {
        right = rightChild;
    }

    public boolean canHaveChildren() {
        // a int node cant have any children
        if (!operators.contains(element.toString())) {
            return false;
        }
        // and alreay have two child
        if (right != null && left != null) {
            return false;
        }
        return true;
    }

    public Node<E> findValidParentToInsert(Node<E> node) throws Exception {
        if (node.getParent() == null) {
            throw new Exception("No where to insert!!");
        }
        if (node.getParent().canHaveChildren()) {
            return node.getParent();
        } else {
            return node.getParent().findValidParentToInsert(node.getParent());
        }
    }
}