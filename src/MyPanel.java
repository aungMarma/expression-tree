import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.Deque;
import java.util.LinkedList;

class MyPanel extends JPanel {

    // need access from gui
    protected ExpressionTree expt = new ExpressionTree();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Node<Character> root = expt.getTree();
        if (root != null) {
            // set coordinates
            setCoordinates(root);
            drawTree(g, root);
        }
    }

    // drawing tree bsf
    public void drawTree(Graphics g, Node<Character> root) {
        Deque<Node<Character>> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            Node<Character> parent = deque.pollFirst();

            // grapics work
            g.setColor(Color.GREEN);
            g.drawOval(parent.getX(), parent.getY(), 30, 30);
            g.fillOval(parent.getX(), parent.getY(), 30, 30);
            String s = Character.toString(parent.getElement());
            g.setColor(Color.RED);
            g.drawString(s, parent.getX() + 10, parent.getY() + 10);

            if (parent.getLeft() != null) {
                Node<Character> child = parent.getLeft();
                // since oval is 30 and 30, getX + 15 => from midpoint
                g.drawLine(parent.getX() + 15, parent.getY() + 15, child.getX() + 15, child.getY() + 15);
                deque.add(child);
            }
            if (parent.getRight() != null) {
                Node<Character> child = parent.getRight();
                g.drawLine(parent.getX() + 15, parent.getY() + 15, child.getX() + 15, child.getY() + 15);
                deque.add(child);
            }
        }
    }

    // set x and y of all nodes
    // textbook algorithm modified
    private void setCoordinates(Node<Character> root) {
        // starting position is 100 from left and 100 from the top
        setCoordinates(root, 100, 100);
    }

    private static int setCoordinates(Node<Character> root, int y, int x) {
        if (root.getLeft() != null) {
            x = setCoordinates(root.getLeft(), y + 40, x);
        }
        root.setX(x);
        root.setY(y);
        x += 40;
        if (root.getRight() != null) {
            x = setCoordinates(root.getRight(), y + 40, x);
        }
        return x;
    }
}